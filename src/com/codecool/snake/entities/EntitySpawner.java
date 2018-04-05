package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.scene.layout.Pane;

public class EntitySpawner extends GameEntity implements Animatable {
    private int posX;
    private int posY;
    private int frequency;
    private int entityLimit;
    private Class classType;
    int cycleCounter = 0;
    private Pane pane;

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
            try {
                classType.getDeclaredConstructor(Pane.class).newInstance(pane);
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
