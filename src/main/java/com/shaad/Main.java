package com.shaad;

import com.shaad.hierarchy.Cell;
import com.shaad.hierarchy.Expression;

public class Main {
    //todo: should be removed;
    public static String[][] tempTable = new String[26][10];

    public static void main(String[] args) {

//        System.out.println(new Expression("232/444-3*2").getResult());

        tempTable[1][0] = "1";
        tempTable[1][1] = "343";

        tempTable[0][0] = "120";
        tempTable[0][1] = "11";
        tempTable[0][2] = "=A1+A2";
        tempTable[0][3] = "=A1/A2*A1*A3";
        tempTable[0][4] = "5";
        tempTable[0][5] = "\'555";
        tempTable[0][6] = "=555+a1*b2+a3/A10";
        tempTable[0][7] = "\'aaa";
        tempTable[0][8] = "=A8";
        tempTable[0][9] = "=B1+B2";

        int i = 0;
        for (String str : tempTable[0]) {
            if (null != str) {
                System.out.println(new Cell(str).getComputedContent() + " " + i);
                i++;
            }
        }
        //System.out.println(new Cell(tempTable[0][8]).getComputedContent());
/*        System.out.println(new Cell(tempTable[0][6]).getComputedContent());
        System.out.println(new Cell(tempTable[0][7]).getComputedContent());*/

/*
        System.out.println(new Cell("=A1+B2").getComputedContent());
        System.out.println(new Cell("=A1").getComputedContent());
        System.out.println(new Cell("=5").getComputedContent());
        System.out.println(new Cell("=b223").getComputedContent());
        System.out.println(new Cell("=b223ff").getComputedContent());
        System.out.println(new Cell("=5b").getComputedContent());
*/

/*        new Expression("A1*A2");
        new Expression("A1");
        new Expression("A1+A2");
        new Expression("A1-A3");
        new Expression("A1/A3");
        new Expression("5/A3");
        new Expression("5");
        new Expression("12121");
        new Expression("12121*B3");*/


        //MainForm form = new MainForm();
    }
}
