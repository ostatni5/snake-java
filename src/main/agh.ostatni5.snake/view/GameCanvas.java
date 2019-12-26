package agh.ostatni5.snake.view;


import agh.ostatni5.snake.core.*;
import agh.ostatni5.snake.core.Rectangle;
import agh.ostatni5.snake.my.MyPercentPosition;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameCanvas extends JPanel {
    private int tileSize;
    private int canvasSize = 800;
    private WorldMap worldMap;
    private Color backgroundColor = new Color(0, 191, 191);
    private Color backgroundColorDead = new Color(79, 5, 0);
    private boolean gameEnded = false;
    private boolean[][] mask = new boolean[10][10];
    private boolean maskEnded = false;
    private Snake snake;
    private boolean paused = false;


    public GameCanvas(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.tileSize = canvasSize / worldMap.getRectangle().getBiggerDimension();
        snake = worldMap.getSnake();
        for (int i = 0; i < 10; i++) {
            boolean[] arr = new boolean[10];
            Arrays.fill(arr, false);
            mask[i] = arr;
        }
    }

    private int resize(int a) {
        return a * tileSize;
    }

    private void fillRecResized(Graphics g, Rectangle rectangle) {
        g.fillRect(resize(rectangle.corners[0].x), resize(rectangle.corners[0].y), resize(rectangle.width), resize(rectangle.height));
    }

    private void fillTile(Graphics g, Vector2d vector2d) {
        g.fillRect(resize(vector2d.x), resize(vector2d.y), tileSize, tileSize);
    }

    private void paintGrid(Graphics g) {
        for (int i = 0; i < worldMap.getRectangle().width; i++) {
            for (int j = 0; j < worldMap.getRectangle().height; j++) {
                g.drawRect(resize(i), resize(j), tileSize, tileSize);
            }
        }
    }

    private void paintBackground(Graphics g) {
        g.setColor(backgroundColor);
        fillRecResized(g, worldMap.getRectangle());
    }

    private void paintSnake(Graphics g, Snake snake) {
        g.setColor(snake.getColor());
        for (Vector2d vector2d : snake.getBody()) {
            fillTile(g, vector2d);
        }
        paintEye(g, snake.getHead());
    }

    private void paintEye(Graphics g, Vector2d vector2d) {
        g.setColor(Color.white);
        g.fillOval(resize(vector2d.x) + tileSize / 6, resize(vector2d.y) + tileSize / 6, tileSize * 2 / 3, tileSize * 2 / 3);
        g.setColor(Color.BLACK);
        g.fillOval(resize(vector2d.x) + tileSize * 3 / 8, resize(vector2d.y) + tileSize * 3 / 8, tileSize / 4, tileSize / 4);
    }

    private void paintFood(Graphics g, Food food) {
        g.setColor(food.getColor());
        g.fillOval(resize(food.getPosition().x), resize(food.getPosition().y), tileSize, tileSize);
        g.setColor(Color.red);
        g.fillOval(resize(food.getPosition().x) + tileSize * 3 / 8, resize(food.getPosition().y) + tileSize * 3 / 8, tileSize / 4, tileSize / 4);
    }

    private void paintDeadScreen(Graphics g) {
        g.setColor(backgroundColorDead);
        if (maskEnded) {
            g.fillRect(0, 0, canvasSize, canvasSize);
            g.setColor(Color.red);
            g.setFont(new Font("TimesRoman", Font.PLAIN, (100)));
            g.drawString("GAME OVER", 100, 300);
            g.setColor(Color.yellow);
            g.setFont(new Font("TimesRoman", Font.PLAIN, (80)));
            g.drawString(snake.getScore() + "", 100, 500);
            g.setColor(Color.green);
            g.drawString("R TO RESTART", 100, 600);
        } else
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (mask[i][j])
                        g.fillRect(canvasSize / 10 * i, canvasSize / 10 * j, canvasSize / 10, canvasSize / 10);
                }
            }

    }

    private void paintPause(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("TimesRoman", Font.PLAIN, (80)));
        g.drawString("PAUSED", 100, 500);

    }


    public void animateDeadScreen() {
        gameEnded = true;
        paused = false;
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    mask[i][j] = true;
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            maskEnded = true;
        });
        thread.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvasSize, canvasSize);
    }


    @Override
    public void paint(Graphics g) {

        paintBackground(g);
        paintSnake(g, worldMap.getSnake());
        paintGrid(g);
        paintFood(g, worldMap.getFood());
        if (gameEnded) {
            paintDeadScreen(g);
        }
        if (paused) {
            paintPause(g);
        }
    }

    public int getCanvasSize() {
        return canvasSize;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
