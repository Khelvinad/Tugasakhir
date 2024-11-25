package model;

import seed.Pokemon;
import utils.Random;

public class User {
    private String name;
    public int userId;
    public int money = 30000;
    private String password;
    public Backpack backpack;

    public User(String nama, String password, int userId) {
        backpack = new Backpack();
        this.name = nama;
        this.password = password;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public Card[] gacha(int total) {
        int[] gachaIndex = new int[total];
        Card[] newCards = new Card[total];

        for (int i = 0; i < total; i++) {
            gachaIndex[i] = Random.init(Pokemon.getPokemonSeed().length - 1, 0);
        }

        for (int i = 0; i < total; i++) {
            newCards[i] = Pokemon.getPokemonSeed()[gachaIndex[i]];
        }

        return newCards;
    }
}
