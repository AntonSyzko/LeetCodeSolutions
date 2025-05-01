package leet.code.solutions.sliding_window;

/*
https://www.callicoder.com/longest-substring-with-same-letters-after-replacement/

Given a string with lowercase letters only. If you are allowed to replace at most k letters with any letter, find the length of the longest substring having the same letters after replacement.

Example 1:

Input: s="abcababb", k=2
Output: 5
Explanation: Replace the two 'a' with 'b' in the substring 'ababb' to get the longest substring "bbbbb" with same letters.
Example 2:

Input: s="abccde", k=1
Output: 3
Explanation: Replace the 'b' or 'd' with 'c' to get the the longest substring "ccc" with same letters.
 */
public class LongestSubstringWithSameLettersAfterReplacement {

    public static void main(String[] args) {
        String s = "abcababb";
        int k = 2;
        int result = characterReplacement(s, k);
        System.out.println("Length of longest substring with same letters after replacement: " + result);
    }

    /*
    Time and Space Complexity
            Time Complexity: O(n)

            We process each character exactly once
            Both pointers move from left to right, never revisiting positions
            The operations inside the loop (updating frequencies and finding max) are all O(1)

            Space Complexity: O(1)

            We use a fixed-size array of length 26 to store character frequencies
            The space requirement doesn't depend on the input size
     */
    public static int characterReplacement(String s, int k) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int[] charFrequency = new int[26]; // Array to store frequency of each character
        int maxLength = 0;
        int maxFrequency = 0; // Tracks the frequency of the most frequent character
        int windowStart = 0;

        // Iterate through the string with windowEnd pointer
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            // Update frequency of the current character
            char currentChar = s.charAt(windowEnd);
            charFrequency[currentChar - 'a']++;

            // Update the max frequency if current character's frequency is higher
            maxFrequency = Math.max(maxFrequency, charFrequency[currentChar - 'a']);

            // Current window size is (windowEnd - windowStart + 1)
            // Number of characters to be replaced = window size - frequency of most frequent character
            // If characters to be replaced > k, shrink the window
            if ((windowEnd - windowStart + 1) - maxFrequency > k) {
                // Decrease the frequency of the character at windowStart
                charFrequency[s.charAt(windowStart) - 'a']--;
                windowStart++; // Shrink the window
            }

            // Calculate the current max length
            maxLength = Math.max(maxLength, ( windowEnd - windowStart + 1));
        }

        return maxLength;
    }
}
