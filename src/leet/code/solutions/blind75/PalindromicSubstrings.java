package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/palindromic-substrings/

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:

Input: s = "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


Constraints:

1 <= s.length <= 1000
s consists of lowercase English letters.
 */
public class PalindromicSubstrings {

    public static void main(String[] args) {
        String str = "abc";//3
        int palindromesDetected = countSubstrings(str);
        System.out.println(palindromesDetected);

        str = "aaa";//6
         palindromesDetected = countSubstrings(str);
        System.out.println(palindromesDetected);

          str = "cabac";//7
           palindromesDetected = countSubstrings(str);
        System.out.println(palindromesDetected);

          str = "cbba";//5
           palindromesDetected = countSubstrings(str);
        System.out.println(palindromesDetected);
    }


    private static int countSubstrings(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }

        int count = 0;

        for (int index = 0; index < str.length(); index++) {

            int isOddLengthPalindrom = expandToLeftAndRight(str, index, index);
            int isEvenLengthPalindrom = expandToLeftAndRight(str, index, index+1);

            count += isOddLengthPalindrom + isEvenLengthPalindrom;

        }

        return count;

    }

    private static int expandToLeftAndRight(String str, int left, int right) {
        int palindromeOccurenceCount =  0;

        while (left>= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
            palindromeOccurenceCount++;

        }
        return palindromeOccurenceCount;
    }
}
