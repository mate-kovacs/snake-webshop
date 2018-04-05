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

        //String backgroundStyle = "-fx-background-color: rgba(0, 0, 0, 1);";
        //Pane gameOverPane = Utils.createColoredLayer(backgroundStyle);


        /*Text startTitle = new Text("Da snake game\n");
        startTitle.setFont(Font.font ("Verdana", 100));
        startTitle.setFill(Color.RED);*/

        /*ImageView gameOverImage = new ImageView();
        gameOverImage.setImage(Globals.gameOver);
        gameOverImage.setFitHeight(200);
        gameOverImage.setPreserveRatio(true);*/


        /*Text startGameText = new Text();
        String content = "\nPress 'SPACE' to start the game!";
        Utils.animateBlinkingTextNode(startGameText, content);
        startGameText.setFont(Font.font ("Verdana", 20));
        startGameText.setFill(Color.RED);*/

        /*VBox startBox = new VBox();
        startBox.getChildren().addAll(startTitle, gameOverImage, startGameText);
        startBox.setAlignment(Pos.CENTER);
        startBox.setLayoutX((Globals.WINDOW_WIDTH/2)-(startBox.getBoundsInLocal().getWidth()/2));
        startBox.setLayoutY((Globals.WINDOW_HEIGHT/2)-(startBox.getBoundsInLocal().getHeight()));*/

        StartScreen startScreen = new StartScreen();
        startScreen.initStartScreen();
        //gameOverPane.getChildren().add(startBox);

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
