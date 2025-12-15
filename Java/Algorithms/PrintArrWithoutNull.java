public class PrintArrWithoutNull
{
    // Prints directly to console
    public static void printArr(String[] arr)
    {
        System.out.print("[");
        
        for(int i = 0; i < arr.length; i++)
        {
            if(i == 0 && arr[i] != null)
            {
                System.out.print(arr[i]);
            }
            else if(arr[i] != null)
            {
                System.out.print(", " + arr[i]);
            }
        }
        
        System.out.print("]");
    }
    
    // Returns array via String
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