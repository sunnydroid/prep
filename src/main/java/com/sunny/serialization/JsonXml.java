package com.sunny.serialization;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunny.common.Logger;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by sunshah on 9/24/16.
 */
public class JsonXml {
//    public static final Logger

    public static void main(String[] args) {
        xmlToJson();
    }

    public static void xmlToJson() {
        /* load cluster.xml from resources */
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            StringBuilder stringBuilder = new StringBuilder();
            /* read file as a resource
             first load resource using the scanner and expected character encoding
             */
            URL fileUrl = JsonXml.class.getClassLoader().getResource("cluster.xml");
            /* create a file and file stream to read from */
            File xmlFile = new File(fileUrl.toURI());
            FileInputStream fileInputStream = new FileInputStream(xmlFile);
            /* use the scanner to read from file input stream */
            Scanner scanner = new Scanner(fileInputStream, StandardCharsets.UTF_8.name());
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }

            String xmlString = stringBuilder.toString();
            /* JSONObject has no default serializer, write it as an object first */
            JSONObject json = XML.toJSONObject(xmlString);
            Object jsonMap = objectMapper.readValue(json.toString(), Object.class);
            String jsonString = objectMapper.writeValueAsString(jsonMap);

            /* write json to file using a buffered writer */
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("clusters.json"), StandardCharsets.UTF_8);
            bufferedWriter.write(jsonString);

            Logger.log("File successfully written ");

        } catch (IOException e) {
            Logger.log("Error loading cluster");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}

