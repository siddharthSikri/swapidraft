package com.spotdraft.swapi.utilities;

import com.spotdraft.swapi.constants.Constants;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Web utilities class.
 */
@Service
public class WebUtils {
    /**
     * GET HTTP Call.
     *
     * @param uri GET endpoint.
     * @return Response string.
     */
    public String get(String uri) {
        try {
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .header(Constants.ACCEPT, Constants.APPLICATION_JSON)
                    .uri(URI.create(uri))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            return (Constants.EXCEPTION_OCCURRED + e.getMessage());
        }
    }
}
