public class WordBackwards
{
    private String word;
    private String wordBackwards = "";
    
    public WordBackwards(String theWord)
    {
        word = theWord;
    }
    
    public void spellWordBackwards()
    {
        for(int i = 0; i < word.length(); i++)
        {
            wordBackwards += word.substring(word.length() - i - 1, word.length() - i);
        }
    }
    
    public String toString()
    {
        return "Your word spelt backwards is: " + wordBackwards;
    }
}