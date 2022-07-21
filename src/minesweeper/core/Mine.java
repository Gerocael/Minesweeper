package minesweeper.core;

import java.io.Serializable;

/**
 * Mine tile.
 */
public class Mine extends Tile implements Serializable {

    @Override
    public String toString() {
        if (this.getState() == Tile.State.OPEN) {
            return "*";
        }
        return super.toString();
    }
}

