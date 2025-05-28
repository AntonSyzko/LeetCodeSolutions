package leet.code.solutions.greedy;

import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/maximum-length-of-pair-chain/description/

You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.

A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.

Return the length longest chain which can be formed.

You do not need to use up all the given intervals. You can select pairs in any order.

Example 1:

Input: pairs = [[1,2],[2,3],[3,4]]
Output: 2
Explanation: The longest chain is [1,2] -> [3,4].
Example 2:

Input: pairs = [[1,2],[7,8],[4,5]]
Output: 3
Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].

Constraints:

n == pairs.length
1 <= n <= 1000
-1000 <= lefti < righti <= 1000
 */
public class ChainOfPairs {

    public static void main(String[] args) {
        int[][] pairs = {{1,2},{2,3},{3,4},{4,5}};
        int numberOfPossiblePairs = chainPairs(pairs);
        System.out.println(numberOfPossiblePairs);

        int[][] pairs2 = {{1,2},{7,8},{4,5}};
        int numberOfPossiblePairs2 = chainPairs(pairs2);
        System.out.println(numberOfPossiblePairs2);
    }

    /*
Time Complexity: O(n log n)

    Sorting the pairs takes O(n log n) time
    Iterating through the sorted pairs takes O(n) time
    Overall complexity is dominated by the sorting step: O(n log n)


Space Complexity: O(1) or O(log n)

    We only use a constant amount of extra space for variables
    The space used by the sorting algorithm depends on the implementation (typically O(log n) for the call stack in quick sort)
     */
    private static int chainPairs(int[][] pairs) {

        // We can always include at least one pair
        int possibleChainedPairs = 1;

        // Sort the pairs based on their ENDING points
        Arrays.sort(pairs, Comparator.comparingInt(arr -> arr[1]));

        int currentEnd = pairs[0][1];// End of the first pair after sorting

        for (int pairIndex = 1; pairIndex < pairs.length; pairIndex++) {//start from pair 1

            int currentPairStart = pairs[pairIndex][0];

            // If the current pair's start is greater than the previous pair's end -> we can add this pair to our chain
            if(currentPairStart > currentEnd) {
                possibleChainedPairs++;

                int currentPairEnd = pairs[pairIndex][1];

                //set current end as END of this current pair for future pairs comparison
                currentEnd = currentPairEnd;
            }
        }

        return possibleChainedPairs;
    }
}
