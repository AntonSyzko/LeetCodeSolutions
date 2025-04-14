package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/longest-palindromic-substring/

Given a string s, return the longest palindromic substring in s.

Example 1:
Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.

Example 2:

Input: s = "cbbd"
Output: "bb"

Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
public class LongestPalindromicSubstring {

    // Main method for testing
    public static void main(String[] args) {

        // Test cases
        System.out.println(longestPalindrome("babad")); // Expected: "bab" or "aba"
        System.out.println(longestPalindrome("cbbd"));  // Expected: "bb"
        System.out.println(longestPalindrome("a"));     // Expected: "a"
        System.out.println(longestPalindrome("ac"));    // Expected: "a"
    }

    // Method to find the longest palindromic substring
    private static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        // Initialize variables to track the start and end of the longest palindrome
        int start = 0;
        int end = 0;

        // Iterate through each character as a potential middle of palindrome
        for (int charIndex = 0; charIndex < s.length(); charIndex++) {
            // Try to expand around center charIndex (odd length palindrome)
            int oddLen = expandAroundCenter(s, charIndex, charIndex);// charIndex is both left and right as we are dealing with ODD number

            // Try to expand around center i and i+1 (even length palindrome)
            int evenLen = expandAroundCenter(s, charIndex, charIndex + 1);// charIndex is left , charIndex + 1 is right as number of chars in EVEN

            // Get the longer palindrome length
            int len = Math.max(oddLen, evenLen);

            // Update start and end if we found a longer palindrome
            if (len > end - start) {
                // Calculate new start and end positions
                start = charIndex - (len - 1) / 2;
                end = charIndex + len / 2;
            }
        }

        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }

    // Helper method to expand around center
    private  static int expandAroundCenter(String s, int left, int right) {
        // While within bounds and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // Expand outward
            left--;
            right++;
        }

        // Return the length of palindrome (right-left-1)
        // We subtract 1 because the loop increments/decrements once more after finding mismatch
        return right - left - 1;
    }
}
