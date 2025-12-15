import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Hangman
{
    private static ArrayList<String> randomWords = new ArrayList<String>();
    
    private static ArrayList<String> guesses = new ArrayList<String>();
    
    private static ArrayList<String> wordList = new ArrayList<String>();
    
    private static int tries = 12;
    
    private static String board = "";
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        try
        {
            Scanner words = new Scanner(new File("hangman_words.txt"));
            while(words.hasNextLine())
            {
                randomWords.add(words.nextLine().trim());
            }
            words.close();
        }
        catch(Exception e)
        {
            System.out.println("Error reading or parsing hangman_words.txt");
        }
        
        String word = randomWord();
        
        for(int i = 0; i < word.length(); i++)
        {
            board += "_";
            wordList.add(word.substring(i, i + 1));
        }
        
        while(board.indexOf("_") != -1)
        {
            System.out.println("Guesses left: " + tries);
            
            System.out.println(board.replace("", " ").trim() + "\n");
            
            System.out.println("Letters already guessed: " + guesses + "\n");
    
            System.out.print("Guess a letter: ");
            String guess = input.nextLine();
            
            System.out.println();
            
            if(guess.length() > 1  || Character.isUpperCase(guess.charAt(0)))
            {
                System.out.println("Sorry, you entered an invalid guess. Please try again.\n");
                continue;
            }
            
            if(guesses.contains(guess))
            {
                System.out.println("You have already guessed that letter. Please try again.\n");
                continue;    
            }
            
            checkGuess(guess, word);
            
            if(tries == 0)
            {
                System.out.println("You lost. The word was " + "'" + word + "'.");
                break;
            }
            
            if(tries != 0 && board.indexOf("_") == -1)
            {
                System.out.println(board.replace("", " ").trim());
                System.out.println("You guessed the word!");
            }
        }

        input.close();
    }
    
    public static String randomWord()
    {
        int index = (int)(Math.random() * randomWords.size());
        return randomWords.get(index);
    }
    
    public static void checkGuess(String guess, String word)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(guess.equals(word.substring(i, i + 1)))
            {
                if(!guesses.contains(guess))
                {
                    guesses.add(guess);
                }
                board = board.substring(0, i) + guess + board.substring(i + 1);
            }
        }
        
        if(!wordList.contains(guess))
        {
            tries--;
            System.out.println("'" + guess + "' is wrong");
            if(!guesses.contains(guess))
            {
                guesses.add(guess);
            }
        }
    }
}