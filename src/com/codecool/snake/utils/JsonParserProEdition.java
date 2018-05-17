package com.codecool.snake.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class JsonParserProEdition {

    public static Map<String, Integer> parseJsonToMap(String serverResponse) {
        JsonElement element = new com.google.gson.JsonParser().parse(serverResponse);
        JsonObject object = element.getAsJsonObject();
        int numberOfItems = object.get("numberOfItems").getAsInt();
        int totalPrice = object.get("priceSum").getAsInt();
        Map<String, Integer> map = new HashMap<>();
        map.put("itemsNum", numberOfItems);
        map.put("totalPrice", totalPrice);
        return map;
    }

}
