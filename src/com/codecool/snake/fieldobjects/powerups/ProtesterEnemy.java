package com.codecool.snake.fieldobjects.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class ProtesterEnemy extends AbstractFieldObject implements Animatable, Interactable {

    private static final int damage = 10;

    public ProtesterEnemy(Pane pane) {
        super(pane);

        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        setDefaultStatus(MovementStatus.STANDSTILL);
        setMovementStatus(getDefaultStatus());
    }

    @Override
    public void apply(SnakeHead player) {
        if (Globals.bodyParts.empty()){
            System.out.println("Game Over");
            Globals.gameLoop.stop();
            // Globals.snakeHeadNode.gameOver();
            return;
        }

        Globals.bodyParts.pop();
        Globals.snakeHeadNode.getTail().destroy();
        if (!Globals.bodyParts.empty()) {
            Globals.snakeHeadNode.setTail((SnakeBody)Globals.bodyParts.peek());
        } else {
            Globals.snakeHeadNode.setTail(Globals.snakeHeadNode);
        }

        destroy();

    }

    @Override
    public String getMessage() {
        return "Voter stolen!";
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
