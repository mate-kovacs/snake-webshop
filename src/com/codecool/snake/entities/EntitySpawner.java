package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.BoundingBox;
import javafx.scene.layout.Pane;

import java.util.Random;

public class EntitySpawner extends GameEntity implements Animatable {
    private int posX;
    private int posY;
    private int frequency;
    private int entityLimit;
    private Class classType;
    int cycleCounter = 0;
    private Pane pane;
    int hitBoxWidth = 200;
    int hitBoxHeight = 200;

    public EntitySpawner(Pane pane, int frequency, int limit, Class classType, int posX, int posY){
        super(pane);
        this.pane = pane;
        this.frequency = frequency;
        this.entityLimit = limit;
        this.classType = classType;
        this.posX = posX;
        this.posY = posY;

        pane.getChildren().add(this);
    }

    @Override
    public void step() {
        cycleCounter++;
        if (cycleCounter > frequency && entityLimit > getNumberOfType()) {
            double xCoord;
            double yCoord;
            BoundingBox hitBox;
            do {
                Random rnd = new Random();
                xCoord = rnd.nextDouble() * Globals.WINDOW_WIDTH;
                yCoord = rnd.nextDouble() * Globals.WINDOW_HEIGHT;
                hitBox = new BoundingBox(xCoord, yCoord, hitBoxWidth, hitBoxHeight);
            } while (hitBox.intersects(Globals.snakeHeadNode.hitBox));
            try {
                Class [] argClassArray = new Class[3];
                argClassArray[0] = Pane.class;
                argClassArray[1] = Double.class;
                argClassArray[2] = Double.class;

                classType.getDeclaredConstructor(argClassArray).newInstance(pane, xCoord, yCoord);
            } catch (Exception e) {
                System.out.println("Spawner error: " + e.getMessage());
            }
            cycleCounter = 0;
        }
    }

    private int getNumberOfType() {
        int counter = 0;
        for (GameEntity object : Globals.gameObjects) {
            if (object.getClass() == classType) {
                counter++;
            }
        }
        return counter;
    }

}
