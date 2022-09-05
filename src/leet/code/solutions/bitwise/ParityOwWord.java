package leet.code.solutions.bitwise;

/*
parity is number of bits set to 1 is ODD ( 1 )  or EVEN (0)  ?
010101 -  ODD : parity of 1
01010  - EVEN:  parity of 0
 */
public class ParityOwWord {
    public static void main(String[] args) {
        long num = 2L;
        short parity = parity(num);
        System.out.println(" parity of " + num + " is " + parity);
        num = 3L;
        parity = parity(num);
        System.out.println(" parity of " + num + " is " + parity);
    }

    private static short parity(long x) {
        short parity = 0;

        while (x != 0) {
            //The XOR (^) operator compares each binary digit of two integers and gives back 1 if both the compared bits are different.
            // This means that if bits of both the integers are 1 or 0 the result will be 0; otherwise, the result will be 1:
            parity ^= (x & 1);// compare to 1 and modulo 2 ( ^=)
            x >>>= 1;//shift right to compare next bit ( drop rightmost one )
        }
        return parity;
    }

    private static short parityOptimized(long x) {
        short parity = 0;

        while (x != 0) {
            parity ^= 1; // current parity compared to 1 ( so it's 0 or 1 ay the end )
            x &= (x - 1); //drops the lowest set bit of x ( x -1 :  1-1= 0  or 0 - 1 = -1 ) qnd then drop it
        }

        return parity;
    }

}
