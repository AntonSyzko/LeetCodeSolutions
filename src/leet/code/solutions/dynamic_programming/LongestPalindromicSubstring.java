package leet.code.solutions.dynamic_programming;

/*

https://www.callicoder.com/longest-palindromic-substring/


Given a string s, find the longest palindromic substring in s.

Example:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.

Input: "cbbd"
Output: "bb"
 */
public class LongestPalindromicSubstring {

    public static void main(String[] args) {
       String  s = "babad";
       String longestPal = findLongestPalindromicSubstring(s);
       System.out.println(longestPal);
    }


    private static String findLongestPalindromicSubstring(String input) {
        if(input.isEmpty()){
            return "";
        }

        int longestPalindromeSubstringLength = 0;
        int len = input.length();

        int start = 0;
        int end = 0;

        boolean[][] DP = new boolean[len][len];

        for (int outer = 0; outer < len ; outer++) {

            DP[outer][outer] = true;

            for (int inner = 0; inner < outer ; inner++) {

                if(input.charAt(outer) == input.charAt(inner) &&
                        (outer - inner <= 2 || DP[inner + 1 ][outer - 1])){

                    DP[inner][outer] = true;

                    if(outer - inner + 1 > longestPalindromeSubstringLength){
                        longestPalindromeSubstringLength = outer - inner + 1;
                        start = inner;
                        end = outer;
                    }
                }
            }

        }

        return input.substring(start, end + 1);
    }
}
