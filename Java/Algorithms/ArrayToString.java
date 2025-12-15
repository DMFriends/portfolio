public class ArrayToString
{
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
}