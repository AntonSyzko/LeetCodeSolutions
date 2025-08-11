package leet.code.solutions.arrays;

import java.util.Arrays;

/*
833

https://leetcode.com/problems/find-and-replace-in-string/description/

You are given a 0-indexed string s that you must perform k replacement operations on. The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.

To complete the ith replacement operation:

Check if the substring sources[i] occurs at index indices[i] in the original string s.
If it does not occur, do nothing.
Otherwise if it does occur, replace that substring with targets[i].
For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".

All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.

For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
Return the resulting string after performing all replacement operations on s.

A substring is a contiguous sequence of characters in a string.



Example 1:


Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
Output: "eeebffff"
Explanation:
"a" occurs at index 0 in s, so we replace it with "eee".
"cd" occurs at index 2 in s, so we replace it with "ffff".
 */
public class FindAndReplaceInString {

    public static void main(String[] args) {
        String s = "abcd";
        int[] indices = {0, 2};
        String[] sources = {"a", "cd"};
        String[] targets = {"eee", "ffff"};

        String res =findReplaceString(s, indices, sources, targets);
        System.out.println(res);
    }

    private static String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        int len = s.length();
        // Array to keep track of the valid replacement indices
        int[] replacementIndices = new int[len];
        // Initialize the replacementIndices array with -1 indicating no replacement initially
        Arrays.fill(replacementIndices, -1);

        // Loop through indices to find valid replacements
        for (int index = 0; index < indices.length; ++index) {

            int replaceAt = indices[index];
            // Check if the current source string is present in 's' starting at the index 'replaceAt'
            if (s.startsWith(sources[index], replaceAt)) {
                // Mark the valid replacement index
                replacementIndices[replaceAt] = index;
            }
        }

        StringBuilder resultBuilder = new StringBuilder();

        int index = 0;

        while(index < len) {

            // If there is a valid replacement at the current index 'index', i.e. not -1
            if (replacementIndices[index] >= 0) {

                int indexToReplaceAt = replacementIndices[index];
                // Append the target replacement string to resultBuilder
                resultBuilder.append(targets[indexToReplaceAt]);
                // Increment 'index' by the length of the source at this valid index to skip replaced part
                index += sources[indexToReplaceAt].length();

            } else {

                // No valid replacement, append the current character and move to the next
                resultBuilder.append(s.charAt(index));
                index++;

            }
        }

        return resultBuilder.toString();
    }
}