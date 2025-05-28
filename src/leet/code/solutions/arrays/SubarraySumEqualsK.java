package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/subarray-sum-equals-k/

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,1,1], k = 2
Output: 2

Example 2:
Input: nums = [1,2,3], k = 3
Output: 2

Constraints:
1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 */
public class SubarraySumEqualsK {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        System.out.println(subarraySum(nums, 2));
    }

    private static int subarraySum(int[] nums, int targetSum) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        if(nums.length ==1){
            return nums[0] == targetSum ? 1 : 0;
        }

        //map of  sum : times met
        Map<Integer, Integer> subarraySums = new HashMap<>();
        subarraySums.put(0, 1);//by default, we have seen zero sum ( sum of nothing)  once

        int ongoingSum = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {

            ongoingSum += nums[i];
                                         //sum=5 - 2(target) = 3 -> so in between 3 and 5 there's subarray whose sum = 2(target)
            if (subarraySums.containsKey(ongoingSum - targetSum)) {
                result += subarraySums.get(ongoingSum - targetSum);
            }

            subarraySums.put(ongoingSum, subarraySums.getOrDefault(ongoingSum, 0) + 1);//times this sum already met before
        }
        return result;
    }

    public int subarraySum2(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        if(nums.length ==1){
            return nums[0] == k ? 1 : 0;
        }

        int res = 0;

        Map<Integer,Integer> sumOccurred = new HashMap<>();
        sumOccurred.put(0,1);//sum of 0 we saw once as to start with

        int sum = 0;

        for(int i = 0; i < nums.length; i++){
            sum += nums[i];

            int complement = sum - k;

            if(sumOccurred.containsKey(complement)){
                res += sumOccurred.get(complement) ;
            }

            sumOccurred.put(sum, sumOccurred.getOrDefault(sum, 0) +1);

        }

        return res;
    }

    private static int subarraySumBruteForce(int[] nums, int k) {
        int res = 0;
        int ongoingSum = 0;
        for (int i = 0; i < nums.length; i++) {
            ongoingSum = 0;
            for (int j = i; j < nums.length; j++) {
                ongoingSum += nums[j];
                if (ongoingSum == k) {
                    res++;
                }
            }
        }
        return res;
    }
}
