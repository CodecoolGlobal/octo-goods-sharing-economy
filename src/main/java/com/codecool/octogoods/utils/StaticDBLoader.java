package com.codecool.octogoods.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StaticDBLoader {

    private static final String statusPostUrl = "http://localhost:8080/api/status";
    private static final String userPostUrl = "http://localhost:8080/api/user";
    private static final String itemPostUrl = "http://localhost:8080/api/item";
    private static final String categoryPostUrl = "http://localhost:8080/api/category";

    private void loadData(String jsonString, String postUrl) {
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

        loadData(add, statusPostUrl);
        loadData(reserve, statusPostUrl);
        loadData(collect, statusPostUrl);
        loadData(returned, statusPostUrl);
        loadData(maintain, statusPostUrl);
        loadData(remove, statusPostUrl);

        System.out.println("..Loaded default statuses");

    }

    public void loadInitialUsers() {
        String marek = "{\n" +
                "\t\"name\": \"Marek\",\n" +
                "\t\"isActive\": true\n" +
                "}";

        String tatiana = "{\n" +
                "\t\"name\": \"Tatiana\",\n" +
                "\t\"isActive\": true\n" +
                "}";

        String liz = "{\n" +
                "\t\"name\": \"Liz\",\n" +
                "\t\"isActive\": true\n" +
                "}";

        loadData(marek, userPostUrl);
        loadData(tatiana, userPostUrl);
        loadData(liz, userPostUrl);

        System.out.println("..Loaded default users");

    }

    public void loadInitialCategories() {
        String garden = "{\n" +
                "\t\"name\": \"Garden\",\n" +
                "\t\"active\": true\n" +
                "}";

        String kitchen = "{\n" +
                "\t\"name\": \"Kitchen\",\n" +
                "\t\"active\": true\n" +
                "}";

        String vehicle = "{\n" +
                "\t\"name\": \"Vehicle\",\n" +
                "\t\"active\": true\n" +
                "}";

        loadData(garden, categoryPostUrl);
        loadData(kitchen, categoryPostUrl);
        loadData(vehicle, categoryPostUrl);

        System.out.println("..Loaded default categories");

    }

    public void loadInitialItems() {
        String lawnMower = "{\n" +
                "\t\"name\": \"lawn mower\",\n" +
                "\t\"userId\": 1,\n" +
                "\t\"categoryName\": \"Garden\",\n" +
                "\t\"description\": \"Brand new.\"\n" +
                "}";

        String juicer = "{\n" +
                "\t\"name\": \"juicer\",\n" +
                "\t\"userId\": 2,\n" +
                "\t\"categoryName\": \"Kitchen\",\n" +
                "\t\"description\": \"Works great!\"\n" +
                "}";

        String bike = "{\n" +
                "\t\"name\": \"bike\",\n" +
                "\t\"userId\": 3,\n" +
                "\t\"categoryName\": \"Vehicle\",\n" +
                "\t\"description\": \"One wheel only...\"\n" +
                "}";

        loadData(lawnMower, itemPostUrl);
        loadData(juicer, itemPostUrl);
        loadData(bike, itemPostUrl);

        System.out.println("..Loaded default items");

    }
}
