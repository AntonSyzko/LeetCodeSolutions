package leet.code.solutions.arrays;

/*
459

https://leetcode.com/problems/repeated-substring-pattern/description/

Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

Example 1:

Input: s = "abab"
Output: true
Explanation: It is the substring "ab" twice.
Example 2:

Input: s = "aba"
Output: false
Example 3:

Input: s = "abcabcabcabc"
Output: true
Explanation: It is the substring "abc" four times or the substring "abcabc" twice.

Constraints:

1 <= s.length <= 104
s consists of lowercase English letters.
 */
public class RepeatedSubstringPatterns {

    public static void main(String[] args) {
        String s = "abcabcabcabc";
        boolean repeted = repeatedSubstringPattern(s);
        System.out.println(repeted);

        s = "abab";
        repeted = repeatedSubstringPattern(s);
        System.out.println(repeted);

        s = "aba";
        repeted = repeatedSubstringPattern(s);
        System.out.println(repeted);

        s = "abac";
        repeted = repeatedSubstringPattern(s);
        System.out.println(repeted);
    }


    private static boolean repeatedSubstringPattern(String s) {
        int len = s.length();

        int allowedRepeats = len / 2;

        for (int i = allowedRepeats; i >= 1 ; i--) {// mind >= 1  to avoid division by 0

            if(len % i == 0){ // is len divisible by i at all ?

                int num_repeats = len / i; // times this substr can repeat in string

                String substr = s.substring(0, i);//always substr from beginning ( 0 ) to i

                StringBuilder sb = new StringBuilder();

                while (num_repeats > 0) {//time ww gonna repeatedly append

                    sb.append(substr);

                    num_repeats--;

                    if(sb.toString().equals(s)){//repeated N times is EXACT match with input
                        return true;
                    }

                }
            }
        }

        return false;
    }
}
