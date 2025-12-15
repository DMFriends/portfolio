public class Ship
{
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int row = UNSET;
    private int col = UNSET;
    private int length = UNSET;
    private int direction = UNSET;
    
    public Ship(int length)
    {
        this.length = length;
    }
    
    // Has the location been initialized
    public boolean isLocationSet()
    {
        return row != UNSET && col != UNSET;
    }
    
    // Has the direction been initialized
    public boolean isDirectionSet()
    {
        return direction == VERTICAL || direction == HORIZONTAL;
    }
    
    // Set the location of the ship 
    public void setLocation(int row, int col)
    {
        this.row = row;
        this.col = col;
    }
    
    // Set the direction of the ship
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    
    // Getter for the row value
    public int getRow()
    {
        return row;
    }
    
    // Getter for the column value
    public int getCol()
    {
        return col;
    }
    
    public Coordinates[] getLocations()
    {
        Coordinates[] coords = new Coordinates[length];

        if(direction == HORIZONTAL)
        {
            for(int i = 0; i < length; i++)
            {
                coords[i] = new Coordinates(row, col + i);
            }
        }
        else if(direction == VERTICAL)
        {
            for(int i = 0; i < length; i++)
            {
                coords[i] = new Coordinates(row + i, col);
            }
        }
        
        return coords;
    }
    
    public int getEndRow()
    {
        if(direction == HORIZONTAL)
        {
            return row;
        }
        else
        {
            return row + length - 1;
        }
    }
    
    public int getEndCol()
    {
        if(direction == VERTICAL)
        {
            return col;
        }
        else
        {
            return col + length - 1;
        }
    }
    
    // Getter for the length of the ship
    public int getLength()
    {
        return length;
    }
    
    // Getter for the direction
    public int getDirection()
    {
        return direction;
    }
    
    public boolean isSunk(Grid g)
    {
        Coordinates[] coords = getLocations();
        
        for(int i = 0; i < coords.length; i++)
        {
            Location loc = g.get(coords[i].getRow(), coords[i].getCol());

            if(!loc.checkHit())
            {
                return false;
            }
        }
        
        return true;
    }
    
    // Helper method to get a string value from the direction
    private String directionToString()
    {
        if(direction == VERTICAL)
        {
            return "vertical";
        }
        else if(direction == HORIZONTAL)
        {
            return "horizontal";
        }
        
        return "unset direction";
    }
    
    // Helper method to get a (row, col) string value from the location
    private String locationToString()
    {
        if(isLocationSet())
        {
            return "(" + row + ", " + col + ")";
        }
        else
        {
            return "(unset location)";
        }
    }
    
    // toString value for this Ship
    public String toString()
    {
        return directionToString() + " ship of length " + length + " at " + locationToString();
    }
}