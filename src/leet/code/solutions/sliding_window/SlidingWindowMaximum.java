package leet.code.solutions.sliding_window;

import java.util.*;

/*
239

https://leetcode.com/problems/sliding-window-maximum/description/

You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

Return the max sliding window.

Example 1:

Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]
Explanation:
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Example 2:

Input: nums = [1], k = 1
Output: [1]

Constraints:

1 <= nums.length <= 105
-104 <= nums[i] <= 104
1 <= k <= nums.length
 */

public class SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        //int[] nums = {1,3,-1,5};
        int[] maxSliding = maxSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(maxSliding));

        int[] nums2 = {1};
        int[] maxSliding2 = maxSlidingWindow(nums2, 1);
        System.out.println(Arrays.toString(maxSliding2));

        int[] nums3 = {1, -1};
        int[] maxSliding3 = maxSlidingWindow(nums3, 1);
        System.out.println(Arrays.toString(maxSliding3));
    }

    /**
     * Sliding Window Maximum using Monotonic Deque
     * Time Complexity: O(n) - each element is added and removed at most once
     * Space Complexity: O(k) - deque stores at most k elements
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] result = new int[len - k + 1];

        // Deque stores indices in decreasing order of their corresponding values
        // Front of deque always contains the index of maximum element in current window
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            // Remove indices that are out of current window bounds
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) {//comparison by indexes
                deque.removeFirst();
            }

            // Maintain decreasing order: remove indices whose values are smaller than current
            // This ensures the front always has the maximum element's index
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.removeLast();
            }

            // Add current index to deque
            deque.addLast(i);

            // Once we have a complete window, record the maximum
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];//max at FRONT always
            }
        }

        return result;
    }


        //: O(n log k)
    public static int[] maxSlidingWindowMaxHip(int[] nums, int k) {

        int len = nums.length;
        int[] res = new int[len - k + 1];

        // Max heap to store elements with their indices
        Queue<Pair> maxHip = new PriorityQueue<>((a,b) -> b.num - a.num);

        // Add first k elements to heap
        for(int i = 0; i < k; i++){
            int numAtIndex = nums[i];
            maxHip.offer(new Pair(numAtIndex,i));
        }

        // First window maximum
        res[0] = maxHip.peek().num;

        // Process remaining elements starting from k
        for(int i = k; i < len; i++){

            int numAtIndex = nums[i];

            // Add current element
            maxHip.offer(new Pair(numAtIndex,i));

            // Remove elements outside current window
            while (!maxHip.isEmpty() && maxHip.peek().index <=  i - k){
                maxHip.poll();
            }

            // Current window maximum
            res[i-k+1] = maxHip.peek().num;
        }


        return res;
    }

    private record Pair (int num , int index){}
}