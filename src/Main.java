import model.Backpack;
import model.Kartu;
import model.Table;
import model.User;
import utils.ListUser;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
        boolean login = false;
        User[] user = new User[5];
        int jmluser = 0;

        while(!login) {
            System.out.println("""
                    --- Welcome to ---
                    - Ternak Pokemon -
                    1.Login
                    2.Register
                    Pilihan :\s""");
            int userp = sc.nextInt();
            String nama, password;
            if (userp == 1) {
                System.out.print("Masukkan Username : ");
                nama = sc.next();
                System.out.print("Masukkan Password : ");
                password = sc.next();

                boolean found = false;
                for (int i = 0; i<user.length; i++) {
                    if (user[i] != null && user[i].getnama().equals(nama) && user[i].getpass().equals(password)) {
                        System.out.println("Login berhasil!");
                        login = true;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Login gagal! Username belum terdaftar.");
                }
            } else if (userp == 2) {
                System.out.print("Masukkan Username : ");
                nama = sc.next();
                System.out.print("Masukkan Password : ");
                password = sc.next();
                user[jmluser++] = new User(nama, password);
                System.out.println("Pendaftaran berasil! silakan login.");
            }
        }

        do {
            System.out.print("""
                    --- Welcome to ---
                    - Ternak Pokemon -
                    1. Gacha
                    2. Jual
                    3. List Ternak
                    4. Upgrade
                    5. uang
                    0. Keluar
                    Pilihan :\s""");
            int pilih = sc.nextInt();

            switch (pilih) {
                case 0:
                    System.out.println("Keluar...");
                    User.uang = -1;
                    break;
                case 1:
                    System.out.println("1x Gacha Rp.10.000");
                    System.out.print("Gacha berapa kali :");
                    Table.Tgacha();
                    System.out.println();
                    break;
                case 2:
                    Kartu.jual();
                    break;
                case 3:
                    System.out.println("Slot Bpack : "+ Backpack.slot);
                    System.out.printf("|%-16s |%-16s |%-16s |\n", "List Ternak", "Value", "Rarity");
                    System.out.println("+-----------------------------------+");
                    for (int i = 0; i < Backpack.index; i++) {
                        System.out.printf("|%-17s|%-17d|%-17s|\n", Backpack.GetHasil()[i].pokemon, Backpack.GetHasil()[i].harga, Backpack.GetHasil()[i].rarity);
                    }
                    break;
                case 4:
                    System.out.println("""
                            -Pilihan Upgrade Bpack-
                            1. +5 Slot
                            2. +10 Slot
                            Pilihan :\s""");
                    int upgrade = sc.nextInt();
                    System.out.println("Slot Bpack : "+ Backpack.slot);
                    switch (upgrade) {
                        case 1:
                            Backpack.upgrade(5);
                            break;
                        case 2:
                            Backpack.upgrade(10);
                            break;
                    }
                    break;
                case 5:
                    System.out.println(User.uang);
                    break;
            }
        } while (User.uang != -1);
    }


}
