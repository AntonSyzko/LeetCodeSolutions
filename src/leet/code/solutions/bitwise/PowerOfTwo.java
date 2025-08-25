package leet.code.solutions.bitwise;

public class PowerOfTwo {

    public static void main(String[] args) {
        int n = 16;

        System.out.println(isPowerOfTwo(n));

        n = 15;

        System.out.println(isPowerOfTwo(n));

    }

    public  static boolean isPowerOfTwo(int n) {

        return n >= 0 && Integer.bitCount(n) == 1;
    }

}