package leet.code.solutions.arrays;

import java.util.Arrays;

/*
Given a string s and a character c that occurs in s, return an array of integers answer where answer.length == s.length a
nd answer[i] is the distance from index i to the closest occurrence of character c in s.

The distance between two indices i and j is abs(i - j), where abs is the absolute value function.

Example 1:

Input: s = "loveleetcode", c = "e"
Output: [3,2,1,0,1,0,0,1,2,2,1,0]

Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
The closest occurrence of 'e' for index 8 is at index 6, so the distance is abs(8 - 6) = 2.

Example 2:

Input: s = "aaab", c = "b"
Output: [3,2,1,0]
 */
public class ShortestDistanceToCharacter {

    public static void main(String[] args) {
      String str = "loveleetcode";
      char ch = 'e';

      int[] shortest = shortestToChar(str, ch);
        System.out.println(Arrays.toString(shortest));
    }

    private static int[] shortestToChar(String str, char ch) {
        int len = str.length();
        int[] res = new int[len];

        int closest_seen_position  = -len; // dummy to start, cannot be zero, so we take negative len

        //1. forward sweep
        for(int i = 0; i < len; i++) {

            if(str.charAt(i) == ch) {
                closest_seen_position = i;
            }
            res[i] = i -  closest_seen_position;

        }

        //backward sweep
        for(int i = len - 1; i >= 0; i--){

            if(str.charAt(i) == ch) {
                closest_seen_position = i;
            }
            res[i] = Math.min(res[i], Math.abs(i -  closest_seen_position));

        }

        return res;
    }
}
