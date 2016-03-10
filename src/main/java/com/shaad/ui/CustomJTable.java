package com.shaad.ui;

import com.shaad.entities.TableHolder;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;
import java.awt.Component;

public class CustomJTable extends JTable {
    public CustomJTable(int numRows, int numColumns) {
        super(numRows, numColumns);
    }

    @Override
    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        Component c = super.prepareEditor(editor, row, column);
        if (c instanceof JTextComponent) {
            ((JTextComponent) c).setText(TableHolder.getInstance().getBackendTable()[row][column]);
        }
        return c;
    }
}
