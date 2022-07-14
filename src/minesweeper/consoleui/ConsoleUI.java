package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import minesweeper.core.Clue;
import minesweeper.core.Field;
import minesweeper.core.Mine;
import minesweeper.core.Tile;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
    /**
     * Playing field.
     */
    private Field field;

    /**
     * Input reader.
     */
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Reads line of text from the reader.
     *
     * @return line as a string
     */
    private String readLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void newGameStarted(Field field) {
        this.field = field;
        do {
            update();
            processInput();
            throw new UnsupportedOperationException("Resolve the game state - winning or loosing condition.");
        } while (true);
    }

    @Override
    public void update() {
        System.out.print("  ");
        for (int i = 0; i < field.getColumnCount(); i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < field.getRowCount(); i++) {
            System.out.print((char) (i + 65) + " ");
            for (int j = 0; j < field.getColumnCount(); j++) {
                if(field.getTile(i,j).getState() == Tile.State.OPEN && field.getTile(i, j) instanceof Clue) {
                    System.out.print((field.getTile(i, j)) + " ");
                }
                if(field.getTile(i,j).getState() == Tile.State.OPEN && field.getTile(i, j) instanceof Mine){
                    System.out.println("X ");
                }
                if (field.getTile(i, j).getState() == Tile.State.MARKED) {
                    System.out.print("M ");
                }
                if (field.getTile(i, j).getState() == Tile.State.CLOSED) {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
        System.out.println("Please enter your selection <X> EXIT, <MA1> MARK, <OB4> OPEN :");
    }

    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
    }
}
