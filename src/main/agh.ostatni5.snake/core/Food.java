package agh.ostatni5.snake.core;

import agh.ostatni5.snake.view.HSLColor;

import java.awt.*;
import java.util.Random;

public class Food {
    private Vector2d position;
    private Color color = new Color(54, 200, 46);

    public Food(Vector2d position) {
        this.position = position;
        HSLColor hslColor = new HSLColor(color);
        Random random = new Random();
        color = hslColor.adjustLuminance(random.nextInt(50)+25);
    }


    public Color getColor() {
        return color;
    }

    public Vector2d getPosition() {
        return position;
    }
}
