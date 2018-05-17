package com.codecool.snake.fieldobjects;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class CodeCoinPowerUp extends AbstractFieldObject implements Interactable, Animatable {
    private static final int BONUS_COIN = 1;

    public CodeCoinPowerUp(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());
    }

    @Override
    float initSpeed() {
        return 4;
    }

    @Override
    Image initImage() {
        return Globals.powerupCoin;
    }

    @Override
    int initNumberOfFrames() {
        return 1;
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(BONUS_COIN);
        destroy();
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public BoundingBox getHitbox() {
        return new BoundingBox(getX(), getY(), 60, 60);
    }
}
