package com.codecool.snake.fieldobjects;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

// a simple enemy TODO make better ones.
public class DistractorEnemy extends AbstractFieldObject implements Animatable, Interactable {

    private static final int damage = 10;

    public DistractorEnemy(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
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

    @Override
    Image initImage() {
        return Globals.simpleEnemy;
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
