package com.shaad.entities;

/**
 * Class which holds main table.
 */
public class TableHolder {
    /**
     * Changes if used through console.
     */
    public static int tableColumnCount = 26;
    /**
     * Changes if used through console.
     */
    public static int tableRowCount = 25;

    public static String[][] backendTable;

    public static void initializeTable() {
        backendTable = new String[tableRowCount][tableColumnCount];
        for (int i = 0; i < tableRowCount; i++) {
            for (int j = 0; j < tableColumnCount; j++) {
                backendTable[i][j] = "";
            }
        }
    }
}
