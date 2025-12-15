package com.sgr.ums.Services.ChukulService;

import com.sgr.ums.ResponseModel.HighLowResponse;
import com.sgr.ums.ResponseModel.HistoryDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ChukulApiClient {

    @Value("${chukul_baseurl}")
    private String BASE_URL;

    // private static final String BASE_URL = "https://chukul.com/api/data";
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public HighLowResponse getHighLow(String symbol) throws Exception {
        String url = BASE_URL + "/v2/high-low-avg-count/?symbol=" + symbol;

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(resp.body(), HighLowResponse.class);
    }

    public HistoryDataResponse[] getHistoryData(String symbol) throws Exception {
        String url = BASE_URL + "/historydata/?symbol=" + symbol;

        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
        return mapper.readValue(resp.body(), HistoryDataResponse[].class);
    }

}
