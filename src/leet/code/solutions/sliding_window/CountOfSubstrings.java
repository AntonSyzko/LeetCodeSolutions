package leet.code.solutions.sliding_window;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
3305

https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-i/description/

You are given a string word and a non-negative integer k.

Return the total number of substrings of word that contain every vowel ('a', 'e', 'i', 'o', and 'u') at least once and exactly k consonants.

Example 1:

Input: word = "aeioqq", k = 1

Output: 0

Explanation:

There is no substring with every vowel.

Example 2:

Input: word = "aeiou", k = 0

Output: 1

Explanation:

The only substring with every vowel and zero consonants is word[0..4], which is "aeiou".

Example 3:

Input: word = "ieaouqqieaouqq", k = 1

Output: 3

Explanation:

The substrings with every vowel and one consonant are:

word[0..5], which is "ieaouq".
word[6..11], which is "qieaou".
word[7..12], which is "ieaouq".


Constraints:

5 <= word.length <= 250
word consists only of lowercase English letters.
0 <= k <= word.length - 5
 */
public class CountOfSubstrings {

    public static void main(String[] args) {
        String s = "ieaouqqieaouqq";
        int k = 1;

        int count = countOfSubstrings(s, k);
        System.out.println(count);
    }

/*
        Time Complexity : O(n²)
        Space Complexity: O(1)
  */
    private static int countOfSubstrings(String word, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        int res = 0;
        int len = word.length();

        for (int start = 0; start < len; start++) {

            Set<Character> seenVowels = new HashSet<>();//reset at each iteration
            int consonantsCount = 0;

            for (int end = start; end < len; end++) {

                char curr = word.charAt(end);

                if(vowels.contains(curr)){
                    seenVowels.add(curr);
                }else{
                    consonantsCount++;
                }

                if(seenVowels.size() == 5 && consonantsCount == k){
                    res++;
                }

                if (consonantsCount > k){
                    break;
                }
            }
        }

        return res;
    }
}