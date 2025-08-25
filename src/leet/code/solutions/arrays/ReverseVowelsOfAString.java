package leet.code.solutions.arrays;

import java.util.Set;

/*
345

https://leetcode.com/problems/reverse-vowels-of-a-string/description/

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

Example 1:

Input: s = "IceCreAm"

Output: "AceCreIm"

Explanation:

The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

Example 2:

Input: s = "leetcode"

Output: "leotcede"

Constraints:

1 <= s.length <= 3 * 105
s consist of printable ASCII characters.
 */
public class ReverseVowelsOfAString {

    public static void main(String[] args) {
        String s = "IceCreAm";

        String rev = reverseVowels(s);

        System.out.println(rev);
    }

    public static String reverseVowels(String s) {
         Set<Character> vowelSet = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int left = 0;
        int right = s.length() - 1;

        char[] chars = s.toCharArray();

        while (left < right) {

            while ( left < right && !vowelSet.contains(chars[left])){//till vowel met
                left++;
            }

            while ( left < right && !vowelSet.contains(chars[right])){//till vowel met
                right--;
            }

            char temp = chars[left];//swap
            chars[left] = chars[right];
            chars[right] = temp;

            left++;//move on since vowels swapped
            right--;

        }

        return new String(chars);
    }
}