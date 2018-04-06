package com.codecool.snake.fieldobjects.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends AbstractFieldObject implements Interactable, Animatable {

    public SimplePowerup(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.RANDOM_MOVING);
        setMovementStatus(getDefaultStatus());

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.addPart(1);
        destroy();
    }

    @Override
    public String getMessage() {
        return "Got simple-power-up :)";
    }

    @Override
    float initSpeed() {
        return 1;
    }

    @Override
    Image initImage() {
        return Globals.simplePowerUp;
    }

    @Override
    public BoundingBox getHitbox() {
        return new BoundingBox(getX(), getY(), 70, 60);
    }

    @Override
    int initNumberOfFrames() {
        return 4;
    }
}
