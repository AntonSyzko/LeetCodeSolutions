package leet.code.solutions.dynamic_programming;

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

    }


    public int minDistance_DP(String word1, String word2) {
        int w1Len = word1.length(), w2Len = word2.length();

        int[][] dp = new int[w1Len][w2Len];
        //fill dp
        for (int i = 0; i < w1Len; i++) {
            for (int j = 0; j < w2Len; j++) {
                dp[i][j] = -1;
            }
        }
        return dfs_DP(0, 0, word1, word2, w1Len, w2Len, dp);
    }

    private int dfs_DP(int w1Index, int w2Index, String word1, String word2, int w1Len, int w2Len, int[][] dp) {

        if (w1Index == w1Len) return w2Len - w2Index;
        if (w2Index == w2Len) return w1Len - w1Index;

        if (dp[w1Index][w2Index] != -1) return dp[w1Index][w2Index];

        if (word1.charAt(w1Index) == word2.charAt(w2Index)) {
            dp[w1Index][w2Index] = dfs(w1Index + 1, w2Index + 1, word1, word2, w1Len, w2Len);
        } else {
            int res = Math.min(dfs(w1Index + 1, w2Index, word1, word2, w1Len, w2Len),
                    dfs(w1Index, w2Index + 1, word1, word2, w1Len, w2Len));
            res = Math.min(res, dfs(w1Index + 1, w2Index + 1, word1, word2, w1Len, w2Len));
            dp[w1Index][w2Index] = res + 1;
        }
        return dp[w1Index][w2Index];
    }

    //--------------- recursive --------------
    public int minDistance(String word1, String word2) {
            int w1Len = word1.length();
            int w2Len = word2.length();

            return dfs(0, 0, word1, word2, w1Len, w2Len);
    }

    /*
    Time & Space Complexity
            Time complexity:
            O(3 m+n)

            Space complexity:
            O(m+n)

            Where m is the length of word1 and n is the length of word2.
     */
    private int dfs(int w1Index, int w2Index, String word1, String word2, int w1Len, int w2Len) {
        if(w1Index == w1Len){
            return w1Len -1;
        }

        if(w2Index == w2Len){
            return w2Len - 1;
        }

        //case 1: both chars are same at their idexes
        if(word1.charAt(w1Index) == word2.charAt(w2Index)){
            //just advance indexes
            return dfs(w1Index + 1 , w2Index + 1, word1, word2, w1Len, w2Len);
        }

        //min of advance either one at a time
        int res = Math.min(
                dfs(w1Index +1 ,w2Index,word1, word2,w1Len,w2Len),//insert operation
                dfs(w1Index  ,w2Index,word1 +1 , word2,w1Len,w2Len)//delete operation
        );

        res = Math.min(res,    dfs(w1Index +1 ,w2Index +1,word1, word2,w1Len,w2Len));//replace operation

        return res +1 ;//+1 took one more operation anyways
    }
}
