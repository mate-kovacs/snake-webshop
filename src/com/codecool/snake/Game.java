package com.codecool.snake;

import com.codecool.snake.entities.Health;
import com.codecool.snake.entities.EntitySpawner;
import com.codecool.snake.fieldobjects.CodeCoinPowerUp;
import com.codecool.snake.fieldobjects.QuestItem;
import com.codecool.snake.fieldobjects.SpeederPowerUp;
import com.codecool.snake.fieldobjects.TormentorEnemy;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.lordofstrings.netconnector.GameRequests;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    static GameRequests gameRequests = GameRequests.getInstance();

    public Game() {
        initGameEntities();
    }

    private void initGameEntities () {
        ImageView bgImg = new ImageView(Globals.bgImage);
        bgImg.setFitWidth(Globals.WINDOW_WIDTH);
        bgImg.setPreserveRatio(true);
        this.getChildren().add(bgImg);

        new SnakeHead(this, 500, 500);
        new Health(this);

        new EntitySpawner(this, 100, 5, QuestItem.class, 100, 100 );
        //new EntitySpawner(this, 100, 5, DistractorEnemy.class, 100, 100 );
        new EntitySpawner(this, 300, 1, TormentorEnemy.class, 100, 100 );
        //new EntitySpawner(this, 100, 5, QuestItem.class, 100, 100 );
        new EntitySpawner(this, 100, 5, CodeCoinPowerUp.class, 100, 100 );
        //new EntitySpawner(this, 30, 2, FreezerPowerUp.class, 100, 100 );
        new EntitySpawner(this, 100, 3, SpeederPowerUp.class, 100, 100 );
    }

    public void start() {
        gameRequests.getShoppingCartId();
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
