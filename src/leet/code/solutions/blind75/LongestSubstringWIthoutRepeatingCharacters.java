package leet.code.solutions.blind75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/longest-substring-without-repeating-characters/

Given a string s, find the length of the longest substring without duplicate characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */
public class LongestSubstringWIthoutRepeatingCharacters {

    public static void main(String[] args) {
       String str = "abcabcbb";

       int longestWIthoutRepeating = lengthOfLongestSubstringSet(str);
        System.out.println(longestWIthoutRepeating);

         str = "bbbbb";

         longestWIthoutRepeating = lengthOfLongestSubstring(str);
        System.out.println(longestWIthoutRepeating);

         str = "pwwkew";

         longestWIthoutRepeating = lengthOfLongestSubstring(str);
        System.out.println(longestWIthoutRepeating);

        str = "abatman";
        longestWIthoutRepeating = lengthOfLongestSubstring(str);
        System.out.println(longestWIthoutRepeating);

    }

    /*
            Time Complexity: O(n) - We scan the string once with the two pointers
        Space Complexity: O(min(m, n)) where m is the size of the character set and n is the string length

        In the worst case, the HashMap could store all characters in the string
        In practice, the size is bounded by the character set (e.g., 128 for ASCII)
     */

    private static int lengthOfLongestSubstringSet(String str) {
        if(str==null || str.length()==0) return 0;
        int res = 0;

        Set<Character> seen = new HashSet<>();

        int left = 0;
        int right = 0;

        while (right < str.length()) {
            char currChar = str.charAt(right);

            if(!seen.contains(currChar)) {
                seen.add(currChar);
                res = Math.max(res, right - left);
            } else{
                seen.remove(str.charAt(left));
                left++;
            }

            right++;
        }
        return res;

    }


        private  static int lengthOfLongestSubstring(String s) {
            // Edge case - empty string
            if (s == null || s.length() == 0) {
                return 0;
            }

            int n = s.length();
            int maxLength = 0;

            // HashMap approach - store character and its most recent index
            Map<Character, Integer> charIndexMap = new HashMap<>();

            // Use sliding window technique with two pointers
            for (int right = 0, left = 0; right < n; right++) {
                char currentChar = s.charAt(right);

                // If character is already in our current window, move left pointer
                // to position after the last occurrence
                if (charIndexMap.containsKey(currentChar)) {
                    // Math.max ensures we don't move left pointer backwards
                    left = Math.max(left, charIndexMap.get(currentChar) + 1);
                }

                // Update the last position of current character
                charIndexMap.put(currentChar, right);

                // Update max length found so far
                maxLength = Math.max(maxLength, right - left + 1);
            }

            return maxLength;
    }

    public int lengthOfLongestSubstringMap(String s) {
        if (s == null || s.length() == 0) return 0;

        int res = 0;
        int left = 0;

        // Map to store the position of each character
        Map<Character, Integer> charMap = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {
            char curr = s.charAt(right);

            // If the character is already in our window, move left pointer
            if (charMap.containsKey(curr) && charMap.get(curr) >= left) {
                left = charMap.get(curr) + 1;
            }

            // Update character position
            charMap.put(curr, right);

            // Update max length
            res = Math.max(res, right - left + 1);
        }

        return res;
    }

    /*
            Solution Approach: Sliding Window with HashMap
        The most efficient approach uses a sliding window technique combined with a HashMap to track character positions.
        Algorithm Explanation

        Initialize variables:

        Create a HashMap to store each character and its most recent position
        Set maxLength to track the longest valid substring found
        Use two pointers: left and right to represent the current window


        Sliding Window Process:

        Expand the window by moving the right pointer one character at a time
        For each character at position right:

        If the character is already in our window, move the left pointer to the position just after the last occurrence of this character
        Update the character's position in our HashMap
        Calculate the current window length (right - left + 1) and update maxLength if needed




        Edge Cases:

        Empty string returns 0
        The Math.max(left, charIndexMap.get(currentChar) + 1) ensures we don't move the left pointer backwards
     */
}
