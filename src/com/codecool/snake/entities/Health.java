package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Locale;

import java.text.NumberFormat;

public class Health extends GameEntity implements Animatable{

    private HBox root;
    private NumberFormat format;

    public Health(Pane pane) {
        super(pane);
        format = NumberFormat.getCurrencyInstance(Locale.US);
        String currency = format.format(new Integer(Globals.snakeHeadNode.getHealth()));

        Text dispHealth = new Text(1, 15, "");
        dispHealth.setText(currency); //+ Integer.toString(health));
        dispHealth.setFont(Font.font ("Verdana", 20));
        dispHealth.setFill(Color.RED);

        root = new HBox();
        root.setSpacing(10);
        root.setPadding(new Insets(15,20,10,10));
        root.setMaxSize(4,4);
        root.setAlignment(Pos.CENTER);
        ImageView image = new ImageView();
        image.setImage(Globals.coins);
        image.setFitWidth(40);
        image.setFitHeight(40);

        root.getChildren().add(image);
        root.getChildren().add(dispHealth);
        pane.getChildren().add(root);
    }

    @Override
    public void step() {
        setHealth(Globals.snakeHeadNode.getHealth());
    }

    public void setHealth(int health) {
        for (Node n:root.getChildren()) {
            if (n instanceof Text) {
                String currency = format.format(new Integer(health));
                ((Text) n).setText(currency);
            }
        }
    }
}
