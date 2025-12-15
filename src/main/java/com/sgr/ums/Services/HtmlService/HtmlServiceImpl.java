package com.sgr.ums.Services.HtmlService;

import com.sgr.ums.Utilities.HtmlUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HtmlServiceImpl implements HtmlService {
    @Autowired
    private HtmlUtility htmlUtility;

    @Override
    public String convertHtmlToPdf(String html, String baseUri, String fileName) throws Exception {
        return htmlUtility.saveHtmlAsPdf(html, baseUri, fileName);
    }

    @Override
    public String convertHtmlToExcel(String html, String fileName) throws Exception {
        return htmlUtility.saveHtmlAsExcel(html, fileName);
    }
}

