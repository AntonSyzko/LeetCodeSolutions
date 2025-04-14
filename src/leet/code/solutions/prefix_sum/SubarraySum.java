package leet.code.solutions.prefix_sum;

import java.util.*;

/*
https://algo.monster/problems/subarray_sum


Given an array of integers and an integer target, find a subarray that sums to target and return the start and end indices of the subarray.

Input: arr: 1 -20 -3 30 5 4 target: 7

Output: 1 4

Explanation: -20 - 3 + 30 = 7. The indices for subarray [-20,-3,30] is 1 and 4 (right exclusive).

 */
public class SubarraySum {


    public static void main(String[] args) {
        int[] nums = {1,2,3};
        basicPrefixSum(nums);
        System.out.println("--------------------------");
        basicPrefixSumMap(nums);
        System.out.println("-------------------------------");

        int[] nums2 = { 1 ,-20, -3, 30, 5, 4};
        List<Integer> nums3 = List.of( 1 ,-20, -3, 30, 5, 4);
        int target = 7;
        List<Integer> subarraySum = subarraySum(nums3, target);
        System.out.println(subarraySum);
    }



    public static List<Integer> subarraySum(List<Integer> arr, int target) {


         Map<Integer, Integer> prefixSumsMap = new HashMap<>();
        // prefix_sum 0 happens when we have an empty array
        prefixSumsMap.put(0,0);

         int ongoingSum = 0;

         for(int i = 0; i < arr.size(); i++) {
             ongoingSum += arr.get(i);

             int diffToTarget = ongoingSum - target;

             if(prefixSumsMap.containsKey(diffToTarget)) {

                 return List.of(prefixSumsMap.get(diffToTarget), i + 1);
             }
             prefixSumsMap.put(ongoingSum, i + 1 );
         }
        return Collections.emptyList();
    }


        private static void basicPrefixSum(int[] nums){
        int[] prefixSum = new int[nums.length];
         prefixSum[0] = nums[0];

        for (int i = 1; i < nums.length ; i++) {

            prefixSum[i] = prefixSum[i-1] + nums[i];
        }

        System.out.println(Arrays.toString(prefixSum));
    }

    private static void basicPrefixSumMap(int[] nums){

        Map<Integer, Integer> indexToSumAtIndexMap  = new HashMap<>();
        indexToSumAtIndexMap.put(0, nums[0]);

        for (int index = 1; index < nums.length ; index++) {
            indexToSumAtIndexMap.put(index, indexToSumAtIndexMap.get(index-1) + nums[index]);
        }

        indexToSumAtIndexMap.forEach((k,v) -> System.out.println(k+":"+v));
    }
}
