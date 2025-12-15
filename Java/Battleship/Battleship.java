import java.util.Scanner;
import java.io.File;

public class Battleship// extends ConsoleProgram
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private static Player player1 = new Player(1);
    
    private static Player player2 = new Player(2);

    private static int choice = 0;

    private static File human1 = new File("human1.txt");

    private static File human2 = new File("human2.txt");
    
    public static void main(String[] args)//void run()
    {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        while(choice != 1 || choice != 2)
        {
            System.out.print("Would you like to play human vs. computer (1) or human vs. human (2)? ");
            choice = input.nextInt();

            if(choice == 1)
            {
                clearScreen();
                humanVsComputer();
            }
            else if(choice == 2)
            {
                clearScreen();
                humanVsHuman();
            }
            else
            {
                System.out.println("\nInvalid input, please try again.");
            }
        }
    }

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void placeHumanShips(Player p)
    {
        Scanner ships = null;

        try
        {
            if(p.playerNum == 1)
            {
                ships = new Scanner(human1);
            }
            else
            {
                ships = new Scanner(human2);
            }
            
            for(int i = SHIP_LENGTHS.length - 1; i >= 0; i--)
            {
                Ship ship = new Ship(SHIP_LENGTHS[i]);

                String[] arr = new String[3];

                while(ships.hasNextLine())
                {
                    arr = ships.nextLine().split(",");
                    ship.setLocation(Integer.valueOf(arr[0]), Integer.valueOf(arr[1]));
                    ship.setDirection(Integer.valueOf(arr[2]));
                
                    if(!p.validateShip(ship, true))
                    {
                        System.out.println("You have an invalid coordinate in either 'human1.txt' or 'human2.txt': (" + Integer.valueOf(arr[0]) + ", " + Integer.valueOf(arr[1]) + ")");
                        System.out.println("Please check both files and try again.\n");
                        System.exit(0);
                    }
                    else
                    {
                        p.addShip(ship);
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static void placeComputerShips()
    {        
        for(int i = SHIP_LENGTHS.length - 1; i >= 0; i--)
        {
            Ship ship = new Ship(SHIP_LENGTHS[i]);
            
            int row = (int)(Math.random() * Grid.NUM_ROWS + 1);
            
            int col = (int)(Math.random() * Grid.NUM_COLS + 1);
            
            int dir = (int)(Math.random() * 2);
            
            ship.setLocation(row, col);
            
            ship.setDirection(dir);
            
            if(player2.validateShip(ship, false))
            {
                player2.addShip(ship);
            }
            else
            {
                i++;
            }
        }
        
        // FOR DEBUGGING ONLY
        //System.out.println("Computer's Ships:\n");
        //computer.printMyShips();
    }

    public static void humanVsComputer()
    {
        placeHumanShips(player1);
        
        clearScreen();
        
        placeComputerShips();
        
        while(player1.hasShips() && player2.hasShips())
        {
            player1.makeHumanMove(choice);
            player2.makeComputerMove();
        }
        
        if(player1.hasShips())
        {
            System.out.println("\nHuman has won!");
        }
        else if(player2.hasShips())
        {
            System.out.println("\nComputer has won!");
        }
    }

    public static void humanVsHuman()
    {
        placeHumanShips(player1);

        clearScreen();

        placeHumanShips(player2);

        clearScreen();
        
        while(player1.hasShips() && player2.hasShips())
        {
            player1.makeHumanMove(choice);
            player2.makeHumanMove(choice);
        }
        
        if(player1.hasShips())
        {
            System.out.println("\nHuman 1 has won!");
        }
        else if(player2.hasShips())
        {
            System.out.println("\nHuman 2 has won!");
        }
    }
}