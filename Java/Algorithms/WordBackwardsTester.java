import java.util.Scanner;

public class WordBackwardsTester
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("Enter a word: ");
        
        String word = input.nextLine();
        
        WordBackwards wordBackwards = new WordBackwards(word);
        
        wordBackwards.spellWordBackwards();
        
        System.out.println(wordBackwards);
    }
}