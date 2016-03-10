package com.shaad.file;

import com.shaad.Runner;
import com.shaad.entities.Cell;
import com.shaad.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileHandler {
    private int rowCount = 0;
    private int columnCount = 0;

    public FileHandler() {

    }

    private String[] parseLine(String line) {
        //set correct array size;
        line = line.replaceAll("\t", " ");
        line = line.replaceAll("\\ +", " ");
        return line.split(" ");
    }

    public void parseFile(String filePath) {

        try {
            List<String> lineList = Files.readAllLines(Paths.get(filePath));
            String firstLine = lineList.get(0);
            lineList.remove(0);
            rowCount = Integer.parseInt(firstLine.substring(0, firstLine.indexOf(' ')));
            columnCount = Integer.parseInt(firstLine.substring(firstLine.indexOf(' ') + 1));

            int currentRow = 0;
            for (String str : lineList) {
                //todo: correct this filling
                String[] cells = parseLine(str);
                System.arraycopy(cells, 0, Runner.backendTable[currentRow], 0, cells.length);
                currentRow++;
            }
        } catch (IOException e) {
            //todo: proper logging
            e.printStackTrace();
        }
    }

    public void printComputedTable() {
        int maxCellLength = Util.getLargestCellLength(Runner.backendTable);
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                String outputCell = new Cell(Runner.backendTable[i][j]).getValue();
                while (outputCell.length() < maxCellLength) {
                    outputCell += " ";
                }
                System.out.print(outputCell + " ");
            }
            System.out.println();
        }
    }
}
