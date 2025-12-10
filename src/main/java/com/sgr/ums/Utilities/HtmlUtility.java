package com.sgr.ums.Utilities;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.UUID;

@Component
public class HtmlUtility {
    private static final Logger log = LoggerFactory.getLogger(HtmlUtility.class);

    @Value("${pdf.save.path}")
    private String pdfSavePath;

    @Value("${excel.save.path}")
    private String excelSavePath;

    /**
     * ---------------------------------------------------------------
     * Convert HTML → PDF and SAVE IN FOLDER
     * ---------------------------------------------------------------
     */
    public String saveHtmlAsPdf(String html, String baseUri, String fileName) throws IOException {
        log.info("Starting PDF generation for fileName: {}", fileName);
        if (!fileName.endsWith(".pdf")) {
            fileName = UUID.randomUUID().toString() + fileName;
            fileName = fileName + ".pdf";
        }
        log.debug("Ensured PDF directory exists: {}", pdfSavePath);
        File folder = new File(pdfSavePath);
        log.info("Saving PDF to: {}", JsonUtils.toJson(folder));
        folder.mkdirs();

        String fullPath = pdfSavePath + File.separator + fileName;

        try (OutputStream out = new FileOutputStream(fullPath)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(html, baseUri == null ? "" : baseUri);
            builder.toStream(out);
            log.debug("Running PDF renderer...");
            builder.run();
            log.info("PDF generation completed: {}", JsonUtils.toJson(fullPath));
        } catch (Exception e) {
            log.error("Failed to create PDF: {}", e.getMessage(), e);
            throw new IOException("Failed to create PDF", e);
        }


        return fullPath;
    }

    /**
     * ---------------------------------------------------------------
     * Convert HTML → XLSX and SAVE IN FOLDER
     * ---------------------------------------------------------------
     */
    public String saveHtmlAsExcel(String html, String fileName) throws IOException {
        log.info("Starting Excel generation for fileName: {}", fileName);
        if (!fileName.endsWith(".xlsx")) {
            fileName = fileName + ".xlsx";
            fileName = UUID.randomUUID().toString() + fileName;
        }

        File folder = new File(excelSavePath);
        log.debug("Ensured Excel directory exists: {}", JsonUtils.toJson(folder));
        folder.mkdirs();

        String fullPath = excelSavePath + File.separator + fileName;
        log.info("Saving Excel to: {}", fullPath);


        try (Workbook workbook = new XSSFWorkbook();
             OutputStream out = new FileOutputStream(fullPath)) {
            log.debug("Parsing HTML...");
            Document doc = Jsoup.parse(html);
            Elements tables = doc.select("table");

            if (tables.isEmpty()) {
                log.warn("No tables found in HTML → Writing plain text.");
                Sheet sheet = workbook.createSheet("Sheet1");
                sheet.createRow(0).createCell(0).setCellValue(doc.body().text());
            } else {
                for (int i = 0; i < tables.size(); i++) {
                    log.info("Found {} HTML table(s).", tables.size());
                    Sheet sheet = workbook.createSheet("Table " + (i + 1));
                    Element table = tables.get(i);

                    int rowNum = 0;
                    for (Element tr : table.select("tr")) {
                        Row row = sheet.createRow(rowNum++);
                        int cellNum = 0;

                        for (Element td : tr.select("th, td")) {
                            row.createCell(cellNum++).setCellValue(td.text());
                        }
                    }
                }
            }
            log.debug("Writing XLSX data to disk...");
            workbook.write(out);
            log.info("Excel generation completed: {}", fullPath);
        }

        return fullPath;
    }
}