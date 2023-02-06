package leet.code.solutions.arrays;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*

https://www.techiedelight.com/find-longest-subsequence-formed-by-consecutive-integers/


Given an integer array, find the length of the longest subsequence formed by the consecutive integers.
The subsequence should contain all distinct values, and the character set should be consecutive,
irrespective of its order.

For example,

Input : [2, 0, 6, 1, 5, 3, 7]
Output: 4

Explanation: The longest subsequence formed by the consecutive integers is [2, 0, 1, 3].
 It has distinct values and length 4.


Input : [2, 4, 6, 3, 7, 4, 8, 1]
Output: 4

Explanation: The longest subsequence formed by the consecutive integers is [2, 4, 3, 4, 1].
The distinct subsequence is [2, 4, 3, 1] having length 4.
 */
public class LongestConsecutiveSubsequence {
    // Function to find the length of the largest subsequence formed by
    // consecutive integers
    public static int findMaxLenSubseq(int[] arr) {
        // construct a set out of input elements
        Set<Integer> sequenceStartCandidates = IntStream.of(arr)        // Returns IntStream
                .boxed()
                .collect(Collectors.toSet());

        // initialize result by 0
        int maxLen = 0;

        // do for each element of the input sequence
        for (int e: arr) {
            // check if the current element `e` is a candidate for starting a sequence,
            // i.e., the previous element `e-1` doesn't exist in the set
            if (!sequenceStartCandidates.contains(e - 1)) {
                // `len` stores the length of subsequence, starting with the
                // current element
                int len = 1;

                // check for presence of elements `e+1`, `e+2`, `e+3`, … ,`e+len`
                // in the set
                while (sequenceStartCandidates.contains(e + len)) {
                    len++;
                }

                // update result with the length of current consecutive subsequence
                maxLen = Math.max(maxLen, len);
            }
        }

        // return result
        return maxLen;
    }

    public static void main (String[] args)
    {
        int[] arr = { 2, 0, 6, 1, 5, 3, 7 };

        System.out.println("The length of the maximum consecutive subsequence is " +
                findMaxLenSubseq(arr));
    }


}
