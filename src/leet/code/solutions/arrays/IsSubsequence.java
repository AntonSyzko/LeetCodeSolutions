package leet.code.solutions.arrays;

/*
392

https://leetcode.com/problems/is-subsequence/description/


Given two strings s and t, return true if s is a subsequence of t, or false otherwise.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false

Constraints:

0 <= s.length <= 100
0 <= t.length <= 104
s and t consist only of lowercase English letters.
 */
public class IsSubsequence {

    public static void main(String[] args) {
      String s= "abc";
      String t = "ahbgdc";

        System.out.println(isSubsequence(s,t));

        String s1 = "acb";
        String t1 = "ahbgdc";

        System.out.println(isSubsequence(s1,t1));

    }

    private static boolean isSubsequence(String s, String pattern) {
        if(s.isEmpty()) return true;
        if(s.equals(pattern)) return true;

        int indexInS = 0;//index in S , will be increasing each time we see same char in pattern

        for (int i = 0; i < pattern.length(); i++) {//index of t just grows in loop

            if(s.charAt(indexInS) == pattern.charAt(i)) {
                indexInS++;
            }

            if(indexInS >= s.length()) {//if increased index in S reached length of s

                return true;//return fast

            }
        }

        //pattern length exhausted but indexS in S did not increas enought times
        return false;
    }

    private static boolean isSubsequence2(String s, String pattern) {
        if(s.isEmpty())return  true;
        if(s.equals(pattern)) return true;

        int sPointer = 0 ;
        int patternPointer = 0 ;

        while (patternPointer < pattern.length()) {

            if(pattern.charAt(patternPointer) == s.charAt(sPointer)) {//same chars met

                sPointer++;

                if(sPointer == s.length()) {//exhausted s length -> done
                    return true;
                }
            }

            patternPointer++;
        }

        return false;
    }
}
