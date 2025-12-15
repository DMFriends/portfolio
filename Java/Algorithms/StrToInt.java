public class StrToInt
{
    public static void main(String[] args)
    {
        System.out.println(strToInt("12345h"));
    }
    
    public static int strToInt(String val)
    {
        for(int i = 0; i < val.length(); i++)
        {
            if(Character.isLetter(val.charAt(i)))
            {
                return -1;
            }
        }
        
        return Integer.valueOf(val);
    }
}