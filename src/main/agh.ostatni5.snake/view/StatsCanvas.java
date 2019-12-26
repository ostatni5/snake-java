package agh.ostatni5.snake.view;

import agh.ostatni5.snake.core.Snake;
import agh.ostatni5.snake.core.WorldMap;
import agh.ostatni5.snake.my.MyPercentPosition;

import javax.swing.*;
import java.awt.*;

public class StatsCanvas extends JPanel {
    private int height = 100;
    private int width;
    private WorldMap worldMap;
    private GameCanvas gameCanvas;
    private Color backgroundColor = new Color(2, 29, 46);
    private MyPercentPosition percentPos;
    private int lineHeight = 25;
    private float fontSize = lineHeight-4;
    private Snake snake;

    public StatsCanvas(WorldMap worldMap, GameCanvas gameCanvas) {
        this.worldMap = worldMap;
        this.gameCanvas = gameCanvas;
        width = gameCanvas.getCanvasSize();
        percentPos = new MyPercentPosition(width, height);
        snake = worldMap.getSnake();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintBackground(g);
        paintName(g);
        paintScore(g);
        paintHelp(g);
        paintBestScore(g);
    }

    private void paintBackground(Graphics g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, width, height);
    }

    private void paintName(Graphics g) {
        setFont(getFont().deriveFont(fontSize));
        g.setColor(Color.white);
        g.drawString("Player", percentPos.getWidth(2), lineHeight + percentPos.getHeight(5));
        g.setColor(Color.yellow);
        g.drawString(snake.getName(), percentPos.getWidth(25), lineHeight + percentPos.getHeight(5));
    }

    private void paintScore(Graphics g) {
        setFont(getFont().deriveFont(fontSize));
        g.setColor(Color.white);
        g.drawString("Score", percentPos.getWidth(2), lineHeight * 2 + percentPos.getHeight(5));
        g.setColor(Color.cyan);
        g.drawString(snake.getScore()+"", percentPos.getWidth(25), lineHeight * 2 + percentPos.getHeight(5));
    }

    private void paintBestScore(Graphics g) {
        setFont(getFont().deriveFont(fontSize));
        g.setColor(Color.white);
        g.drawString("Best Score", percentPos.getWidth(2), lineHeight * 3 + percentPos.getHeight(5));
        g.setColor(Color.red);
        g.drawString("100000", percentPos.getWidth(25), lineHeight * 3 + percentPos.getHeight(5));
    }

    private void paintHelp(Graphics g) {
        setFont(getFont().deriveFont(fontSize));
        g.setColor(Color.white);
        g.drawString("Movement", percentPos.getWidth(50), lineHeight * 1 + percentPos.getHeight(5));
        g.drawString("Pause", percentPos.getWidth(50), lineHeight * 2 + percentPos.getHeight(5));
        g.setColor(Color.green);
        g.drawString("↑ → ↓ ←", percentPos.getWidth(70), lineHeight * 1 + percentPos.getHeight(5));
        g.drawString("P", percentPos.getWidth(70), lineHeight * 2 + percentPos.getHeight(5));

}


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
