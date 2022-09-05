package leet.code.solutions.arrays;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

/*
https://leetcode.com/problems/majority-element/

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:
n == nums.length
1 <= n <= 5 * 104
-231 <= nums[i] <= 231 - 1
Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 3};
        int res = majorityElement(nums);
        System.out.println(res);

        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        res = majorityElement(nums);
        System.out.println(res);
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> intToTimesOccurred = new HashMap<>();

        for (int each : nums) {
            // +1 because we have to account for current occurence
            if (intToTimesOccurred.containsKey(each) && intToTimesOccurred.get(each) + 1 > nums.length / 2) {//> then a half of elements (length/2)
                return each;
            } else {
                intToTimesOccurred.put(each, intToTimesOccurred.getOrDefault(each, 0) + 1);
            }
        }
        return -1;//not found
    }

    public static int majorityElement_just_most_occured(int[] nums) {


        Map<Integer, Integer> intToTimesOccurred = new HashMap<>();

        for (int each : nums) {
            if (!intToTimesOccurred.containsKey(each)) {
                intToTimesOccurred.put(each, 1);
            } else {
                intToTimesOccurred.put(each, intToTimesOccurred.get(each) + 1);
            }
        }

        return intToTimesOccurred.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
