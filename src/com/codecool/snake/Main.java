package com.codecool.snake;

import com.lordofstrings.netconnector.GameRequests;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import com.codecool.snake.screens.StartScreen;

public class Main extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Snake Game");

        Game game = new Game();
        Scene gameScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        StartScreen startScreen = new StartScreen();
        startScreen.initStartScreen();
        GameRequests gameRequest = GameRequests.getInstance();
        gameRequest.getProducts();
        Scene startScene = new Scene(startScreen, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        startScene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                primaryStage.setScene(gameScene);
                game.start();
            }
        });

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

}
