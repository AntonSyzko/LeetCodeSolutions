package leet.code.solutions.arrays;

/*

https://leetcode.com/problems/ransom-note/description/


Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.



Example 1:

Input: ransomNote = "a", magazine = "b"
Output: false
Example 2:

Input: ransomNote = "aa", magazine = "ab"
Output: false
Example 3:

Input: ransomNote = "aa", magazine = "aab"
Output: true
 */
public class RansomeNote {

    public static void main(String[] args) {

        String ransome = "aa";
        String magaz = "aab";
        System.out.println(canConstruct(ransome, magaz));

    }

    private static boolean canConstruct(String ransomNote, String magazine) {
        if(magazine.length() < ransomNote.length()) return false;

        int[] chars = new int[26];
        for (char c : magazine.toCharArray()) {
            chars[c - 'a']++;
        }

        int withdawnRansomeNoteLetters = ransomNote.length();

        for (char c : ransomNote.toCharArray()) {
            if(chars[c - 'a'] == 0) {
                return false;
            }else{
                chars[c - 'a']--;
                withdawnRansomeNoteLetters--;
            }
        }

        return withdawnRansomeNoteLetters == 0;
    }
}
