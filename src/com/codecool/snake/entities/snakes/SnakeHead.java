package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.interfaces.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.screens.GameOverScreen;
import com.codecool.snake.entities.interfaces.Interactable;
import com.codecool.snake.entities.SpriteCalculator;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


public class SnakeHead extends GameEntity implements Animatable {

    private float speed;
    private float defaultSpeed = 2;
    private int stepCount;
    private int statePeriod = 1000;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int money = 0;

    private SpriteCalculator spriteCalculator;
    private int numOfFrames = 4;
    public BoundingBox hitBox;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        tail = this;
        speed = defaultSpeed;
        setImage(Globals.snakeHead);
        this.spriteCalculator = new SpriteCalculator(getImage(), numOfFrames, 10);
        setViewport(spriteCalculator.getCurrentViewport());
        hitBox = new BoundingBox(getX(), getY(), 70, 60);
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

        //Update hitbox
        hitBox = new BoundingBox(getX(), getY(), 70, 60);

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (entity instanceof Interactable) {
                if (hitBox.intersects(((Interactable)entity).getHitbox())) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds()) {
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

    public void changeMoney(int diff) {
        money += diff;
    }

    public int getMoney() {
        return money;
    }

    public void setSpeed(float newSpeed) {
        speed = newSpeed;
    }

    public GameEntity getTail() {
        return tail;
    }

    public void setTail(GameEntity snakeBody){
        this.tail = snakeBody;
    }

    public void gameOver() {
        Pane pane = (Pane)Globals.snakeHeadNode.getParent();
        GameOverScreen gameOverScreen = new GameOverScreen();
        gameOverScreen.initGameOverScreen();
        pane.getChildren().addAll(gameOverScreen);
    }
}
