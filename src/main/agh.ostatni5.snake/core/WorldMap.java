package agh.ostatni5.snake.core;


import agh.ostatni5.snake.view.GameCanvas;

public class WorldMap {
    private Rectangle rectangle;
    private Snake snake;
    private boolean borderless;
    private Food food;
    private Direction lastDirection = Direction.DOWN;
    private GameCanvas gameCanvas;

    public WorldMap(int mapWidth, int mapHeight, boolean borderless, int initialLength) {
        rectangle = new Rectangle(mapWidth, mapHeight);
        snake = new Snake(this, rectangle.getCenter(), initialLength);
        this.borderless = borderless;
        food = new Food(rectangle.randomIn());
    }

    public void next(Direction direction) {
        if (!direction.isOppositeOf(lastDirection)) {
            snake.setRotation(direction.rotation);
            lastDirection = direction;
        }
        snake.nextStep();
    }

    public Action whatHappenOn(Vector2d vector2d) {
        if (!borderless && !rectangle.isIn(vector2d)) {
            return Action.DIE;
        }
        if(snake.isBodyAt(vector2d)) return Action.DIE;
        if(food.getPosition().equals(vector2d)) return Action.EAT;
        return Action.MOVE;
    }

    public void eatFood(){
        Vector2d pos = rectangle.randomIn();
        while (snake.isBodyAt(pos)) pos = rectangle.randomIn();
        food = new Food(pos);
    }


    public Vector2d correctPos(Vector2d vector2d) {
        if(!borderless) return vector2d;
        int x = vector2d.x < rectangle.corners[0].x ? rectangle.corners[1].x : vector2d.x > rectangle.corners[1].x ? rectangle.corners[0].x : vector2d.x;
        int y = vector2d.y < rectangle.corners[0].y ? rectangle.corners[2].y : vector2d.y > rectangle.corners[2].y ? rectangle.corners[0].y : vector2d.y;
        return new Vector2d(x, y);
    }

    public void snakeIsDead(){
        gameCanvas.animateDeadScreen();
    }


    public Rectangle getRectangle() {
        return rectangle;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }
    public void bindGameCanvas(GameCanvas gameCanvas){
        this.gameCanvas=gameCanvas;
    }
}
