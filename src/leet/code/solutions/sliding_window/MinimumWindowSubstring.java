package leet.code.solutions.sliding_window;


/*
https://leetcode.com/problems/minimum-window-substring/description/

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

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

Follow up: Could you find an algorithm that runs in O(m + n) time?
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String str = "ADOBECODEBANC";
        String pattern = "ABC";

        String min = findMinimumWindowSubstring(str, pattern);
        System.out.println(min);
    }

    /*
    Total Time Complexity: O(m + n + m * 1) = O(m + n)

    Total Space Complexity: O(1)
     */

    private static String findMinimumWindowSubstring(String str, String pattern) {
        if (str == null || pattern == null || str.isEmpty() || pattern.isEmpty() || str.length() < pattern.length()) {
            return "";
        }

        int[] patternFrequencies = new int[128];//ASCII
        for(char c : pattern.toCharArray()) {
            patternFrequencies[c]++;//frequencies of chars occured in pattern
        }

        int[] strFrequenceis = new int[128];//ASCII frequencies of chars occurred in string

        int minSubstringLengthSoFar = Integer.MAX_VALUE;
        int resultStringStartIndex = 0;

        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char c = str.charAt(windowEnd);
            strFrequenceis[c]++;

            while(compareFrequenceis(strFrequenceis, patternFrequencies)){//while character occurrences in both strings match

                int currWindowLength = windowEnd - windowStart + 1;

                if(currWindowLength < minSubstringLengthSoFar){

                    minSubstringLengthSoFar = currWindowLength;//set the min window
                    resultStringStartIndex = windowStart;//update start of future result

                }

                char charAtWindowStart = str.charAt(windowStart);
                strFrequenceis[charAtWindowStart]--;//decrease counter of windowStart char as it is leaving the window
                windowStart++;//shrink the window
            }

        }

        if(minSubstringLengthSoFar == Integer.MAX_VALUE) return "";//min length was not updated

        return str.substring(resultStringStartIndex, resultStringStartIndex + minSubstringLengthSoFar);//cut the string start + min length

    }

    private static boolean compareFrequenceis(int[] strFrequenceis, int[] patternFrequencies) {
        for (int i = 0; i < strFrequenceis.length; i++) {
            if(strFrequenceis[i] < patternFrequencies[i]) {//less is not a match
                return false;
            }
        }
        return true;
    }

    //------------------- OPTIMISED

    public static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        // Create frequency maps
        int[] patternFreq = new int[128];
        for (char c : t.toCharArray()) {
            patternFreq[c]++;
        }

        int required = t.length(); // Total number of characters needed
        int formed = 0;            // Total number of characters satisfied

        int[] windowFreq = new int[128];

        int minLen = Integer.MAX_VALUE;
        int startIndex = 0;

        int left = 0;

        // Sliding window
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // Add current character to window frequency
            windowFreq[currentChar]++;

            // If this character is in pattern and we haven't exceeded required frequency
            if (windowFreq[currentChar] <= patternFreq[currentChar]) {
                formed++;
            }

            // While we have all required characters, try to minimize the window
            while (formed == required) {
                // Update result if current window is smaller
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIndex = left;
                }

                // Remove leftmost character from window
                char leftChar = s.charAt(left);
                windowFreq[leftChar]--;

                // If removing this character makes the window invalid
                if (windowFreq[leftChar] < patternFreq[leftChar]) {
                    formed--;
                }

                left++; // Shrink window from left
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
