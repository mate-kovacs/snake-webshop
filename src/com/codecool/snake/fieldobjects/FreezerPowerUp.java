package com.codecool.snake.fieldobjects;

import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

// a powerup that makes the enemies stop moving
public class FreezerPowerUp extends AbstractFieldObject implements Interactable, Animatable {

    public FreezerPowerUp(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());
    }

    @Override
    public void apply(SnakeHead snakeHead) {

        // make enemies stop moving
        for (GameEntity ge : Globals.getGameObjects()){
            if (ge instanceof SimpleEnemy){ // change to AbstractEnemy
                 ((SimpleEnemy) ge).setMovementStatus(MovementStatus.STANDSTILL);
            }
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Enemies frozen :)";
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
        return new BoundingBox(getX(), getY(), 70, 60);
    }

    @Override
    int initNumberOfFrames() {
        return 0;
    }
}