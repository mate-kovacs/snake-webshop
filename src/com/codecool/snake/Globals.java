package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;

// class for holding all static stuff
public class Globals {

    public static final double WINDOW_WIDTH = 1920;
    public static final double WINDOW_HEIGHT = 1080;

    public static Image snakeHead = new Image("player.png");
    public static Image snakeBody = new Image("card.png");
    public static Image simpleEnemy = new Image("nav.png");
    public static Image protesterEnemy = new Image("protester.png");
    public static Image tormentorEnemy = new Image("tormentor.png");

    public static Image questItem = new Image("card.png");
    public static Image coins = new Image("codecoin.png");
    public static Image powerupSpeeder = new Image("drug.png");
    public static Image powerupCoin = new Image("codecoin.png");

    public static Image gameStart = new Image("start_image.png");
    public static Image gameOver = new Image("game_over.png");
    public static Image bgImage = new Image("background.jpg");

    //.. put here the other images you want to use

    public static boolean leftKeyDown;
    public static boolean rightKeyDown;
    public static List<GameEntity> gameObjects;
    public static List<GameEntity> newGameObjects; // Holds game objects crated in this frame.
    public static List<GameEntity> oldGameObjects; // Holds game objects that will be destroyed this frame.
    public static SnakeHead snakeHeadNode;
    public static Stack bodyParts = new Stack();
    public static GameLoop gameLoop;

    static {
        gameObjects = new LinkedList<>();
        newGameObjects = new LinkedList<>();
        oldGameObjects = new LinkedList<>();
    }

    public static void addGameObject(GameEntity toAdd) {
        newGameObjects.add(toAdd);
    }

    public static void removeGameObject(GameEntity toRemove) {
        oldGameObjects.add(toRemove);
    }

    public static List<GameEntity> getGameObjects() {
        return Collections.unmodifiableList(gameObjects);
    }
}
