package com.codecool.snake.fieldobjects.powerups;

import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.SpriteCalculator;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.Random;

abstract class AbstractFieldObject extends GameEntity implements Animatable {
    int stepCount;
    double direction;
    protected float speed;
    private MovementStatus defaultStatus;
    private MovementStatus movementStatus;
    private int stateCounter;
    private int statusChangePeriod = 10000;
    private SpriteCalculator spriteCalculator;
    private int numOfFrames;

    enum MovementStatus {
        STANDSTILL,
        TOWARD_SNAKEHEAD,
        AFAR_SNAKEHEAD,
        RANDOM_MOVING,
    }

    AbstractFieldObject(Pane pane) {
        super(pane);
        speed = initSpeed();
        numOfFrames = initNumberOfFrames();
        setImage(initImage());
        this.spriteCalculator = new SpriteCalculator(getImage(), numOfFrames, 10);
        setViewport(spriteCalculator.getCurrentViewport());
    }

    AbstractFieldObject(Pane pane, Double x, Double y) {
        this(pane);
        setX(x);
        setY(y);

    }

    public void moveRandomly() {
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

    public void moveTowardsSnakeHead() {
        double direction = getAngle(Globals.snakeHeadNode, this);
        setRotate(direction+180);
        Point2D snakeCoordinates = new Point2D(Globals.snakeHeadNode.getX(), Globals.snakeHeadNode.getY());
        Point2D myCoordiantes = new Point2D(this.getX(), this.getY());
        Point2D moveVector = snakeCoordinates.subtract(myCoordiantes);
        moveVector = moveVector.normalize();
        this.setX(getX() + moveVector.getX() * speed);
        this.setY(getY() + moveVector.getY() * speed);
    }

    public void moveAfarSnakeHead() {
        double direction = getAngle(Globals.snakeHeadNode, this);
        setRotate(direction);
        Point2D snakeCoordinates = new Point2D(Globals.snakeHeadNode.getX(), Globals.snakeHeadNode.getY());
        Point2D myCoordiantes = new Point2D(this.getX(), this.getY());
        Point2D moveVector = snakeCoordinates.subtract(myCoordiantes);
        moveVector = moveVector.normalize().multiply(-1);
        this.setX(getX() + moveVector.getX() * speed);
        this.setY(getY() + moveVector.getY() * speed);
    }

    public double getAngle(SnakeHead snakeHead, GameEntity object) {
        double a = object.getX();
        double b = object.getY();
        double c = snakeHead.getX();
        double d = snakeHead.getY();
        double degrees = Math.toDegrees(Math.atan((d - b) / (c - a)));
        degrees = (degrees < 0) ? degrees-90 : degrees+90;
        if (d > b) {
            return degrees-180;
        }
        return degrees;
    }

    public Point2D getMoveVector(SnakeHead snakeHead, GameEntity ge) {
        Point2D moveVector = null;
        // todo
        return moveVector;
    }

    public void setMovementStatus(MovementStatus movementStatus) {
        this.movementStatus = movementStatus;
    }

    public MovementStatus getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(MovementStatus defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    abstract float initSpeed();

    abstract Image initImage();

    abstract  int initNumberOfFrames();

    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setStatus();
        switch (this.movementStatus) {
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

        //Sprite handling
        spriteCalculator.stepCycle();
        setViewport(spriteCalculator.getCurrentViewport());
    }

    protected void setStatus() {
        if (!this.movementStatus.equals(defaultStatus)) {
            stateCounter++;
        }

        if (stateCounter == statusChangePeriod) {
            this.movementStatus = defaultStatus;
            stateCounter = 0;
        }
    }
}
