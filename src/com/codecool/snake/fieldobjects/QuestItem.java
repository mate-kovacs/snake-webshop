package com.codecool.snake.fieldobjects;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.lordofstrings.netconnector.GameRequests;
import javafx.geometry.BoundingBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestItem extends AbstractFieldObject implements Interactable, Animatable {

    private static Random random = new Random();
    private GameRequests gameRequests = GameRequests.getInstance();

    private int productId=1;
    private int price=1;

    public QuestItem(Pane pane, Double x, Double y) {
        super(pane, x, y);

        productId = getRandomProductId();
        price = Globals.productList.get(productId);
        pane.getChildren().add(this);
        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());

        setX(random.nextDouble() * Globals.WINDOW_WIDTH);
        setY(random.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    private Integer getRandomProductId() {
        List<Integer> keys = new ArrayList<Integer>(Globals.productList.keySet());
        return keys.get( random.nextInt(keys.size()) );
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        if (snakeHead.getMoney() >= this.price) {
            gameRequests.addItem(productId);
            snakeHead.changeMoney(-price);
            snakeHead.addPart(1);
            destroy();
        }
    }

    @Override
    public String getMessage() {
        return "Quest item touching";
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
