package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
https://leetcode.com/problems/jewels-and-stones/

You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have.
 Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.
Letters are case sensitive, so "a" is considered a different type of stone from "A".

Example 1:
Input: jewels = "aA", stones = "aAAbbbb"
Output: 3

Example 2:
Input: jewels = "z", stones = "ZZ"
Output: 0

Constraints:
1 <= jewels.length, stones.length <= 50
jewels and stones consist of only English letters.
All the characters of jewels are unique.
 */
public class StonesAndJewels {

    public static void main(String[] args) {
        String stones = "aAAbbb";
        String jewels = "aA";
        int numberOfJewelsInStones = numJewelsInStonesOptimal(jewels, stones);
        System.out.println(numberOfJewelsInStones);
    }

    // O(n)  // key here indexOf which is -1 when not found and the fact that jewels are less than stones
    private static int numJewelsInStonesOptimal(String jewels, String stones) {
        int res = 0;

        for (int i = 0; i < stones.length(); i++) {//iterate over all stones
            if (jewels.indexOf(stones.charAt(i)) > -1) { // if by stone index there's same jewel ( not -1 when there is NOT )
                res++;//match
            }
        }
        return res;
    }


    // O ( m * n )
    private static int numJewelsInStonesASCII(String jewels, String stones) {
        int res = 0;

        Set<Integer> jewelsSetASCII = new HashSet();

        for (char eachInJewels : jewels.toCharArray()) {
            jewelsSetASCII.add(eachInJewels - 'a');
        }

        for (char eachInStones : stones.toCharArray()) {
            if (jewelsSetASCII.contains(eachInStones - 'a')) {
                res++;
            }
        }
        return res;
    }


    // O ( n^2)
    private static int numJewelsInStonesBruteForce(String jewels, String stones) {
        int res = 0;

        for (char eachInStones : stones.toCharArray()) {
            for (char eachInJewels : jewels.toCharArray()) {
                if (eachInJewels == eachInStones) {
                    res++;
                }
            }
        }
        return res;
    }
}
