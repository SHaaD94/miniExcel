package com.shaad.ui;

import com.shaad.Main;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;
import java.awt.Component;

public class MyJTable extends JTable {
    public MyJTable(int numRows, int numColumns) {
        super(numRows, numColumns);
    }

    @Override
    public Component prepareEditor(TableCellEditor editor, int row, int column) {
        Component c = super.prepareEditor(editor, row, column);
        if (c instanceof JTextComponent) {
            ((JTextComponent) c).setText(Main.backendTable[row][column]);
        }
        return c;
    }
}
