import model.Backpack;
import model.Card;
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
    protected static int hargaGacha = 5000;

    public static void main(String[] args) throws InterruptedException {
        auth();
        if (login) gamesLogic();
    }

    static void printMenuGame() {
        System.out.println("""
                1. Gacha
                2. Backpack
                0. Keluar""");
    }

    static void printAuthMenu() {
        System.out.println("=".repeat(lengthConsole));
        printlnCenter("Selamat Datang", lengthConsole);
        printlnCenter("Silahkan masuk menggunakan akun terlebih dahulu", lengthConsole);
        System.out.println("=".repeat(lengthConsole));
        System.out.println("""
                1. Login
                2. Register
                0. Keluar""");
    }

    static void printlnCenter(String word, int length) {
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
                        if (users[i].getName().equals(nama) && users[i].getPassword().equals(password)) {
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
        System.out.println("=".repeat(lengthConsole));
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
            System.out.println("=".repeat(lengthConsole));
            printMenuGame();
            int choice = getAndPrintChoice(true);

            switch (choice) {
                case 0 -> {
                    System.out.println("Keluar.");
                    break loopLogic;
                }
                case 1 -> {
                    while (true) {
                        System.out.println("* Masukkan 0 untuk keluar");
                        System.out.println("1x Gacha Rp10.000");
                        int maxGacha = listUser.fill[indexUserLogin].money / hargaGacha;
                        System.out.println("Max gacha: " + maxGacha + "x");
                        System.out.print("Gacha: ");
                        int totalGacha = sc.nextInt();
                        if (totalGacha > listUser.fill[indexUserLogin].backpack.getSlot()) {
                            System.out.println("Slot tidak cukup.");
                            continue;
                        } else if (totalGacha > maxGacha) {
                            System.out.println("Uang anda kurang.");
                            System.out.println("-".repeat(lengthConsole));
                            continue;
                        } else if (totalGacha == 0) {
                            break;
                        }
                        Card[] gacha = listUser.fill[indexUserLogin].gacha(totalGacha);
                        System.out.println("=".repeat(lengthConsole));
                        System.out.printf("| %-10s | %-18s | Rp%-10s |\n", "Rarity", "Pokemon", "Harga");
                        System.out.println("-".repeat(lengthConsole));
                        int totalPrice = 0;
                        for (Card card : gacha) {
                            System.out.printf("| %-10s | %-18s | Rp%-10s |\n", card.rarity, card.pokemon, moneyFormat(card.harga));
                            totalPrice += card.harga;
                            listUser.fill[indexUserLogin].backpack.addCard(card);
                        }
                        System.out.println("-".repeat(lengthConsole));
                        System.out.printf("%" + (lengthConsole - 2) + "s |\n", "Total Harga: Rp" + moneyFormat(totalPrice));
                        System.out.println("=".repeat(lengthConsole));
                    }
                    System.out.println("=".repeat(lengthConsole));
                }
                case 2 -> {
                    printlnCenter("Daftar Pokemon", lengthConsole);
                    System.out.println("=".repeat(lengthConsole));
                    System.out.printf("| %-10s | %-18s | Rp%-10s |\n", "Rarity", "Pokemon", "Harga");
                    System.out.println("-".repeat(lengthConsole));
                    int totalPrice = 0;
                    if (listUser.fill[indexUserLogin].backpack.getTotalFill() == 0) {
                        printlnCenter("Tidak ada.", lengthConsole);
                    } else {
                        for (Card card : listUser.fill[indexUserLogin].backpack.getCards()) {
                            System.out.printf("| %-10s | %-18s | Rp%-10s |\n", card.rarity, card.pokemon, moneyFormat(card.harga));
                            System.out.printf("| %-10s | %-18s | Rp%-10s |\n", card.rarity, card.pokemon, moneyFormat(card.harga));
                            totalPrice += card.harga;
                            listUser.fill[indexUserLogin].backpack.addCard(card);
                        }
                    }
                    System.out.println("-".repeat(lengthConsole));
                    System.out.printf("%" + (lengthConsole - 2) + "s |\n", "Total Harga: Rp" + moneyFormat(totalPrice));
                    System.out.println("=".repeat(lengthConsole));
                    System.out.println("""
                            Menu Backpack:
                            1. Jual
                            2. Upgrade""");
                    System.out.println("Pilihan: ");
                    int pil = sc.nextInt();
                }
                case 3 -> {
                    System.out.println("""
                            -Pilihan Upgrade Bpack-
                            1. +5 Slot
                            2. +10 Slot
                            Pilihan :\s""");
                    int upgrade = sc.nextInt();
                    switch (upgrade) {
                        case 1:
                            Backpack.upgrade(5);
                            break;
                        case 2:
                            Backpack.upgrade(10);
                            break;
                    }
                }
            }
        } while (true);
    }

    static void printInfoUser(User user) {
        System.out.printf("%-15s: %s\n", "Username", user.getName());
        System.out.printf("%-15s: Rp%s\n", "Uang", moneyFormat(user.money));
        System.out.printf("%-15s: %s\n", "Backpack", user.backpack.getTotalFill() + "/" + user.backpack.getSlot() + " Pokemon");
    }

    static String moneyFormat(int sum) {
        return String.format("%,d", sum);
    }
}
