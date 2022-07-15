package minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import minesweeper.core.*;

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
        } while (field.getState() != GameState.FAILED || field.getState() != GameState.SOLVED);
        if (field.getState() == GameState.SOLVED) {
            System.out.println("Win!");
            System.exit(0);
        } else {
            System.out.println("Game over.");
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
        System.out.println("Please enter your selection <X> EXIT, <MA1> MARK, <OB4> OPEN :");
    }

    /**
     * Processes user input.
     * Reads line from console and does the action on a playing field according to input string.
     */
    private void processInput() {
        String line = readLine();
        System.out.println(line);
        Pattern pattern =
                Pattern.compile("O([A-I])([0-8])", Pattern.CASE_INSENSITIVE);
        Matcher matcher =
                pattern.matcher(line);
        boolean matches = matcher.matches();
        do {
            pattern.matcher(line);
        } while (!matches || readLine() == null);

        if(line.equals("X")){
            System.out.println("Game over.");
            System.exit(1);
        }
        int row = matcher.group(1).charAt(0)-65;
        int column = Integer.parseInt(matcher.group(2));


//        if(line.equals("X")) {
//            System.out.println("Game over.");
//            System.exit(1 );
//        }
//        if(line.equals("M")){
//
//        }
//        int row = zadany char
//        int column zadane cislo
//        if(matcher.matches()){
            if(matcher.group(0).equals("O")){
                field.openTile();
        }
            if(matcher.group(1).equals("M")){
                field.markTile();
            }
    }
}
