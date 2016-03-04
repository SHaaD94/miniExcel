package com.shaad;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class MainForm extends JFrame {

    public MainForm() {
        super("Excel");
        JPanel rootPanel = new JPanel();
        JTable mainTable = new JTable(10, 10);

        setContentPane(rootPanel);
        rootPanel.add(mainTable);

        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainTable.setCellSelectionEnabled(true);
        ListSelectionModel cellSelectionModel = mainTable.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        mainTable.addPropertyChangeListener(evt -> System.out.println("changed"));

        cellSelectionModel.addListSelectionListener(e -> {
            String selectedData = null;

            int[] selectedRow = mainTable.getSelectedRows();
            int[] selectedColumns = mainTable.getSelectedColumns();

            for (int aSelectedRow : selectedRow) {
                for (int selectedColumn : selectedColumns) {
                    selectedData = (String) mainTable.getValueAt(aSelectedRow, selectedColumn);
                }
            }
            System.out.println("Selected: " + selectedData);
        });

        setVisible(true);
    }
}