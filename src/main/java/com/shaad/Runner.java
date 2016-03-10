package com.shaad;

import com.shaad.file.FileHandler;
import com.shaad.ui.MainForm;

public class Runner {
    public static void main(String[] args) {
        if (args.length != 0) {
            FileHandler fileHandler = new FileHandler();
            boolean parseResult = fileHandler.parseFile(args[0]);
            if (parseResult) {
                fileHandler.printComputedTable();
            }
        } else {
            new MainForm();
        }
    }
}
