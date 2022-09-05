package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://www.techiedelight.com/find-sub-array-with-0-sum/

Given an integer array, print all subarrays with zero-sum.

For example,

Input:  { 4, 2, -3, -1, 0, 4 }

Subarrays with zero-sum are

{ -3, -1, 0, 4 }
{ 0 }

Input:  { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 }

Subarrays with zero-sum are

{ 3, 4, -7 }
{ 4, -7, 3 }
{ -7, 3, 1, 3 }
{ 3, 1, -4 }
{ 3, 1, 3, 1, -4, -2, -2 }
{ 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 }
Note that the problem deals with subarrays that are contiguous, i.e., whose elements occupy consecutive positions in the array.
 */
public class SubarraysWithZeroSum {
    public static void main(String[] args) {
        int[] nums = { 4, 2, -3, -1, 0, 4 };
        printAllSubarrays(nums);
    }


    // Utility function to insert <key, value> into the multimap
    private static <K, V> void insert(Map<K, List<V>> hashMap, K key, V value) {
        // if the key is seen for the first time, initialize the list
        hashMap.putIfAbsent(key, new ArrayList<>());
        hashMap.get(key).add(value);
    }

    private static void printAllSubarrays(int[] nums) {
        // create an empty multimap to store the ending index of all subarrays having the same sum
        Map<Integer, List<Integer>> hashMap = new HashMap<>();

        // insert (0, -1) pair into the map to handle the case when subarray with zero-sum starts from index 0
        insert(hashMap, 0, -1);

        int sum = 0;

        // traverse the given array
        for (int i = 0; i < nums.length; i++) {
            // sum of elements so far
            sum += nums[i];

            // if the sum is seen before, there exists at least one subarray with zero-sum
            if (hashMap.containsKey(sum)) {
                List<Integer> list = hashMap.get(sum);

                // find all subarrays with the same sum
                for (Integer index : list) {
                    System.out.println("Subarray with zero sum starts at : " + (index + 1) + " and ends at : " + i);
                }
            }

            // insert (sum so far, current index) pair into the multimap
            insert(hashMap, sum, i);
        }
    }


    private static void printAllSubarraysBruteForce(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == 0) {
                    System.out.println("Zero sum sub array starts at : " + i + " ends at : " + j);
                }
            }
        }
    }


}
