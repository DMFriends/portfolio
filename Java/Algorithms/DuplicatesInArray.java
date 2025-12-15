public class DuplicatesInArray
{
    public static boolean findDuplicate(String[] arr)
    {
        boolean duplicates = false;
        for(int i = 0; i < arr.length; i++)
        {
            for(int j = i + 1; j < arr.length; j++)
            {
                if(j != i && arr[j].equals(arr[i]))
                {
                    duplicates = true;
                    System.out.print("Duplicate found!" + arr[j]);
                    break;
                }
            }
            
            break;
        }
        
        return duplicates;
    }
}