package com.poc.model;

import javax.json.Json;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Scanner;

public class HttpBinService {

    public static final String URL = "http://localhost:80";
    public static final String PATH = "/post";
    public static final String CONTENT_TYPE = "application/json";

    public void post(Map<String, String> data) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new java.net.URL(URL + PATH).openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", CONTENT_TYPE);
        connection.setDoOutput(true);
        var jsonGeneratorFactory = Json.createGeneratorFactory(null);
        var generator = jsonGeneratorFactory.createGenerator(connection.getOutputStream());
        generator.writeStartObject();
        for (var entry : data.entrySet()) {
            generator.write(entry.getKey(), entry.getValue());
        }
        generator.writeEnd();
        generator.close();
        System.out.println("Response code: " + connection.getResponseCode());
        System.out.println("Response body: " + new Scanner(connection.getInputStream()).useDelimiter("\\A").next());
        connection.disconnect();
    }
}
