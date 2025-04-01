package leet.code.solutions.dynamic_programming;

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
        String first = "ABABC";
        String sec   = "BABA";

        int longestCommonSubstringLen = LCSLength(first, sec, first.length(), sec.length());

        System.out.println(longestCommonSubstringLen);
    }

    private static int LCSLength(String firstStr, String secondStr, int firstStrLen, int secondStrLen) {
        int maxLCSLen = 0;

        int[][] LCSLookup = new int[firstStrLen + 1][secondStrLen + 1];

        // MIND we are starting from 1
        for (int firstStrIndex = 1; firstStrIndex <= firstStrLen; firstStrIndex++) {
            for (int secondStrIndex = 1; secondStrIndex <= secondStrLen; secondStrIndex++) {

                if(firstStr.charAt(firstStrIndex-1) == secondStr.charAt(secondStrIndex-1)){// if current prvious chars are COMMON
                    LCSLookup[firstStrIndex][secondStrIndex] = LCSLookup[firstStrIndex-1][secondStrIndex-1] + 1; // set lookup +1

                    //if current lookup is BIGGER than max
                    if(LCSLookup[firstStrIndex][secondStrIndex] > maxLCSLen){
                        maxLCSLen = LCSLookup[firstStrIndex][secondStrIndex];//reset MAX
                    }
                }
            }
        }
        return  maxLCSLen;
    }
}
