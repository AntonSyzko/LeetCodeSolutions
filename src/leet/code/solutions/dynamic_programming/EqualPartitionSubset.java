package leet.code.solutions.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/partition-equal-subset-sum/description/

Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100
 */
public class EqualPartitionSubset {

    public static void main(String[] args) {
        int[] nums = {1,2,3,5};
        boolean canPArtition = canPartition(nums);
        System.out.println(canPArtition);

        int[] nums2 = {1,5,11,5};
        boolean canPArtition2 = canPartition(nums2);
        System.out.println(canPArtition2);

        int[] nums3 = {1,2,3,3,1,2};
        boolean canPArtition3 = canPartition(nums3);
        System.out.println(canPArtition3);
    }

    private static boolean canPartition(int[] nums) {

        if(nums == null || nums.length == 0) return false;

        if(nums.length == 1) return true;

        int aggregatedSum  = Arrays.stream(nums).sum();

        if(aggregatedSum % 2 != 0){
            return false;
        }

        Map<String, Boolean> DP = new HashMap<>();


        int halfSum = aggregatedSum / 2;
        int index = 0;
        return checkHalfSum(nums, index,halfSum, DP);
        //return checkHalfSum2(nums, 0, 0, aggregatedSum/2, DP);
    }

    /*
    Time Complexity: O(n * sum)

            With memoization, we solve each subproblem only once
            There are n * (sum/2) possible states (index from 0 to n-1, halfSum from 0 to sum/2)
            Each state is computed in O(1) time after memoization

Space Complexity: O(n * sum)

        The memoization map stores at most n * (sum/2) entries
        The recursion stack can go as deep as n
     */
    private static boolean checkHalfSum(int[] nums, int index, int halfSum, Map<String, Boolean> DP ) {

        if(halfSum < 0 || index >= nums.length){
            return false;
        }

        String currentStateMemoKey = index + "|" + halfSum;

        if(DP.containsKey(currentStateMemoKey)) {
            return DP.get(currentStateMemoKey);
        }

        if(halfSum == 0) return true;

        boolean with = checkHalfSum(nums, index + 1, halfSum - nums[index], DP);
        boolean without = checkHalfSum(nums, index + 1, halfSum, DP);

        boolean currentPartition = with || without;
        DP.put(currentStateMemoKey, currentPartition);

        return currentPartition;
    }

    private static boolean checkHalfSum2(int[] nums, int index, int sum, int halfSum,  Map<String, Boolean> DP ) {
        String currentState =  index + "" + sum;
        if(DP.containsKey(currentState)) {
            return DP.get(currentState);
        }

        if(sum > halfSum || index >= nums.length){
            return false;
        }

        if(sum == halfSum){
            return true;
        }

        boolean currentPartition = checkHalfSum2(nums, index+1, sum + nums[index], halfSum, DP)
                                     ||
                                     checkHalfSum2(nums, index+1, sum, halfSum,DP);

        DP.put(currentState, currentPartition);

        return currentPartition;
    }


    private static boolean partitionDP(int[] nums, int sum) {
        if(sum %2 >0) return  false;

        int len = nums.length-1;
        int halfSum = sum/2;

        Map<String,Boolean> map = new HashMap<>();

        return subsetSumDP(nums, len, halfSum , map);
    }


    public static boolean subsetSumDP(int[] nums, int len, int sum, Map<String, Boolean> DP){
        if(len < 0 || sum < 0) return false;

        if(sum == 0){
            return true;
        }

        String key = len + "|" + sum;

        if(DP.containsKey(key)){
            return DP.get(key);
        }

        boolean includingCurrentElementInSUm = subsetSumDP(nums, len -1, sum - nums[len], DP);
        boolean excludingCurrentElementInSUm = subsetSumDP(nums, len -1, sum, DP);

        boolean currSumPossible =  includingCurrentElementInSUm || excludingCurrentElementInSUm;

        DP.put(key,currSumPossible);

        return currSumPossible;
    }

    //------------------- WITHOUT DP ---------------------------

    /*
    Time Complexity: O(2^n)

        For each element in the array, we have two choices (include or exclude)
        This creates a binary decision tree of depth n
        In the worst case, we explore all possible subsets (2^n)

Space Complexity: O(n)

        The recursion stack can go as deep as n (the number of elements)
     */

    private static boolean checkHalfSumWithoutDP(int[] nums, int index, int halfSum ) {

        if(halfSum < 0 || index >= nums.length){
            return false;
        }

        if(halfSum == 0) return true;

        boolean with = checkHalfSumWithoutDP(nums, index+1, halfSum - nums[index]);
        boolean without = checkHalfSumWithoutDP(nums, index+1, halfSum);

        return with || without;
    }
}
