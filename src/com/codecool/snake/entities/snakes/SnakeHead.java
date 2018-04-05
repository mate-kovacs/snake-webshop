package com.codecool.snake.entities.snakes;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Health;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.SpriteCalculator;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.text.NumberFormat;
import java.util.Locale;


public class SnakeHead extends GameEntity implements Animatable {

    private float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;

    private SpriteCalculator spriteCalculator;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        tail = this;
        setImage(Globals.snakeHead);
        this.spriteCalculator = new SpriteCalculator(getImage(), 2, 30);
        pane.getChildren().add(this);
        Globals.snakeHeadNode  = this;
        addPart(4);
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
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

    public void changeHealth(int diff) {
        health += diff;
    }

    public int getHealth() {
        return health;
    }

    public void setSpeed(float newSpeed){
        speed = newSpeed;
    }

    protected void gameOver() {
        Pane pane = (Pane)Globals.snakeHeadNode.getParent();
        VBox gameOverVBox = new VBox();
        gameOverVBox.setAlignment(Pos.CENTER);

        // Game Over image
        ImageView gameOverImage = new ImageView();
        gameOverImage.setImage(Globals.gameOver);
        gameOverImage.setFitHeight(200);
        gameOverImage.setPreserveRatio(true);

        // Game Over text
        Text gameOverText = new Text("Game Over");
        gameOverText.setFont(Font.font ("Verdana", 100));
        gameOverText.setFill(Color.RED);

        // Collected voters text
        int snakeBodyPartsNr = 0;
        for (Node node: Globals.gameObjects) {
            if (node instanceof SnakeBody) {
                snakeBodyPartsNr += 1;
            }
        }

        Text collectedText = new Text("Collected voter(s): " + snakeBodyPartsNr);
        collectedText.setFont(Font.font ("Verdana", 20));
        collectedText.setFill(Color.YELLOW);

        String content = "Press 'R' for restart!";
        Text pressRText = new Text(content);
        pressRText.setFont(Font.font ("Verdana", 20));
        pressRText.setFill(Color.DARKMAGENTA);

        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(2000));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                pressRText.setText(content.substring(0, n));
            }

        };

        animation.play();

        gameOverVBox.getChildren().addAll(gameOverImage, gameOverText, collectedText, pressRText);
        gameOverVBox.setLayoutX((Globals.WINDOW_WIDTH/2)-(gameOverVBox.getBoundsInLocal().getWidth()/2));
        gameOverVBox.setLayoutY((Globals.WINDOW_HEIGHT/2)-(gameOverVBox.getBoundsInLocal().getHeight()/2));

        // Grey background for game over
        Pane gameOverPane = new Pane();
        gameOverPane.setStyle("-fx-background-color: rgba(100, 100, 100, 0.9);");
        gameOverPane.setMinSize(Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);

        pane.getChildren().addAll(gameOverPane, gameOverVBox);
    }
}
