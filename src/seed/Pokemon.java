package seed;

import model.Card;

public class Pokemon {

    public static Card[] getPokemonSeed() {
        return new Card[]{
                new Card("Rare", "Pikachu", 20000),
                new Card("Rare", "Charizard", 40000),
                new Card("Rare", "Dewa", 40000),
                new Card("Uncommon", "Slowpoke", 10000),
                new Card("Uncommon", "Muuk", 15000),
                new Card("Uncommon", "Ditto", 13000),
                new Card("Common", "Bulbasaur", 5000),
                new Card("Common", "Charmander", 5000),
                new Card("Common", "lol", 5000)};
    }

}
