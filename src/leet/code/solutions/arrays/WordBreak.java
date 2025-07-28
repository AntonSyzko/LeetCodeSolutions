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
        String word = "leetcode";
        List<String> dictionary = List.of("leet", "code");

        boolean res = wordBreakDP(word, dictionary);
        System.out.println(res);
//
//        word = "applepenapple";
//        dictionary = List.of("apple", "pen");
//        res = wordBreak(word, dictionary);
//        System.out.println(res);

//        String word = "aaaaaaa";
//
//        List<String> dictionary = List.of("aaaa", "aaa");
//        boolean res = wordBreakDynamic(word, dictionary);
//        System.out.println(res);
    }

    private static boolean wordBreak(String s, List<String> wordDict) {
        boolean res = false;

        Set<String> setOfUniques = new HashSet<>(wordDict);//for O(1) access

        StringBuilder currentWord = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            currentWord.append(s.charAt(i));

            if (setOfUniques.contains(currentWord.toString())) {
                res = true;
                currentWord = new StringBuilder();//reset current ongoing//basically scrap the previously built string as such
            } else {
                res = false;//res will be alternating as we go but what's matter is the RES at the very END of iteration
            }
        }
        return res;
    }

    private static boolean wordBreakDP(String s, List<String> wordDict) {
        boolean[] DP = new boolean[s.length() + 1];
        DP[0] = true;

        Set<String> dict = new HashSet<>(wordDict);//O(1) lookup

        for(int inWordIndex = 1; inWordIndex <= s.length(); inWordIndex++){
            for(int fromStart = 0; fromStart < inWordIndex; fromStart++){

                if(DP[fromStart] && dict.contains(s.substring(fromStart,inWordIndex))){
                    DP[inWordIndex] = true;
                    break;//one true entry is anough
                }
            }
        }
        return DP[s.length()];
    }

    private static boolean wordBreakDynamic(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);//for O(1) access

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i < dp.length; i++) {//start from 1 as 0 set to true

            for (int j = i - 1; j >= 0; j--) {//iterate backwards from  i -1 to 0

                String currentSubstr  = s.substring(j, i);

                if (dp[j] && set.contains(currentSubstr)) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    /*
        Time & Space Complexity
            Time complexity:
                O(n∗m∗t)
            Space complexity:
                O(n)

      Where
      n is the length of the string ,
      m is the number of words in wordDict and
      t is the maximum length of any word in wordDict.
     */
    private static boolean wordBreakDPBottomUp(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;//out of bounds true, means all the word got processed if we reached

        for(int i = s.length() - 1; i >= 0; i--) {//reverse iter
            for(String word : wordDict) {

                if(i + word.length() <= s.length() // if in s bounds
                && s.startsWith(word, i)) {//and word matches

                    dp[i] =  dp[i+word.length()];//check next word if true

                    if(dp[i]) {
                        break;//match of one of the words of dictionary -> enough iterating
                    }
                }
            }
        }

        return dp[0];
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

    private static boolean wordBreakRec(String str, List<String> wordDict) {

       int startIndex = 0;
        return canBreak(str, wordDict, startIndex);
    }

    private static boolean canBreak(String str, List<String> wordDict, int index) {
        //BASE
        if(index == str.length()){//index exhaousted
            return true;//if we reached here the entire word matched hence TRUE
        }

        for(String word : wordDict){


            if( index + word.length() <= str.length()){//not out of bound of the very str

                String currComboWord = str.substring(index,  index + word.length());

                if(currComboWord.equals(word)){
                    if( canBreak(str, wordDict,  index + word.length())){//recur with moved INDEX to check if next combo is also true
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean wordBreakWithMemo(String str, List<String> wordDict) {

        Map<Integer, Boolean> memo = new HashMap<>();

        memo.put(str.length(), true); // out of bounds is TRUE

        int startIndex = 0;

        return canBreakWithMemo(str, wordDict, startIndex, memo);
    }

    private static boolean canBreakWithMemo(String str, List<String> wordDict, int index,  Map<Integer, Boolean> memo) {
        //BASE
        if(memo.containsKey(index)){
            return memo.get(index);
        }

        for(String word : wordDict){

            if(index + word.length() <= str.length()){//not out of bound of the very str

                String currComboWord = str.substring(index, index + word.length());

                if(currComboWord.equals(word)){
                    if( canBreak(str, wordDict, index + word.length())){//recur with moved INDEX to check if next combo is also true
                        memo.put(index, true);
                        return true;
                    }
                }
            }
        }

        memo.put(index, false);

        return false;
    }
}
