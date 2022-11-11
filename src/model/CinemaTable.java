package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a cinema table. This class is immutable.
 * It is used to print the cinema table.
 * @version 1.0
 * @since 06 Nov 2022
 */

public class CinemaTable {

    /**
     * This is to set the layout for console's table
     */
	private static final String HORIZONTAL_SEP = "-";
    /**
     * This is the vertical separator for console's table
     */
    private String verticalSep;
    /**
     * This is to join the separators
     */
    private String joinSep;
    /**
     * This is the header of the table
     */
    private String[] headers;
    /**
     * This is the data of the table
     */
    private List<String[]> rows = new ArrayList<>();
    /**
     * This is just to set alighment for the table
     */
    private boolean rightAlign;

    /**
     * This is the constructor of the class
     */
    public CinemaTable() {
        setShowVerticalLines(false);
    }

    /**
     * This is to set right alignment for the table
     * @param rightAlign The right alignment to set
     */
    public void setRightAlign(boolean rightAlign) {
        this.rightAlign = rightAlign;
    }

    /**
     * This is to set the vertical separator for the table
     * @param showVerticalLines The vertical separator to set
     */
    public void setShowVerticalLines(boolean showVerticalLines) {
        verticalSep = showVerticalLines ? "|" : "";
        joinSep = showVerticalLines ? "+" : " ";
    }

    /**
     * This is to set the header for the table
     * @param headers The header to set
     */
    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    /**
     * This is to add rows to the table
     * @param cells The cells to add
     */
    public void addRow(String... cells) {
        rows.add(cells);
    }

    /**
     * This is to print the table
     */
    public void print() {
        int[] maxWidths = headers != null ?
                Arrays.stream(headers).mapToInt(String::length).toArray() : null;

        for (String[] cells : rows) {
            if (maxWidths == null) {
                maxWidths = new int[cells.length];
            }
            if (cells.length != maxWidths.length) {
                throw new IllegalArgumentException("Number of row-cells and headers should be consistent");
            }
            for (int i = 0; i < cells.length; i++) {
                maxWidths[i] = Math.max(maxWidths[i], cells[i].length());
            }
        }

        if (headers != null) {
            printLine(maxWidths);
            printRow(headers, maxWidths);
            printLine(maxWidths);
        }
        for (String[] cells : rows) {
            printRow(cells, maxWidths);
        }
        if (headers != null) {
            printLine(maxWidths);
        }
    }

    /**
     * This is to print each line of the table
     * @param columnWidths
     */
    private void printLine(int[] columnWidths) {
        for (int i = 0; i < columnWidths.length; i++) {
            String line = String.join("", Collections.nCopies(columnWidths[i] +
                    verticalSep.length() + 1, HORIZONTAL_SEP));
            System.out.print(joinSep + line + (i == columnWidths.length - 1 ? joinSep : ""));
        }
        System.out.println();
    }

    /**
     * This is to print each row of the table
     * @param cells
     * @param maxWidths
     */
    private void printRow(String[] cells, int[] maxWidths) {
        for (int i = 0; i < cells.length; i++) {
            String s = cells[i];
            String verStrTemp = i == cells.length - 1 ? verticalSep : "";
            if (rightAlign) {
                System.out.printf("%s %" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            } else {
                System.out.printf("%s %-" + maxWidths[i] + "s %s", verticalSep, s, verStrTemp);
            }
        }
        System.out.println();
    }

 
}
