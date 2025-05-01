package leet.code.solutions.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
https://www.callicoder.com/longest-substring-with-k-distinct-characters/

Given a string, find the length of the longest possible substring in it that has exactly K distinct characters. If there is no possible substring then print -1.

You can assume that K is less than or equal to the length of the given string.

Example 1:

Input: S = "aabacbebebe", K = 3
Output: 7
Explanation: "cbebebe" is the longest substring with 3 distinct characters.
Example 2:

Input:
S = "aaaa", K = 2
Output: -1
Explanation: There's no substring with 2 distinct characters.
 */
public class LongestSubstringWithKRepeatedCharacters {

    public static void main(String[] args) {
        String str = "aabacbebebe";
        int k = 3;

        int max = findLengthOfLongestSubstringWithKUniqueCharacters(str, k);
        System.out.println(max);

         str = "aaaa";
         k = 2;

         max = findLengthOfLongestSubstringWithKUniqueCharacters(str, k);
        System.out.println(max);
    }
    /*
    Big O Analysis:

        Time Complexity: O(n), where n is the length of the input string.

        Each character is processed at most twice (once when adding to the window and once when removing)
        HashMap operations (put, get, remove) are O(1) on average


        Space Complexity: O(K), where K is the number of distinct characters we're looking for.

        The HashMap will store at most K distinct characters
             */

    private static int findLengthOfLongestSubstringWithKUniqueCharacters(String str, int k) {

        Map<Character, Integer>  freqienciesMap = new HashMap<>();
        int longest = -1;

        int left = 0;
        int right = 0;

        while (right < str.length()) {
            char c = str.charAt(right);

            freqienciesMap.put(c, freqienciesMap.getOrDefault(c, 0) + 1);

            if(freqienciesMap.size() == k){ //window hit

                char leftmostChar  = str.charAt(left);
                freqienciesMap.remove(leftmostChar);//delete leftmost from window

                int currLen = right - left +1;
                longest = Math.max(longest, currLen);

                left++;//shift left window

            }

            right++;//always move right window
        }

        return longest;
    }

    /*
    Space Complexity: O(1) or O(256), which simplifies to O(1)

The character frequency array has a fixed size of 256 (for extended ASCII), regardless of input size
This is considered constant space complexity since it doesn't scale with the input
     */
    private static int findLengthOfLongestSubstringWithKUniqueCharacters_ASCI_array(String str, int k) {
        int maxLen = 0;
        int windowStart = 0;
        int windowEnd = 0;
        int[] frequenciesCHart = new int[256];
        int distinctChars = 0;

        while (windowEnd < str.length()) {
            char currChar  = str.charAt(windowEnd);

            if(frequenciesCHart[currChar]==0){
                distinctChars++;
            }

            frequenciesCHart[currChar]++;

            while (distinctChars > k){
                char leftMostChar = str.charAt(windowStart);
                frequenciesCHart[leftMostChar]--;

                if(frequenciesCHart[leftMostChar]==0){
                    distinctChars--;
                }

                windowStart++;
            }

            if(distinctChars == k){
                int currLen = windowEnd - windowStart + 1;
                maxLen = Math.max(maxLen, currLen);
            }

            windowEnd++;
        }

        return maxLen;
    }

}
