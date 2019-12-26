package agh.ostatni5.snake.my;

public class MyPercentPosition {
    private int width;
    private int height;

    public MyPercentPosition(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth(int percent)
    {
        return getPercentOf(width,percent);
    }
    public int getHeight(int percent)
    {
        return getPercentOf(height,percent);
    }

    private int getPercentOf(int value, int percent)
    {
        return value*percent/100;
    }

}
