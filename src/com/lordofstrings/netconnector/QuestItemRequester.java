package com.lordofstrings.netconnector;

import com.codecool.snake.Globals;

public interface QuestItemRequester {
    HttpRequester httpRequester = new HttpRequester(Globals.SHOP_ADDRESS);

    default void addItem(int productId) {
        httpRequester.sendPostRequest("id=" + productId);
    }
}
