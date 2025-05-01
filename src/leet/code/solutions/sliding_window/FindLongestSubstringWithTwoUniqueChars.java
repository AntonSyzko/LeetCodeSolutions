package leet.code.solutions.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
Given a string, find the longest substring that contains only two unique characters.

Forexample, given "abcbbbbcccbdddadacb", the longest substring that contains 2 unique
character is "bcbbbbcccb".

 */
public class FindLongestSubstringWithTwoUniqueChars {

    public static void main(String[] args) {
        String input = "abcbbbbcccbdddadacb";
        String result = findLongestSubstringWithTwoChars(input);
        System.out.println("The longest substring with 2 unique characters is: " + result);
    }

    private static String findLongestSubstringWithTwoChars(String s) {
        if (s == null || s.length() <= 2) {
            return s; // If string is null or has 2 or fewer chars, it's already the answer
        }

        // Use a map to track character frequencies in the current window
        Map<Character, Integer> charFrequency = new HashMap<>();

        int maxLengthRes = 0;

        int startIndex = 0; // Track start index of the longest substring

        int windowStart = 0;

        // Extend the window with windowEnd pointer
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char rightChar = s.charAt(windowEnd);

            // Add current character to our frequency map
            charFrequency.put(rightChar, charFrequency.getOrDefault(rightChar, 0) + 1);

            // Shrink the window until we have exactly 2 unique characters
            while (charFrequency.size() > 2) {
                char leftChar = s.charAt(windowStart);

                // Reduce frequency of the character going out of the window
                charFrequency.put(leftChar, charFrequency.get(leftChar) - 1);

                // If frequency becomes zero, remove the character from map
                if (charFrequency.get(leftChar) == 0) {
                    charFrequency.remove(leftChar);
                }

                windowStart++; // Shrink the window
            }

            // Update maximum length and starting position of the substring
            int currentWindowLength = windowEnd - windowStart + 1;

            if (currentWindowLength > maxLengthRes) {
                maxLengthRes = currentWindowLength;
                startIndex = windowStart;//update start
            }
        }

        // Extract the longest substring
        return s.substring(startIndex, startIndex + maxLengthRes);
    }
}
