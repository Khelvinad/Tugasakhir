package model;
import seed.Pokemon;
import utils.Loading;

import java.util.Scanner;

public class Table {
    public static Scanner sc = new Scanner(System.in);

    public static void Tgacha() throws InterruptedException {
        int gac = sc.nextInt();
        Loading.init();
        if (User.uang< gac * 10000){
            System.out.printf("-Uang anda tidak cukup untuk %d kali gacha-",gac);
            return ;
        }
        Pokemon pokemon = new Pokemon();
        pokemon.Listpokemon();
//        Kartu[][] kartuarr = pokemon.getCard();
        for (int i = 0; i < gac; i++) {
//            if (User.uang >= 10000) {
//                User.uang -= 10000;
//                Kartu hasilgacah = Kartu.Gacha(gac, kartuarr);
//                Backpack.Simpangacha(hasilgacah);
//            }
        }
        System.out.println("Anda dapat : ");
        for (int i = 0; i < gac; i++) {
            System.out.printf("|%-17s|%-17s|%-17d|\n", Backpack.getCards()[i].rarity, Backpack.getCards()[i].pokemon, Backpack.getCards()[i].harga);
        }
    }

}
