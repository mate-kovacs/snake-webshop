package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Health;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.SpriteCalculator;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class SnakeHead extends GameEntity implements Animatable {

    private float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;

    private SpriteCalculator spriteCalculator;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        this.spriteCalculator = new SpriteCalculator(getImage(), 2, 30);
        pane.getChildren().add(this);
        Globals.snakeHeadNode  = this;
        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            Globals.gameLoop.stop();

            Pane pane = (Pane)Globals.snakeHeadNode.getParent();
            Text gameOverText = new Text("Game Over");
            gameOverText.setFont(Font.font ("Verdana", 100));
            gameOverText.setFill(Color.RED);
            gameOverText.setX((Globals.WINDOW_WIDTH/2)-(gameOverText.getLayoutBounds().getWidth()/2));
            gameOverText.setY((Globals.WINDOW_HEIGHT/2) - (gameOverText.getLayoutBounds().getHeight()/2));
            Pane gameOverPane = new Pane();
            gameOverPane.setStyle("-fx-background-color: rgba(100, 100, 100, 0.9);");
            gameOverPane.setMinSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
            pane.getChildren().addAll(gameOverPane, gameOverText);
        }

        //Sprite handling
        spriteCalculator.stepCycle();
        setViewport(spriteCalculator.getCurrentViewport());

    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    public int getHealth() {
        return health;
    }

    public void setSpeed(float newSpeed){
        speed = newSpeed;
    }
}
