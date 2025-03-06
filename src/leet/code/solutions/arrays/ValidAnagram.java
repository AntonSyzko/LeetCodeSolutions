package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Description
Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.

An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.

Example 1:

Input: s = "racecar", t = "carrace"

Output: true
Example 2:

Input: s = "jar", t = "jam"

Output: false
Constraints:

s and t consist of lowercase English letters.
 */
public class ValidAnagram {
    public static void main(String[] args) {
        String first = "racecar";
        String second = "carrace";
        boolean isAnagram = isValidAnagramMy(first, second);
        System.out.println(isAnagram);


    }

    private static boolean isValidAnagramMy(String fisrt, String second){
        if(fisrt==null || second==null){
            return false;
        }
        if(fisrt.length()!=second.length()){
            return false;
        }

        char[] fisrtChars = fisrt.toCharArray();
        char[] secondChars = second.toCharArray();
        Map<Character, Integer> mapa  = new HashMap<>();

        for (int i = 0; i < fisrtChars.length; i++) {
            mapa.put(fisrtChars[i], mapa.getOrDefault(fisrtChars[i], 0) + 1);
        }

        for (int i = 0; i < secondChars.length; i++) {
            if(mapa.containsKey(secondChars[i])){
                mapa.remove(secondChars[i]);
            }
        }
        if(mapa.isEmpty()){
            return true;
        }
        return false;
    }


    public  static boolean isAnagramAlphabet(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }

        int[] alphabets = new int[26];

        for (int i = 0; i < first.length(); i++) {
            alphabets[first.charAt(i) - 'a'] ++ ;
            alphabets[second.charAt(i) - 'a'] -- ;
        }

        for(int each : alphabets){
            if(each!=0){
                return false;
            }
        }
        return true;
    }


        //time O ( n + m )
        public  static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            HashMap<Character, Integer> countS = new HashMap<>();
            HashMap<Character, Integer> countT = new HashMap<>();

            for (int i = 0; i < s.length(); i++) {
                countS.put(s.charAt(i), countS.getOrDefault(s.charAt(i), 0) + 1);
                countT.put(t.charAt(i), countT.getOrDefault(t.charAt(i), 0) + 1);
            }
            return countS.equals(countT);
        }


        //time O(n long * m log m)
    public static boolean isAnagramSorting(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sSort = s.toCharArray();
        char[] tSort = t.toCharArray();
        Arrays.sort(sSort);
        Arrays.sort(tSort);
        return Arrays.equals(sSort, tSort);
    }
}
