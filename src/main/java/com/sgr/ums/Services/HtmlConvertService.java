package com.sgr.ums.Services;

import com.sgr.ums.Utilities.HtmlConverters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;

@Service
public class HtmlConvertService {
    private static final Logger log = LoggerFactory.getLogger(HtmlConvertService.class);

    public byte[] convertHtmlToPdf(String html) {
        log.info("Starting HTML to PDF conversion...");
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            HtmlConverters.htmlToPdfStream(html, null, out);
            return out.toByteArray();
        } catch (Exception e) {
            log.error("PDF conversion failed: {}", e.getMessage(), e);
            throw new RuntimeException("PDF conversion failed", e);
        }
    }

    public byte[] convertHtmlToExcel(String html) {
        log.info("Starting HTML to Excel conversion...");
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            HtmlConverters.htmlToXlsxStream(html, out);
            return out.toByteArray();
        } catch (Exception e) {
            log.error("Excel conversion failed: {}", e.getMessage(), e);
            throw new RuntimeException("Excel conversion failed", e);
        }
    }
}