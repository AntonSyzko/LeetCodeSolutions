package leet.code.solutions.bitwise;

public class SwapBits {

    public static void main(String[] args) {
       long num = 5L;
       long swapped = swapBits(num, 1,2);
        System.out.println(swapped);
    }

    private static long swapBits(long num, int firstToSwap, int secToSwap) {
        //extract first and sec bits and see if they differ
        if (((num >>> firstToSwap) & 1) != ((num >>> secToSwap) & 1)) {//x >>> bitToExtract => gives you extracted, and & to 1 gives you 1 or 0

//select bits to flip with bitMask. Since x^1 = 0 when x=1 and x^1=1 when x=0, we can perform XOR operation
            long bitMask = (1L << firstToSwap) | (1L << secToSwap);
            //XOR with bitMask
            num = num ^ bitMask;
        }
        return num;
    }

}
