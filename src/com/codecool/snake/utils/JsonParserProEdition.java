package com.codecool.snake.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;

public class JsonParserProEdition {

    public static Map<String, Integer> parseTotalPriceAndNumberOfItems(String serverResponse) {
        JsonElement element = new com.google.gson.JsonParser().parse(serverResponse);
        JsonObject object = element.getAsJsonObject();
        int numberOfItems = object.get("numberOfItems").getAsInt();
        int totalPrice = object.get("priceSum").getAsInt();
        Map<String, Integer> map = new HashMap<>();
        map.put("itemsNum", numberOfItems);
        map.put("totalPrice", totalPrice);
        return map;
    }

    public static Map<Integer, Integer> parseProductList(String serverResponse) {
        JsonElement element = new JsonParser().parse(serverResponse);
        JsonObject object = element.getAsJsonObject();

        JsonArray productIdList = object.get("productIdList").getAsJsonArray();
        JsonObject productDataObject = object.get("productData").getAsJsonObject();

        Map<Integer, Integer> productData = new HashMap<>();

        for (int i = 0; i < productIdList.size(); i++) {
            Integer index = productIdList.get(i).getAsInt();
            productData.put(index, productDataObject.get(index.toString()).getAsInt() );
        }
        return productData;
    }

}
