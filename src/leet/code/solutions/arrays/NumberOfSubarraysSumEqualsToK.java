package leet.code.solutions.arrays;

/*
given array of nuyms and tarhet sum find number of subarrays with sum equals to K

[ 7, 2 , -5, 1 , 1  , -1, 5, -5 ]

subarrays  [7 2 -5 1 ]
            [7 2 -5 1 1 -1 ]
            [1 -1 5 ]
            [5]
            [7, 2 , -5, 1 , 1  , -1, 5, -5]

            outpt 5 -> 5 subarrays with sum = 5

 */

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubarraysSumEqualsToK {

    public static void main(String[] args) {

        int[] nums = {7, 2 , -5, 1 , 1  , -1, 5, -5};
        int targetSum = 5;
        int subarraysCount = findNumberOfSubarraysWIthGivenSum(nums, targetSum);

        System.out.println(subarraysCount);

    }

    // time O (N)
    // space O (N)
    private static int findNumberOfSubarraysWIthGivenSum(int[] nums, int targetSum) {

        int subArraysCount = 0;

        Map<Integer, Integer> subArraysAccumulativeSumsMetBefore = new HashMap<>();
        subArraysAccumulativeSumsMetBefore.put(0, 1);//we have seen zero sum ONCE before even starting

        int ongoingSum = 0;

        for (int i = 0; i < nums.length; i++) {

            ongoingSum += nums[i];

            int differenceToTarget = ongoingSum - targetSum;

            if(subArraysAccumulativeSumsMetBefore.containsKey(differenceToTarget)){//if this DIFF to target sum has bee nseen before

                subArraysCount += subArraysAccumulativeSumsMetBefore.get(differenceToTarget);// += TIMES we have seen this difference before

            }

            subArraysAccumulativeSumsMetBefore.put(ongoingSum, subArraysAccumulativeSumsMetBefore.getOrDefault(ongoingSum, 0) + 1);

        }

        return subArraysCount;
    }


        //O ( N ^ 2 )
    private static int findNumberOfSubarraysWIthGivenSumBruteForce(int[] nums, int targetSum) {
        int subarraysCount = 0;

        for (int i = 0; i < nums.length; i++) {

            int ongoingSum  = 0;

            for (int j = i  ; j < nums.length; j++) {

                ongoingSum += nums[j];

                if (ongoingSum == targetSum) {

                    subarraysCount++;

                }
            }
        }
        return subarraysCount;
    }
}
