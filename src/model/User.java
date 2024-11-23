package model;

public class User {
    private String nama;
    public int userId;
    public static int uang = 150000;
    private String password;
    public Backpack backpack;

    public User(String nama, String password, int userId) {
        backpack = new Backpack();
        this.nama = nama;
        this.password = password;
        this.userId = userId;
    }

    public String getNama() {
        return nama;
    }

    public String getPassword() {
        return password;
    }

    public int getUserId() {
        return userId;
    }

    public void gacha(int total){
        int[] gachaIndex = new int[total];

    }
}
