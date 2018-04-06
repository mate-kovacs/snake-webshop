package com.codecool.snake.fieldobjects.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SimpleEnemy extends AbstractFieldObject implements Animatable, Interactable {

    private static final int damage = 10;

    public SimpleEnemy(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.RANDOM_MOVING);
        setMovementStatus(getDefaultStatus());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }

    @Override
    float initSpeed() {
        return 2;
    }

    @Override
    Image initImage() {
        return Globals.simpleEnemy;
    }

    @Override
    public BoundingBox getHitbox() {
        return new BoundingBox(getX(), getY(), 70, 60);
    }
}
