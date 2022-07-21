package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.BestTimes;
import minesweeper.Minesweeper;
import minesweeper.core.*;

import javax.sound.midi.Soundbank;

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
        System.out.println("What should I call you: ");
        String player = readLine();
        do {
            update();
            processInput();
        } while (field.getState() == GameState.PLAYING);
        if (field.getState() == GameState.SOLVED) {
            Minesweeper.getInstance().getBestTimes().addPlayerTime(player, Minesweeper.getPlayingSeconds());
            System.out.printf("Glorious victory! Best times \n %s\n", Minesweeper.getInstance().getBestTimes());
            System.exit(0);;
        } else {
            System.out.println("Game over, potato.");
            System.exit(1);
        }
    }

    @Override
    public void update() {
        System.out.print("  ");
        for (int i = 0; i < field.getColumnCount(); i++) {
            System.out.printf("%2s", i);
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
        field.getRemainingMineCount();
        System.out.printf("Remaining number of marked/remaining mines: %d%n", field.getRemainingMineCount());
        System.out.println("Please enter your selection <X> EXIT, <MA1> MARK, <OB4> OPEN: ");
    }

    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        String line = readLine().trim().toUpperCase();
        try {
            handleInput(line);
        } catch (WrongFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleInput(String input) throws WrongFormatException {
        if (input.equals("X")) {
            System.out.println("Game exited.");
            System.exit(1);
        }
        Pattern pattern = Pattern.compile("(O|M)([A-I])([0-8])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input.toUpperCase());

        if (!matcher.matches()) {
            throw new WrongFormatException("Wrong input.");
        }
        int row = matcher.group(2).charAt(0) - 65;
        int column = Integer.parseInt(matcher.group(3));
        if (matcher.group(1).equals("O")) {
            field.openTile(row, column);
        } else {
            field.markTile(row, column);
        }
    }
}
