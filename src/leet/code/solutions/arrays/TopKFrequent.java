package leet.code.solutions.arrays;

import java.util.*;

/*
https://neetcode.io/problems/top-k-elements-in-list

Given an integer array nums and an integer k, return the k most frequent elements within the array.

The test cases are generated such that the answer is always unique.

You may return the output in any order.

Example 1:

Input: nums = [1,2,2,3,3,3], k = 2

Output: [2,3]
Example 2:

Input: nums = [7,7], k = 1

Output: [7]
Constraints:

1 <= nums.length <= 10^4.
-1000 <= nums[i] <= 1000
1 <= k <= number of distinct elements in nums.
 */
public class TopKFrequent {

    public static void main(String[] args) {
       int[] nums = {1,2,2,3,3,3};
       int k = 2;

       int[] topMostK =  topKFrequentHeap(nums, k);
            System.out.println(Arrays.toString(topMostK));
    }

    /*
    Time Complexity: O(n log k) where n is array length

        Building frequency map: O(n)
        Heap operations: O(unique_elements × log k) ≈ O(n log k) in worst case

        Space Complexity:
            O(n) for the frequency map + O(k) for heap = O(n)
     */
    private static int[] topKFrequentHeap(int[] nums, int k) {

        Map<Integer, Integer> mapKeyToFrequency = new HashMap<>();

        for (int num : nums) {
            mapKeyToFrequency.put(num, mapKeyToFrequency.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[1] - b[1]);//min heap by values a[1], as a[0] contains keys

        for (Map.Entry<Integer, Integer> entry : mapKeyToFrequency.entrySet()) {

            minHeap.offer(new int[]{entry.getKey(), entry.getValue()});

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] res = new int[k];

        for (int i = 0; i < k; i++) {

            res[i] = minHeap.poll()[0];//add keys, not values

        }

        return res;

    }


    private static int[] topKFrequent(int[] nums, int k) {

        int[] res = new int[k];

        int resIndex = 0;

       Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());

           for (int num : nums) {
               map.put(num, map.getOrDefault(num, 0) + 1);
           }

           for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
               if( k > 0){
                   res[resIndex++] = entry.getValue();
                   k--;
               }else{
                   break;
               }
           }

           return res;
    }
}
