package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
https://www.callicoder.com/equal-subset-sum-partition-problem/

Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
Problem Identification
Let’s say that the array nums can be broken down into two subsets S1 and S2 of equal sums. We can observe the following things

// Sum of all the numbers in subset S1 + Sum of all the numbers in subset S2 = Sum of all the numbers in the array
Sum(S1) + Sum(S2) = Sum(nums)

// If Sum(S1) and Sum(S2) is equal
2*Sum(S1) = Sum(nums)

Sum(S1) = Sum(nums)/2
The problem reduces to finding a subset of sum Sum(nums)/2 in the array. If we can find a subset of sum Sum(nums)/2 then we can definitely partition the array into two subsets of equal sums.

Note that I have already explained the solution approach for the subset sum problem. So I will directly jump to the code.
 */
public class EqualSubsetPartitionProblem {

    public static void main(String[] args) {

        int[] vals = {1,5,11,5};
        boolean canBePartitionedInHalf = hasSubsetSum(vals);
        System.out.println(canBePartitionedInHalf);

    }

    /*
    Big O Analysis
            Time Complexity: O(2^n)

            The recursive function creates a binary decision tree where each element can either be included or excluded
            At each step, we make two recursive calls (except when the current element exceeds our target sum)
            The decision tree can have up to 2^n nodes in the worst case
            This is exponential and will not scale well for large inputs

            Space Complexity: O(n)

            The maximum recursion depth is n (the number of elements in the array)
            Each recursive call adds a frame to the call stack
            Therefore, the space complexity is O(n) for the recursion stack
     */

    private static boolean hasSubsetSum(int[] values) {


        // If the sum is odd, we cannot partition the array into two equal subsets
        int totalSum = Arrays.stream(values).sum();

        if (totalSum % 2 != 0) {
            return false;
        }
        int len = values.length;

        //cause if sum of any combination of elements == half sum of all, meaning array can be partitioned in half
        int halfSum = totalSum / 2;

        return hasSubsetSumHelper(values, halfSum, len);

    }

    /*
     * - Recursive Solution: O(2^n) - We have two choices (include/exclude) for each element
     * - Recursive Solution: O(n) for the recursion stack
     */

    private static boolean hasSubsetSumHelper(int[] values, int halfSum, int len) {

        if(halfSum == 0) {//BASE
            return true;
        }

        if(len == 0) {//length exhausted but half sum not reached 0
            return false; // no result can be obtained
        }

        //if current element in index ( len -1 ) is itself BIGGER than our half sum
        if(values[len -1 ] > halfSum){

            return hasSubsetSumHelper(values, halfSum, len -1 );//recur without last element moving to next element left to it from end

        }

        //calling two times recursively - hence 2^n time Big O
        boolean withCurrentElement = hasSubsetSumHelper(values, halfSum - values[len -1], len - 1);

        boolean withoutCurrentElement = hasSubsetSumHelper(values, halfSum, len - 1);

        return withCurrentElement || withoutCurrentElement;

    }
    }
