package seed;

import model.Kartu;

public class Pokemon {
    Kartu[][] kartuarr = new Kartu[3][3];

    public void Listpokemon() {
        kartuarr[2][0] = new Kartu("Rare", "Pikachu", 20000);
        kartuarr[2][1] = new Kartu("Rare", "Charizard", 40000);
        kartuarr[2][2] = new Kartu("Rare", "Dewa", 40000);
        kartuarr[1][0] = new Kartu("Uncommon", "Slowpoke", 10000);
        kartuarr[1][1] = new Kartu("Uncommon", "Muuk", 15000);
        kartuarr[1][2] = new Kartu("Uncommon", "Ditto", 13000);
        kartuarr[0][0] = new Kartu("Common", "Bulbasaur", 5000);
        kartuarr[0][1] = new Kartu("Common", "Charmander", 5000);
        kartuarr[0][2] = new Kartu("Common", "lol", 5000);

    }

    public Kartu[][] getKartuarr() {
        return kartuarr;
    }


}
