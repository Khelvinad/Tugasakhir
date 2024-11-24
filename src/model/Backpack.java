package model;

public class Backpack {
    public static int index = 0;
    public static int lastIndexCard = -1;
    private static Card[] cards = new Card[5];

    public int getSlot() {
        return cards.length;
    }

    public int getTotalFill() {
        return lastIndexCard + 1;
    }

    public static void upgrade(int up) {
        Card[] newCards = new Card[cards.length + up];

        for (int i = 0; i < cards.length; i++) {
            newCards[i] = cards[i];
        }

        cards = newCards;
    }

    public boolean addCard(Card card) {
        if (cards.length == (lastIndexCard + 1))
            return false;
        lastIndexCard += 1;
        cards[lastIndexCard] = card;
        return true;
    }

    public Card[] getCards() {
        return cards;
    }

    public void sellCard(int index) {
        cards[index] = null;
        Card.sortCard(cards);
    }
}
