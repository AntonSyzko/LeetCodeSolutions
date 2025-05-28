package leet.code.solutions.priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://neetcode.io/problems/kth-largest-element-in-an-array

Given an unsorted array of integers nums and an integer k, return the kth largest element in the array.

By kth largest element, we mean the kth largest element in the sorted order, not the kth distinct element.

Follow-up: Can you solve it without sorting?

Example 1:

Input: nums = [2,3,1,5,4], k = 2

Output: 4
Example 2:

Input: nums = [2,3,1,1,5,5,4], k = 3

Output: 4
Constraints:

1 <= k <= nums.length <= 10000
-1000 <= nums[i] <= 1000
 */
public class KthLargestElementInArray {

    public static void main(String[] args) {
        int[] nums = {2,3,1,5,4};
        int k = 2;
        int kLargest = findKthLargest(nums, k);
        System.out.println(kLargest);

        int[] nums2 = {2,3,1,1,5,5,4};
        int k2 = 3;
        int kLargest2 = findKthLargest(nums2, k2);
        System.out.println(kLargest2);
    }

    private static int findKthLargest(int[] nums, int k) {
        Queue<Integer> minHip = new PriorityQueue<>();
        minHip.addAll(Arrays.stream(nums).boxed().toList());

        while (minHip.size() > k) {//when size stopped at K - we have K largest elements left in minHip's tail
            minHip.remove();
        }

        return minHip.peek();
    }
}
