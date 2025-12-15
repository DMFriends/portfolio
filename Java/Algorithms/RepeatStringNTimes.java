public class RepeatStringNTimes
{
    public static String repeatString(String value, int n)
    {
        String returnValue = "";
        
        for(int i = 0; i < n; i++)
        {
            returnValue += value;
        }
        
        return returnValue;
    }
}