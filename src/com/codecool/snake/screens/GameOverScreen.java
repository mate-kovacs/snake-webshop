package com.codecool.snake.screens;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GameOverScreen extends Pane {

    private String backgroundStyle = "-fx-background-color: rgba(100, 100, 100, 0.9);";

    public GameOverScreen() {
        this.setStyle(backgroundStyle);
        this.setMinSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
    }

    public void initGameOverScreen() {
        VBox gameOverVBox = new VBox();
        gameOverVBox.setAlignment(Pos.CENTER);

        // Game Over image
        /*
        ImageView gameOverImage = new ImageView();
        gameOverImage.setImage(Globals.gameOver);
        gameOverImage.setFitHeight(400);
        gameOverImage.setPreserveRatio(true);
        */

        // Game Over text
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0);
        ds.setOffsetX(3.0);
        ds.setColor(Color.BLACK);

        Text gameOverText = new Text("Game Over");
        gameOverText.setEffect(ds);
        gameOverText.setFont(Font.font ("Verdana", 100));
        gameOverText.setFill(Color.RED);

        // Collected voters text
        int snakeBodyPartsNr = Utils.getSnakeBodyPartsNr();

        Text collectedText = new Text("Collected voter(s): " + snakeBodyPartsNr);
        collectedText.setFont(Font.font ("Verdana", 20));
        collectedText.setFill(Color.YELLOW);

        String content = "\nPress 'R' for restart!";
        Text pressRText = new Text();

        Utils.animateBlinkingTextNode(pressRText, content);

        gameOverVBox.getChildren().addAll(gameOverText, collectedText, pressRText);
        gameOverVBox.setLayoutX((Globals.WINDOW_WIDTH/2)-(gameOverVBox.getBoundsInLocal().getWidth()/2));
        gameOverVBox.setLayoutY((Globals.WINDOW_HEIGHT/2)-(gameOverVBox.getBoundsInLocal().getHeight()/2));

        this.getChildren().add(gameOverVBox);
    }
}
