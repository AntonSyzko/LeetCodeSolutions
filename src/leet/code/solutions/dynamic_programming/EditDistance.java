package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*
https://neetcode.io/problems/edit-distance

You are given two strings word1 and word2, each consisting of lowercase English letters.

You are allowed to perform three operations on word1 an unlimited number of times:

Insert a character at any position
Delete a character at any position
Replace a character at any position
Return the minimum number of operations to make word1 equal word2.

Example 1:

Input: word1 = "monkeys", word2 = "money"

Output: 2
Explanation:
monkeys -> monkey (remove s)
monkey -> monkey (remove k)

Example 2:

Input: word1 = "neatcdee", word2 = "neetcode"

Output: 3
Explanation:
neatcdee -> neetcdee (replace a with e)
neetcdee -> neetcde (remove last e)
neetcde -> neetcode (insert o)

Constraints:

0 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {

    public static void main(String[] args) {
        String s1 = "intention";
        String s2 = "execution";

        int stepsTaken = minDistanceDP(s1, s2);
        System.out.println(stepsTaken);
    }


    /*
    Time Complexity:
         O(m × n) with memoization
    Space Complexity:
         O(m × n) for the memo table + O(m + n) for recursion stack
     */
    private static int minDistanceRecursiveWIthMemo(String word1, String word2) {

        Map<String, Integer> memo = new HashMap<>();

        int w1Index = 0;
        int w2Index = 0;

        return dfs(w1Index, w2Index, word1, word2, memo);
    }

    private static int dfs(int w1Index, int w2Index, String word1, String word2, Map<String, Integer> memo) {
        // BASE
        if (w1Index == word1.length()) {
            // Need to insert remaining characters from word2
            return word2.length() - w2Index;
        }

        if (w2Index == word2.length()) {
            // Need to delete remaining characters from word1
            return word1.length() - w1Index;
        }

        // Check memoization
        String key = w1Index + "," + w2Index;

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int result;

        // If characters match, no operation needed
        if (word1.charAt(w1Index) == word2.charAt(w2Index)) {

            result = dfs(w1Index + 1, w2Index + 1, word1, word2, memo);//advance both

        } else {
            // Try all three operations and take minimum
            int insert = dfs(w1Index, w2Index + 1, word1, word2, memo);     // Insert char from word2
            int delete = dfs(w1Index + 1, w2Index, word1, word2, memo);     // Delete char from word1
            int replace = dfs(w1Index + 1, w2Index + 1, word1, word2, memo); // Replace char in word1

            result = 1 + Math.min(replace,
                         Math.min(insert, delete));
        }

        memo.put(key, result);

        return result;
    }

    private static int minDistanceDP(String word1, String word2) {
        int [][] DP = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                DP[i][j] = -1;
            }
        }

        return dfs_DP(0, 0, word1, word2, DP);
    }

    private static int dfs_DP(int w1Index, int w2Index, String word1, String word2, int[][] DP) {
        //BASE
        if (w1Index == word1.length()) {
            return word2.length() - w2Index;
        }

        if(w2Index == word2.length()) {
            return word1.length() - w1Index;
        }

        if(DP[w1Index][w2Index] != -1) {
            return DP[w1Index][w2Index];
        }

        int result;

        if(word1.charAt(w1Index) == word2.charAt(w2Index)) {

            result = dfs_DP(w1Index + 1, w2Index + 1, word1, word2, DP);

        } else{

            int insert = dfs_DP(w1Index, w2Index + 1, word1, word2, DP);// Insert char from word2
            int delete = dfs_DP(w1Index + 1, w2Index, word1, word2, DP);// Delete char from word1
            int replace = dfs_DP(w1Index + 1, w2Index +1, word1, word2, DP);// Replace char in word1

            result = 1 + Math.min(replace,
                         Math.min(insert, delete));
        }

        DP[w1Index][w2Index] = result;

        return result;
    }
}
