package com.shaad.file;

import com.shaad.entities.Cell;
import com.shaad.entities.TableHolder;
import com.shaad.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {
    private TableHolder tableHolder = TableHolder.getInstance();
    private Logger log = Logger.getLogger(FileHandler.class.getName());

    private int rowCount = 0;
    private int columnCount = 0;

    public FileHandler() {

    }

    private String[] parseLine(String line) {
        line = line.replaceAll("\t", " ");
        line = line.replaceAll(" +", " ");
        return line.split(" ");
    }

    public void parseFile(String filePath) {
        try {
            List<String> lineList = Files.readAllLines(Paths.get(filePath));
            if (lineList.isEmpty()) {
                System.out.println("File is empty");
                return;
            }
            String[] tableSize = parseLine(lineList.get(0));
            lineList.remove(0);
            if (tableSize.length < 2) {
                System.out.println("Wrong table size parameters");
                return;
            }
            try {
                rowCount = Integer.parseInt(tableSize[0]);
                columnCount = Integer.parseInt(tableSize[1]);
            } catch (NumberFormatException e) {
                System.out.println("Wrong table size parameters");
                return;
            }

            if (rowCount <= 0 || columnCount <= 0) {
                System.out.println("Wrong table size parameters");
                return;
            }

            if (columnCount > Util.getAlphabetLength()) {
                System.out.println("Too big column count");
                return;
            }

            tableHolder.setTableRowCount(rowCount);
            tableHolder.setTableColumnCount(columnCount);
            tableHolder.initializeTable();

            int currentRow = 0;
            for (String str : lineList) {
                if (currentRow > rowCount) {
                    break;
                }
                String[] cells = parseLine(str);
                System.arraycopy(cells, 0, tableHolder.getBackendTable()[currentRow],
                        0, ((cells.length >= columnCount) ? columnCount : cells.length));
                currentRow++;
            }
        } catch (NoSuchFileException e) {
            System.out.println("No such file");
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error parsing file", e);
        }
    }

    public void printComputedTable() {
        //compute
        String[][] computedTable = new String[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                computedTable[i][j] = new Cell(tableHolder.getBackendTable()[i][j]).getValue();
            }
        }

        int maxCellLength = Util.getLargestCellLength(computedTable);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String outputCell = computedTable[i][j];
                //format
                while (outputCell.length() < maxCellLength) {
                    outputCell += " ";
                }
                System.out.print(outputCell + " ");
            }
            System.out.println();
        }
    }
}
