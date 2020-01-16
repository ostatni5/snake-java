package agh.ostatni5.snake.core;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class KeyMap extends HashMap<Integer, Boolean> {
    private Direction lastDirection = null;

    public KeyMap() {
        put(KeyEvent.VK_LEFT, false);
        put(KeyEvent.VK_DOWN, false);
        put(KeyEvent.VK_UP, false);
        put(KeyEvent.VK_RIGHT, false);
    }

    public void press(Integer integer) {
        Direction direction = Direction.formCode(integer);
        if (direction != null)
            lastDirection = direction;
        replace(integer, true);
    }

    public void release(Integer integer) {
        replace(integer, false);
    }
    public void reset(){lastDirection = null;}

    public Direction getLastDirection() {
        return lastDirection;
    }
}
