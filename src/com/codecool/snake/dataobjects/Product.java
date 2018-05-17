package com.codecool.snake.dataobjects;

public class Product {

    private int productId;
    private int price;

    public Product(int productId, int price) {
        this.productId = productId;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }


    public int getPrice() {
        return price;
    }
}
