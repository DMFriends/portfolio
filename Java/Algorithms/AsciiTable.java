import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Written by CodeConvert AI from converting my own code written in Python into Java
// https://www.codeconvert.ai/app
public class AsciiTable
{
    public static String makeTable(List<List<Object>> rows, List<Object> labels, boolean centered) {
        String returnValue = "";
        List<Integer> columnLengths = new ArrayList<>();
        
        for (int i = 0; i < rows.size(); i++) {
            List<Object> row = rows.get(i);
            for (int j = 0; j < row.size(); j++) {
                int currentLength = String.valueOf(row.get(j)).length() + 2;
                if (i == 0) {
                    columnLengths.add(currentLength);
                } else if (currentLength > columnLengths.get(j)) {
                    columnLengths.set(j, currentLength);
                }
            }
        }
        
        if (labels != null) {
            for (int j = 0; j < labels.size(); j++) {
                int currentLength = String.valueOf(labels.get(j)).length() + 2;
                if (currentLength > columnLengths.get(j)) {
                    columnLengths.set(j, currentLength);
                }
            }
        }
        
        returnValue += makeTopBorder(columnLengths);
        
        if (labels != null) {
            returnValue += "\n" + makeRow(labels, columnLengths, centered);
            
            returnValue += "\n" + makeSeparator(columnLengths);
        }
        
        for (int i = 0; i < rows.size(); i++) {
            returnValue += "\n" + makeRow(rows.get(i), columnLengths, centered);
            if (i == rows.size() - 1) {
                returnValue += "\n" + makeBottomBorder(columnLengths);
            } else {
                returnValue += "\n" + makeSeparator(columnLengths);
            }
        }
        return returnValue;
    }
    
    public static String makeRow(List<Object> row, List<Integer> columnLengths, boolean centered) {
        String returnValue = "│";
        for (int i = 0; i < row.size(); i++) {
            if (centered) {
                int spacesLeft = (columnLengths.get(i) - String.valueOf(row.get(i)).length()) / 2;
                int spacesRight = columnLengths.get(i) - String.valueOf(row.get(i)).length() - spacesLeft;
                returnValue += String.join("", Collections.nCopies(spacesLeft, " "));
                returnValue += row.get(i);
                returnValue += String.join("", Collections.nCopies(spacesRight, " "));
            } else {
                int spacesLeft = 1;
                int spacesRight = columnLengths.get(i) - String.valueOf(row.get(i)).length() - 1;
                returnValue += String.join("", Collections.nCopies(spacesLeft, " "));
                returnValue += row.get(i);
                returnValue += String.join("", Collections.nCopies(spacesRight, " "));
            }
            returnValue += "│";
        }
        return returnValue;
    }
    
    public static String makeTopBorder(List<Integer> columnLengths) {
        String returnValue = "┌";
        for (int i = 0; i < columnLengths.size(); i++) {
            for (int j = 0; j < columnLengths.get(i); j++) {
                returnValue += "─";
            }
            if (i == columnLengths.size() - 1) {
                returnValue += "┐";
            } else {
                returnValue += "┬";
            }
        }
        return returnValue;
    }
    
    public static String makeSeparator(List<Integer> columnLengths) {
        String returnValue = "├";
        for (int i = 0; i < columnLengths.size(); i++) {
            for (int j = 0; j < columnLengths.get(i); j++) {
                returnValue += "─";
            }
            if (i == columnLengths.size() - 1) {
                returnValue += "┤";
            } else {
                returnValue += "┼";
            }
        }
        return returnValue;
    }
    
    public static String makeBottomBorder(List<Integer> columnLengths) {
        String returnValue = "└";
        for (int i = 0; i < columnLengths.size(); i++) {
            for (int j = 0; j < columnLengths.get(i); j++) {
                returnValue += "─";
            }
            if (i == columnLengths.size() - 1) {
                returnValue += "┘";
            } else {
                returnValue += "┴";
            }
        }
        return returnValue;
    }
}