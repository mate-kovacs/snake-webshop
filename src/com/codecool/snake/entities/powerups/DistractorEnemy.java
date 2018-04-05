package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class DistractorEnemy extends AbstractFieldObject implements Animatable, Interactable {

    private static final int damage = 10;

    public DistractorEnemy(Pane pane) {
        super(pane);

        setImage(Globals.simpleEnemy);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        setDefaultStatus(MovementStatus.RANDOM_MOVING);
        setMovementStatus(getDefaultStatus());
    }

    @Override
    public void apply(SnakeHead player) {
        for (GameEntity ge : Globals.getGameObjects()){
            if (ge instanceof SimpleEnemy){
                ((SimpleEnemy) ge).setMovementStatus(MovementStatus.AFAR_SNAKEHEAD);
            }
        }
        destroy();
    }

    @Override
    public String getMessage() {
        return "Voters are now moving away from you!";
    }

    @Override
    float initSpeed() {
        return 2;
    }
}