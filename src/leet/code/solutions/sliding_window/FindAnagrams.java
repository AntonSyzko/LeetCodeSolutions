package leet.code.solutions.sliding_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/find-all-anagrams-in-a-string/description/

438. Find All Anagrams in a String

Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.

Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".


Constraints:

1 <= s.length, p.length <= 3 * 104
s and p consist of lowercase English letters.
 */
public class FindAnagrams {

    public static void main(String[] args) {
        String s = "abab";
        String p = "ab";

        List<Integer> anagramStarts = findAnagrams(s, p);
        System.out.println(anagramStarts);
    }

    /*
    Time and Space Complexity
        O(n × 26) = O(n) where n is length of string s

        Time Complexity: O(n ) where n = s.length,

        Space Complexity: O(1) - only using fixed-size arrays
     */

    private static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        int[] patternCounts = new int[26];

        for (char c : p.toCharArray()) {
            patternCounts[c - 'a']++;
        }

        int left = 0;

        int[] windowCounts  =  new int[26];

        for (int right = 0; right < sLen ; right ++) {

            windowCounts[s.charAt(right) - 'a']++;

            if(right - left + 1 == pLen){

                if(Arrays.equals(patternCounts, windowCounts)){
                    result.add(left);
                }

                windowCounts[s.charAt(left) - 'a']--;
                left++;

            }
        }

        return result;
    }

    // Alternative optimized version using character count matching
    public static List<Integer> findAnagramsOptimized(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();

        if (sLen < pLen) return result;

        int[] patternCounts = new int[26];
        int[] windowCounts = new int[26];

        // Initialize pattern counts and first window
        for (int i = 0; i < pLen; i++) {
            patternCounts[p.charAt(i) - 'a']++;
            windowCounts[s.charAt(i) - 'a']++;
        }

        // Check first window
        if (Arrays.equals(patternCounts, windowCounts)) {
            result.add(0);
        }

        // Slide the window
        for (int i = pLen; i < sLen; i++) {
            // Add new character to window
            windowCounts[s.charAt(i) - 'a']++;

            // Remove character that's sliding out of window
            windowCounts[s.charAt(i - pLen) - 'a']--;

            // Check if current window is an anagram
            if (Arrays.equals(patternCounts, windowCounts)) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }
}
