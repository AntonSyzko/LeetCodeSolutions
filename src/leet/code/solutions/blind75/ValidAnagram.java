package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/valid-anagram/

Given two strings s and t, return true if t is an anagram of s, and false otherwise.



Example 1:

Input: s = "anagram", t = "nagaram"

Output: true

Example 2:

Input: s = "rat", t = "car"

Output: false



Constraints:

1 <= s.length, t.length <= 5 * 104
s and t consist of lowercase English letters.


Follow up: What if the inputs contain Unicode characters? How would you adapt your solution to such a case?
 */

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static void main(String[] args) {
        String str1 = "anagram";
        String str2 = "nagaram";
        System.out.println(isAnagramAlphabet(str1, str2));

        str1 = "rat";
        str2 = "car";
        System.out.println(isAnagramAlphabet(str1, str2));
    }


    private static boolean isAnagramAlphabet(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }

        int[] alphabet = new int[26];

        //both length are same , we have checked
        for (int i = 0; i < str1.length(); i++) {

            alphabet[str1.charAt(i) - 'a']++;//incerase letter count in alphabet cell
            alphabet[str2.charAt(i) - 'a']--;//decrease letter count in alphabet cell

        }

        for (int letterPostion : alphabet) {
            if (letterPostion != 0) {
                return false;
            }
        }

        return true;
    }

    private static boolean isAnagramMap(String str1, String str2) {
        Map<Character, Integer> mapOfLetterCounts = new HashMap<>();

        for (char letter : str1.toCharArray()) {
            mapOfLetterCounts.put(letter, mapOfLetterCounts.getOrDefault(letter, 0) + 1);
        }

        for (char letter : str2.toCharArray()) {
            mapOfLetterCounts.put(letter, mapOfLetterCounts.getOrDefault(letter, 0) - 1);
        }

        for (int val : mapOfLetterCounts.values()) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }
}
