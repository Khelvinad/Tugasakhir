package seed;

import model.Kartu;

public class Pokemon {
    Kartu[] seed = new Kartu[9];

    public void Listpokemon() {
        seed = new Kartu[]{
                new Kartu("Rare", "Pikachu", 20000),
                new Kartu("Rare", "Charizard", 40000),
                new Kartu("Rare", "Dewa", 40000),
                new Kartu("Uncommon", "Slowpoke", 10000),
                new Kartu("Uncommon", "Muuk", 15000),
                new Kartu("Uncommon", "Ditto", 13000),
                new Kartu("Common", "Bulbasaur", 5000),
                new Kartu("Common", "Charmander", 5000),
                new Kartu("Common", "lol", 5000)};

    }

//    public Kartu[][] getKartuarr() {
//        return kartuarr;
//    }

    public Kartu[] getPokemonSeed(){
        return seed;
    }

}
