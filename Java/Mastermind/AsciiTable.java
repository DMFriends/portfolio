public class AsciiTable
{
    private static int[] columnLengths = {28, 28, 30};
    
    private static String[] labels = {"Previous Guesses", "Colors in Correct Position", "Colors in Incorrect Position"};
    
    public static String makeTable(String[] guessHistory, int[] correctPosHistory, int[] incorrectPosHistory, int guessCounter)
    {
        String returnValue = "";
        
        returnValue += makeTopBorder();
        
        returnValue += "\n" + makeLabels();
            
        returnValue += "\n" + makeSeparator();
        
        for (int i = 0; i < guessCounter; i++) {
            returnValue += "\n" + makeRow(guessHistory[i], correctPosHistory[i], incorrectPosHistory[i]);
            if (i == guessCounter - 1) {
                returnValue += "\n" + makeBottomBorder();
            } else {
                returnValue += "\n" + makeSeparator();
            }
        }
        
        return returnValue;
    }
    
    public static String makeRow(String guess, int correctPosCount, int incorrectPosCount)
    {
        String returnValue = "│";
        
        returnValue += makeCell(guess, 0);
        returnValue += makeCell(Integer.toString(correctPosCount), 1);
        returnValue += makeCell(Integer.toString(incorrectPosCount), 2);
        
        return returnValue;
    }
    
    public static String makeTopBorder()
    {
        String returnValue = "┌";
        for (int i = 0; i < columnLengths.length; i++) {
            for (int j = 0; j < columnLengths[i]; j++) {
                returnValue += "─";
            }
            if (i == columnLengths.length - 1) {
                returnValue += "┐";
            } else {
                returnValue += "┬";
            }
        }
        
        return returnValue;
    }
    
    public static String makeSeparator()
    {
        String returnValue = "├";
        for (int i = 0; i < columnLengths.length; i++) {
            for (int j = 0; j < columnLengths[i]; j++) {
                returnValue += "─";
            }
            if (i == columnLengths.length - 1) {
                returnValue += "┤";
            } else {
                returnValue += "┼";
            }
        }
        
        return returnValue;
    }
    
    public static String makeBottomBorder()
    {
        String returnValue = "└";
        for (int i = 0; i < columnLengths.length; i++) {
            for (int j = 0; j < columnLengths[i]; j++) {
                returnValue += "─";
            }
            if (i == columnLengths.length - 1) {
                returnValue += "┘";
            } else {
                returnValue += "┴";
            }
        }
        
        return returnValue;
    }
    
    public static String makeLabels()
    {
        String returnValue = "│";
        
        for(int i = 0; i < labels.length; i++)
        {
            returnValue += makeCell(labels[i], i);
        }
        
        return returnValue;
    }
    
    public static String makeCell(String value, int column)
    {
        String returnValue = "";
        
        int spacesLeft = (columnLengths[column] - value.length()) / 2;;
        int spacesRight = columnLengths[column] - value.length() - spacesLeft;
        
        returnValue += repeatString(" ", spacesLeft);
        returnValue += value;
        returnValue += repeatString(" ", spacesRight);
        returnValue += "│";
        
        return returnValue;
    }
    
    public static String repeatString(String value, int n)
    {
        String returnValue = "";
        
        for(int i = 0; i < n; i++)
        {
            returnValue += value;
        }
        
        return returnValue;
    }
}