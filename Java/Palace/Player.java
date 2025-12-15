import java.util.ArrayList;

public class Player
{
	private Hand hand;
	private int playerID;
	private ArrayList<Card> bank = new ArrayList<Card>();
	
	public Player(int playerID)
	{
	    hand = new Hand();
	    this.playerID = playerID;
	}
	
	public ArrayList<Card> getCards()
	{
	   return hand.getCards();
	}

	public ArrayList<Card> getBank()
	{
		return bank;
	}

	public void populateBank(Card c)
	{
	    bank.add(c);
	}

	public void removeFromBank(Card c)
	{
		bank.remove(find(c.getRank(), c.getSuit()));
	}

	public Card find(int rank, int suit)
    {
        for(Card card : bank)
        {
            if(card.getRank() == rank && card.getSuit() == suit)
            {
                return card;
            }
        }
        
        return null;
    }
	
	public Hand getHand()
	{
	    return hand;
	}
	
	public int getPlayerID()
	{
	    return playerID;
	}
	
	public boolean hasWon()
	{
	    return getCards().isEmpty() && bank.isEmpty();
	}

    public void printCards()
	{
	    System.out.println(getCards());
	}
}