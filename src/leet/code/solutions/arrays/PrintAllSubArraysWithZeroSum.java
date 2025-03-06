package leet.code.solutions.arrays;

import java.util.*;

/*
https://www.techiedelight.com/find-sub-array-with-0-sum/

Given an integer array, print all subarrays with zero-sum.

For example,

Input:  { 4, 2, -3, -1, 0, 4 }
Subarrays with zero-sum are { -3, -1, 0, 4 }
                             { 0 }
 Input:  { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 }

Subarrays with zero-sum are { 3, 4, -7 }
                            { 4, -7, 3 }
                            { -7, 3, 1, 3 }
                            { 3, 1, -4 }
                            { 3, 1, 3, 1, -4, -2, -2 }
                            { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 }
 */
public class PrintAllSubArraysWithZeroSum {
    public static void main(String[] args) {
        int[] nums = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
        printAllSubArraysWithZeroSum(nums);
    }

    private static void printAllSubArraysWithZeroSum(int[] arr) {
        // create an empty multimap to store the ending index of all subarrays having the same sum
        Map<Integer, List<Integer>> multiMap = new HashMap<>();

        // insert (0, -1) pair into the map to handle the case when subarray with zero-sum starts from index 0
        insertIntMultiMap(multiMap, 0, -1);

        int sum = 0;

        // traverse the given array
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];


            // if the sum is seen before, there exists at least one subarray with zero-sum
            if(multiMap.containsKey(sum)){
                List<Integer> currentSubarrayWithZeroSum = multiMap.get(sum);
                // find all subarrays with the same sum
                for (int each : currentSubarrayWithZeroSum) {
                    System.out.println("Subarray:[" + (each+1) + " ..." + i + "]");
                }

            }
            // insert (sum so far, current index) pair into the multimap
            insertIntMultiMap(multiMap, sum, i);

        }
    }

    private static <K,V> void insertIntMultiMap(Map<K, List<V>> map, K key, V value) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(value);
    }
}
