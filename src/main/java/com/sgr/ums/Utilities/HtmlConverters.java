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
import java.io.IOException;
import java.io.OutputStream;
import java.io.*;
import java.io.*;

public final class HtmlConverters {
    private static final Logger log = LoggerFactory.getLogger(HtmlConverters.class);

    private HtmlConverters() {

    }

    /**
     * HTML → PDF (Stream output)
     */
    public static void htmlToPdfStream(String html, String baseUri, OutputStream out) throws IOException {
        log.info("Starting HTML → PDF stream conversion");
        try {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            log.debug("Initializing PdfRendererBuilder...");
            builder.withHtmlContent(html, baseUri == null ? "" : baseUri);
            builder.toStream(out);
            builder.run();
            log.info("PDF stream generation completed successfully");
        } catch (Exception e) {
            log.error("Failed to create PDF: {}", e.getMessage(), e);
            throw new IOException("Failed to create PDF", e);
        }
    }

    /**
     * HTML → XLSX (Stream output)
     */
    public static void htmlToXlsxStream(String html, OutputStream out) throws IOException {
        log.info("Starting HTML → XLSX stream conversion");
        Document doc = Jsoup.parse(html);
        Elements tables = doc.select("table");

        try (Workbook wb = new XSSFWorkbook()) {
            if (tables.isEmpty()) {
                Sheet sheet = wb.createSheet("Sheet1");
                sheet.createRow(0).createCell(0).setCellValue(doc.body().text());
            } else {
                log.info("Processing {} HTML table(s)", tables.size());
                for (int i = 0; i < tables.size(); i++) {
                    Sheet sheet = wb.createSheet("Table " + (i + 1));
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
            log.debug("Writing XLSX to OutputStream...");
            wb.write(out);
            log.info("XLSX stream generation completed successfully");
        }
        catch (Exception e) {
            log.error("Failed to create XLSX: {}", e.getMessage(), e);
            throw new IOException("Failed to create XLSX", e);
        }

    }
}

