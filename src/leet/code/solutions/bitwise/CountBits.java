package leet.code.solutions.bitwise;

/*
count bits set to 1
number 3  - bitwise 11 -> bits set to 1 is 2
number 2  - bitwise 10 -> bits set to 1 is 1
 */
public class CountBits {

    public static void main(String[] args) {
        int num = 3;
        short bitsSetTo1 = countBits(num);
        System.out.println(bitsSetTo1);

    }

    private static short countBits(int x) {
        short bits = 0;

        while (x != 0) {
            bits += (x & 1);// x & 1 -> x AND 1 compares last bit of X to 1 - is rightmost bit of X is 1 -> we add it or 0 (zero) if it's not
            x >>>= 1; //shift right ( drops rightmost one )  to compare next bit
        }

        return bits;
    }
}
