package com.shaad.table;

import com.shaad.Main;
import com.shaad.hierarchy.Cell;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.StringContent;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MainForm extends JFrame {

    public MainForm() {
        super("Excel");
        initializeTable();
        JPanel rootPanel = new JPanel();
        JTable mainTable = new JTable(26, 10);

        mainTable.putClientProperty("terminateEditOnFocusLost", true);

        setContentPane(rootPanel);
        rootPanel.add(mainTable);

        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
/*

        mainTable.setCellSelectionEnabled(false);
        ListSelectionModel cellSelectionModel = mainTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(e -> {
            System.out.println(mainTable.getSelectedColumn() + " " + mainTable.getSelectedRow());
*/
/*            String cellValue = (String) mainTable.getValueAt(mainTable.getSelectedRow(), mainTable.getSelectedColumn());
        Main.tempTable[mainTable.getSelectedRow()][mainTable.getSelectedColumn()] = cellValue;
        System.out.println(cellValue);
        Cell currentCell = new Cell(cellValue);
        mainTable.setValueAt(currentCell.getValue(), mainTable.getSelectedRow(), mainTable.getSelectedColumn());*//*

        });

        mainTable.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                System.out.println(1);
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                System.out.println(2);
            }
        });
        mainTable.getColumnModel().addColumnModelListener(new TableColumnModelListener() {
            @Override
            public void columnAdded(TableColumnModelEvent e) {

            }

            @Override
            public void columnRemoved(TableColumnModelEvent e) {

            }

            @Override
            public void columnMoved(TableColumnModelEvent e) {

            }

            @Override
            public void columnMarginChanged(ChangeEvent e) {

            }

            @Override
            public void columnSelectionChanged(ListSelectionEvent e) {
                System.out.println(mainTable.getSelectedColumn() + " " + mainTable.getSelectedRow());
            }
        });
*/

        TableCellListener cellListener = new TableCellListener(mainTable);
        setVisible(true);
    }

    private void initializeTable() {
        Main.tempTable = new String[26][10];
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 10; j++) {
                Main.tempTable[i][j] = "";
            }
        }

    }
}