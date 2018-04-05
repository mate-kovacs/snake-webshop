package com.codecool.snake.fieldobjects.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

public class SimpleEnemy extends AbstractFieldObject implements Animatable, Interactable {

    private static final int damage = 10;

    public SimpleEnemy(Pane pane) {
        super(pane);

        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

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
}
