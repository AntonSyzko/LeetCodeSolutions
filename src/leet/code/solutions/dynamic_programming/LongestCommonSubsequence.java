package leet.code.solutions.dynamic_programming;


import java.util.Map;

/*
https://www.techiedelight.com/longest-common-subsequence/

The Longest Common Subsequence (LCS) problem is finding the longest subsequence present in given two sequences in the same order, i.e.,
find the longest sequence which can be obtained from the first original sequence by deleting some items and from the second original sequence by deleting other items.

mind ! subsequence are not substrings and do not contain chars positioned next to each other
X: ABCBDAB
Y: BDCABA
The length of the LCS is 4
LCS are
BDAB, BCAB, and BCBA
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
          String firstStr = "ABCBDAB";
          String secStr   = "BDCABA";

          int firstLen = firstStr.length();
          int secondLen = secStr.length();

          int longestCommonSubsequence = LCSSubsequenceLength(firstStr, secStr, firstLen, secondLen);

        System.out.println(longestCommonSubsequence);
    }

    public static int LCSSubsequenceLength(String firstString, String secondString, int firstLen, int secLen){
        //BASE
        if(firstLen == 0 || secLen == 0){//if ANY of strings length is exhausted
            return 0 ;
        }

        if(firstString.charAt(firstLen - 1) == secondString.charAt(secLen - 1)){//both Strings end with SAME character

            return  LCSSubsequenceLength(firstString, secondString, firstLen - 1, secLen - 1) + 1;//add 1 do LCS length res and continue without those last 2 same characters

        }

        return Integer.max(
                LCSSubsequenceLength(firstString, secondString, firstLen - 1, secLen), //first string without LAST char
                //OR
                LCSSubsequenceLength(firstString, secondString, firstLen, secLen - 1)//second String without LAST char
        );
    }

    public static int LCSLengthDynamic(String X, String Y, int xLen, int yLen, Map<String, Integer> lookup) {
        // return if the end of either string is reached
        if (xLen == 0 || yLen == 0) {
            return 0;
        }

        // construct a unique map key from dynamic elements of the input
        String key = xLen + "|" + yLen;

        // if the subproblem is seen for the first time, solve it and
        // store its result in a map
        if (!lookup.containsKey(key)) {
            // if the last character of `X` and `Y` matches
            if (X.charAt(xLen - 1) == Y.charAt(yLen - 1)) {
                lookup.put(key, LCSLengthDynamic(X, Y, xLen - 1, yLen - 1, lookup) + 1);
            } else {
                // otherwise, if the last character of `X` and `Y` don't match
                lookup.put(key,
                           Integer.max(LCSLengthDynamic(X, Y, xLen, yLen - 1, lookup),
                                       LCSLengthDynamic(X, Y, xLen - 1, yLen, lookup)));
            }
        }

        // return the subproblem solution from the map
        return lookup.get(key);
    }

    // Function to find the length of the longest common subsequence of substring
    // `X[0…m-1]` and `Y[0…n-1]`
    public static int LCSLengthBottomUp(String X, String Y) {
        int xLen = X.length();
        int yLen = Y.length();

        // lookup table stores solution to already computed subproblems,
        // i.e., `T[i][j]` stores the length of LCS of substring
        // `X[0…i-1]` and `Y[0…j-1]`
        int[][] T = new int[xLen + 1][yLen + 1];

        // fill the lookup table in a bottom-up manner
        for (int i = 1; i <= xLen; i++) {
            for (int j = 1; j <= yLen; j++) {
                // if the current character of `X` and `Y` matches
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {

                    T[i][j] = T[i - 1][j - 1] + 1;

                } else {// otherwise, if the current character of `X` and `Y` don't match

                    T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);

                }
            }
        }

        // LCS will be the last entry in the lookup table
        return T[xLen][yLen];
    }
}
