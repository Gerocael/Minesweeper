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

//    private int format = 2;

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
//        this.format = "%" + format + "%s;
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
            System.out.printf("%2s",i);
        }
        System.out.println();
        for (int i = 0; i < field.getRowCount(); i++) {
                        System.out.printf("%2c", (i + 65));
            for (int j = 0; j < field.getColumnCount(); j++) {
                Tile t = field.getTile(i, j);
                if (t.getState() == Tile.State.OPEN) {
                    System.out.printf("%2s", t);
                }
                if (t.getState() == Tile.State.MARKED) {
                    System.out.printf("%2s", "M");
                }
                if (t.getState() == Tile.State.CLOSED) {
                    System.out.printf("%2s", "-");
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
//        String line = readLine();
//        System.out.println(line);
    }
}
