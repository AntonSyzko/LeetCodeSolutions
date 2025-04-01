package leet.code.solutions.blind75;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
All the strings of wordDict are unique.
 */
public class WordBreak {

    public static void main(String[] args) {
        String word = "espn";
        List<String> dict = List.of("p", "es","n");
        boolean res = wordBreak(word, dict);
        System.out.println(res);
    }


    private static boolean wordBreak(String word, List<String> wordDict) {

        boolean[] DP = new boolean[word.length() + 1];// stores each substring of word occurrence in word dict
        DP[0] = true;// occurrence of substring of zero length is by default true

        Set<String> fasterDict = new HashSet<>(wordDict);

        for (int eachLetter = 1; eachLetter <= word.length(); eachLetter++) {//traverse letters of word
            for (int j = eachLetter - 1; j >=0; j--) {//go in reverse

                if(DP[j]  && // if true in DP AND
                fasterDict.contains(word.substring(j, eachLetter))){//curr substring in dict

                    DP[eachLetter] = true;//set to true for curr letter
                    break;

                }
            }
        }
        return DP[word.length()];
    }
    }
