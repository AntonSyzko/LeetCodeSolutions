package leet.code.solutions.sliding_window;

/*
https://leetcode.com/problems/longest-repeating-character-replacement/description/

You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.


Constraints:

1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
 */
public class LongestRepeatingCharacterAfterReplacement {

    public static void main(String[] args) {
        String s = "ABAB";
        int k = 2;
        int result = characterReplacement(s, k);
        System.out.println("Length of longest substring with same letters after replacement: " + result);

        s = "AABABBA";
        k = 1;
        result = characterReplacement(s, k);
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
            charFrequency[currentChar - 'A']++;

            // Update the max frequency if current character's frequency is higher
            maxFrequency = Math.max(maxFrequency, charFrequency[currentChar - 'A']);

            // Current window size is (windowEnd - windowStart + 1)
            // Number of characters to be replaced = window size - frequency of most frequent character
            // If characters to be replaced > k, shrink the window
            if (windowEnd - windowStart + 1 - maxFrequency > k) {
                // Decrease the frequency of the character at windowStart
                charFrequency[s.charAt(windowStart) - 'A']--;
                windowStart++; // Shrink the window
            }

            // Calculate the current max length
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }


}
