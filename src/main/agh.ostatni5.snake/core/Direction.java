package agh.ostatni5.snake.core;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public enum Direction {
    LEFT(KeyEvent.VK_LEFT, Rotation.R3),
    RIGHT(KeyEvent.VK_RIGHT, Rotation.R1),
    UP(KeyEvent.VK_UP, Rotation.R2),
    DOWN(KeyEvent.VK_DOWN, Rotation.R0),
    ;
    private static HashMap<Integer,Direction> byCode = new HashMap<>();
    private static HashMap<Direction,Direction> opposite = new HashMap<>();
    static {
        for (Direction direction : values()) {
            byCode.put(direction.code, direction);
        }
        opposite.put(LEFT,RIGHT);
        opposite.put(RIGHT,LEFT);
        opposite.put(UP,DOWN);
        opposite.put(DOWN,UP);
    }
    final public int code;
    final  public Rotation rotation;
    Direction(int vkLeft, Rotation rotation) {
        code=vkLeft;
        this.rotation = rotation;
    }


    public static Direction formCode(int code)
    {
        return byCode.get(code);
    }
    public boolean isOppositeOf(Direction direction){
        return opposite.get(direction).equals(this);
    }

}
