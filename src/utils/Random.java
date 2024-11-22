package utils;

public class Random {
    public static int init(int max, int min) {
        return (int) (Math.random() * max) + min;
    }
}
