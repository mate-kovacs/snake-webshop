package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.SpriteCalculator;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.BoundingBox;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeBody extends GameEntity implements Animatable {

    public class HistoryObject {
        public Vec2d vector;
        public double rotation;

        HistoryObject (double x, double y, double rotation) {
            vector = new Vec2d(x, y);
            this.rotation = rotation;
        }


    }

    private GameEntity parent;
    private Queue<HistoryObject> history = new LinkedList<>();
    private static final int space = 4;
    private static final int historySize = 10 * space;
    private int stepCounter;
    private SpriteCalculator spriteCalculator;

    public SnakeBody(Pane pane, GameEntity parent) {
        super(pane);
        this.parent = parent;
        setImage(Globals.snakeBody);
        this.spriteCalculator = new SpriteCalculator(getImage(), 4, 10);
        setViewport(spriteCalculator.getCurrentViewport());
        Globals.bodyParts.push(this);

        // place it visually below the current tail
        List<Node> children = pane.getChildren();
        children.add(children.indexOf(parent), this);

        double xc = parent.getX();
        double yc = parent.getY();
        setX(xc);
        setY(yc);

        for (int i = 0; i < historySize; i++) {
            history.add(new HistoryObject(xc, yc, parent.getRotate()));
        }
    }

    public void step() {
        HistoryObject pos = history.poll(); // remove the oldest item from the history
        stepCounter++;
        setRotate(pos.rotation);
        if (stepCounter > space) {
            setX(pos.vector.x);
            setY(pos.vector.y);
        }

        history.add(new HistoryObject(parent.getX(), parent.getY(), parent.getRotate())); // add the parent's current position to the beginning of the history

        //Sprite handling
        spriteCalculator.stepCycle();
        setViewport(spriteCalculator.getCurrentViewport());
    }

}
