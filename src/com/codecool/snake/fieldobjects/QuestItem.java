package com.codecool.snake.fieldobjects;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class QuestItem extends AbstractFieldObject implements Interactable, Animatable {

    private static List<Integer> productList = new ArrayList();

    private int productId;
    private int price = 10;

    public QuestItem(Pane pane, Double x, Double y) {
        super(pane, x, y);

        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getMoney() >= this.price) {
            snakeHead.changeMoney(-price);
            snakeHead.addPart(1);
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Got new quest item :)";
    }

    @Override
    float initSpeed() {
        return 1;
    }

    @Override
    Image initImage() {
        return Globals.questItem;
    }

    @Override
    public BoundingBox getHitbox() {
        return new BoundingBox(getX(), getY(), 50, 62);
    }

    @Override
    int initNumberOfFrames() {
        return 1;
    }
}
