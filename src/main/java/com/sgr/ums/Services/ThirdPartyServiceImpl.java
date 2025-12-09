package com.sgr.ums.Services;

import com.sgr.ums.RequestModel.ThirdPartyRequestModel.*;
import com.sgr.ums.ResponseModel.ThirdPartyApiResponse;
import com.sgr.ums.Utilities.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Service
public class ThirdPartyServiceImpl implements ThirdPartyService {

    @Value("${third_party_baseurl}")
    private String BASE_URL;
    private static final Logger log = LoggerFactory.getLogger(ThirdPartyServiceImpl.class);
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public ThirdPartyApiResponse listAllObject() {
        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to consume api");

        try {

            String url = BASE_URL;
            log.info("thirdParty url is {}", BASE_URL);
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();      // <-- HTTP status
            String responseBody = resp.body();       // <-- Response body

            log.info("Response status: {}, body: {}", statusCode, responseBody);
            if (statusCode == 200) {
                response.setCode("success");
                response.setMessage("fetch data successfully");
                // Parse JSON into Java object (Map or List)
                Object jsonData = mapper.readValue(responseBody, Object.class);
                response.setDto(jsonData);      // assuming your response object has `data`
            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception in listAllObject: {}", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ThirdPartyApiResponse getObjectById(Long id) {
        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to consume api");

        try {
            String url = BASE_URL + "/" + id;
            log.info("Calling getObjectById API: {}", BASE_URL + "/" + id);
            //   String url = BASE_URL + "/v2/high-low-avg-count/?symbol=" + symbol;

            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();      // <-- HTTP status
            String responseBody = resp.body();       // <-- Response body
            log.info("Exception While GetObjectById:{}", JsonUtils.toJson(responseBody));

            if (statusCode == 200) {
                response.setCode("success");
                response.setMessage("fetch data successfully");
                // Parse JSON into Java object (Map or List)
                Object jsonData = mapper.readValue(responseBody, Object.class);
                response.setDto(jsonData);      // assuming your response object has `data`
            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception in getObjectById:{}", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ThirdPartyApiResponse addNewObject(AddThirdPartyRequest request) {

        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to consume api");

        try {
            String url = BASE_URL; // External API URL
            log.info("Calling GetObjectById API: {}", BASE_URL);

            // Convert request object to JSON
            String requestBody = mapper.writeValueAsString(request);

            // Build POST request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody)) // <-- correct POST syntax
                    .build();

            // Send request
            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();
            String responseBody = resp.body();
            log.info("Exception While AddNewObject:{}", JsonUtils.toJson(responseBody));

            if (statusCode == 200) {
                response.setCode("success");
                response.setMessage("Add successfully");

                // Parse JSON into Java object (Map/List)
                Object jsonData = mapper.readValue(responseBody, Object.class);
                response.setDto(jsonData);
            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception in addNewObject: {}", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ThirdPartyApiResponse getObjectByIds(List<Integer> ids) {
        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to consume api");

        try {
            // String BASE_URL = "https://api.restful-api.dev/objects";

            // Build URL: ?id=3&id=5&id=10
            String url = BASE_URL + "?" +

                    ids.stream()
                            .map(id -> "id=" + id)
                            .collect(Collectors.joining("&"));
            log.info("Calling getObjectByIds API: {}", BASE_URL);
            // Build GET request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Send request
            HttpResponse<String> resp =
                    client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();
            String responseBody = resp.body();
            log.info("Exception While GetObjectByIds:{}", JsonUtils.toJson(responseBody));

            if (statusCode == 200) {
                response.setCode("success");
                response.setMessage("Retrieved successfully");

                // Parse JSON into Map/List
                Object jsonData = mapper.readValue(responseBody, Object.class);
                response.setDto(jsonData);

            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception inGetObjectById: {}", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ThirdPartyApiResponse updateObjectById(Long id, UpdateThirdPartyRequest request) {
        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to update api");
        try {
            String url = BASE_URL + "/" + id;
            log.info("Calling updateObjectById API: {}", BASE_URL + "/" + id);

            String requestBody = mapper.writeValueAsString(request);

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))   // <-- PUT
                    .build();

            HttpResponse<String> resp =
                    client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();
            String responseBody = resp.body();
            log.info("Exception while UpdateObjectById:{}", JsonUtils.toJson(responseBody));

            if (statusCode == 200) {
                response.setCode("success");
                response.setMessage("Object updated successfully");

                Object jsonData = mapper.readValue(responseBody, Object.class);
                response.setDto(jsonData);
            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception in UpdateObjectBy id: {}", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ThirdPartyApiResponse patchObjectById(Long id, PatchThirdPartyRequest request) {
        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to update api");

        try {
            String url = BASE_URL + "/" + id;
            log.info("Calling patchObjectById API: {}", BASE_URL + "/" + id);
            // Convert request object to JSON
            String requestBody = mapper.writeValueAsString(request);

            // Build PATCH request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Content-Type", "application/json")
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody)) // PATCH
                    .build();

            // Send request
            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();
            String responseBody = resp.body();
            log.info("Exception while PatchObjectBy id:{}", JsonUtils.toJson(responseBody));

            if (statusCode == 200) {
                response.setCode("success");
                response.setMessage("Object updated successfully");

                // Parse JSON response
                Object jsonData = mapper.readValue(responseBody, Object.class);
                response.setDto(jsonData);
            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception in patchObjectById: {}", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @Override
    public ThirdPartyApiResponse deleteObjectById(Long id) {
        ThirdPartyApiResponse response = new ThirdPartyApiResponse();
        response.setCode("fail");
        response.setMessage("fail to delete api");

        try {
            String url = BASE_URL + "/" + id;
            log.info("calling deleteObjectById:{} ", BASE_URL + "/" + id);
            // Build DELETE request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE()   // DELETE request has NoBody
                    .build();

            // Send request
            HttpResponse<String> resp = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            int statusCode = resp.statusCode();
            String responseBody = resp.body();
            log.info("Exception while DeleteObjectBy id:{}", JsonUtils.toJson(responseBody));

            if (statusCode == 200 || statusCode == 204) {
                response.setCode("success");
                response.setMessage("Object deleted successfully");
                response.setDto(responseBody);
            } else {
                response.setCode("http_error");
                response.setMessage("HTTP error: " + statusCode);
                response.setDto(responseBody);
            }

        } catch (Exception e) {
            log.error("Exception while deleteObject by id;{} ", e.getMessage());
            response.setCode("exception");
            response.setMessage(e.getMessage());
        }

        return response;

    }

}
