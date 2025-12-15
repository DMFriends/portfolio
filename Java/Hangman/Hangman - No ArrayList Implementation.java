import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

public class Hangman
{   
    private static int triesLeft = 12;
    
    private static int numGuesses = 0;
    
    private static String[] guesses = new String[26]; // all letters in alphabet
    
    private static String[] wordList = null;
    
    private static String board = "";
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        String word = "";
        
        try
        {
            Scanner randomWords = new Scanner(new File("hangman_words.txt"));
            
            String[] randWordsArr = txtFileToArr(randomWords);
            
            word = randomWord(randWordsArr);
        }
        catch(Exception e)
        {
            System.out.println("Error reading or parsing hangman_words.txt\n");
            e.printStackTrace();
        }
        
        wordList = word.split("", word.length());
        
        for(int i = 0; i < word.length(); i++)
        {
            board += "_";
        }
        
        // FOR DEBUGGING ONLY
        //System.out.println(word);
        
        while(board.indexOf("_") != -1)
        {
            System.out.println("Guesses left: " + triesLeft);
            
            System.out.println(board.replace("", " ").trim() + "\n");
            
            System.out.print("Letters already guessed: ");
            System.out.print(printArr(guesses));
            System.out.print("\n");
            
            System.out.print("Guess a letter: ");
            String guess = input.nextLine();
            
            System.out.println();
            
            if(guess.length() > 1  || Character.isUpperCase(guess.charAt(0)))
            {
                System.out.println("Sorry, you entered an invalid guess. Please try again.\n");
                continue;
            }
            
            if(contains(guess, guesses))
            {
                System.out.println("You have already guessed '" + guess + "'. Please try again.\n");
                continue;
            }
            
            checkGuess(guess, word);
            
            if(triesLeft == 0)
            {
                System.out.println("You lost. The word was " + "'" + word + "'.");
                break;
            }
            
            if(triesLeft != 0 && board.indexOf("_") == -1)
            {
                System.out.println(board.replace("", " ").trim());
                System.out.println("You guessed the word!");
            }
        }
    }
    
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
    
    // Validates each guess entered by the user
    public static void checkGuess(String guess, String word)
    {
        // Correct guess
        for(int i = 0; i < word.length(); i++)
        {
            if(guess.equals(word.substring(i, i + 1)))
            {
                if(!contains(guess, guesses))
                {
                    guesses[numGuesses] = guess;
                }
                board = board.substring(0, i) + guess + board.substring(i + 1);
            }
        }
        
        // Incorrect guess
        if(!contains(guess, wordList))
        {
            System.out.println("'" + guess + "' is wrong\n");
            if(!contains(guess, guesses))
            {
                guesses[numGuesses] = guess;
            }
            triesLeft--;
        }
        
        numGuesses++;
    }
    
    // Checks if a given string exists in a given array of strings
    public static boolean contains(String item, String[] arr)
    {
        boolean result = false;
        
        for(String str : arr)
        {
            if(str != null && str.equals(item))
            {
                result = true;
            }
        }
        
        return result;
    }
    
    // Prints a String array without the null values
    public static String printArr(String[] arr)
    {
        String result = "[";
        
        for(int i = 0; i < arr.length; i++)
        {
            if(i == 0 && arr[i] != null)
            {
                result += arr[i];
            }
            else if(i != 0 && arr[i] != null)
            {
                result += ", " + arr[i];
            }
        }
        
        result += "]";
        
        return result;
    }
}