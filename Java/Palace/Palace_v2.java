import java.util.Scanner;
import java.util.ArrayList;

public class Palace_v2
{
    public static Player player1 = new Player(1);
    public static Player player2 = new Player(2);
    public static ArrayList<Card> playerMoves = new ArrayList<Card>();
    public static Deck deck = new Deck();

    public static void main(String[] args)
    {        
        // These are the face down cards.
        for(int i = 0; i < 3; i++)
        {
            player1.populateBank(deck.draw());
            player2.populateBank(deck.draw());
        }

        deck.dealCards(player1);
        deck.dealCards(player2);

        clearScreen();
        chooseBankCards(player1);
        clearScreen();
        chooseBankCards(player2);        
        
        while(!player1.hasWon() && !player2.hasWon())
        {
            while(player1.getHand().size() < 6)
            {
                player1.getHand().add(deck.draw());
            }
            while(player2.getHand().size() < 6)
            {
                player2.getHand().add(deck.draw());
            }

            clearScreen();
            if(player1.getCards().isEmpty())
            {
                endGame(player1);
            }
            else
            {
                makeMove(player1);
            }
            clearScreen();
            if(player2.getCards().isEmpty())
            {
                endGame(player2);
            }
            else
            {
                makeMove(player2);
            }
        
            if(player1.hasWon() && deck.getDeck().isEmpty())
            {
                System.out.println("Player 1 has won!");
            }
            else if(player2.hasWon() && deck.getDeck().isEmpty())
            {
                System.out.println("Player 2 has won!");
            }
            else
            {
                continue;
            }
        }
    }

    public static void chooseBankCards(Player p)
    {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        System.out.print("It is Player " + p.getPlayerID() + "'s turn to choose cards to 'bank'. Press Enter when ready. ");
        input.nextLine();
        clearScreen();

        System.out.println("Player " + p.getPlayerID() + ", you have the following cards:\n(yellow cards are ghost cards)");
        System.out.println(p.getHand().printCards());

        while(true)
        {
            System.out.print("\nPlayer " + p.getPlayerID() + ", choose 3 cards to place on top of your 'bank'. Separate each with a ', ': ");
            String card = input.nextLine();
            String[] cards = card.split(", ");
            if(cards.length < 3)
            {
                System.out.println("You need to choose exacty 3 cards -- no more, no less. Please try again.");
                continue;
            }

            ArrayList<Card> bankedCards = new ArrayList<Card>();

            boolean isValidChoice = true;
            
            for(int i = 0; i < cards.length; i++)
            {
                if(cardNotInHand(p, cards[i]) || cards[i].equals("") || cards[i] == null)
                {
                    System.out.println("You entered one or more invalid cards or cards that are not in your hand. Please try again.");
                    isValidChoice = false;
                    break;
                }
                else
                {
                    bankedCards.add(p.getHand().find(cards[i]));
                }
            }

            if(!isValidChoice)
            {
                continue;
            }

            for(Card c : bankedCards)
            {
                c.setBanked(true);
            }

            break;
        }
    }
    
