package leet.code.solutions.two_pointers;

/*
https://neetcode.io/problems/palindromic-substrings

Given a string s, return the number of substrings within s that are palindromes.

A palindrome is a string that reads the same forward and backward.

Example 1:

Input: s = "abc"

Output: 3
Explanation: "a", "b", "c".

Example 2:

Input: s = "aaa"

Output: 6
Explanation: "a", "a", "a", "aa", "aa", "aaa". Note that different substrings are counted as different palindromes even if the string contents are the same.

Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        String s = "abc";
        int palindromes = countSubstrings(s);
        System.out.println(palindromes);
    }

    private static int countSubstrings(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            int palsOdd = countPals(s, i, i);
            count += palsOdd;

            int palsEven = countPals(s, i,i + 1 );
            count += palsEven;

        }
        return count;
    }

    private static int countPals(String s, int start, int end) {
        int count = 0;
        while (start >= 0  && end< s.length() && s.charAt(start) == s.charAt(end)) {

            count++;

            start--;
            end++;
        }

        return count;
    }
}
