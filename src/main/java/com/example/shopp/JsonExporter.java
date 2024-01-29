package com.example.shopp;

import com.example.shopp.CustomersData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonExporter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void exportToJson(List<CustomersData> dataList, String filePath) {
        try {
            // JSON ->file
            objectMapper.writeValue(new File(filePath), dataList);
            System.out.println("Export to JSON successful!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
