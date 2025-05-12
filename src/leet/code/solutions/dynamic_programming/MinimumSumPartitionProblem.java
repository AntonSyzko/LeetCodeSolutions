package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*
https://www.techiedelight.com/minimum-sum-partition-problem/

Given a set of positive integers S, partition set S into two subsets, S1 and S2, such that the difference between the sum of elements in S1 and S2 is minimized.
The solution should return the minimum absolute difference between the sum of elements of two partitions.

For example, consider S = {10, 20, 15, 5, 25}.

 We can partition S into two partitions where the minimum absolute difference between the sum of elements is 5.

S1 = {10, 20, 5}
S2 = {15, 25}

Note that this solution is not unique. The following is another solution:

S1 = {10, 25}
S2 = {20, 15, 5}
 */
public class MinimumSumPartitionProblem {

    public static void main(String[] args) {
        int[] items = { 10, 20, 15, 5, 25 };
        int lastIndex = items.length -1;
        int subsetOneSUm = 0;
        int subsetTwoSum = 0;

        // create a map to store solutions to subproblems
        Map<String, Integer> dp = new HashMap<>();

        System.out.println("DP The minimum difference is " + findMinAbsDiff_DP(items, lastIndex, subsetOneSUm, subsetTwoSum, dp));

        System.out.println("The minimum difference is " + findMinAbsDiff(items, lastIndex, subsetOneSUm, subsetTwoSum));
    }

    /*
           TIME:   O(n × sum)

            SPACE:  O(n × sum)

    where n is the size of the input and sum is the sum of all elements in the input.
     */

    private static int findMinAbsDiff_DP(int[] items, int index, int subsetOneSUm, int subsetTwoSum, Map<String, Integer> dp) {
        // BASE case: if the list becomes empty, return the absolute difference between both sets
        if (index < 0) {
            return Math.abs(subsetOneSUm - subsetTwoSum);
        }

        // Construct a unique map key from dynamic elements of the input.
        // Note that we can uniquely identify the subproblem with `n` and `S1` only, as `S2` is nothing but `S-S1`, where `S` is the sum of all elements
        String dpKey =  index + "|" + subsetOneSUm;

        // If the subproblem is seen for the first time, solve it and store its result in a map
        if(!dp.containsKey(dpKey)) {

            // Case 1. Include the current item in subset `S1` and recur for the remaining items `index-1`
            int sumWithoutCurrIndexInSubsetOne = findMinAbsDiff_DP(items, index - 1, subsetOneSUm - items[index], subsetTwoSum, dp);

            // Case 2. Exclude the current item from subset `S1`, meaning include in subset TWO  and recur for the remaining items `index-1`
            int sumWithoutCurrIndexInSubsetTwo = findMinAbsDiff_DP(items, index - 1, subsetOneSUm, subsetTwoSum - items[index], dp);

            int minOfTwo = Math.min(sumWithoutCurrIndexInSubsetOne, sumWithoutCurrIndexInSubsetTwo);

            dp.put(dpKey, minOfTwo);
        }

        return dp.get(dpKey);
    }


    //The time complexity of the  solution is exponential and occupies space in the call stack.
    private static int findMinAbsDiff(int[] items, int index, int subsetOneSum, int subsetTwoSum) {
        //BASE
        if(index ==0){//items exhausted
            return Math.abs(subsetOneSum - subsetTwoSum);//return final ABS diff
        }

        // Case 1. Include the current item in subset `S1` and recur for the remaining items `index-1`
        int sumWithoutCurrentInSubsetOne = findMinAbsDiff(items, index - 1, subsetOneSum - items[index], subsetTwoSum);

        // Case 2. Exclude the current item from subset `S1`, meaning include in subset TWO  and recur for the remaining items `index-1`
        int sumWithoutCurrentInSubsetTwo = findMinAbsDiff(items, index - 1, subsetOneSum, subsetTwoSum - items[index]);

        return Math.min(sumWithoutCurrentInSubsetOne, sumWithoutCurrentInSubsetTwo);// pick MIN of two
    }
}

/*

This problem is an optimization version of the partition problem. The idea is to consider each item in the given set S one by one, and for each item, there are two possibilities:

       1)  Include the current item in subset S1 and recur for the remaining items.

       2)  Include the current item from the subset S2 and recur for the remaining items.

Finally, return the minimum difference we get by including the current item in S1 and S2.
When there are no items left in the set, return the absolute difference between elements of S1 and S2.
 */
