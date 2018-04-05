package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.image.ImageView;

public class Health {
    private ImageView[] stars;
    private HBox root;
    private int starsNr = 5;

    public Health(Pane pane, SnakeHead head) {

        stars = new ImageView[starsNr];

        for (int i = 0; i < starsNr; i++) {
            stars[i] = new ImageView();
        }

        root = new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(15,20,10,10));
        root.setMaxSize(4,4);

        for (ImageView image: stars) {
            image.setImage(Globals.star);
            image.setFitWidth(40);
            image.setFitHeight(40);
            root.getChildren().add(image);
        }


        //setX(1);
        //setY(15);
        pane.getChildren().add(root);
    }

}
