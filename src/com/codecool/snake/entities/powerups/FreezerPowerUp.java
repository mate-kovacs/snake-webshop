package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a powerup that makes the enemies stop moving
public class FreezerPowerUp extends AbstractPowerUp implements Interactable, Animatable {


    public FreezerPowerUp(Pane pane) {
        super(pane);
        setImage(Globals.powerupSpeeder);
        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {

        // make enemies stop moving
        for (GameEntity ge : Globals.getGameObjects()){
            if (ge instanceof SimpleEnemy){ // change to AbstractEnemy
                // ge.setMovementStatus(MovementStatus.STANDSTILL);
                // todo
                // change move to standstill
            }
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Enemies frozen :)";
    }
}