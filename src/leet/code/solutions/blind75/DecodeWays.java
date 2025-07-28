package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/decode-ways/description/

You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'
"2" -> 'B'
...

"25" -> 'Y'
"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.
The test cases are generated so that the answer fits in a 32-bit integer.

Example 1:

Input: s = "12"
Output: 2
Explanation:
"12" could be decoded as "AB" (1 2) or "L" (12).

Example 2:

Input: s = "226"
Output: 3
Explanation:
"226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

Example 3:

Input: s = "06"
Output: 0
Explanation:
"06" cannot be mapped to "F" because of the leading zero ("6" is different from "06"). In this case, the string is not a valid encoding, so return 0.

Constraints:

1 <= s.length <= 100
s contains only digits and may contain leading zero(s).
 */
public class DecodeWays {

    public static void main(String[] args) {
        String str = "2123";
        int decodeWays = numDecodingsBottomUp(str);
        System.out.println(decodeWays);
    }

    //time O(N), space O(N)
    private static int numDecodingsBottomUp(String s) {
        int len = s.length();

        // If the string is empty or starts with 0, there's no valid decoding
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // dp[i] = number of ways to decode s[0:i] by defau;t prefilled with zeroes
        int[] dp = new int[len + 1];
        dp[0] = 1;  // Empty string can be decoded in 1 way
        dp[1] = 1;  // Single digit (not 0) can be decoded in 1 way

        for (int i = 2; i <= len; i++) {//start from 2 !!!!!!!!!!!!!!!!

            char currentDigit = s.charAt(i - 1);
            // If the current digit is not 0, we can use it as a single digit
            if (currentDigit != '0') {
                dp[i] = dp[i] +  dp[i - 1];// fill +  with previous
            }

            // Check if the last two digits form a valid code (10-26)
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));

            if (twoDigit >= 10 && twoDigit <= 26) {//falls within 10 - 26
                dp[i] = dp[i] + dp[i - 2];//as we have above calculated for 1 digit - and here treating as two digits - add prev prev to account for 2 digits
            }
        }

        return dp[len];
    }

    //time O(n)
    //space O(1)
    private static int numDecodings(String s) {
        int len = s.length();

        // If the string is empty or starts with 0, there's no valid decoding
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        // Initialize for the first two positions
        int prev = 1;  // dp[i-2]
        int curr = 1;  // dp[i-1]

        for (int i = 1; i < len; i++) {

            int temp = 0;//is set 0 for every loop iteration

            // If current digit is not 0, we can use it as a single digit
            if (s.charAt(i) != '0') {
                temp += curr;
            }

            // Check if the last two digits form a valid code (10-26)
            int twoDigit = Integer.parseInt(s.substring(i - 1, i + 1));// i + 1 excluding hence 2 chars

            if (twoDigit >= 10 && twoDigit <= 26) {
                temp += prev;
            }

            // Update pointers
            prev = curr;
            curr = temp;
        }

        return curr;
    }
}
