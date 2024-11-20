package model;

public class User {
    private String nama;
    public static int uang = 150000;
    private String password;
    public Backpack backpack;

    public User(String nama, String password) {
        backpack = new Backpack();
        this.nama = nama;
        this.password = password;
    }

    public String getnama() {
        return nama;
    }

    public String getpass() {
        return password;
    }
}
