package minesweeper;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

public class Settings {
    private final int rowCount, columnCount, mineCount;
    public static final Settings Beginner = new Settings(9, 9, 10);
    public static final Settings Intermediate = new Settings(16, 16, 40);
    public static final Settings Expert = new Settings(16, 30, 99);
    private static final String SETTING_FILE = new String(System.getProperty("user.home") + System.getProperty("file.separator") + "minesweeper.settings");

    public Settings(int rowCount, int columnCount, int mineCount) {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.mineCount = mineCount;
    }

    public void save() {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new File FileOutputStream);
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("");
        }
    }

    public void load() {

    }

    @Override
    public int hashCode() {
        return rowCount * columnCount * mineCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        return true;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getMineCount() {
        return mineCount;
    }
}
