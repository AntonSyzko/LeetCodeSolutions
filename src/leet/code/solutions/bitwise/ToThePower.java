package leet.code.solutions.bitwise;

public class ToThePower {
    public static void main(String[] args) {
        int num = 2;
        long toPower = toPowerOf(num, 4);
        System.out.println(toPower);
    }

    private static long toPowerOf(int num, int power) {
        //minus one since bit shift doesn't start from 0 ( so 1 will be power of 2, we need to extract 1 always )
        return (long) num << power - 1;
    }
}
