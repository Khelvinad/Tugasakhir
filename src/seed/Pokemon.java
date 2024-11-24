package seed;

import model.Card;

public class Pokemon {
    private static Card[] seed = new Card[9];

    public static Card[] getPokemonSeed() {
        seed = new Card[]{
                new Card("Rare", "Pikachu", 20000),
                new Card("Rare", "Charizard", 40000),
                new Card("Rare", "Dewa", 40000),
                new Card("Uncommon", "Slowpoke", 10000),
                new Card("Uncommon", "Muuk", 15000),
                new Card("Uncommon", "Ditto", 13000),
                new Card("Common", "Bulbasaur", 5000),
                new Card("Common", "Charmander", 5000),
                new Card("Common", "lol", 5000)};
        return seed;
    }

}
