package leet.code.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/valid-anagram/

Input: s = "anagram", t = "nagaram"
Output: true

Input: s = "rat", t = "car"
Output: false
 */
public class IsAnagram {
    public static void main(String[] args) {
        String first = "anagram";
        String sec = "nagaram";
        boolean isAnagram = isAnagram2(first, sec);
        System.out.println(isAnagram);

        String first1 = "rat";
        String sec1 = "car";
        boolean isAnagram2 = isAnagram2(first1, sec1);
        System.out.println(isAnagram2);
    }

    private static boolean isAnagram3(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] alphabetToInts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            alphabetToInts[s.charAt(i) - 'a']++;
            alphabetToInts[t.charAt(i) - 'a']--;
        }

        for (int each : alphabetToInts){
            if(each != 0){
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Set<Character> setOgFirst = new HashSet<>();
        for (char ch : s.toCharArray()) {
            setOgFirst.add(ch);
        }

        Set<Character> setOgSec = new HashSet<>();
        for (char ch : t.toCharArray()) {
            setOgSec.add(ch);
        }
        if (setOgFirst.containsAll(setOgSec)) {
            return true;
        }
        return false;
    }

    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Set<Character> uniques = new HashSet<>();
        for (char ch : s.toCharArray()) {
            uniques.add(ch);
        }

        for (char ch : t.toCharArray()) {
            uniques.remove(ch);
        }

        return uniques.isEmpty();
    }
}
