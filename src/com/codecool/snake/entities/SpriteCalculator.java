package com.codecool.snake.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class SpriteCalculator {
    private final Rectangle2D[] cellClips;
    private int numCells;
    private int cycleCounter;
    private final int duration;
    private int currentSprite;

    public SpriteCalculator(Image image, int numCells, int duration) {
        this.numCells = numCells;
        this.duration = duration;

        double cellWidth  = image.getWidth() / numCells;
        double cellHeight = image.getHeight();

        cellClips = new Rectangle2D[numCells];
        for (int i = 0; i < numCells; i++) {
            cellClips[i] = new Rectangle2D(i * cellWidth, 0, cellWidth, cellHeight);
        }
    }

    public void stepCycle() {
        cycleCounter++;
        if (cycleCounter == duration) {
            currentSprite++;
            if (currentSprite == numCells) {
                currentSprite = 0;
            }
           cycleCounter = 0;
        }
    }

    public Rectangle2D getCurrentViewport() {
        return cellClips[currentSprite];
    }
}

