package leet.code.solutions.sliding_window;

/*
567

https://leetcode.com/problems/permutation-in-string/description/

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:

1 <= s1.length, s2.length <= 104
s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString {

    public static void main(String[] args) {
        String pattern = "adc";
        String str = "dcda";

        boolean isPermutation = checkInclusion(pattern, str);
        System.out.println(isPermutation);
    }


    private static boolean checkInclusion(String pattern, String str) {
        if(str.isEmpty() || pattern.isEmpty()){
            return false;
        }

        if(pattern.length() > str.length()){
            return false;
        }

        if(pattern.equals(str)){
            return true;
        }

        int strLen = str.length();
        int patternLen = pattern.length();

        char[] patternChars = new char[26];
        for(char c : pattern.toCharArray()){
            patternChars[c - 'a']++;
        }

        int left = 0;
        char[] strChars = new char[26];

        for (int right = 0; right < strLen; right++) {

            strChars[str.charAt(right) - 'a']++;

            if( right - left + 1 == patternLen){//hit window

                if(isSameCharCounts(patternChars,strChars)){
                    return true;
                }

                strChars[str.charAt(left) - 'a']--;//decrease count by char leaving the window
                left++;//shrink the window
            }
        }
        return false;
    }

    private static boolean isSameCharCounts(char[] patternChars, char[] strChars) {
        for(int i = 0 ; i < strChars.length ; i++){
            if(patternChars[i] != strChars[i]){
                return false;
            }
        }
        return true;
    }
}