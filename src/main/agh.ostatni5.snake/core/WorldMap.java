package agh.ostatni5.snake.core;


public class WorldMap {
    private Rectangle mapRec;
    private Snake snake;
    private boolean borderless;

    public WorldMap(int mapWidth, int mapHeight, boolean borderless, int initialLength) {
        mapRec = new Rectangle(mapWidth, mapHeight);
        snake= new Snake(mapRec.getCenter(),initialLength);
        this.borderless=borderless;
    }



    public Vector2d correctPos(Vector2d vector2d) {
        int x = vector2d.x < mapRec.corners[0].x ? mapRec.corners[1].x : vector2d.x > mapRec.corners[1].x ? mapRec.corners[0].x : vector2d.x;
        int y = vector2d.y < mapRec.corners[0].y ? mapRec.corners[2].y : vector2d.y > mapRec.corners[2].y ? mapRec.corners[0].y : vector2d.y;
        return new Vector2d(x, y);
    }

}

