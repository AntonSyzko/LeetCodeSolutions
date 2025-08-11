package leet.code.solutions.dynamic_programming;

/*

https://www.techiedelight.com/longest-common-substring-problem/

The longest common substring problem is the problem of finding the longest string (or strings) that is a substring (or are substrings) of two strings.

For example, the longest common substring of strings
ABABC,
BABCA

is the string BABC having length 4.

 Other common substrings are ABC, A, AB, B, BA, BC, and C.
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        String first = "ABABC";
        String sec   = "BABA";

        int firstLen = first.length();
        int secondLen = sec.length();

        String longestCommonSubstring = LCSLength(first, sec, firstLen, secondLen);

       System.out.println(longestCommonSubstring);
}

/*

Time Complexity: O(m × n)
Space Complexity: O(m × n)
 */

private static String LCSLength(String firstString, String secondString, int firstLen, int secLen){

       int maxLength = 0;// stores the max length of LCS

       int endingIndex =  0; // stores the ending index of LCS in `firstString`

    // `lookup[i][j]` stores the length of LCS of substring `firstString[0…i-1]`, `secondString[0…j-1]`
       int[][] lookup = new int[firstLen + 1][secLen + 1];

        // fill the lookup table in a bottom-up manner
        for(int i = 1; i <= firstLen; i++){//start from 1
           for(int j = 1; j <= secLen; j++){

               // if the current ( previous)  character of `firstString` and `secondString` matches
               if( firstString.charAt(i - 1) == secondString.charAt(j - 1)){//chars are common

                   lookup[i][j] = lookup[i - 1][j - 1] + 1;//common length in lookup table increases by 1

                   // update the maximum length and ending index
                   if(lookup[i][j] > maxLength){
                       maxLength = lookup[i][j];
                       endingIndex = i;
                   }
               }
           }
       }

        // return longest common substring having length `maxlen`
       return firstString.substring(endingIndex - maxLength, endingIndex);
    }
}
