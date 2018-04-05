package com.codecool.snake;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
