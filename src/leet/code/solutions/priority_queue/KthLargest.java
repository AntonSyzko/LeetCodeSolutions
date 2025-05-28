package leet.code.solutions.priority_queue;

import java.util.PriorityQueue;
import java.util.Queue;

/*
https://neetcode.io/problems/kth-largest-integer-in-a-stream

Design a class to find the kth largest integer in a stream of values, including duplicates. E.g. the 2nd largest from [1, 2, 3, 3] is 3. The stream is not necessarily sorted.

Implement the following methods:

constructor(int k, int[] nums) Initializes the object given an integer k and the stream of integers nums.
int add(int val) Adds the integer val to the stream and returns the kth largest integer in the stream.
Example 1:

Input:
["KthLargest", [3, [1, 2, 3, 3]], "add", [3], "add", [5], "add", [6], "add", [7], "add", [8]]

Output:
[null, 3, 3, 3, 5, 6]

Explanation:
KthLargest kthLargest = new KthLargest(3, [1, 2, 3, 3]);

kthLargest.add(3);   // return 3
kthLargest.add(5);   // return 3
kthLargest.add(6);   // return 3
kthLargest.add(7);   // return 5
kthLargest.add(8);   // return 6

1,2,3,3,3,5,6,7,8
 */
public class KthLargest {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(6));
        System.out.println(kthLargest.add(7));
        System.out.println(kthLargest.add(8));


    }

    private  final Queue<Integer> minHip;
    private  final int hipSize;

    public KthLargest(int k, int[] nums) {

        minHip = new PriorityQueue<>();
        hipSize = k;

        for (int num : nums) {
            minHip.add(num);
            if (minHip.size() > hipSize) {
                minHip.remove();
            }
        }
    }

    public int add(int val) {

        minHip.add(val);

        while (!minHip.isEmpty() && minHip.size() > hipSize) {
            minHip.remove();
        }

        return minHip.peek();
    }
}
