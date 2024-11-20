package model;

public class Backpack {
    public static int index = 0;
    public static int slot = 10;
    private static Kartu[] Hasil = new Kartu[slot];

    public static void upgrade(int up) {
        slot += up;
        Kartu[] newHasil = new Kartu[slot];

        for (int i = 0; i < index; i++) {
            newHasil[i] = Hasil[i];
        }
        Hasil = newHasil;
    }

    public static void Simpangacha(Kartu hasil) {
        if (index < Hasil.length) {
            Hasil[index] = hasil;
            index++;
        } else {
            System.out.println("Kosong");
        }
    }

    public static Kartu[] GetHasil(){
        return Hasil;
    }
}
