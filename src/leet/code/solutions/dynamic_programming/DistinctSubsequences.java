package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
Given a string S and a string T, count the number of distinct subsequences of T in S.
A subsequence of a string is a new string which is formed from the original string by
deleting some (can be none) of the characters without disturbing the relative positions
of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is
not).
Here is an example: S = "rabbbit", T = "rabbit"
Return 3.


 */
public class DistinctSubsequences {

    public static void main(String[] args) {
        String S = "rabbbit";
        String T = "rabbit";
        System.out.println("Number of distinct subsequences: " + numDistinct(S, T));

        // An additional smaller example for easier tracing
        String S2 = "babgbag";
        String T2 = "bag";
        System.out.println("Number of distinct subsequences for '" + S2 + "' and '" + T2 + "': " + numDistinctMemo(S2, T2));
    }

    public static int numDistinct(String first, String second) {
        // Base cases
        if (first == null || second == null) return 0;

        if (second.length() == 0) return 1;

        if (first.length() < second.length()) return 0;

        // Create a 2D DP array
        // dp[i][j] represents the number of distinct subsequences of
        // second[0...i-1] in first[0...j-1]
        int[][] dp = new int[second.length() + 1][first.length() + 1];

        // Empty string is a subsequence of any string once
        for (int firstIndex = 0; firstIndex <= first.length(); firstIndex++) {
            dp[0][firstIndex] = 1;
        }

        // Fill the dp table
        for (int secondIndex = 1; secondIndex <= second.length(); secondIndex++) {
            for (int firstIndex = 1; firstIndex <= first.length(); firstIndex++) {

                // If current characters match, we have two options:

                // 1. Use the current character of first -> dp[secondIndex][firstIndex-1]
                // 2. Don't use the current character of first -> dp[secondIndex-1][firstIndex-1]
                if (second.charAt(secondIndex - 1) == first.charAt(firstIndex - 1)) {

                    dp[secondIndex][firstIndex] = dp[secondIndex][firstIndex - 1]//use only char from first
                            +
                            dp[secondIndex - 1][firstIndex - 1];//do not use any

                } else {
                    // If characters don't match, we can only skip current character of first
                    dp[secondIndex][firstIndex] = dp[secondIndex][firstIndex - 1];
                }
            }
        }

        return dp[second.length()][first.length()];
    }

    //--------------------- MEMO -------------------------------
    private static int numDistinctMemo(String first, String second) {
        // Base cases
        if (first == null || second == null) return 0;
        if (second.length() == 0) return 1;
        if (first.length() < second.length()) return 0;

        // Create a memoization array initialized with -1
        // memo[i][j] will store the result for second[0...i] and first[0...j]
        int[][] memo = new int[second.length()][first.length()];

        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return countSubsequencesMemo(first, second, first.length() - 1, second.length() - 1, memo);
    }

    private static int countSubsequencesMemo(String first, String second, int firstLen, int secondLen, int[][] memo) {
        // Base cases, order matter
        if (secondLen < 0) return 1; // We've matched all characters in second
        if (firstLen < 0) return 0; // We've exhausted first but still have characters in second

        // If we've already computed this subproblem
        if (secondLen >= 0 && firstLen >= 0 && memo[secondLen][firstLen] != -1) {
            return memo[secondLen][firstLen];
        }

        // If current characters match, we have two options
        if (first.charAt(firstLen) == second.charAt(secondLen)) {
            // 1. Use current character of first: match it with current character of second
            // 2. Skip current character of first
            memo[secondLen][firstLen] = countSubsequencesMemo(first, second, firstLen - 1, secondLen, memo)
                                          +
                                         countSubsequencesMemo(first, second, firstLen - 1, secondLen - 1, memo);

        } else {
            // If characters don't match, we can only skip current character of first
            memo[secondLen][firstLen] = countSubsequencesMemo(first, second, firstLen - 1, secondLen, memo);
        }

        return memo[secondLen][firstLen];
    }

    //-------------------- PLAIN RECURSION ---------------------

    public static int numDistinctRec(String first, String second) {
        // Base cases
        if (first == null || second == null) return 0;
        if (second.length() == 0) return 1;
        if (first.length() < second.length()) return 0;

        return countSubsequences(first, second, first.length() - 1, second.length() - 1);
    }

    private static int countSubsequences(String first, String second, int firstLen, int secondLen) {
        // Base cases
        if (secondLen < 0) {
            // We've matched all characters in second - found a valid subsequence
            return 1;
        }

        if (firstLen < 0) {
            // We've exhausted first but still have characters in second - impossible
            return 0;
        }

        // Recursive cases
        if (first.charAt(firstLen) == second.charAt(secondLen)) {
            // Current characters match, we have two options:

            // Option 1: Use current character of first to match with second
            // This advances both indices
            int useCurrentChar = countSubsequences(first, second, firstLen - 1, secondLen - 1);

            // Option 2: Skip current character of first
            // This advances only the first index
            int skipCurrentChar = countSubsequences(first, second, firstLen - 1, secondLen);

            // Total ways = ways when using current char + ways when skipping current char
            return useCurrentChar + skipCurrentChar;
        } else {
            // Characters don't match, can only skip current character of first
            return countSubsequences(first, second, firstLen - 1, secondLen);
        }
    }
}


