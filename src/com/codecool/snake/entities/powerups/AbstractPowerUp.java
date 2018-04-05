package com.codecool.snake.entities.powerups;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

abstract class AbstractPowerUp extends GameEntity {
    AbstractPowerUp (Pane pane){
        super(pane);
    }
    int stepCount = 0;
    double direction = 0;
    SnakeHead snakeHead;
    MovementStatus movementStatus;


    enum MovementStatus {
        STANDSTILL,
        TOWARD_SNAKEHEAD,
        AFAR_SNAKEHEAD,
        RANDOM_MOVING
    }

    public void moveRandomly(){
        float speed = 0;
        Point2D heading;
        Random rnd = new Random();
        int directionChangeFrequency = 100;

        if (stepCount % directionChangeFrequency == 0) {
            direction = rnd.nextDouble() * 360;
            setRotate(direction);
        }
        heading = Utils.directionToVector(direction, speed);
        this.setX(getX() + heading.getX());
        this.setY(getY() + heading.getY());
        stepCount++;
    }

    public void moveTowardsSnakeHead (){
        float speed = 4;
        Point2D heading;
        double directionHead = getAngle(Globals.snakeHeadNode, this);
        heading = Utils.directionToVector(directionHead, speed);
        this.setX(getX() + heading.getX());
        this.setY(getY() + heading.getY());
    }

    public void moveAfarSnakeHead (){
        // todo
    }

    public double getAngle(SnakeHead snakeHead, GameEntity object){
        double a = object.getX();
        double b = object.getY();
        double c =  snakeHead.getX();
        double d = snakeHead.getY();
        return Math.toDegrees(Math.atan((d-b)/(c-a)));
    }

    public void step () {
        if (isOutOfBounds()) {
            destroy();
        }
        switch (this.movementStatus){
            case RANDOM_MOVING:
                this.moveRandomly();
                break;
            case AFAR_SNAKEHEAD:
                this.moveAfarSnakeHead();
                break;
            case TOWARD_SNAKEHEAD:
                this.moveTowardsSnakeHead();
                break;
        }
    }
}
