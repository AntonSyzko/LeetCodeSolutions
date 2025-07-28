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


    // --------------------- NON DYNAMIC --------------------
    // Method to find the longest palindromic substring
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";

        int start = 0;  // Starting index of longest palindrome found
        int maxLen = 1; // Length of longest palindrome found

        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (center at i)
            int len1 = expandAroundCenter(s, i, i);

            // Check for even-length palindromes (center between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);

            // Take the maximum of the two
            int currentMaxLen = Math.max(len1, len2);

            // Update global maximum if current palindrome is longer
            if (currentMaxLen > maxLen) {
                maxLen = currentMaxLen;
                // Calculate starting position based on center and length
                //When we find a palindrome of length L centered at position i, we need to calculate where this palindrome begins in the original string.
                start = i - (currentMaxLen - 1) / 2;// i minus half center is a start of PAL, so for PAL len 5 ENDING on index 7 it will be  start = 7 - (5 - 1)/2 = 1; start on 1 end on 7 ( but in substring 7 is exlcusive so 6
            }
        }

        return s.substring(start, start + maxLen);
    }

    // Helper method to expand around center
    private  static int expandAroundCenter(String s, int left, int right) {
        // While within bounds and characters match
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // Expand outward
            left--;
            right++;
        }

        // Return the length of palindrome (right-left-1)
        // We subtract 1 because the loop increments/decrements once more after finding mismatch
        return right - left - 1;
    }

    /**

    Key Insights:

     When we find a palindrome of length L centered at position i, we need to calculate where this palindrome begins in the original string.

     Distance from center to start: In any palindrome, the starting position is exactly (L-1)/2 positions to the left of the center
        Why (L-1)/2?

        Odd length palindromes: If length is 7, there are 6 characters around the center (3 on each side)
        Even length palindromes: If length is 4, center is between two middle characters, with 1.5 characters on each side, but (4-1)/2 = 1 gives the correct offset
     **/
}
