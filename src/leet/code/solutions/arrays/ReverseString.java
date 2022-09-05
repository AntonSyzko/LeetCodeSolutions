package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://leetcode.com/problems/reverse-string/

Write a function that reverses a string. The input string is given as an array of characters s.
You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:
Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]

Example 2:
Input: s = ["H","a","n","n","a","h"]
Output: ["h","a","n","n","a","H"]

Constraints:
1 <= s.length <= 105
s[i] is a printable ascii character.
 */
public class ReverseString {
    public static void main(String[] args) {
        char[] array = new char[]{'h', 'e', 'l', 'l', 'o'};
        reverseString(array);

       array = new char[]{'H','a','n','n','a','h'};
        reverseString(array);
    }

    public static void reverseString(char[] s) {

        int start = 0;
        int end = s.length - 1;

        while (start < end) {
            //swap in place
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;

            end--;
            start++;
        }
        System.out.println(Arrays.toString(s));
    }
}
