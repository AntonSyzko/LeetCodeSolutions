package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/find-words-that-can-be-formed-by-characters/description/

You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once for each word in words).

Return the sum of lengths of all good strings in words.

Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
Example 2:

Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.

Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.
 */
public class FormWordsByCharacters {

    public static void main(String[] args) {
        String [] words = {"cat","bt","hat","tree"};
        String chars = "atach";
        int  resLen = countCharacters(words,chars);
        System.out.println(resLen);

        String [] words2 = {"hello","world","leetcode"};
        String chars2 = "welldonehoneyr";
        int  resLen2 = countCharacters(words2,chars2);
        System.out.println(resLen2);
    }

    private static int countCharacters(String[] words, String chars) {
        int resLen = 0;

        int[] alpahbets = new int[26];

        for(char each : chars.toCharArray()){
            alpahbets[each -'a']++;
        }

        for(String word : words){

            int[] currWordAlphabets = new int[26];

            for(char each : word.toCharArray()){
                currWordAlphabets[each -'a']++;
            }

            if(charCountsMatch(alpahbets, currWordAlphabets)){//char counts match in both strings
                resLen += word.length();//this current word had allowed set of chars as pattern -> include in res
            }
        }

        return resLen;
    }

    private static boolean charCountsMatch(int[] alpahbets, int[] currWordAlphabets) {
        for(int i = 0; i <26; i++){

            //if word has more chars than pattern allows for this particular char -. not a valid word
            if(currWordAlphabets[i] > alpahbets[i]){
                return false;
            }
        }

        return true;
    }
}
