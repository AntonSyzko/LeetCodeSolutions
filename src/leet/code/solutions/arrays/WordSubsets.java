package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/word-subsets/

You are given two string arrays words1 and words2.

A string b is a subset of string a if every letter in b occurs in a including multiplicity.

For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.

Return an array of all the universal strings in words1. You may return the answer in any order.



Example 1:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]

Output: ["facebook","google","leetcode"]

Example 2:

Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lc","eo"]

Output: ["leetcode"]

Example 3:

Input: words1 = ["acaac","cccbb","aacbb","caacc","bcbbb"], words2 = ["c","cc","b"]

Output: ["cccbb"]



Constraints:

1 <= words1.length, words2.length <= 104
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.

 */
public class WordSubsets {

    public static void main(String[] args) {
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String [] words2 = {"e","o"};
        List<String> subsets = wordSubsets(words1, words2);
        System.out.println(subsets);
    }


    private static List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> res = new ArrayList<>();

        int[] maxFreq = new int[26];

        for(String s : words2){
            int[] currChars = new int[26];
            for(char c : s.toCharArray()){
                currChars[c -'a']++;
            }

            for(int i = 0; i < 26; i++){
                maxFreq[i] = Math.max(maxFreq[i], currChars[i]);
            }
        }

        for(String s : words1){
            int [] currChars = new int[26];
            for(char c : s.toCharArray()){
                currChars[c -'a']++;
            }

            if(sameForBoth(currChars, maxFreq)){
                res.add(s);
            }
        }

        return res;
    }

    private  static boolean sameForBoth(int[] currChars, int[] maxFreq){
        for(int i = 0; i < 26; i++){
            if( currChars[i] < maxFreq[i] ){
                return false;
            }
        }

        return true;
    }
}
