package leet.code.solutions.dynamic_programming;

/*

https://www.techiedelight.com/longest-repeated-subsequence-problem/

The Longest Repeating Subsequence (LRS) problem is finding the longest subsequences of a string that occurs at least twice.

The problem differs from the problem of finding the longest repeating substring. Unlike substrings, subsequences are not required to occupy consecutive positions within the original string.

 For example, consider the sequence ATACTCGGA.

The length of the longest repeating subsequence is 4The longest repeating subsequence is ATCG

A T A C T C G G A
A T A C T C G G A

Note that repeated characters holds a different index in the input string.


 */
public class LongestRepeatedSubsequence {

    public static void main(String[] args) {
         String str = "ATACTCGGA";

         int longestRepeatedSubseq = LRSLength(str,str.length() , str.length());

         System.out.println(longestRepeatedSubseq);
    }

    public static int LRSLength(String str, int index1, int index2){
        if(index1 == 0 || index2 == 0){
            return 0;
        }

        if(index1 !=  index2//indexes DIFF
                &&
                str.charAt(index1 - 1 ) == str.charAt(index2 - 1) ){//indexes DIFF but chars at indexes SAME

            return LRSLength(str, index1 - 1, index2 - 1) + 1;

        }

        int withoutLastCharIndex1 = LRSLength(str, index1 - 1, index2);
        int withoutLastCharIndex2 = LRSLength(str, index1 , index2 - 1);

        return Math.max(withoutLastCharIndex1, withoutLastCharIndex2);

    }

    public static int LRSLength(String X) {
        int n = X.length();

        // lookup table stores solution to already computed subproblems,
        // i.e., lookup[i][j] stores the length of LRS of substring
        // `X[0…i-1]` and `X[0…j-1]`
        int[][] lookup = new int[n + 1][n + 1];

        // fill the lookup table in a bottom-up manner
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // if characters at index `i` and `j` matches
                // and the index are different
                if (X.charAt(i - 1) == X.charAt(j - 1) && i != j) {
                    lookup[i][j] = lookup[i - 1][j - 1] + 1;
                }
                // otherwise, if characters at index `i` and `j` are different
                else {
                    lookup[i][j] = Integer.max(lookup[i - 1][j], lookup[i][j - 1]);
                }
            }
        }

        // LRS will be the last entry in the lookup table
        return lookup[n][n];
    }
}
