package com.shaad;

import com.shaad.file.FileHandler;
import com.shaad.ui.MainForm;

public class Runner {
    public static final int TABLE_ROW_COUNT = 26;
    public static final int TABLE_COLUMN_COUNT = 26;
    public static String[][] backendTable;

    private static void initializeTable() {
        backendTable = new String[TABLE_ROW_COUNT][TABLE_COLUMN_COUNT];
        for (int i = 0; i < TABLE_ROW_COUNT; i++) {
            for (int j = 0; j < TABLE_COLUMN_COUNT; j++) {
                backendTable[i][j] = "";
            }
        }
    }

    public static void main(String[] args) {
        initializeTable();
        FileHandler fileHandler = new FileHandler();
        fileHandler.parseFile("D:/git/excel/test.txt");
        fileHandler.printComputedTable();
        //MainForm form = new MainForm();
    }
}
