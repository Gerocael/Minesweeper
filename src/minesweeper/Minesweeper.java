package minesweeper;

import minesweeper.consoleui.ConsoleUI;
import minesweeper.consoleui.UserInterface;
import minesweeper.core.Field;
import minesweeper.core.GameState;
import minesweeper.core.Tile;

/**
 * Main application class.
 */
public class Minesweeper {
    /**
     * User interface.
     */
    private UserInterface userInterface;
    private static long startTime;
    private static Minesweeper instance;

    private BestTimes bestTimes = new BestTimes();

    public static Minesweeper getInstance(){
        if(instance == null) {
            new Minesweeper();
        }
        return instance;
    }

    /**
     * Constructor.
     */
    private Minesweeper() {
        userInterface = new ConsoleUI();
        instance = this;
        Field field = new Field(9, 9, 1);
        userInterface.newGameStarted(field);

    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        startTime = System.currentTimeMillis();
        new Minesweeper();
        getPlayingSeconds();
    }

    public static int getPlayingSeconds() {
        return (int) ((System.currentTimeMillis() - startTime) / 1000);
    }

    public BestTimes getBestTimes() {
        return bestTimes;
    }
}
