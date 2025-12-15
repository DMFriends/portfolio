import java.util.Scanner;

public class ExtractFromCoords
{
    public static void main(String[] args)
    {
        String coord = "";
        
        String[] arr = null;
        
        Scanner input = new Scanner(System.in);
        
        while(!coord.contains(","))
        {
            System.out.print("Enter an x and y coordinate, separated by a comma (no parentheses): ");
            coord = input.nextLine();
            
            System.out.println();
            
            if(coord.contains(","))
            {
                arr = coord.split(",");
                break;
            }
            else
            {
                System.out.println("Sorry, invalid coordinate. Please try again.");
                continue;
            }
        }
        
        int[] arrInt = {Integer.valueOf(arr[0].trim()), Integer.valueOf(arr[1].trim())};
        
        System.out.println("x coor: " + arrInt[0]);
        System.out.println("y coor: " + arrInt[1]);
        
        /*String str = " 1";
        
        System.out.println(Integer.valueOf(str.trim()));*/
    }
}