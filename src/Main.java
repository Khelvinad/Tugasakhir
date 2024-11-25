import model.Backpack;
import model.Card;
import model.User;
import utils.ListUser;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ListUser listUser = new ListUser();
    static boolean login = false;
    protected static int lengthConsole = 50;
    protected static int userIdCounter = 1;
    protected static int indexUserLogin = -1;
    protected static int hargaGacha = 5000;
    protected static User userLogin;

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            auth();
            if (login) gamesLogic();
        }
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
                            indexUserLogin = i;
                            userLogin = users[i];
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
                    logout();
                    break loopLogic;
                }
                case 1 -> {
                    while (true) {
                        System.out.println("* Masukkan 0 untuk keluar");
                        System.out.println("1x Gacha Rp" + moneyFormat(hargaGacha));
                        int maxGacha = userLogin.backpack.getEmptySlot();
                        System.out.println("Max gacha: " + maxGacha + "x");
                        System.out.print("Gacha: ");
                        int totalGacha = sc.nextInt();
                        if (totalGacha > listUser.fill[indexUserLogin].backpack.getSlot()) {
                            System.out.println("Uang atau slot tidak cukup.");
                            System.out.println("-".repeat(lengthConsole));
                            continue;
                        } else if (totalGacha > maxGacha) {
                            System.out.println("Uang atau slot anda kurang.");
                            System.out.println("-".repeat(lengthConsole));
                            continue;
                        } else if (totalGacha == 0) {
                            break;
                        }
                        listUser.fill[indexUserLogin].money -= totalGacha * hargaGacha;
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
                    loopBackpack:
                    while (true) {
                        printlnCenter("Daftar Pokemon", lengthConsole);
                        printlnCenter(userLogin.backpack.getTotalFill() + "/" + userLogin.backpack.getSlot() + " Pokemon", lengthConsole);
                        System.out.println("=".repeat(lengthConsole));
                        System.out.printf("| %2s | %-10s | %-14s | Rp%-9s |\n", "ID", "Rarity", "Pokemon", "Harga");
                        System.out.println("-".repeat(lengthConsole));
                        int totalPrice = 0;
                        if (listUser.fill[indexUserLogin].backpack.getTotalFill() == 0) {
                            printlnCenter("Tidak ada.", lengthConsole);
                        } else {
                            Card[] cards = listUser.fill[indexUserLogin].backpack.getCards();
                            for (int i = 0; i < cards.length; i++) {
                                if (cards[i] != null) {
                                    System.out.printf("| %2d | %-10s | %-14s | Rp%-9s |\n", i + 1, cards[i].rarity, cards[i].pokemon, moneyFormat(cards[i].harga));
                                    totalPrice += cards[i].harga;
                                } else {
                                    System.out.printf("| %2d | %-10s | %-14s | %-11s |\n", i + 1, "-", "-", "-");
                                }
                            }
                        }
                        System.out.println("-".repeat(lengthConsole));
                        System.out.printf("%" + (lengthConsole - 2) + "s |\n", "Total Harga: Rp" + moneyFormat(totalPrice));
                        System.out.println("=".repeat(lengthConsole));
                        System.out.println("""
                                Menu Backpack:
                                1. Jual Kartu
                                2. Upgrade Bacpack
                                0. Kembali""");
                        System.out.print("Pilihan: ");
                        int pil = sc.nextInt();
                        switch (pil) {
                            case 1 -> {
                                if (listUser.fill[indexUserLogin].backpack.getLastIndexCard() == -1) {
                                    System.out.println("Tidak ada kartu yang tersedia. Lakukan gacha!");
                                    break;
                                }
                                System.out.print("ID: ");
                                int id = sc.nextInt();
                                userLogin.money += userLogin.backpack.getCards()[id - 1].harga;
                                listUser.fill[indexUserLogin].backpack.sellCard(id - 1);
                            }
                            case 2 -> {
                                System.out.println("""
                                        Upgrade Backpack:
                                        1. +5 Slot - Rp50.000
                                        2. +10 Slot - Rp100.000""");
                                System.out.print("Pilihan: ");
                                int upgrade = sc.nextInt();
                                switch (upgrade) {
                                    case 1:
                                        userLogin.money -= 50000;
                                        userLogin.backpack.upgrade(5);
                                        break;
                                    case 2:
                                        userLogin.money -= 100000;
                                        userLogin.backpack.upgrade(10);
                                        break;
                                }
                                System.out.println("Backpack ter-upgrade.");
                            }
                            case 0 -> {
                                break loopBackpack;
                            }
                        }
                        System.out.println("=".repeat(lengthConsole));
                    }
                    System.out.println("=".repeat(lengthConsole));
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
