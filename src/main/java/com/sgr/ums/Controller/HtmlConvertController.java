package com.sgr.ums.Controller;
import com.sgr.ums.Services.HtmlService.HtmlConvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/convert")
public class HtmlConvertController {

    private final HtmlConvertService service;
    private static final Logger log = LoggerFactory.getLogger(HtmlConvertController.class);


    public HtmlConvertController(HtmlConvertService service) {
        this.service = service;
    }

    @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> convertToPdf(@RequestBody String html) {
        log.info("Received request: Convert HTML to PDF. HTML size: {} chars", (html != null ? html.length() : 0));
        byte[] pdf = service.convertHtmlToPdf(html);
        log.info("PDF conversion successful. Output size: {} bytes", pdf.length);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.pdf").body(pdf);
    }

    @PostMapping(value = "/excel", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public ResponseEntity<byte[]> convertToExcel(@RequestBody String html) {
        log.info("Received request: Convert HTML to Excel. HTML size: {} chars", (html != null ? html.length() : 0));
        byte[] xlsx = service.convertHtmlToExcel(html);
        log.info("Excel conversion successful. Output size: {} bytes", xlsx.length);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.xlsx").body(xlsx);
    }
}