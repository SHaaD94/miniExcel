package com.shaad.ui;

import com.shaad.Runner;
import com.shaad.entities.Cell;

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
            Runner.backendTable[row][column] = (String) newValue;
            refillTable();
        }
    }

    private void refillTable() {
        for (int i = 0; i < Runner.TABLE_ROW_COUNT; i++) {
            for (int j = 0; j < Runner.TABLE_COLUMN_COUNT; j++) {
                Cell cell = new Cell(Runner.backendTable[i][j]);
                if (null != cell.getContent() && !cell.getContent().equals(" ")) {
                    String cellValue = cell.getValue();
                    table.setValueAt(cellValue, i, j);
                }
            }
        }
    }

}
