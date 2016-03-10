package com.shaad.entities;

/**
 * Class which holds main table.
 */
public class TableHolder {

    private static TableHolder instance;

    private TableHolder() {
    }

    public static synchronized TableHolder getInstance() {
        if (instance == null) {
            instance = new TableHolder();
        }
        return instance;
    }

    private int tableColumnCount = 26;
    private int tableRowCount = 25;
    private String[][] backendTable;

    public void initializeTable() {
        backendTable = new String[tableRowCount][tableColumnCount];
    }

    public int getTableColumnCount() {
        return tableColumnCount;
    }

    public void setTableColumnCount(int tableColumnCount) {
        this.tableColumnCount = tableColumnCount;
    }

    public int getTableRowCount() {
        return tableRowCount;
    }

    public void setTableRowCount(int tableRowCount) {
        this.tableRowCount = tableRowCount;
    }

    /**
     * Get backend table.
     * <p>
     * breaking encapsulation, because only one
     * instance of this array should exist
     *
     * @return backend table
     */
    public String[][] getBackendTable() {
        return backendTable;
    }
}
