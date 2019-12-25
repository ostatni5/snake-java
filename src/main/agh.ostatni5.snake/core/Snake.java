package agh.ostatni5.snake.core;

import java.util.LinkedList;

public class Snake {
    int length;
    Vector2d head;
    Vector2d tail;
    Body body = new Body();
    Snake(Vector2d head, int length)
    {
        this.head= new Vector2d(head);
    }


}
class Body extends LinkedList<Vector2d>
{

}
