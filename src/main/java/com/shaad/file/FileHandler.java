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

/**
 * Used to work with files.
 */
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

    public boolean parseFile(String filePath) {
        try {
            List<String> lineList = Files.readAllLines(Paths.get(filePath));
            if (lineList.isEmpty()) {
                System.out.println("File is empty");
                return false;
            }
            String[] tableSize = parseLine(lineList.get(0));
            lineList.remove(0);
            if (tableSize.length != 2) {
                System.out.println("Wrong table size parameters");
                return false;
            }
            try {
                rowCount = Integer.parseInt(tableSize[0]);
                columnCount = Integer.parseInt(tableSize[1]);
            } catch (NumberFormatException e) {
                System.out.println("Wrong table size parameters");
                return false;
            }

            if (rowCount <= 0 || columnCount <= 0) {
                System.out.println("Wrong table size parameters");
                return false;
            }

            if (columnCount > Util.getAlphabetLength()) {
                System.out.println("Too big column count");
                return false;
            }

            tableHolder.setTableRowCount(rowCount);
            tableHolder.setTableColumnCount(columnCount);
            tableHolder.initializeTable();

            int currentRow = 0;
            for (String str : lineList) {
                if (currentRow >= rowCount) {
                    break;
                }
                String[] cells = parseLine(str);
                System.arraycopy(cells, 0, tableHolder.getBackendTable()[currentRow],
                        0, ((cells.length >= columnCount) ? columnCount : cells.length));
                currentRow++;
            }
        } catch (NoSuchFileException e) {
            System.out.println("No such file");
            return false;
        } catch (IOException e) {
            log.log(Level.SEVERE, "Error parsing file", e);
            return false;
        }
        return true;
    }

    public void printComputedTable() {
        String[][] computedTable = new String[rowCount][columnCount];
        int[] maxLengthInColumn = new int[columnCount];
        //compute
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                Cell currentCell = new Cell(tableHolder.getBackendTable()[i][j]);
                TableHolder.getInstance().getBackendTableMap().put(i + "." + j, currentCell);
                computedTable[i][j] = currentCell.getValue();
            }
        }
        //get max length in column
        for (int i = 0; i < columnCount; i++) {
            maxLengthInColumn[i] = 0;
            for (int j = 0; j < rowCount; j++) {
                if (computedTable[j][i].length() > maxLengthInColumn[i]) {
                    maxLengthInColumn[i] = computedTable[j][i].length();
                }
            }
        }

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String outputCell = computedTable[i][j];
                //format
                while (outputCell.length() < maxLengthInColumn[j]) {
                    outputCell += " ";
                }
                System.out.print(outputCell + "  ");
            }
            System.out.println();
        }
    }
}
