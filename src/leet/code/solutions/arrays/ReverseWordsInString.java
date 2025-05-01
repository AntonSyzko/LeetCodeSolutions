package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/reverse-words-in-a-string/

Given an input string s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.

Return a string of the words in reverse order concatenated by a single space.

Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.



Example 1:

Input: s = "the sky is blue"
Output: "blue is sky the"
Example 2:

Input: s = "  hello world  "
Output: "world hello"
Explanation: Your reversed string should not contain leading or trailing spaces.
Example 3:

Input: s = "a good   example"
Output: "example good a"
Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.


Constraints:

1 <= s.length <= 104
s contains English letters (upper-case and lower-case), digits, and spaces ' '.
There is at least one word in s.


Follow-up: If the string data type is mutable in your language, can you solve it in-place with O(1) extra space?
 */
public class ReverseWordsInString {

    public static void main(String[] args) {
        // Test cases
        String[] tests = {
                "the sky is blue",
                "  hello world  ",
                "a good   example"
        };

        for (String test : tests) {
            System.out.println("Input: \"" + test + "\"");
            System.out.println("Using split method: \"" + reverseWords(test) + "\"");
            System.out.println("Using in-place method: \"" + reverseWordsInPlace(test) + "\"");
            System.out.println("--------");
        }
    }

    /**
     * Reverses order of words in a string, handling multiple spaces correctly.
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(n) for storing the result
     */
    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";

        // Trim leading and trailing spaces, and handle single character case
        s = s.trim();
        if (s.length() <= 1) return s;

        // Split by one or more spaces
        String[] words = s.split("\\s+");

        // Reverse the words
        StringBuilder result = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    //less efficient
    private static String reverseWords2(String s) {
        if(s.isEmpty()) return "";
        if(s.length() == 1) return s;

        String[] words = s.split(" ");

        int left = 0;
        int right = words.length - 1;

        while (left < right) {

            String temp = words[left];
            words[left] = words[right];
            words[right] = temp;
            left++;
            right--;
        }

        return String.join(" ", words);
    }

    //---------------- follow up

    /**
     * In-place solution (if string was mutable, as in follow-up)
     * Using char array to simulate in-place operation in Java
     * Time Complexity: O(n) where n is the length of the string
     * Space Complexity: O(n) for char array, but conceptually O(1) extra space
     */
    public static String reverseWordsInPlace(String s) {
        if (s == null || s.length() == 0) return "";

        // Convert to character array for in-place operations
        char[] chars = s.toCharArray();
        int n = chars.length;

        // Step 1: Reverse the entire string
        reverse(chars, 0, n - 1);

        // Step 2: Reverse each word
        int start = 0;
        for (int end = 0; end < n; end++) {
            if (chars[end] == ' ') {
                reverse(chars, start, end - 1);
                start = end + 1;
            }
        }

        // Reverse the last word
        reverse(chars, start, n - 1);

        // Step 3: Clean up spaces (one pass)
        return cleanSpaces(chars, n);
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }

    private static String cleanSpaces(char[] chars, int n) {
        int i = 0, j = 0;

        // Skip leading spaces
        while (j < n && chars[j] == ' ') j++;

        // Process the rest of the string
        while (j < n) {
            // Copy non-space character
            if (chars[j] != ' ') {
                chars[i++] = chars[j++];
            }
            // Handle spaces
            else {
                // Add exactly one space
                chars[i++] = ' ';

                // Skip all consecutive spaces
                while (j < n && chars[j] == ' ') j++;
            }
        }

        // Handle trailing spaces
        if (i > 0 && chars[i - 1] == ' ') i--;

        return new String(chars, 0, i);
    }

}
