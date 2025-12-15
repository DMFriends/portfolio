import java.util.ArrayList;
import java.util.Collections;

public class Hand
{
    private ArrayList<Card> playerCards = new ArrayList<Card>();
    
    public Hand()
    {
        
    }
    
    public void add(Card c)
    {
		playerCards.add(c);
	}
	
	public void add(ArrayList<Card> listOfCards)
	{
	    for(Card c : listOfCards)
	    {
	        playerCards.add(c);
	    }
	}

	public void remove(Card c)
	{
		playerCards.remove(find(c.getRank(), c.getSuit()));
	}
	
	public Card find(int rank, int suit)
    {
        for(Card card : playerCards)
        {
            if(card.getRank() == rank && card.getSuit() == suit)
            {
                return card;
            }
        }
        
        return null;
    }
    
    public int size()
    {
		return playerCards.size();
	}
	
	public Card getCard(int i)
	{
	    return playerCards.get(i);
	}
	
	public ArrayList<Card> getCards()
	{
	    return playerCards;
	}
	
	public ArrayList<String> sort()
	{
	    //String[] temp = new String[size()];
	    ArrayList<String> sorted = new ArrayList<String>();
	    
	    for(int i = 0; i < size(); i++)
	    {
	        sorted.add(getCard(i).toString());
	    }
	    
	    Collections.sort(sorted);
	    
	    return sorted;
	}
}