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
    private static String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }

        // Initialize variables to track the start and end of the longest palindrome
        int start = 0;
        int end = 0;

        // Iterate through each character as a potential middle of palindrome
        for (int charIndex = 0; charIndex < s.length(); charIndex++) {
            // Try to expand around center charIndex (odd length palindrome)
            int oddLen = expandAroundCenter(s, charIndex, charIndex);// charIndex is both left and right as we are dealing with ODD number

            // Try to expand around center i and i+1 (even length palindrome)
            int evenLen = expandAroundCenter(s, charIndex, charIndex + 1);// charIndex is left , charIndex + 1 is right as number of chars in EVEN

            // Get the longer palindrome length
            int len = Math.max(oddLen, evenLen);

            // Update start and end if we found a longer palindrome
            if (len > end - start) {
                // Calculate new start and end positions
                start = charIndex - (len - 1) / 2;
                end = charIndex + len / 2;
            }
        }

        // Return the longest palindromic substring
        return s.substring(start, end + 1);
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
}
