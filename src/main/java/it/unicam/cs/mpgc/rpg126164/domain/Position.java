package it.unicam.cs.mpgc.rpg126164.domain;

/**
 * This class represents the position in the world game, given the coordinates. It can't be updated, but it has
 * to be substituted with a new position to update any object that uses this class.
 */
public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}
