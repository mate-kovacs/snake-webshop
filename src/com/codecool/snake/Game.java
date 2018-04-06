package com.codecool.snake;

import com.codecool.snake.entities.Health;
import com.codecool.snake.entities.EntitySpawner;
import com.codecool.snake.fieldobjects.powerups.*;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        initGameEntities();
    }

    private void initGameEntities () {
        new SnakeHead(this, 500, 500);
        new Health(this);

        new EntitySpawner(this, 100, 5, SimpleEnemy.class, 100, 100 );
        //new EntitySpawner(this, 100, 5, DistractorEnemy.class, 100, 100 );
        new EntitySpawner(this, 300, 1, ProtesterEnemy.class, 100, 100 );
        new EntitySpawner(this, 30, 3, SimplePowerup.class, 100, 100 );
        //new EntitySpawner(this, 30, 2, FreezerPowerUp.class, 100, 100 );
        new EntitySpawner(this, 30, 2, SpeederPowerUp.class, 100, 100 );
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
