package leet.code.solutions.arrays;

import java.util.*;

/*
https://leetcode.com/problems/group-anagrams/

Given an array of strings strs, group the anagrams together. You can return the answer in any order.
An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
typically using all the original letters exactly once.

Example 1:
Input: strs = ["eat","tea","tan","ate","nat","bat"]
Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Example 2:
Input: strs = [""]
Output: [[""]]

Example 3:
Input: strs = ["a"]
Output: [["a"]]

Constraints:
1 <= strs.length <= 104
0 <= strs[i].length <= 100
strs[i] consists of lowercase English letters.
 */
public class GroupAnagrams {

    public static void main(String[] args) {
    String [] words = {"eat","tea","tan","ate","nat","bat"};
  //  String [] words = {"eat","tea","tan"};
    List<List<String>> grouppedAnagrams = groupAnagrams(words);
        for(List<String> group : grouppedAnagrams){
            System.out.println("anagram group " + group);
        }
    }

    /*
            Time Complexity: O(n * k log k), where:

                n is the number of strings in the input array

                k is the maximum length of a string

                The sorting operation for each string takes O(k log k) time


        Space Complexity: O(n * k) for storing all n strings of max len k  in the HashMap
     */

    private static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groupedAnagrams = new ArrayList<>();//future result
        Map<String, List<String>> wordToAnagrams = new HashMap<>();//word(sorted) : [ it's anagrams ] ( aka. {ate : [tea, aet] }

        for (String current : strs) {//traverse all 

            char[] currentWordChars = current.toCharArray();

            Arrays.sort(currentWordChars);

            String sortedCurrentWord = new String(currentWordChars);

          //  wordToAnagrams.computeIfAbsent(sortedCurrentWord, k -> new ArrayList<>()).add(current);

            //of NOT in map
            if(!wordToAnagrams.containsKey(sortedCurrentWord)) {
                wordToAnagrams.put(sortedCurrentWord, new ArrayList<>());//put to map with empty array so far
            }

            wordToAnagrams.get(sortedCurrentWord).add(current);//add to existing array
        }

        //post for
        groupedAnagrams.addAll(wordToAnagrams.values());

        return groupedAnagrams;
    }
}
