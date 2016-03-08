package com.shaad.ui;

import com.shaad.Main;
import com.shaad.hierarchy.Cell;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 *  This class listens for changes made to the data in the table via the
 *  TableCellEditor. When editing is started, the value of the cell is saved
 *  When editing is stopped the new value is saved. When the oold and new
 *  values are different, then the provided Action is invoked.
 *
 *  The source of the Action is a TableCellListener instance.
 */
public class TableCellListener implements PropertyChangeListener, Runnable {
    private JTable table;
    private int row;
    private int column;
    private Object oldValue;
    private Object newValue;

    /**
     * Create a TableCellListener.
     *
     * @param table the ui to be monitored for data changes
     */
    public TableCellListener(JTable table) {
        this.table = table;
        this.table.addPropertyChangeListener(this);
    }

    /**
     * Get the column that was last edited
     *
     * @return the column that was edited
     */
    public int getColumn() {
        return column;
    }

    /**
     * Get the new value in the cell
     *
     * @return the new value in the cell
     */
    public Object getNewValue() {
        return newValue;
    }

    /**
     * Get the old value of the cell
     *
     * @return the old value of the cell
     */
    public Object getOldValue() {
        return oldValue;
    }

    /**
     * Get the row that was last edited
     *
     * @return the row that was edited
     */
    public int getRow() {
        return row;
    }

    /**
     * Get the table of the cell that was changed
     *
     * @return the table of the cell that was changed
     */
    public JTable getTable() {
        return table;
    }

    //
//  Implement the PropertyChangeListener interface
//
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        if ("tableCellEditor".equals(e.getPropertyName())) {
            if (table.isEditing())
                processEditingStarted();
            else
                processEditingStopped();
        }
    }

    private void processEditingStarted() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        row = table.convertRowIndexToModel(table.getEditingRow());
        column = table.convertColumnIndexToModel(table.getEditingColumn());
        oldValue = table.getModel().getValueAt(row, column);
        newValue = null;
    }


    private void processEditingStopped() {
        newValue = table.getModel().getValueAt(row, column);

        if (null != newValue && !newValue.equals(oldValue)) {
            Main.backendTable[row][column] = (String) newValue;
            refillTable();
        }
    }

    private void refillTable() {
        for (int i = 0; i < Main.TABLE_ROW_COUNT; i++) {
            for (int j = 0; j < Main.TABLE_COLUMN_COUNT; j++) {
                Cell cell = new Cell(Main.backendTable[i][j]);
                if (null != cell.getContent() && !cell.getContent().equals(" ")) {
                    String cellValue = cell.getValue();
                    table.setValueAt(cellValue, i, j);
                }
            }
        }
    }

}
