package leet.code.solutions.priority_queue;

import java.util.*;


/*
https://leetcode.com/problems/top-k-frequent-elements/

Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]

Example 2:
Input: nums = [1], k = 1
Output: [1]

Constraints:
1 <= nums.length <= 105
k is in the range [1, the number of unique elements in the array].
It is guaranteed that the answer is unique.

Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] input = {1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
        int[] maxKFrequent = topKFrequent(input, 2);
        System.out.println(Arrays.toString(maxKFrequent));//2,1

        int[] maxKRare = topKRare(input, 2);
        System.out.println(Arrays.toString(maxKRare));//3,4
    }

    private static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> mapOfOccurences = new HashMap<>();
        for (int num : nums) {
            mapOfOccurences.put(num, mapOfOccurences.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(mapOfOccurences::get));

        for (Map.Entry<Integer, Integer> entry : mapOfOccurences.entrySet()) {
            priorityQueue.add(entry.getKey());
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        return priorityQueue.stream().mapToInt(i -> i).toArray();
    }

    private static int[] topKRare(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int each : nums) {
            map.put(each, map.getOrDefault(each, 0) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((first, second) -> map.get(second) - map.get(first));

        for (Map.Entry<Integer, Integer> each : map.entrySet()) {
            priorityQueue.add(each.getKey());
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.stream().mapToInt(i -> i).toArray();

    }

}
