package com.codecool.snake;

import com.codecool.snake.entities.snakes.SnakeBody;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Utils {

    /*
    Converts a direction in degrees (0...360) to x and y coordinates.
    The length of this vector is the second parameter
    */
    public static Point2D directionToVector(double directionInDegrees, double length) {
        double directionInRadians = directionInDegrees / 180 * Math.PI;
        Point2D heading = new Point2D(length * Math.sin(directionInRadians), - length * Math.cos(directionInRadians));
        return heading;
    }

    /**
     * Adds blinking animation to Text the node
     * @param text
     * @param content
     */
    public static void animateBlinkingTextNode(Text text, String content) {
        text.setText(content);
        text.setFont(Font.font ("Verdana", 20));
        text.setFill(Color.WHITE);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.1), text);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
    }

    /**
     * Counts snake's bodyparts
     * @return
     */
    public static int getSnakeBodyPartsNr() {
        int snakeBodyPartsNr = 0;
        for (Node node: Globals.gameObjects) {
            if (node instanceof SnakeBody) {
                snakeBodyPartsNr += 1;
            }
        }
        return snakeBodyPartsNr;
    }
}
