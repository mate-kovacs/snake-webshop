package com.codecool.snake.fieldobjects;

import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

// a powerup that makes the snake go faster
public class SpeederPowerUp extends AbstractFieldObject implements Interactable, Animatable {

    public SpeederPowerUp(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());
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

    @Override
    public BoundingBox getHitbox() {
        return new BoundingBox(getX(), getY(), 50, 60);
    }

    @Override
    int initNumberOfFrames() {
        return 1;
    }
}