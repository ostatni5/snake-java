package agh.ostatni5.snake.core;


import agh.ostatni5.snake.my.MyRandom;

import java.awt.*;


public class Snake {
    private Color originalColor = new Color(97, 12, 14);
    private Color color = new Color(97, 12, 14);
    private MyRandom myRandom = new MyRandom();
    private String name ="Mr Snake";
    private Body body;
    private Rotation rotation = Rotation.R0;
    private WorldMap worldMap;
    private int score = 0;
    private boolean alive = true;

    Snake(WorldMap worldMap, Vector2d head, int length, String snakeName) {
        this.worldMap = worldMap;
        body = new Body(head, length);
        name = snakeName;
    }

    public boolean isBodyAt(Vector2d vector2d) {
        return body.get(vector2d) != null;
    }

    public Body getBody() {
        return body;
    }

    public Vector2d getHead() {
        return body.getLast();
    }

    public Color getColor() {
        return color;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }


    public void nextStep() {
        if (!alive) return;
        Vector2d correctedPos = worldMap.correctPos(getHead().add(rotation.getUnitVector()));
        switch (worldMap.whatHappenOn(correctedPos)) {
            case DIE:
                justDie();
                break;
            case MOVE:
                justMove(correctedPos);
                break;
            case EAT:
                justEat(correctedPos);
                break;
        }

    }

    private void justDie() {
        alive = false;
        color = Color.RED;
        worldMap.snakeIsDead();
    }

    private void justMove(Vector2d justMove) {
        body.add(justMove);
        body.poll();
    }

    private void justEat(Vector2d vector2d) {
        body.add(vector2d);
        worldMap.eatFood();
        colorize();
        score += body.size() * 10;
    }


    private void colorize() {
        Thread threadColorize = new Thread(() -> {
            for (int i = 0; i < 10 && alive; i++) {
                color = myRandom.getRandomColor();
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            color = new Color(originalColor.getRGB());
        });
        threadColorize.start();
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}

