package com.codecool.snake;

import com.codecool.snake.entities.EntitySpawner;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.FreezerPowerUp;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SpeederPowerUp;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        initGameEntities();
    }

    private void initGameEntities () {
        new SnakeHead(this, 500, 500);

        new EntitySpawner(this, 60, 10, SimpleEnemy.class, 100, 100 );
        new EntitySpawner(this, 300, 2, SimplePowerup.class, 100, 100 );

        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);
        new SimplePowerup(this);

        new SpeederPowerUp(this);
        new SpeederPowerUp(this);
        new SpeederPowerUp(this);
        new SpeederPowerUp(this);

        new FreezerPowerUp(this);
        new FreezerPowerUp(this);
        new FreezerPowerUp(this);
        new FreezerPowerUp(this);
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case R: restart(); break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public void restart() {
        resetGame();
        initGameEntities();
        start();
    }

    private void resetGame() {
        Globals.gameLoop.stop();
        Globals.newGameObjects.clear();
        Globals.oldGameObjects.clear();
        Globals.gameObjects.clear();
        this.getChildren().clear();
    }
}
