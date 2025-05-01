package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/partition-labels/description/

You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"], but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.

Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.

Return a list of integers representing the size of these parts.

Example 1:

Input: s = "ababcbacadefegdehijhklij"
Output: [9,7,8]
Explanation:
The partition is "ababcbaca", "defegde", "hijhklij".
This is a partition so that each letter appears in at most one part.
A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.

Example 2:

Input: s = "eccbbbbdec"
Output: [10]

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class PartitionLabels {

    public static void main(String[] args) {
        String str = "ababcc";
        List<Integer> result  = partitionLabels(str);
        System.out.println(result);

        str = "ababcbacadefegdehijhklij";
        result = partitionLabels(str);
        System.out.println(result);

        str = "eccbbbbdec";
        result = partitionLabels(str);
        System.out.println(result);
    }

    private static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int[] lastOccurrences = new int[26];

        for (int i = 0; i < s.length(); i++) {
            lastOccurrences[s.charAt(i)-'a'] = i;
        }

        for (int partitionSTart = 0; partitionSTart < s.length(); partitionSTart++) {

            char curr = s.charAt(partitionSTart);

            int  partitionEnd = lastOccurrences[curr - 'a'];

            for (int j = partitionSTart + 1; j <= partitionEnd; j++) {//iterate till last occurrence of the char we started with in above loop

                char nextCharInPartition = s.charAt(j);
                int nextCharLastOccurrence = lastOccurrences[nextCharInPartition - 'a'];

                partitionEnd = Math.max(partitionEnd, nextCharLastOccurrence);//if smth after char we started with ends LATER that that starting char, then //MOVE the partition end
            }

            int partitionLength = partitionEnd - partitionSTart + 1;

            res.add(partitionLength);//store result

            partitionSTart = partitionEnd;//RESET partition start - for next partition, everything BEFORE that we have already processed and are not interested

        }
        return res;
    }
}
