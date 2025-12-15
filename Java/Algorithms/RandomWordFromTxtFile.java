import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class RandomWordFromTxtFile
{
    // Populates an array of Strings with the contents of a text file
    public static String[] txtFileToArr(Scanner file)
    {
        ArrayList<String> arrList = new ArrayList<String>();
        
        while(file.hasNextLine())
        {
            arrList.add(file.nextLine());
        }
        
        String[] arr = new String[arrList.size()];
        
        for(int i = 0; i < arr.length; i++)
        {
            arr[i] = arrList.get(i);
        }
        
        return arr;
    }
    
    // Generates a random word from a text file to use for the game
    public static String randomWord(String[] arr)
    {
        int randInt = (int)(Math.random() * arr.length);
        
        return arr[randInt];
    }
}