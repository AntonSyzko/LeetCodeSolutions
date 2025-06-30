package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
720

https://leetcode.com/problems/longest-word-in-dictionary/description/

Given an array of strings words representing an English Dictionary, return the longest word in words that can be built one character at a time by other words in words.

If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.

Note that the word should be built from left to right with each additional character being added to the end of a previous word.

Example 1:

Input: words = ["w","wo","wor","worl","world"]
Output: "world"
Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:

Input: words = ["a","banana","app","appl","ap","apply","apple"]
Output: "apple"
Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 30
words[i] consists of lowercase English letters.

 */
public class LongestWordInDictionary {

    public static void main(String[] args) {
        // Test case 1
        String[] words1 = {"w","wo","wor","worl","world"};
        System.out.println("Test 1: " + longestWord(words1)); // Expected: "world"

        // Test case 2
        String[] words2 = {"a","banana","app","appl","ap","apply","apple"};
        System.out.println("Test 2: " + longestWord(words2)); // Expected: "apple"

        // Test case 3 - your failing case
        String[] words3 = {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"};
        System.out.println("Test 3: " + longestWord(words3)); // Expect  "mocha"
    }

  /*
  Big O Analysis

        Time Complexity: O(n × m) where n = number of words, m = average word length

        We check each word once: O(n)
        For each word, we check all its prefixes: O(m)
        Set lookup is O(1)


    Space Complexity:
         O(n × m) for storing all words in the HashSet
   */
    private static String longestWord(String[] words) {

        Set<String> seenWords = new HashSet<>(Arrays.asList(words));//O(1) lookup

        String longest = "";

        for (String word : words) {

            if(canBeBuiltLexicographically(word, seenWords)){

                if(word.length() > longest.length() // longer word
                        || //OR
                  (word.length() == longest.length() && word.compareTo(longest) < 0)){//same length but lexicograph smaller

                    longest = word;

                }
            }
        }

        return longest;
    }


    private static boolean canBeBuiltLexicographically(String word, Set<String> seenWords) {

        for(int i = 1 ; i < word.length() ; i++){//start from 1 !!! otherwise substring (0,0) will yeild empty str which is not in a set and will never work

            String plusCharAdded = word.substring(0, i);

            if(!seenWords.contains(plusCharAdded)){
                return false;
            }

        }

        return true;
    }

    }
