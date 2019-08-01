package com.codecool.octogoods.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StaticDBLoader {

    private static final String postUrl = "http://localhost:8080/api/status";

    private void loadActionStatus(String jsonString) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(jsonString, headers);

        restTemplate.postForObject(postUrl, request, String.class);
    }

    public void loadInitialActionStatuses() {
        String add = "{\n" +
                "\t\"name\": \"ADD\",\n" +
                "\t\"available\": true,\n" +
                "\t\"active\": true\n" +
                "}";

        String reserve = "{\n" +
                "\t\"name\": \"RESERVE\",\n" +
                "\t\"available\": false,\n" +
                "\t\"active\": true\n" +
                "}";

        String collect = "{\n" +
                "\t\"name\": \"COLLECT\",\n" +
                "\t\"available\": false,\n" +
                "\t\"active\": true\n" +
                "}";

        String returned = "{\n" +
                "\t\"name\": \"RETURN\",\n" +
                "\t\"available\": true,\n" +
                "\t\"active\": true\n" +
                "}";

        String maintain = "{\n" +
                "\t\"name\": \"MAINTAIN\",\n" +
                "\t\"available\": false,\n" +
                "\t\"active\": true\n" +
                "}";

        String remove = "{\n" +
                "\t\"name\": \"REMOVE\",\n" +
                "\t\"available\": false,\n" +
                "\t\"active\": false\n" +
                "}";

        loadActionStatus(add);
        loadActionStatus(reserve);
        loadActionStatus(collect);
        loadActionStatus(returned);
        loadActionStatus(maintain);
        loadActionStatus(remove);
    }
}
