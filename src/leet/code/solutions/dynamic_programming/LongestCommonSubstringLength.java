package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
The longest common substring problem is the problem of finding the longest string (or strings) that is a substring (or are substrings) of two strings.

For example, the longest common substring of strings
ABABC,
BABCA

is the string BABC having length 4.

 Other common substrings are ABC, A, AB, B, BA, BC, and C.

 */
public class LongestCommonSubstringLength {

    public static void main(String[] args) {
        String first = "ABBA";
        String sec   = "CBBA";

        int firstLen = first.length();
        int secondLen = sec.length();

        int longestCommonSubstringLen = LCSLength(first, sec, firstLen, secondLen);

        System.out.println(longestCommonSubstringLen);
    }

    private static int LCSLength(String firstStr, String secondStr, int firstStrLen, int secondStrLen) {
        int maxLCSLen = 0;//res

        int[][] DP = new int[firstStrLen + 1][secondStrLen + 1];

        // MIND we are starting from 1
        for (int firstStrIndex = 1; firstStrIndex <= firstStrLen; firstStrIndex++) {
            for (int secondStrIndex = 1; secondStrIndex <= secondStrLen; secondStrIndex++) {


                if(firstStr.charAt(firstStrIndex - 1) == secondStr.charAt(secondStrIndex - 1)){// if current prvious chars are COMMON

                    DP[firstStrIndex][secondStrIndex] = DP[firstStrIndex - 1][secondStrIndex - 1] + 1; // set lookup +1

                    //if current lookup is BIGGER than max
                    if(DP[firstStrIndex][secondStrIndex] > maxLCSLen){
                        maxLCSLen = DP[firstStrIndex][secondStrIndex];//reset MAX
                    }
                }
            }
        }

        return  maxLCSLen;
    }
}
