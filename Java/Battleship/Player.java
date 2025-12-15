import java.util.ArrayList;
import java.util.Scanner;

public class Player
{
    private Grid playerGrid;
    
    private Grid opponentGrid;
    
    private boolean[][] availableLocations = new boolean[Grid.NUM_ROWS + 1][Grid.NUM_COLS + 1];
    
    private Ship[] ships = new Ship[Battleship.SHIP_LENGTHS.length];

    private static ArrayList<Coordinates> guesses = new ArrayList<Coordinates>();
    
    private int shipCount = 0;

    public int playerNum;
    
    public Player(int playerNum)
    {
        playerGrid = new Grid();
        opponentGrid = new Grid();
        
        for(int row = 1; row < Grid.NUM_ROWS + 1; row++)
        {
            for(int col = 1; col < Grid.NUM_COLS + 1; col++)
            {
                availableLocations[row][col] = true;
            }
        }

        this.playerNum = playerNum;
    }

    // Checks for duplicate guesses made by the player
    public boolean checkDuplicateGuess(int row, int col)
    {
        for(int i = 0; i < guesses.size(); i++)
        {
            if(guesses.get(i).getRow() == row && guesses.get(i).getCol() == col)
            {
                return true;
            }
        }

        return false;
    }

    // Human's move are made with this method
    public void makeHumanMove(int choice)
    {
        if(choice == 1)
        {
            System.out.println("\nHuman's Guesses:\n");
        }
        else
        {
            System.out.println("\nHuman " + playerNum + "'s Guesses:\n");
        }

        printOpponentGuesses();
        
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        System.out.print("\nEnter your row guess: ");
        int row = input.nextInt();
        
        System.out.println();
        
        System.out.print("Enter your column guess: ");
        int col = input.nextInt();

        if(checkDuplicateGuess(row, col))
        {
            System.out.println("\nSorry, you have already guessed that location. Please try again.");
            makeHumanMove(choice);
            return;
        }

        guesses.add(new Coordinates(row, col));
        
        boolean hit = checkOpponentGuess(row, col);

        recordPlayerGuess(row, col, hit);

        if(hit)
        {
            Ship ship =  findShip(row, col);
            if(ship.isSunk(opponentGrid))
            {
                System.out.println("Hit! You sunk a ship of length " + ship.getLength() + "\n");
            }
            else
            {
                System.out.println("\nHit!\n");
            }
        }
        else
        {
            System.out.println("\nMiss!\n");
        }
    }
    
    // Computer's moves are made with this method
    public void makeComputerMove()
    {
        int row = (int)(Math.random() * Grid.NUM_ROWS + 1);
            
        int col = (int)(Math.random() * Grid.NUM_COLS + 1);

        if(checkDuplicateGuess(row, col))
        {
            makeComputerMove();
            return;
        }
        
        System.out.println("Computer's guess is (" + row + ", " + col + ").");
        
        boolean hit = checkOpponentGuess(row, col);

        recordPlayerGuess(row, col, hit);
        
        if(hit)
        {
            Ship ship =  findShip(row, col);
            if(ship.isSunk(opponentGrid))
            {
                System.out.println("Hit! You sunk a ship of length " + ship.getLength());
            }
            else
            {
                System.out.println("\nHit!\n");
            }
        }
        else
        {
            System.out.println("\nMiss!\n");
        }
        
        System.out.println("Computer's Guesses:\n");
        printOpponentGuesses();
    }
    
    // Checks if an integer x is within the range of integers y and z
    public boolean isBetween(int start, int target, int end)
    {
        return start <= target && target <= end;
    }
    
