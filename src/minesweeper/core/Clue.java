package minesweeper.core;

import java.io.Serializable;

/**
 * Clue tile.
 */
public class Clue  extends Tile implements Serializable {
    /** Value of the clue. */
    private final int value;
    
    /**
     * Constructor.
     * @param value  value of the clue
     */
    public Clue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (this.getState() == Tile.State.OPEN) {
            return String.valueOf(value);
        }
        return super.toString();
    }
}
