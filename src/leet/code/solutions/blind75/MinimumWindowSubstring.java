package leet.code.solutions.blind75;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/minimum-window-substring/

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:

m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
           String s = "ADOBECODEBANC";
           String target = "ABC";

           String minWindow =  minWindow(s, target);
           System.out.println(minWindow);
    }


    private static String minWindow(String s, String targetStr) {
        // Edge cases
        if (s == null || targetStr == null || s.length() == 0 || targetStr.length() == 0 || s.length() < targetStr.length()) {
            return "";
        }

        // Frequency map for characters in targetStr
        Map<Character, Integer> targetMap = new HashMap<>();
        for (char c : targetStr.toCharArray()) {
            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
        }

        // Frequency map for characters in the current window
        Map<Character, Integer> currentStrWindowMap = new HashMap<>();

        // Sliding window variables
        int left = 0;
        int minLengthSoFar = Integer.MAX_VALUE;
        int minLeft = 0;
        int required = targetMap.size(); // Number of unique characters in targetStr
        int formed = 0; // Number of unique characters in current window that match the frequency in targetStr

        // Sliding window algorithm
        for (int right = 0; right < s.length(); right++) {

            char rightChar = s.charAt(right);

            // Add right character to curr window
            currentStrWindowMap.put(rightChar, currentStrWindowMap.getOrDefault(rightChar, 0) + 1);

            // Check if this character is in targetStr and if we have matched its required frequency
            if (targetMap.containsKey(rightChar) && currentStrWindowMap.get(rightChar).intValue() == targetMap.get(rightChar).intValue()) {
                formed++;
            }

            // Try to minimize the window by moving left pointer
            while (left <= right && formed == required) {//formed == required means str that  target length is SAME now as str we have formed
                char leftChar = s.charAt(left);

                // Update minimum window if current is smaller
                int windowLength = right - left + 1;

                if (windowLength  <  minLengthSoFar) {//curr window length is smaller than min so far
                    minLengthSoFar = windowLength;
                    minLeft = left;
                }

                // Remove left character from window
                currentStrWindowMap.put(leftChar, currentStrWindowMap.get(leftChar) - 1);

                // If this character is in targetStr and removing it breaks the required frequency
                if (targetMap.containsKey(leftChar) && currentStrWindowMap.get(leftChar) < targetMap.get(leftChar)) {
                    formed--;
                }

                // Move left pointer
                left++;
            }
        }

        return minLengthSoFar == Integer.MAX_VALUE ? "" : s.substring(minLeft, minLeft + minLengthSoFar);
    }
}

/*
        Algorithm Steps:

        Initialize frequency maps:

        Count characters in t to create targetMap
        Initialize an empty windowMap for the current window


        Initialize window pointers and tracking variables:

        left: left edge of the window
        minLength: length of smallest valid window found
        minLeft: starting index of smallest valid window
        required: number of unique characters in t
        formed: number of characters that meet the frequency requirement


        Expand window with right pointer:

        Add each character to windowMap
        If the character exists in targetMap and its frequency matches, increment formed


        Contract window with left pointer when valid:

        When formed == required, we have a valid window
        Try to minimize it by moving the left pointer
        Update minLength and minLeft if a smaller valid window is found
        Stop contracting when the window becomes invalid


        Return result:

        If no valid window found, return empty string
        Otherwise, return the substring using minLeft and minLength
 */