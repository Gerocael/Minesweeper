package minesweeper;

import org.w3c.dom.ls.LSOutput;

import java.io.*;
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
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(
                    new FileOutputStream(SETTING_FILE));
            oos.writeObject(this);
        } catch (IOException e) {
            System.out.println("Unable to write settings into the object.");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public static Settings load() {
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(
                    new FileInputStream(SETTING_FILE));
            Settings s = (Settings) ois.readObject();
            return s;
        } catch (IOException e) {
            System.out.println("Cannot open Settings doc. Default = Beginner.");
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot read Settings doc. Default = Beginner.");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                }
            }
        }
        return Beginner;
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
