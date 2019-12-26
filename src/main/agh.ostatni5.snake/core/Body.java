package agh.ostatni5.snake.core;

import java.util.HashMap;
import java.util.LinkedList;

public class Body extends LinkedList<Vector2d> {
    private HashMap<Vector2d,Vector2d> positions = new HashMap<>();
    Body(Vector2d head, int length) {
        for (int i = 0; i < length; i++) {
            push(new Vector2d(head.x, head.y - i));
        }
    }

    @Override
    public boolean add(Vector2d vector2d) {
        positions.put(vector2d,vector2d);
        return super.add(vector2d);
    }

    @Override
    public Vector2d poll() {
        Vector2d pollReturn = super.poll();
        positions.remove(pollReturn);
        return pollReturn;
    }

    public Vector2d get(Vector2d vector2d) {
        return positions.get(vector2d);
    }
}
