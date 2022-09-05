package leet.code.solutions.arrays;

import java.util.*;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/word-break/

Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false

Constraints:
1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique
 */
public class WordBreak {

    public static void main(String[] args) {
//        String word = "leetcode";
//        List<String> dictionary = List.of("leet", "code");
//
//        boolean res = wordBreak(word, dictionary);
//        System.out.println(res);
//
//        word = "applepenapple";
//        dictionary = List.of("apple", "pen");
//        res = wordBreak(word, dictionary);
//        System.out.println(res);

        String word = "aaaaaaa";

        List<String> dictionary = List.of("aaaa", "aaa");
        boolean res = wordBreakDynamic(word, dictionary);
        System.out.println(res);
    }

//    private static boolean wordBreak(String s, List<String> wordDict) {
//        boolean res = false;
//
//        Set<String> setOfUniques = new HashSet<>(wordDict);
//        StringBuilder currentWord = new StringBuilder();
//
//        System.out.println(setOfUniques);
//
//        for (int i = 0; i < s.length(); i++) {
//
//            currentWord.append(s.charAt(i));
//
//            if (setOfUniques.contains(currentWord.toString())) {
//                res = true;
//                currentWord = new StringBuilder();//reset current ongoing
//            } else {
//                res = false;
//            }
//        }
//        return res;
//    }

    private static boolean wordBreakDynamic(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        Set<String> set = new HashSet<>(wordDict);

        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }


    public static boolean wordBreak3(String s, Set<String> wordDict) {
        int[] pos = new int[s.length()+1];

        Arrays.fill(pos, -1);

        pos[0]=0;

        for(int i=0; i<s.length(); i++){
            if(pos[i]!=-1){
                for(int j=i+1; j<=s.length(); j++){
                    String sub = s.substring(i, j);
                    if(wordDict.contains(sub)){
                        pos[j]=i;
                    }
                }
            }
        }
        return pos[s.length()]!=-1;
    }
}
