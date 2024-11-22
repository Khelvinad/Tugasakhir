import model.Backpack;
import model.Kartu;
import model.Table;
import model.User;
import utils.ListUser;

import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ListUser listUser = new ListUser();
    static boolean login = false;
    protected static int lengthConsole = 50;
    protected static int userIdCounter = 1;
    protected static int userIdLogin = 0;
    protected static int indexUserLogin = -1;

    public static void main(String[] args) throws InterruptedException {
        auth();
        if (login) gamesLogic();
    }

    static void printMenuGame() {
        System.out.println("=".repeat(lengthConsole));
        printCenter("Selamat Datang", lengthConsole);
        printCenter("Menu", lengthConsole);
        System.out.println("=".repeat(lengthConsole));
        System.out.println("""
                1. Gacha
                2. Jual
                3. List Ternak
                4. Upgrade
                0. Keluar""");
    }

    static void printAuthMenu() {
        System.out.println("=".repeat(lengthConsole));
        printCenter("Selamat Datang", lengthConsole);
        printCenter("Silahkan masuk menggunakan akun terlebih dahulu", lengthConsole);
        System.out.println("=".repeat(lengthConsole));
        System.out.println("""
                1. Login
                2. Register
                0. Keluar""");
    }

    static void printCenter(String word, int length) {
        int wordLength = word.length();
        System.out.println(" ".repeat((length - wordLength) / 2) + word);
    }

    static int getAndPrintChoice(boolean withEndLine) {
        System.out.print("Pilihan: ");
        int c = sc.nextInt();
        if (withEndLine) System.out.println("=".repeat(lengthConsole));
        return c;
    }

    static void auth() {
        loopAuth:
        while (!login) {
            printAuthMenu();
            int choiceMenu = getAndPrintChoice(true);
            String nama, password;

            switch (choiceMenu) {
                case 1 -> {
                    System.out.print("Masukkan Username : ");
                    nama = sc.next();
                    System.out.print("Masukkan Password : ");
                    password = sc.next();

                    User[] users = listUser.fill;
                    for (int i = 0; i < users.length; i++) {
                        if (users[i].getNama().equals(nama) && users[i].getPassword().equals(password)) {
                            System.out.println("Login berhasil!");
                            login = true;
                            userIdLogin = users[i].getUserId();
                            indexUserLogin = i;
                            break loopAuth;
                        }
                    }

                    System.out.println("Login gagal! Username belum terdaftar.");
                }
                case 2 -> {
                    System.out.print("Masukkan Username : ");
                    nama = sc.next();
                    System.out.print("Masukkan Password : ");
                    password = sc.next();
                    listUser.add(new User(nama, password, userIdCounter));
                    System.out.println("Pendaftaran berasil! silakan login.");
                }
                case 0 -> {
                    System.out.println("Keluar.");
                    break loopAuth;
                }
                default -> System.out.println("Pilihan tidak tersedia");
            }
        }
    }

    static void logout() {
        userIdLogin = 0;
        login = false;
        indexUserLogin = -1;
    }

    static void gamesLogic() throws InterruptedException {
        loopLogic:
        do {
            printInfoUser(listUser.fill[indexUserLogin]);
            printMenuGame();
            int choice = getAndPrintChoice(true);

            switch (choice) {
                case 0 -> {
                    System.out.println("Keluar.");
                    break loopLogic;
                }
                case 1 -> {
                    System.out.println("1x Gacha Rp.10.000");
                    System.out.print("Gacha berapa kali :");
                    Table.Tgacha();
                    System.out.println();
                }
                case 2 -> {
                    Kartu.jual();
                }
                case 3 -> {

//                    System.out.println("Slot Bpack : " + Backpack.slot);
                    System.out.printf("|%-16s |%-16s |%-16s |\n", "List Ternak", "Value", "Rarity");
                    System.out.println("+-----------------------------------+");
                    for (int i = 0; i < Backpack.index; i++) {
                        System.out.printf("|%-17s|%-17d|%-17s|\n", Backpack.getCards()[i].pokemon, Backpack.getCards()[i].harga, Backpack.getCards()[i].rarity);
                    }
                    break;
                }
                case 4 -> {

                    System.out.println("""
                            -Pilihan Upgrade Bpack-
                            1. +5 Slot
                            2. +10 Slot
                            Pilihan :\s""");
                    int upgrade = sc.nextInt();
//                    System.out.println("Slot Bpack : " + Backpack.slot);
                    switch (upgrade) {
                        case 1:
                            Backpack.upgrade(5);
                            break;
                        case 2:
                            Backpack.upgrade(10);
                            break;
                    }
                    break;
                }
                case 5 -> {

                }
            }
        } while (true);
    }

    static void printInfoUser(User user) {
        System.out.println("Username: " + user.getNama());
        System.out.println("Uang: " + user.uang);
        System.out.println("Backpack: " + user.backpack.getTotalFill() + "/" + user.backpack.getSlot() + " Pokemon");
    }
}