    // Validates the given ship's location and direction
    public boolean validateShip(Ship s, boolean isHuman)
    {
        if(!isBetween(1, s.getRow(), Grid.NUM_ROWS) ||
            !isBetween(1, s.getCol(), Grid.NUM_COLS) || 
            !isBetween(1, s.getEndRow(), Grid.NUM_ROWS) ||
            !isBetween(1, s.getEndCol(), Grid.NUM_COLS))
        {
            if(isHuman)
            {
                System.out.println("Ship cannot be placed outside the board\n");
            }
            return false;
        }
        else if(!(s.getDirection() == 1 || s.getDirection() == 0))
        {
            if(isHuman)
            {
                System.out.println("Ship cannot have a direction of " + s.getDirection() +"\n");
            }
            return false;
        }
        
        // This checks for ships touching each other when placing ships
        else if(!shipLocationsAvailable(s))
        {
            if(isHuman)
            {
                System.out.println("Ships cannot be placed next to or on top of each other\n");
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    // Adds the given ship to the player's grid
    public void addShip(Ship s)
    {
        if(shipCount < 5)
        {
            playerGrid.addShip(s);
            setLocationsUnavailable(s);
            ships[shipCount] = s;
            shipCount++;
        }
    }
    
    // Checks if all ships on the player's grid have been sunk
    public boolean hasShips()
    {
        for(int i = 0; i < ships.length; i++)
        {
            if(!ships[i].isSunk(playerGrid))
            {
                return true;
            }
        }
        
        return false;
    }
    
    // Checks if the location of the given ship is available
    public boolean shipLocationsAvailable(Ship s)
    {
        Coordinates[] coords = s.getLocations();
        
        for(int i = 0; i < coords.length; i++)
        {
            if(!availableLocations[coords[i].getRow()][coords[i].getCol()])
            {
                return false;
            }
        }
        
        return true;
    }
    
    // Sets all surrounding locations of a ship unavailable
    public void setLocationsUnavailable(Ship s)
    {
        if(s.getDirection() == Ship.HORIZONTAL)
        {
            for(int i = s.getCol(); i <= s.getEndCol(); i++)
            {
                availableLocations[s.getRow()][i] = false;
                if(s.getRow() > 1)
                {
                    availableLocations[s.getRow() - 1][i] = false;
                }
                
                if(s.getRow() < Grid.NUM_ROWS)
                {
                    availableLocations[s.getRow() + 1][i] = false;
                }
            }
            
            if(s.getCol() > 1)
            {
                availableLocations[s.getRow()][s.getCol() - 1] = false;
            }
            
            if(s.getEndCol() < Grid.NUM_COLS)
            {
                availableLocations[s.getRow()][s.getEndCol() + 1] = false;
            }
        }
        else if(s.getDirection() == Ship.VERTICAL)
        {
            for(int i = s.getRow(); i <= s.getEndRow(); i++)
            {
                availableLocations[i][s.getCol()] = false;
                if(s.getCol() > 1)
                {
                    availableLocations[i][s.getCol() - 1] = false;
                }
                
                if(s.getCol() < Grid.NUM_COLS)
                {
                    availableLocations[i][s.getCol() + 1] = false;
                }
            }
            
            if(s.getRow() > 1)
            {
                availableLocations[s.getRow() - 1][s.getCol()] = false;
            }
            
            if(s.getEndRow() < Grid.NUM_ROWS)
            {
                availableLocations[s.getEndRow() + 1][s.getCol()] = false;
            }
        }
    }

    // Finds the Ship object at the given row and column
    public Ship findShip(int row, int col)
    {
        for(int i = 0; i < ships.length; i++)
        {
            Coordinates[] coords = ships[i].getLocations();

            for(int j = 0; j < coords.length; j++)
            {
                if(coords[j].getRow() == row && coords[j].getCol() == col)
                {
                    return ships[i];
                }
            }
        }

        return null;
    }
    
    // Print your ships on the grid
    public void printMyShips()
    {
        playerGrid.printShips();
    }
    
    // Print opponent guesses
    public void printOpponentGuesses()
    {
        opponentGrid.printStatus();
    }
    
    // Print your guesses
    public void printMyGuesses()
    {
        playerGrid.printStatus();
    }
    
    // Check if the given location has a ship
    public boolean checkOpponentGuess(int row, int col)
    {
        return playerGrid.hasShip(row, col);
    }
    
    // Record a guess from the player
    public void recordPlayerGuess(int row, int col, boolean hit)
    {
        if(hit)
        {
            opponentGrid.markHit(row, col);
        }
        else
        {
            opponentGrid.markMiss(row, col);
        }
    }
}