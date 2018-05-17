package com.codecool.snake.fieldobjects;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class TormentorEnemy extends AbstractFieldObject implements Animatable, Interactable {
    public TormentorEnemy(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.TOWARD_SNAKEHEAD);
        setMovementStatus(getDefaultStatus());
    }

    @Override
    float initSpeed() {
        return 2;
    }

    @Override
    Image initImage() {
        return Globals.tormentorEnemy;
    }

    @Override
    int initNumberOfFrames() {
        return 4;
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        Globals.gameLoop.stop();
        Globals.snakeHeadNode.gameOver();
    }

    @Override
    public String getMessage() {
        return "Hit by the Tormentor";
    }

    @Override
    public BoundingBox getHitbox() {
        return new BoundingBox(getX(), getY(), 70, 90);
    }
}