    public static void makeMove(Player p)
    {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        System.out.print("It is Player " + p.getPlayerID() + "'s turn to move. Press Enter when ready. ");
        input.nextLine();
        clearScreen();

        System.out.println("Player Moves: " + playerMoves + "\n");

        while(true)
        {
            System.out.println("Player " + p.getPlayerID() + ", you have the following cards:\n(red cards are banked cards, yellow cards are ghost cards, blue cards are banked and ghost cards)");
            System.out.println(p.getHand().printCards());

            System.out.print("\nPlayer " + p.getPlayerID() + ", what is your move? Separate multiple cards with a ', '. Type 'take' to take the discard pile into your hand, if you cannot make a legal move. ");
            String playerMove = input.nextLine();
            
            if(!playerMove.equals("take") && contains(",", playerMove))
            {
                String[] temp = playerMove.split(",");
                
                ArrayList<Card> cardsInMove = new ArrayList<Card>();

                for(int i = 0; i < temp.length; i++)
                {
                    temp[i] = temp[i].trim();
                    cardsInMove.add(p.getHand().find(temp[i]));
                }

                boolean cardsNotBanked = true;

                for(Card card : cardsInMove)
                {
                    if(card.isBanked())
                    {
                        System.out.println("One or more of the cards you played are banked. You cannot play banked cards. Please try again.\n");
                        cardsNotBanked = false;
                        break;
                    }
                }

                if(!cardsNotBanked)
                {
                    continue;
                }
    
                if(cardsInMove.size() == 4 && checkSameRank(cardsInMove))
                {
                    for(Card card : cardsInMove)
                    {
                        p.getHand().remove(card);
                        playerMoves.clear();
                    }
                    continue;
                }
                else if((!playerMoves.isEmpty() && !checkSameRank(cardsInMove)) || (checkSameRank(cardsInMove) && !playerMoves.isEmpty() && cardsInMove.get(cardsInMove.size() - 1).getRank() < playerMoves.get(playerMoves.size() - 1).getRank()) || !cardsDontMatch(cardsInMove))
                {
                    System.out.println("That is not a valid move. Please try again.\n");
                    continue;
                }
                else
                {
                    for(Card card : cardsInMove)
                    {
                        p.getHand().remove(card);
                        playerMoves.add(card);
                    }
                    break;
                }
            }
            else if((cardNotInHand(p, playerMove) || playerMove.equals("") || playerMove == null) && !playerMove.equals("take"))
            {
                System.out.println("You do not have that card or you entered an invalid card. Please try again.\n");
                continue;
            }
            else if(!playerMove.equals("take") && p.getHand().find(playerMove).isBanked())
            {
                System.out.println("That card is banked. You cannot play it. Please try again.\n");
                continue;
            }
            else if(playerMove.equals("take"))
            {
                p.getHand().add(playerMoves);
                playerMoves.clear();
                break;
            }
            else
            {
                if(((!playerMoves.isEmpty() && !p.getHand().find(playerMove).isGhost()) && playerMoves.get(playerMoves.size() - 1).getRank() <= p.getHand().find(playerMove).getRank()) || playerMoves.isEmpty() || p.getHand().find(playerMove).isGhost())
                {
                    p.getHand().remove(new Card(playerMove));
                    playerMoves.add(new Card(playerMove));

                    if(playerMoves.size() >= 4)
                    {
                        int[] ranks = new int[4];

                        for(int i = playerMoves.size() - 4; i < playerMoves.size(); i++)
                        {
                            for(int j = 0; j < ranks.length; j++)
                            {
                                ranks[j] = playerMoves.get(i).getRank();
                            }
                        }

                        if(ranksMatch(ranks))
                        {
                            playerMoves.clear();
                        }
                    }

                    break;
                }
                else
                {
                    System.out.println("That is not a valid move. The rank of your card must be greater than or equal to the rank of the last played card. Please try again.\n");
                    continue;
                }
            }
        }
    }

    public static void endGame(Player p)
    {
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);
        
        int randIndex = (int) (Math.random() * p.getBank().size());

        System.out.print("Player " + p.getPlayerID() + ", you have reached the endgame. Your move will be displayed on the next screen (once you press Enter). If the rank of this card is smaller than the rank of the last played card, you must take all the cards that have been played. ");
        input.nextLine();

        if(p.getBank().get(randIndex).getRank() < playerMoves.get(playerMoves.size() - 1).getRank())
        {
            System.out.println("Player " + p.getPlayerID() + ", your move will be: " + p.getBank().get(randIndex));
            System.out.println("Unfortunately, the rank of this card is smaller than the rank of the last played card.");
            p.getHand().add(playerMoves);
            playerMoves.clear();
        }
        else
        {
            System.out.println("Player " + p.getPlayerID() + ", your move will be: " + p.getBank().get(randIndex));
            playerMoves.add(p.getBank().get(randIndex));
            p.getBank().remove(p.getBank().get(randIndex));

            System.out.print("Press Enter when ready. ");
            input.nextLine();
        }
    }

    public static boolean ranksMatch(int[] arr)
    {
        int count = 0;

        for(int i = 0; i < arr.length - 1; i++)
        {
            if(arr[i] == arr[i + 1])
            {
                count++;
            }
        }

        if(count == 4)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkSameRank(ArrayList<Card> arr)
    {
        boolean sameRank = true;

        for(int i = 0; i < arr.size() - 1; i++)
        {
            if(arr.get(i).getRank() != arr.get(i + 1).getRank())
            {
                sameRank = false;
                break;
            }
        }

        return sameRank;
    }

    public static boolean cardsDontMatch(ArrayList<Card> arr)
    {
        boolean cardsDontMatch = false;

        for(int i = 0; i < arr.size() - 1; i++)
        {
            if(arr.get(i) != arr.get(i + 1))
            {
                cardsDontMatch = true;
                break;
            }
        }

        return cardsDontMatch;
    }
    
    public static boolean cardNotInHand(Player p, String card)
    {
        boolean cardNotInHand = true;
                
        for(Card c : p.getCards())
        {
            if(c.toString().equals(card))
            {
                cardNotInHand = false;
                break;
            }
        }
        
        return cardNotInHand;
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

    public static void clearScreen()
    {
        System.out.print("\033[H\033[2J");
    }
}