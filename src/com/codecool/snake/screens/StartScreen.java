package com.codecool.snake.screens;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class StartScreen extends Pane {
    private String backgroundStyle = "-fx-background-color: rgba(0, 0, 0, 1);";

    public StartScreen() {
        this.setStyle(backgroundStyle);
        this.setMinSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
    }

    public void initStartScreen() {
        Text startTitle = new Text("Election Run\n");
        startTitle.setFont(Font.font ("System", 100));
        startTitle.setFill(Color.RED);

        ImageView gameStartImage = new ImageView();
        gameStartImage.setImage(Globals.gameStart);
        gameStartImage.setFitHeight(250);
        gameStartImage.setPreserveRatio(true);

        Text startGameText = new Text();
        String content = "\nPress 'SPACE' to start the game!";
        Utils.animateBlinkingTextNode(startGameText, content);
        startGameText.setFont(Font.font ("Verdana", 20));
        startGameText.setFill(Color.RED);

        VBox startBox = new VBox();
        startBox.getChildren().addAll(startTitle, gameStartImage, startGameText);
        startBox.setAlignment(Pos.CENTER);
        startBox.setLayoutX((Globals.WINDOW_WIDTH/2)-(startBox.getBoundsInLocal().getWidth()/2));
        startBox.setLayoutY((Globals.WINDOW_HEIGHT/2)-(startBox.getBoundsInLocal().getHeight()));

        this.getChildren().add(startBox);
    }
}
