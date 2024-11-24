package model;

public class Card {
    public String rarity;
    public String pokemon;
    public int harga;

    public Card(String rarity, String pokemon, int harga) {
        this.rarity = rarity;
        this.pokemon = pokemon;
        this.harga = harga;
    }

    public static void sortCard(Card[] cardArr) {
        int l = cardArr.length;
        int index = 0;

        for (int i = 0; i < l; i++) {
            if (cardArr[i] != null) {
                cardArr[index++] = cardArr[i];
            }
        }
        while (index < l) {
            cardArr[index++] = null;
        }
    }
}
