package com.lordofstrings.netconnector;

import com.codecool.snake.Globals;
import com.codecool.snake.utils.JsonParserProEdition;

public class GameRequests {

    static GameRequests gameRequestsInstance = null;

    private HttpRequester httpRequester = new HttpRequester();

    private GameRequests() {

    }

    public static GameRequests getInstance(){
        if (gameRequestsInstance == null) {
            gameRequestsInstance = new GameRequests();
        }
        return gameRequestsInstance;
    }

    public void getProducts() {
        String serverResponse = httpRequester.sendPostRequest("http://localhost:8080/snake-products", "");
        Globals.productList = JsonParserProEdition.parseProductList(serverResponse);
    }

    public void getShoppingCartId() {
        Globals.shoppingCartId = httpRequester.sendPostRequest("http://localhost:8080/snake-cart", "");
    }

    public void addItem(int productId) {
        httpRequester.sendPostRequest("http://localhost:8080/", "id=" + productId + "&shoppingcart_id=" + Globals.shoppingCartId);
    }
}
