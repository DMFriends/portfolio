import java.util.Scanner;

public class Mastermind
{
    public static void main(String[] args)
    {
        // This array stores the colors used for the computer-chosen combination
        String[] validColors = {"Red", "Green", "Blue", "Yellow", "White", "Black"};
        
        while(true)
        {
            System.out.println("Generating game...");
            
            try
            {
                Thread.sleep(3000);
                System.out.print("\033[H\033[2J");
            }
            catch (InterruptedException e)
            {
                
            }
            
            Scanner input = new Scanner(System.in);
            
            // This array stores the computer-chosen combination that the user has to guess
            // within 10 tries
            String[] combination = new String[4];
            
            // This array stores all of the user's guesses
            String[] guessHistory = new String[10];
            
            // This array stores the number of colors guessed correctly but in the
            // wrong spot for each guess
            int[] incorrectPosHistory = new int[10];
            
            // This array stores the number of colors guessed correctly for each guess
            int[] correctPosHistory = new int[10];

            // Loop to populate the combination array with the computer-chosen combination
            for(int i = 0; i < combination.length; i++)
            {
                while(combination[i] == null)
                {
                    int randInt = (int)(Math.random() * 6);
                    if(!contains(validColors[randInt], combination))
                    {
                        combination[i] = validColors[randInt];
                    }
                }
            }
            
            String combinationStr = arrToString(combination);
            
            String guess = "";
            
            int guessCounter = 0;
            
            int errorCounter = 0;
            
            // Print the valid colors to the console so the player knows which colors to
            // choose from
            System.out.println("Here are the colors you can choose from for your guesses:");
            for(int i = 0; i < validColors.length; i++)
            {
                if(i == validColors.length - 1)
                {
                    System.out.println(validColors[i] + "\n");
                }
                else
                {
                    System.out.print(validColors[i] + ", ");
                }
            }
        
            System.out.println("Note: No colors can repeat.\n");
            
            // FOR DEBUGGING ONLY
            //System.out.println(combinationStr + "\n");
        
            // MAIN GAME LOOP
            while(!combinationStr.equals(guess))
            {
                // User input to enter their guess
                System.out.println("Try to guess the combination. Type the names of each color in order with each starting letter capitalized and separate each color with a space: \nType \"quit\" to quit.");
                guess = input.nextLine();
                
                if(guess.toLowerCase().equals("quit"))
                {
                    System.out.println("\nThe combination was \"" + combinationStr + "\".");
                    System.exit(0);
                }
                
                String[] guessArr = guess.split(" ", 4);
                
                boolean isValid = true;
    
                boolean duplicates = false;
                
                boolean restart = false;
                
                // --- Guess validation ---
                for(int i = 0; i < guessArr.length; i++)
                {
                    if(guessArr[i] != null && !contains(guessArr[i], validColors))
                    {
                        errorCounter++;
                        isValid = false;
                        System.out.print("\nInvalid combination.");
                        if(errorCounter != 10)
                        {
                            System.out.println(" Please try again.\n");
                        }
                        break;
                    }
                }
                
                for(int i = 0; i < guessArr.length; i++)
                {
                    for(int j = i + 1; j < guessArr.length; j++)
                    {
                        if(guessArr[i] != null && guessArr[j] != null && j != i && guessArr[j].equals(guessArr[i]))
                        {
                            errorCounter++;
                            duplicates = true;
                            System.out.print("\nYou entered a combination with duplicate colors.");
                            if(errorCounter != 10)
                            {
                                System.out.println(" Please try again.\n");
                            }
                            break;
                        }
                    }
                    
                    break;
                }
                
                if(isValid && !duplicates)
                {
                    errorCounter = 0;
                    guessHistory[guessCounter] = guess;
                }
                
                if(errorCounter == 10)
                {
                    System.out.println("\n\nSorry, it seems you are having trouble. Would you like to restart? (true/false)");
                    restart = input.nextBoolean();
                    if(!restart)
                    {
                        System.exit(0);
                    }
                    else
                    {
                        System.out.print("\033[H\033[2J");
                        break;
                    }
                }
                
                if(errorCounter > 0)
                {
                    continue;
                }
                // --- Guess validation ---
                
                // Validates whether or not the combination inputted by the codebreaker matches the code created by the computer (codemaker)
                if(combinationStr.equals(guess))
                {
                    System.out.println("\nCongratulations! You have guessed the combination!\nWould you like to play again? (true/false)");
                    restart = input.nextBoolean();
                    if(!restart)
                    {
                        System.exit(0);
                    }
                    else
                    {
                        System.out.print("\033[H\033[2J");
                        break;
                    }
                }
                
                System.out.println("\nThat is not the correct combination.");
                
                // --- Checking for correct/incorrect colors in guess ---
                int correctColors = 0;
                
                for(int i = 0; i < guessArr.length; i++)
                {
                    if(!guessArr[i].equals(combination[i]) && contains(guessArr[i], combination))
                    {
                        correctColors++;
                    }
                }
                
                incorrectPosHistory[guessCounter] = correctColors;
                
                int colorsInCorrectSpot = 0;
                
                for(int i = 0; i < guessArr.length; i++)
                {
                    if(guessArr[i].equals(combination[i]))
                    {
                        colorsInCorrectSpot++;
                    }
                }
                
                correctPosHistory[guessCounter] = colorsInCorrectSpot;
                
                guessCounter++;
                // --- Checking for correct/incorrect colors in guess ---
                
                // Printing previous guesses and results
                String table = AsciiTable.makeTable(guessHistory, correctPosHistory, incorrectPosHistory, guessCounter);
                System.out.println("\n" + table + "\n");
                
                // Guess counter check
                if(guessCounter == 9)
                {
                    System.out.println("You have 1 try left.\n");
                }
                else if(guessCounter != 10)
                {
                    System.out.println("You have " + (10 - guessCounter) + " tries left.\n");
                }
                else if(guessCounter == 10)
                {
                    System.out.println("You have run out of tries. The combination was \"" + combinationStr + "\". Better luck next time!\nWould you like to play again? (true/false)");
                    restart = input.nextBoolean();
                    if(!restart)
                    {
                        System.exit(0);
                    }
                    else
                    {
                        System.out.print("\033[H\033[2J");
                        break;
                    }
                }
            }
        }
    }
    
    // Converts a given array of strings to one string
    public static String arrToString(String[] arr)
    {
        String text = "";
        
        for(int i = 0; i < arr.length; i++)
        {
            if(i == arr.length - 1)
            {
                text += arr[i];
            }
            else
            {
                text += arr[i] + " ";
            }
        }
        
        return text;
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
}