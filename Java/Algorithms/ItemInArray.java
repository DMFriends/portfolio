import java.util.ArrayList;

public class ItemInArray
{
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

    // Checks if a given integer exists in a given array of integers
    public static boolean contains(int item, int[] arr)
    {
        boolean result = false;
        
        for(int num : arr)
        {
            if(num == item)
            {
                result = true;
            }
        }
        
        return result;
    }

    // Checks if a given string exists in a given ArrayList of strings
    public static boolean contains(String item, ArrayList<String> arr)
    {
        boolean result = false;
        
        for(int i = 0; i < arr.size(); i++)
        {
            if(arr.get(i) != null && arr.get(i).equals(item))
            {
                result = true;
            }
        }
        
        return result;
    }

    // Checks if a given integer exists in a given ArrayList of integers
    public static boolean contains(int item, ArrayList<Integer> arr)
    {
        boolean result = false;
        
        for(int i = 0; i < arr.size(); i++)
        {
            if(arr.get(i).equals(item))
            {
                result = true;
            }
        }
        
        return result;
    }
}