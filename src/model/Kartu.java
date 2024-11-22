package model;

public class Kartu {
    public String rarity;
    public String pokemon;
    public int harga;

    public Kartu(String rarity, String pokemon, int harga) {
        this.rarity = rarity;
        this.pokemon = pokemon;
        this.harga = harga;
    }



    public static Kartu Gacha(int a, Kartu[][] kartuarr) {
        int index = 0;
        int index2 = 0;
        for (int i = 0; i < a; i++) {
            if (a < 3) {
                index = (int) (Math.random() * 2);
                index2 = (int) (Math.random() * 2);
            }
            if (a >= 3) {
                index = (int) (Math.random() * kartuarr.length);
                index2 = (int) (Math.random() * kartuarr.length);
            }
        }
        return kartuarr[index][index2];
    }

    public static void jual(){
        int beli = (int) (Math.random() * Backpack.index);
        for (int i = 0; i < Backpack.index; i++) {
            if (Backpack.getCards()[beli] == null) {
                beli = (int) (Math.random() * Backpack.index);
                if (Backpack.getCards()[beli] != null) break;
            }
        }
        System.out.println("Membeli : ");
        System.out.printf("|%-17s|%-17d|%-17s|\n", Backpack.getCards()[beli].pokemon, Backpack.getCards()[beli].harga, Backpack.getCards()[beli].rarity);
//        User.uang += Backpack.getCards()[beli].harga;
        Backpack.getCards()[beli] = null;
        Backpack.index -= 1;
    }
}
