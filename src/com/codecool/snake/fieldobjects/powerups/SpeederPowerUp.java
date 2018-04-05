package com.codecool.snake.fieldobjects.powerups;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// a powerup that makes the snake go faster
public class SpeederPowerUp extends AbstractFieldObject implements Interactable, Animatable {

    public SpeederPowerUp(Pane pane) {
        super(pane);
        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

    }

    @Override
    public void apply(SnakeHead snakeHead) {
        // make snake faster
        final float fastSpeed = 4.004f;
        snakeHead.setSpeed(fastSpeed);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got speeded-up :)";
    }

    @Override
    float initSpeed() {
        return 4;
    }

    @Override
    Image initImage() {
        return Globals.powerupSpeeder;
    }
}