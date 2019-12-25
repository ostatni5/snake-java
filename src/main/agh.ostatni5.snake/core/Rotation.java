package agh.ostatni5.snake.core;

import java.util.Random;

public enum Rotation {
    R0(new Vector2d(0, 1)), R1(new Vector2d(1, 0)), R2(new Vector2d(0, -1)),  R3(new Vector2d(-1, 0));
    private static Random random = new Random();
    private Vector2d unitVector;
    private final static String[] symbols = {"↑", "→",  "↓",  "←"};
    private final static String[] symbolsReverse = {"↓", "→","↑", "←"};
    Rotation(Vector2d vector2d) {
        this.unitVector = vector2d;
    }

    static public Rotation getRandom() {
        return Rotation.values()[random.nextInt(Rotation.values().length)];
    }

    public Vector2d getUnitVector() {
        return new Vector2d(unitVector);
    }

    public Rotation rotate(Rotation rotation) {
        return Rotation.values()[(this.ordinal() + rotation.ordinal()) % Rotation.values().length];
    }

    public String toString() {
        return symbols[this.ordinal()];
    }
    public String toStringReverse() {
        return symbolsReverse[this.ordinal()];
    }
}
