package com.shaad.ui;

import com.shaad.entities.TableHolder;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class MainForm extends JFrame {
    private final TableCellListener cellListener;

    public MainForm() {
        super("Excel");

        JPanel rootPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTable mainTable = new CustomJTable(TableHolder.getInstance().getTableRowCount(),
                TableHolder.getInstance().getTableColumnCount());
        TableHolder.getInstance().initializeTable();

        mainTable.putClientProperty("terminateEditOnFocusLost", true);

        setPreferredSize(new Dimension(700, 400));
        setMinimumSize(new Dimension(700, 400));
        setContentPane(rootPanel);

        JScrollPane scrollPane = new JScrollPane(mainTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        scrollPane.setPreferredSize(new Dimension(2000, 350));
        rootPanel.add(scrollPane);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cellListener = new TableCellListener(mainTable);
        setVisible(true);
    }
}