package agh.ostatni5.snake.core;

import agh.ostatni5.snake.my.MyMath;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;
    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Vector2d(Vector2d a){
        this.x = a.x;
        this.y = a.y;
    }
    public String toString(){
        return "("+ this.x+","+ this.y+")";
    }
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (!(other instanceof Vector2d))
            return false;
        Vector2d that = (Vector2d) other;

        return that.x==this.x && that.y==this.y;
    }
    public boolean precedes(Vector2d a){
        return a.x <= this.x && a.y <= this.y;
    }
    public boolean follows(Vector2d a){
        return a.x >= this.x && a.y >= this.y;
    }

    public Vector2d upperRight(Vector2d a){
        return  new Vector2d(Math.max(this.x,a.x),Math.max(this.y,a.y));
    }
    public Vector2d lowerLeft(Vector2d a){
        return  new Vector2d(Math.min(this.x,a.x),Math.min(this.y,a.y));
    }
    public Vector2d add(Vector2d a){
        return  new Vector2d(this.x+a.x,this.y+a.y);
    }
    public Vector2d subtract(Vector2d a){
        return  new Vector2d(this.x-a.x,this.y-a.y);
    }
    public Vector2d opposite(){
        return  new Vector2d(this.x*-1,this.y*-1);
    }
    public Vector2d avg(Vector2d a)
    {
        return new Vector2d(MyMath.avgInt(this.x,a.x),MyMath.avgInt(this.y,a.y));
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}