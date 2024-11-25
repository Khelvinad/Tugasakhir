package seed;

import model.Card;

public class Pokemon {

    public static Card[] getPokemonSeed() {
        return new Card[]{
                new Card("Rare", "Pikachu", 8000),
                new Card("Rare", "Charizard", 10000),
                new Card("Rare", "Mewtwo", 9500),
                new Card("Rare", "Rayquaza", 9000),
                new Card("Rare", "Lugia", 8500),
                new Card("Uncommon", "Slowpoke", 7000),
                new Card("Uncommon", "Muk", 7500),
                new Card("Uncommon", "Ditto", 6800),
                new Card("Uncommon", "Scyther", 7200),
                new Card("Uncommon", "Jigglypuff", 7300),
                new Card("Common", "Bulbasaur", 5000),
                new Card("Common", "Charmander", 5500),
                new Card("Common", "Squirtle", 5300),
                new Card("Common", "Pidgey", 4800),
                new Card("Common", "Rattata", 4500),
                new Card("Common", "Magikarp", 4000),
                new Card("Common", "Zubat", 4200),
                new Card("Common", "Caterpie", 3500),
                new Card("Common", "Weedle", 3000),
                new Card("Common", "Sandshrew", 3700)
        };
    }
}