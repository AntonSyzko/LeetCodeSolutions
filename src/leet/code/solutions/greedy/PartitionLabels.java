package leet.code.solutions.greedy;

import java.util.ArrayList;
import java.util.List;

/*
https://neetcode.io/problems/partition-labels

You are given a string s consisting of lowercase english letters.

We want to split the string into as many substrings as possible, while ensuring that each letter appears in at most one substring.

Return a list of integers representing the size of these substrings in the order they appear in the string.

Example 1:

Input: s = "xyxxyzbzbbisl"

Output: [5, 5, 1, 1, 1]
Explanation: The string can be split into ["xyxxy", "zbzbb", "i", "s", "l"].

Example 2:

Input: s = "abcabc"

Output: [6]
Constraints:

1 <= s.length <= 100
 */
public class PartitionLabels {

    public static void main(String[] args) {
       String s = "xyxxyzbzbbisl";
       List<Integer> substrings = partitionLabels(s);
       System.out.println(substrings);
    }

    private static List<Integer> partitionLabels(String s) {
         List<Integer> res =  new ArrayList<>();

         int[] lastOccurrenceIndexes = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastOccurrenceIndexes[s.charAt(i) - 'a'] = i;//last index of char occurrence in a String
        }

        for (int i = 0; i < s.length(); i++) {

            int charLastIndex = lastOccurrenceIndexes[s.charAt(i) - 'a'];

            for(int j = i; j < charLastIndex; j++){//iterate till last index of occurrence

                char currentChar = s.charAt(j);
                int currCharLastOccurrenceIndex = lastOccurrenceIndexes[currentChar - 'a'];

                if(currCharLastOccurrenceIndex > charLastIndex){//if char within substring has later index
                    charLastIndex = currCharLastOccurrenceIndex;//reset substring to this later index
                }
            }

            res.add((charLastIndex - i) +1);//+1 cause zero based

            i = charLastIndex;//start new substring from last char index onwards

        }

        return res;
    }
}
