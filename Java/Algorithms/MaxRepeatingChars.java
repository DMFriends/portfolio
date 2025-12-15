public class MaxRepeatingChars
{
    public static void main(String[] args)
    {
        longestStreak("CCAAAAATTT!");
    }
    
    public static void longestStreak(String str)
    {
        int count = 0;
    	String letter = "";
    	for(int i = 0; i < str.length() - 1; i++)
    	{
    	    int curCount = 1;
    	    
    	    for(int j = i + 1; j < str.length() - 1; j++)
    	    {
        		if(!str.substring(i, i + 1).equals(str.substring(j, j + 1)))
        		{
        			break;
        		}
        		curCount++;
        		
        		if(curCount > count)
        		{
        			count = curCount;
                    letter = str.substring(i, i + 1);
        		}
    	    }
    	}
    	
    	System.out.println(letter + " " + count);
    }

}