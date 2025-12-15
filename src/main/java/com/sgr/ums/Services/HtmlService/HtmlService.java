package com.sgr.ums.Services.HtmlService;

public interface HtmlService {

    String convertHtmlToPdf(String html, String baseUri, String fileName) throws Exception;

    String convertHtmlToExcel(String html, String fileName) throws Exception;
}
