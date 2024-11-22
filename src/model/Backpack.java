package model;

public class Backpack {
    public static int index = 0;
    public static int lastIndexCard = 0;
    private static Kartu[] cards = new Kartu[5];

    public int getSlot(){
        return cards.length;
    }

    public int getTotalFill(){
        return lastIndexCard;
    }

    public static void upgrade(int up) {
        Kartu[] newCards = new Kartu[cards.length + up];

        for (int i = 0; i < cards.length; i++) {
            newCards[i] = cards[i];
        }

        cards = newCards;
    }

    public boolean addCard(Kartu card) {
        if (cards.length == (lastIndexCard + 1))
            return false;
        cards[lastIndexCard] = card;
        lastIndexCard += 1;
        return true;
    }

    public static Kartu[] getCards() {
        return cards;
    }

    public void sellCard(int index) {
        cards[index] = null;
        Kartu[] newCards = new Kartu[cards.length];

        loopNewCards:
        for (int i = 0; cards.length > i; i++) {
            loopOldCards:
            for (Kartu card : cards) {
               if (card == null){
                   continue loopOldCards;
               }

            }
        }
    }
}
