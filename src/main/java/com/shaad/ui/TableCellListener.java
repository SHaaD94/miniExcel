package com.shaad.ui;

import com.shaad.entities.Cell;
import com.shaad.entities.TableHolder;

import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 *  This class used to detect cell changed event.
 */
public class TableCellListener implements PropertyChangeListener, Runnable {
    private TableHolder tableHolder = TableHolder.getInstance();
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
            tableHolder.getBackendTable()[row][column] = (String) newValue;
            refillTable();
        }
    }

    private void refillTable() {
        for (int i = 0; i < tableHolder.getTableRowCount(); i++) {
            for (int j = 0; j < tableHolder.getTableColumnCount(); j++) {
                Cell cell = new Cell(tableHolder.getBackendTable()[i][j]);
                if (null != cell.getContent() && !cell.getContent().equals(" ")) {
                    String cellValue = cell.getValue();
                    table.setValueAt(cellValue, i, j);
                }
            }
        }
    }

}
