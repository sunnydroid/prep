package com.sunny.java;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sunny.common.Logger;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



/**
 * Created by sunshah on 8/16/16.
 */
public class JsonSerialization {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        map.put("key", "value");
        map2.put("key2", "value2");

        List<Map<String, String>> listMap = new ArrayList<Map<String, String>>();

        listMap.add(map);
        listMap.add(map2);


        ObjectNode objectNode1 = objectMapper.createObjectNode();
        ObjectNode objectNode2 = objectMapper.createObjectNode();
        objectNode1.put("keyA", "valueA");
        objectNode2.put("keyB", "valueB");

        Logger.log("ObjectNode1 => " + objectNode1.toString());
        Logger.log("ObjectNode2 => " + objectNode2.toString());

        try {

            /* in order to serialize the above list map into an object, it must start and end with '{' and '}'
            * use the pretty printer to serialize list as an object so it can be read in
            */
            String jsonSerialized = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(new Pojo(listMap));
            Logger.log(jsonSerialized);

            Pojo pojo = objectMapper.readValue(jsonSerialized, Pojo.class);
            Logger.log("deserialized pojo");
            Logger.log(pojo.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class Pojo {

        @JsonProperty("pairs")
        List<Map<String, String>> listMap;

        /* default constructor */
        public Pojo() {}

        public Pojo(List<Map<String, String>> list) {
            this.listMap = list;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map<String, String> map : listMap) {
                for (Entry<String, String> entry : map.entrySet()) {
                    stringBuilder.append(entry.getKey());
                    stringBuilder.append("=>");
                    stringBuilder.append(entry.getValue());
                    stringBuilder.append(System.getProperty("line.separator"));
                }
            }

            return stringBuilder.toString();
        }

    }
}
