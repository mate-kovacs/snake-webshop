package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.screens.GameOverScreen;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.SpriteCalculator;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


public class SnakeHead extends GameEntity implements Animatable {

    private float speed;
    private float defaultSpeed = 2;
    private int stepCount;
    private int statePeriod = 1000;
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
        speed = defaultSpeed;
        setImage(Globals.snakeHead);
        this.spriteCalculator = new SpriteCalculator(getImage(), 2, 30);
        pane.getChildren().add(this);
        Globals.snakeHeadNode  = this;
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set speed
        if (speed != defaultSpeed){
            stepCount++;
        }
        if (stepCount == statePeriod){
            speed = defaultSpeed;
            stepCount = 0;
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
            gameOver();
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

    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    public GameEntity getTail() {
        return tail;
    }

    public void setTail(SnakeBody snakeBody){
        this.tail = snakeBody;
    }

    public void gameOver() {
        Pane pane = (Pane)Globals.snakeHeadNode.getParent();
        GameOverScreen gameOverScreen = new GameOverScreen();
        gameOverScreen.initGameOverScreen();
        pane.getChildren().addAll(gameOverScreen);
    }

}
