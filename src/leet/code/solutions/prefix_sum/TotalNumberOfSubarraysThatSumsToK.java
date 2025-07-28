package leet.code.solutions.prefix_sum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://algo.monster/problems/subarray_sum

Find the total number of subarrays that sums up to target.


 */
public class TotalNumberOfSubarraysThatSumsToK {

    public static void main(String[] args) {
     List<Integer> nums = List.of(1,2,3);
     int target = 3;

     int numOfSubs = subarraySumTotal(nums, target);
        System.out.println(numOfSubs);
    }

    public static int subarraySumTotal(List<Integer> arr, int target) {

        int numberOfSubarraysWithSumEqualToK = 0;
        Map<Integer,Integer> prefixSumToIndex = new HashMap<>();
        prefixSumToIndex.put(0,1);

        int ongoingSum = 0;

        for (int i = 0; i < arr.size(); i++) {

            ongoingSum += arr.get(i);

            int complement = ongoingSum - target;

            if(prefixSumToIndex.containsKey(complement)){

                numberOfSubarraysWithSumEqualToK += prefixSumToIndex.get(complement);

            }

            prefixSumToIndex.put(ongoingSum,prefixSumToIndex.getOrDefault(ongoingSum,0) + 1);
        }

        return numberOfSubarraysWithSumEqualToK;
    }
    }
