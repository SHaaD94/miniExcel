package com.shaad;

import com.shaad.file.FileHandler;
import com.shaad.ui.MainForm;

public class Runner {
    /**
     * Always equals alphabet length.
     */
    public static final int TABLE_COLUMN_COUNT = 26;
    /**
     * Changes if used through console.
     */
    public static int TABLE_ROW_COUNT = 25;
    //todo: try to find better place for it;
    public static String[][] backendTable;

    public static void initializeTable() {
        backendTable = new String[TABLE_ROW_COUNT][TABLE_COLUMN_COUNT];
        for (int i = 0; i < TABLE_ROW_COUNT; i++) {
            for (int j = 0; j < TABLE_COLUMN_COUNT; j++) {
                backendTable[i][j] = "";
            }
        }
    }

    public static void main(String[] args) {
        initializeTable();
        if (args.length != 0) {
            FileHandler fileHandler = new FileHandler();
            fileHandler.parseFile(args[0]);
            fileHandler.printComputedTable();
        } else {
            new MainForm();
        }
    }
}
