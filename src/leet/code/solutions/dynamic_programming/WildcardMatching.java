package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
44

https://leetcode.com/problems/wildcard-matching/description/


Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "*"
Output: true
Explanation: '*' matches any sequence.
Example 3:

Input: s = "cb", p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.

Constraints:

0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters, '?' or '*'.
 */
public class WildcardMatching {

    public static void main(String[] args) {
        String s = "cbe";
        String p = "c?*";

        System.out.println(isMatch(s, p));
    }

    /*
    O( m * n)
    O( m * n )

    m = s.len
    n = p.len
      */
    private   static boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();

        boolean[][] DP = new boolean[sLen + 1][pLen + 1];//len +1
        DP[0][0] = true;//empty str cell is true

        // 1. set  pattern * defaults
        for(int pIndex = 1; pIndex <= pLen; pIndex++) {//start from 1

            if(p.charAt(pIndex - 1) == '*'){
                DP[0][pIndex] = DP[0][pIndex - 1];
            }

        }

        //2. compare s and p positions
        for(int sIndex = 1; sIndex <= sLen; sIndex++) {
            for(int pIndex = 1; pIndex <= pLen; pIndex++) {


                if(s.charAt(sIndex - 1) == p.charAt(pIndex - 1) || p.charAt(pIndex - 1) == '?'){

                    DP[sIndex][pIndex] = DP[sIndex -1][pIndex - 1];

                }else if (p.charAt(pIndex-1) == '*'){

                    DP[sIndex][pIndex] = DP[sIndex -1][pIndex] || DP[sIndex][pIndex - 1];// any TRUE is OK

                }

            }
        }

        return DP[sLen][pLen];//last
    }
}