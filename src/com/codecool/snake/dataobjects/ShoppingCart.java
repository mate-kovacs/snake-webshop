package com.codecool.snake.dataobjects;

public class ShoppingCart {
    private int sumPrice;
    private int numOfItems;

    public ShoppingCart(int sumPrice, int numOfItems) {
        this.sumPrice = sumPrice;
        this.numOfItems = numOfItems;
    }

    public int getSumPrice() {
        return sumPrice;
    }

    public int getNumOfItems() {
        return numOfItems;
    }
}
