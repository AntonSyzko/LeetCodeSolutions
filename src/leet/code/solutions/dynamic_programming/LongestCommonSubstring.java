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
        String sec = "BABA";

        String longestCommonSubstring = LCSLength(first, sec, first.length(), sec.length());

                System.out.println(longestCommonSubstring);
}

private static String LCSLength(String firstString, String secondString, int firstLen, int secLen){

       int maxLength = 0;// stores the max length of LCS
       int endingIndex =  firstLen; // stores the ending index of LCS in `X`

    // `lookup[i][j]` stores the length of LCS of substring `X[0…i-1]`, `Y[0…j-1]`
       int[][] lookup = new int[firstLen+1][secLen+1];

        // fill the lookup table in a bottom-up manner
        for(int i = 1; i <= firstLen; i++){
           for(int j = 1; j <= secLen; j++){

               // if the current character of `X` and `Y` matches
               if( firstString.charAt(i-1) == secondString.charAt(j-1)){
                   lookup[i][j] = lookup[i-1][j-1] + 1;

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
