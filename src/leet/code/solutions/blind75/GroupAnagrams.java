package leet.code.solutions.blind75;

import leet.code.solutions.tree_traversals.NAryTreePostOrder;

import java.util.*;

/*
https://leetcode.com/problems/group-anagrams/

Given an array of strings strs, group the anagrams together. You can return the answer in any order.



Example 1:

Input: strs = ["eat","tea","tan","ate","nat","bat"]

Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

Explanation:

There is no string in strs that can be rearranged to form "bat".
The strings "nat" and "tan" are anagrams as they can be rearranged to form each other.
The strings "ate", "eat", and "tea" are anagrams as they can be rearranged to form each other.
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
       String[] anagrams = {"eat", "tea", "tan", "ate", "nat", "bat"};
       List<List<String>> res = groupAnagrams(anagrams);
       System.out.println(res);
    }

    /*
    Time Complexity for Alternative Approach: O(n * k)

        n = number of strings
        k = maximum length of a string
     */
    private static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for(String str : strs) {

            //count occurrences of each char
            int[] alphabets = new int[26];
            for(char letter : str.toCharArray()) {
                alphabets[letter - 'a']++;
            }

            //build unique string representing common anagram base
            StringBuilder uniqueStr = new StringBuilder();

            for(int index = 0; index < 26; index++) {
                // Append # as a separator to ensure uniqueness
                uniqueStr.append('#'); // to separate things like 1, 11  or 3,33
                uniqueStr.append(alphabets[index]);
            }

            String uniqueAnagramBase  = uniqueStr.toString();

            // Add to the appropriate anagram key  group
            if(!map.containsKey(uniqueAnagramBase)) {
                map.put(uniqueAnagramBase, new ArrayList<>());
            }

            map.get(uniqueAnagramBase).add(str);

        }

        return new ArrayList<>(map.values());
    }

    /*
    Time and Space Complexity Analysis
        Time Complexity: O(n * k * log k)

        n = number of strings in the input array
        k = maximum length of a string in the array
        For each string (n iterations):

        We sort it which takes O(k log k) time
        Other operations (like putting in the map) take O(1) time


        Thus, overall time complexity is O(n * k * log k)

        Space Complexity: O(n * k)

        We need to store all the strings (total length is n * k)
        The hash map will have at most n entries (in the worst case, no anagrams)
        Each entry in the hash map points to a list containing some strings
        In total, we store all strings exactly once
        Thus, overall space complexity is O(n * k)
     */

    private static List<List<String>> groupAnagramsSorting(String[] strs) {

            if (strs == null || strs.length == 0) {
                return new ArrayList<>();
            }
             Map<String, List<String>> map = new HashMap<>();

        for (int str = 0; str < strs.length; str++) {

            String currStr = strs[str];

            char[] chars = currStr.toCharArray();
            Arrays.sort(chars);
            String currSorted = new String(chars);

            if(!map.containsKey(currSorted)){
                map.put(currSorted, new ArrayList<>());
            }

            map.get(currSorted).add(currStr);
        }

         return new ArrayList<>(map.values());
    }
}
