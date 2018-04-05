package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends AbstractFieldObject implements Interactable, Animatable {

    final int bonusHealth = 10;

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupBerry);
        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.AFAR_SNAKEHEAD);
        setMovementStatus(getDefaultStatus());

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(bonusHealth);
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
}
