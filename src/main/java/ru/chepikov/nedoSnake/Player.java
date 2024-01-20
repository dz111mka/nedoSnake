package ru.chepikov.nedoSnake;

public class Player extends Entity{
    private Direction direction;

    public Player(int x, int y, Direction direction) {
        super(x, y);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
