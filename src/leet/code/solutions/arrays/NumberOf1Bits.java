package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/number-of-1-bits/
Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).
Note:
Note that in some languages, such as Java, there is no unsigned integer type. In this case, the input will be given as a signed integer type. It should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 3, the input represents the signed integer. -3.

Example 1:
Input: n = 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.

Example 2:
Input: n = 00000000000000000000000010000000
Output: 1
Explanation: The input binary string 00000000000000000000000010000000 has a total of one '1' bit.

Example 3:
Input: n = 11111111111111111111111111111101
Output: 31
Explanation: The input binary string 11111111111111111111111111111101 has a total of thirty one '1' bits.

Constraints:The input must be a binary string of length 32.

Follow up: If this function is called many times, how would you optimize it?
 */
public class NumberOf1Bits {

    public static void main(String[] args) {
        int n = 1000000101;
        int res = hammingWeight22(n);
        System.out.println("\r\n " + res);
    }

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    public static int hammingWeightBitwise(int n) {
        int countOfSignedBits = 0;

        while (n != 0) {
            countOfSignedBits += (n % 2) & 1; //if last set bit ( by MOD 2 we get it ) is 1 or 0 ( by comparing & with 1 )
            n >>>= 1; //shift LEFT 
        }
        return countOfSignedBits;
    }

    public static int hammingWeightBitwiseDetailed(int n) {
        int countOfSignedBits = 0;

        int lastCurrentBit = 0;
        while (n != 0) {

            lastCurrentBit = n % 2 ; // MOD 2 gives us the last bit ( will be 1 or zero , and checked below )

            if((lastCurrentBit & 1) == 1){ // AND 1 checks if last current bit is 1 or zero
                countOfSignedBits ++;
            }
            n >>>= 1; //shift LEFT with zeros ( >> without and won't work for negative nums )
        }
        return countOfSignedBits;
    }


    public static int hammingWeight22(int n) {

        String binaryN = Integer.toBinaryString(n);

        char[] array = binaryN.toCharArray();

        List<Character> chars = new ArrayList<>();

        for (char each : array) {
            chars.add(each);
        }

        return (int) chars.stream().filter(e -> e == '1').count();
    }


    public static int hammingWeight2(int n) {

        String nAsString = Integer.toString(n);
        int countOfOneBits = 0;

        char[] chars = nAsString.toCharArray();
        for (char each : chars) {
            if (each == '1') {
                countOfOneBits++;
            }
        }
        return countOfOneBits;
    }


}
