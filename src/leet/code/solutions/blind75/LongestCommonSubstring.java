package leet.code.solutions.blind75;

public class LongestCommonSubstring {

    public static void main(String[] args) {
      String s1 = "ddabcff";
      String s2 = "abc";

      System.out.println(longestCommonSubstringDP(s1, s2));
    }

    private static int longestCommonSubstringDP(String text1, String text2) {
        int[][] DP = new int[text1.length() + 1][text2.length() + 1];//+1 cause we will prefill zeroes ad additional cells

        for (int text1Row = text1.length()-1; text1Row >=0 ; text1Row--) {//backwards traversal of first str
            for (int text2Row = text2.length()-1; text2Row >=0 ; text2Row--) {//backwards traversal of second str

                if (text1.charAt(text1Row) == text2.charAt(text2Row)) {//both chars match

                    DP[text1Row][text2Row] = 1 + DP[text1Row + 1][text2Row + 1 ] ;//1 plus value at DIAGONAL cell

                }else{//chars do not match

                    DP[text1Row][text2Row] = Math.max(DP[text1Row + 1][text2Row], DP[text1Row][text2Row +1]);// max of values at ADJACENT cells
                }

            }
        }

        return DP[0][0];//first cell contains longest
    }



    private static int longestCommonSubstringRecursive(String text1, String text2) {
        if(text1.length()==0 || text2.length()==0){
            return 0;
        }

        int res = lcsHelper(text1, 0, text2, 0);

        return res;
    }

    private static int lcsHelper(String text1, int text1Index, String text2, int text2Index) {
        //BASE
        if(text1Index == text1.length() || text2Index == text2.length() ){ // BOTH length exhausted by reaching end of strings length
            return 0;
        }

        if(text1.charAt(text1Index) == text2.charAt(text2Index)){//letters in both strings by index ARE SAME

            return 1 + lcsHelper(text1, text1Index + 1, text2, text2Index + 1);//add1 as cost and continue in next indicies in both strings

        }else{

            int matchOnFirstString = lcsHelper(text1, text1Index + 1, text2, text2Index);//move first str index
            int matchOnSecondString = lcsHelper(text1, text1Index, text2, text2Index + 1);//move second str index

            return Math.max(matchOnFirstString, matchOnSecondString);//pick max of two
        }
    }
}
