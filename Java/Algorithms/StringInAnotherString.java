public class StringInAnotherString
{
    public static boolean contains(char key, String word)
    {
        for(int i = 0; i < word.length(); i++)
        {
            if(word.charAt(i) == key)
            {
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean contains(String key, String word)
    {
        String[] wordList = word.split("");
        
        for(int i = 0; i < wordList.length; i++)
        {
            if(wordList[i] != null && wordList[i].equals(key))
            {
                return true;
            }
        }
        
        return false;
    }
}