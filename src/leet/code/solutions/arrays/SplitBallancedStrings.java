package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/split-a-string-in-balanced-strings/description/

Balanced strings are those that have an equal quantity of 'L' and 'R' characters.

Given a balanced string s, split it into some number of substrings such that:

Each substring is balanced.
Return the maximum number of balanced strings you can obtain.



Example 1:

Input: s = "RLRRLLRLRL"
Output: 4
Explanation: s can be split into "RL", "RRLL", "RL", "RL", each substring contains same number of 'L' and 'R'.
Example 2:

Input: s = "RLRRRLLRLL"
Output: 2
Explanation: s can be split into "RL", "RRRLLRLL", each substring contains same number of 'L' and 'R'.
Note that s cannot be split into "RL", "RR", "RL", "LR", "LL", because the 2nd and 5th substrings are not balanced.
Example 3:

Input: s = "LLLLRRRR"
Output: 1
Explanation: s can be split into "LLLLRRRR".
 */
public class SplitBallancedStrings {

    public static void main(String[] args) {
        String str = "RLRRLLRLRL";
        int splitted =  balancedStringSplit(str);
        System.out.println(splitted);

        str = "RLRRRLLRLL";
        splitted =  balancedStringSplit(str);
        System.out.println(splitted);
    }

    private  static int balancedStringSplit(String s) {
        if(s==null || s.isEmpty()) return 0;

        int  ongoingCharsOccurrenceCount = 0;
        int ballancedStringsRes = 0;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i)=='R'){
                ongoingCharsOccurrenceCount++;
            }else if (s.charAt(i)=='L'){
                ongoingCharsOccurrenceCount--;
            }

            if(ongoingCharsOccurrenceCount==0){
                ballancedStringsRes++;
            }
        }

        return ballancedStringsRes;
    }


    private  static int balancedStringSplit2(String s) {
        if(s==null || s.isEmpty()) return 0;

        int Rcount = 0;
        int Lcount = 0;

        int substringsCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'L') {
                Lcount++;
            }else if(s.charAt(i) == 'R') {
                Rcount++;
            }
            if(Lcount == Rcount) {
                substringsCount++;
                Lcount = 0;
                Rcount = 0;
            }
        }

        return substringsCount;
    }
}
