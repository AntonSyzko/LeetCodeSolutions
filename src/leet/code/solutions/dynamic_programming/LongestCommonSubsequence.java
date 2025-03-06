package leet.code.solutions.dynamic_programming;


import java.util.Map;

/*
https://www.techiedelight.com/longest-common-subsequence/

The Longest Common Subsequence (LCS) problem is finding the longest subsequence present in given two sequences in the same order, i.e.,
find the longest sequence which can be obtained from the first original sequence by deleting some items and from the second original sequence by deleting other items.

X: ABCBDAB
Y: BDCABA
The length of the LCS is 4LCS are
BDAB, BCAB, and BCBA
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
          String firstStr = "ABCBDAB";
          String secStr = "BDCABA";

          int longestCommonSubsequence = LCSLength(firstStr, secStr, firstStr.length(), secStr.length());
        System.out.println(longestCommonSubsequence);
    }

    public static int LCSLength(String firstString, String secondString, int firstLen, int secLen){
        //BASE
        if(firstLen== 0 || secLen==0){//if ANY of strings length is exhausted
            return 0 ;
        }

        if(firstString.charAt(firstLen - 1) == secondString.charAt(secLen - 1)){//both Strings end with same character

            return  LCSLength(firstString, secondString, firstLen - 1, secLen - 1) + 1;//add 1 do LCS length res and continue without those last 2 same characters

        }

        return Integer.max(
                LCSLength(firstString, secondString, firstLen-1, secLen), //first string without LAST char
                //OR
                LCSLength(firstString, secondString, firstLen, secLen-1));//second String without LAST char
    }




    public static int LCSLengthDynamic(String X, String Y, int m, int n, Map<String, Integer> lookup) {
        // return if the end of either string is reached
        if (m == 0 || n == 0) {
            return 0;
        }

        // construct a unique map key from dynamic elements of the input
        String key = m + "|" + n;

        // if the subproblem is seen for the first time, solve it and
        // store its result in a map
        if (!lookup.containsKey(key)) {
            // if the last character of `X` and `Y` matches
            if (X.charAt(m - 1) == Y.charAt(n - 1)) {
                lookup.put(key, LCSLengthDynamic(X, Y, m - 1, n - 1, lookup) + 1);
            }
            else {
                // otherwise, if the last character of `X` and `Y` don't match
                lookup.put(key, Integer.max(LCSLengthDynamic(X, Y, m, n-1, lookup),
                        LCSLengthDynamic(X, Y, m - 1, n, lookup)));
            }
        }

        // return the subproblem solution from the map
        return lookup.get(key);
    }

    // Function to find the length of the longest common subsequence of substring
    // `X[0…m-1]` and `Y[0…n-1]`
    public static int LCSLengthBottomUp(String X, String Y) {
        int m = X.length(), n = Y.length();

        // lookup table stores solution to already computed subproblems,
        // i.e., `T[i][j]` stores the length of LCS of substring
        // `X[0…i-1]` and `Y[0…j-1]`
        int[][] T = new int[m + 1][n + 1];

        // fill the lookup table in a bottom-up manner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // if the current character of `X` and `Y` matches
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    T[i][j] = T[i - 1][j - 1] + 1;
                }
                // otherwise, if the current character of `X` and `Y` don't match
                else {
                    T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
                }
            }
        }

        // LCS will be the last entry in the lookup table
        return T[m][n];
    }

}
