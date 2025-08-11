package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

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
      int ways = decodeWays("12");//2
        System.out.println(ways);

         ways = decodeWays("121");//3
        System.out.println(ways);

        ways = decodeWaysMAp("441");//1
        System.out.println(ways);
    }
    /*
    Time Complexity:

        O(n) where n is the length of the string
        We process each character once and perform constant-time operations

Space Complexity:

        Array implementation: O(n) for the DP array
        HashMap implementation: O(n) for the HashMap
     */

    private static int decodeWays(String str) {
        int[] DP = new int[str.length() + 1];//+ 1 since we will put 0 first as there is 1 way to decode zero length string - and this one way is no way

        DP[0] = 1; // one way to decode zero length string - there is 1 way to decode zero length string - and this one way is no way
        DP[1] =  str.charAt(0) == '0' ? 0 : 1;//if it is zero - 0 ways  otherwise one way

        for (int i = 2; i <= str.length() ; i++) {//start from 2 as we have populated 0 and 1

            int oneDigit = Integer.valueOf(str.substring(i - 1, i));//substract that 1 digit

            int twoDigits = Integer.valueOf(str.substring(i - 2, i));//substract 2 digits

            if(oneDigit >= 1){
                DP[i] += DP[i - 1];// += !
            }

            if(twoDigits >= 10 && twoDigits <= 26){
                DP[i] += DP[i-2];//this adds on top of line 108
            }
        }
        return DP[str.length()];
    }

    private static int decodeWaysMAp(String str) {
        if(str.isEmpty()) return 0;
        if(str.startsWith("0") ) return 0;

        Map<Integer,Integer> DP = new HashMap<>();//index to number of ways to decode at this index
        DP.put(0,1);// one way to decode zero length string - there is 1 way to decode zero length string - and this one way is no way
        DP.put(1,str.charAt(0) == '0' ? 0 : 1);//if it is zero - 0 ways  otherwise one way

        for (int i = 2; i <= str.length(); i++) {
            int numWays = 0;

            int oneDigit = Integer.parseInt(str.substring(i-1, i));
            int twoDigits = Integer.parseInt(str.substring(i-2, i));

            // If one digit is valid (not 0), add ways from previous position
            if(oneDigit >= 1){
                numWays += DP.get(i-1);
            }

            // If two digits form a valid character (10-26), add ways from two positions back
            if(twoDigits >= 10 && twoDigits <= 26){
                numWays += DP.get(i-2);
            }

            // If no valid decodings at this position, return 0
            if(numWays == 0){
                return 0;//fail fast
            }

            DP.put(i, numWays);
        }

        return DP.get(str.length());
    }
    }
