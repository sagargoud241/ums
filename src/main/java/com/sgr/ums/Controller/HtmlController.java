package com.sgr.ums.Controller;

import com.sgr.ums.RequestModel.HtmlRequest;
import com.sgr.ums.Services.HtmlService.HtmlService;
import com.sgr.ums.Utilities.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/html")
public class HtmlController {
    private static final Logger log = LoggerFactory.getLogger(HtmlController.class);
    @Autowired
    private HtmlService htmlService;

    /**
     * -------------------------
     * Convert HTML → PDF
     * -------------------------
     */
    @PostMapping("/pdf")
    public String convertToPdf(@RequestBody HtmlRequest request) throws Exception {
        log.info("API Called: /api/html/pdf");
        log.info("PDF Request Received with fileName={}", JsonUtils.toJson(request));
        return htmlService.convertHtmlToPdf(
                request.getHtml(),
                null,
                request.getFileName()
        );
    }

    /**
     * -------------------------
     * Convert HTML → EXCEL
     * -------------------------
     */
    @PostMapping("/excel")
    public String convertToExcel(@RequestBody HtmlRequest request) throws Exception {
        log.info("API Called: /api/html/excel");
        log.info("Excel Request Received with fileName={}", JsonUtils.toJson(request));
        return htmlService.convertHtmlToExcel(
                request.getHtml(),
                request.getFileName()
        );
    }
}