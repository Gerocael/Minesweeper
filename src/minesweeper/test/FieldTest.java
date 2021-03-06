package minesweeper.test;

import minesweeper.core.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class FieldTest {

    private Random randomGenerator = new Random();
    private Field field;
    private int rowCount;
    private int columnCount;
    private int minesCount;

    @BeforeEach
    public void initTests() {
        rowCount = randomGenerator.nextInt(10) + 5;
        columnCount = rowCount;
        minesCount = Math.max(1, randomGenerator.nextInt(rowCount + columnCount));
        field = new Field(rowCount, columnCount, minesCount);
    }

    public FieldTest() {
        rowCount = randomGenerator.nextInt(10) + 5;
        columnCount = rowCount;
        minesCount = Math.max(1, randomGenerator.nextInt(rowCount * columnCount));
        field = new Field(rowCount, columnCount, minesCount);
    }

    @Test
    public void checkMinesCount() {
        int minesCounter = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int column = 0; column < columnCount; column++) {
                if (field.getTile(row, column) instanceof Mine) {
                    minesCounter++;
                }
            }
        }

        assertEquals(minesCount, minesCounter, "Field was initialized incorrectly - " +
                "a different amount of mines was counted in the field than amount given in the constructor.");
    }

    @Test
    public void checkFieldInitialization() {
        assertEquals(rowCount, field.getRowCount(), "Different row count initialized.");
        assertEquals(columnCount, field.getColumnCount(), "Different column-count initialized.");
        assertEquals(minesCount, field.getMineCount(), "Different mine-count initialized.");
        assertEquals(GameState.PLAYING, field.getState(), "Not playing.");
    }

    @Test
    public void checkMarkTile() {
        int row = 5, col = 5;
        assertEquals(Tile.State.CLOSED, field.getTile(row, col).getState());
        field.markTile(row, col);
        assertEquals(Tile.State.MARKED, field.getTile(row, col).getState());
        field.markTile(row, col);
        assertEquals(Tile.State.CLOSED, field.getTile(row, col).getState());
        field.openTile(row, col);
        assertEquals(Tile.State.OPEN, field.getTile(row, col).getState());
        field.markTile(row, col);
        assertEquals(Tile.State.OPEN, field.getTile(row, col).getState());
    }

    @Test
    public void fieldWithTooManyMines() {
        Field fieldWithTooManyMines = null;
        int higherMineCount = rowCount * columnCount + randomGenerator.nextInt(10) + 1;
        try {
            fieldWithTooManyMines = new Field(rowCount, columnCount, higherMineCount);
        } catch (Exception e) {
            // field with more mines than tiles should not be created - it may fail on exception
        }
        assertTrue((fieldWithTooManyMines == null) || (fieldWithTooManyMines.getMineCount() <= (rowCount * columnCount)));
    }

    @Test
    public void checkOpenClue() {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                if (field.getTile(rowCount, columnCount) instanceof Clue) {
                    if(((Clue) field.getTile(rowCount, columnCount)).getValue()>0){
                        field.getTile(rowCount,columnCount).setState(Tile.State.OPEN);
                    }
                    if(((Clue) field.getTile(rowCount, columnCount)).getValue()==0){
                        field.getTile(rowCount,columnCount).setState(Tile.State.OPEN);
                    }
                    if(field.getTile(rowCount,columnCount).getState()==Tile.State.CLOSED){
                        field.getTile(rowCount,columnCount).setState(Tile.State.MARKED);
                    }
                }
            }
        }
        assertEquals(field.getTile(rowCount,columnCount).getState(), GameState.PLAYING, "Not playing.");
        assertEquals(field.getTile(rowCount,columnCount).getState(), GameState.PLAYING, "Not playing.");
        assertEquals(field.getTile(rowCount,columnCount).getState(), Tile.State.MARKED, "Not marked.");
    }
}
