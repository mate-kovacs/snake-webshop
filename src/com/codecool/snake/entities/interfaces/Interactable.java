package com.codecool.snake.entities.interfaces;

import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;

// interface that all game objects that can be interacted with must implement.
public interface Interactable {

    void apply(SnakeHead snakeHead);

    String getMessage();

    BoundingBox getHitbox();

}
