package com.shaad.table;

import com.shaad.Main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class MainForm extends JFrame {
    private final TableCellListener cellListener;

    public MainForm() {
        super("Excel");
        JPanel rootPanel = new JPanel();
        JTable mainTable = new JTable(Main.TABLE_ROW_COUNT, Main.TABLE_COLUMN_COUNT);

        mainTable.putClientProperty("terminateEditOnFocusLost", true);

        setContentPane(rootPanel);
        rootPanel.add(new JScrollPane(mainTable));

        pack();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cellListener = new TableCellListener(mainTable);
        setVisible(true);
    }
}