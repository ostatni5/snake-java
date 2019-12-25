package agh.ostatni5.snake.view;


import agh.ostatni5.snake.core.Rectangle;
import agh.ostatni5.snake.core.Vector2d;
import agh.ostatni5.snake.core.WorldMap;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    private int tileSize;

    private WorldMap worldMap;
    private Color creatureColor = new Color(97, 12, 14);


    public GameCanvas(WorldMap worldMap, int canvasSize) {
        this.worldMap = worldMap;
        this.tileSize = 10;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension();
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension();
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, (int) (tileSize * 0.8)));


    }
}
