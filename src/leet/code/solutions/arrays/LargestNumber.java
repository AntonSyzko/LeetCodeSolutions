package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/*
179. Largest Number

https://leetcode.com/problems/largest-number/description/

Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.

Since the result may be very large, so you need to return a string instead of an integer.

Example 1:

Input: nums = [10,2]
Output: "210"
Example 2:

Input: nums = [3,30,34,5,9]
Output: "9534330"

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 109
 */
public class LargestNumber {

    public static void main(String[] args) {
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(nums));
        System.out.println(smallestNumber(nums));

        System.out.println(largestNumberPQ(nums));
    }

    /*
         Time: O(n log n × m)

         Space: O(n * m )

         Where:
             n = number of integers
             m = average length of string representation
  */
    private static String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (first,second) -> (second + first).compareTo(first + second));

        if(strs[0].equals("0")){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }

    /*
    Time: O(n log n × m)

    Space: O(n * m )

    Where:
        n = number of integers
        m = average length of string representation
     */
    private static String largestNumberPQ(int[] nums) {

        Queue<String> pq = new PriorityQueue<>((first,second) -> (second + first).compareTo(first + second));//swap order for smallest num

        for(int i = 0; i < nums.length; i++){
            pq.add(String.valueOf(nums[i]));
        }

        if(!pq.isEmpty() && pq.peek().equals("0")){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll());
        }
        return sb.toString();

    }

    private static String smallestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(strs, (first,second) -> (first + second).compareTo(second + first));

        if(strs[0].equals("0")){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        return sb.toString();
    }
    }




/**
 Key Insight: The beauty of this problem lies in discovering that (b + a).compareTo(a + b) captures the exact ordering we need. This comparator is both transitive and consistent, making it perfect for sorting algorithms.
 */
