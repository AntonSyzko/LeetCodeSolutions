package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/single-number/
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 3:
Input: nums = [1]
Output: 1
 */
public class SingleNumber {

    public static void main(String[] args) {

        int[] nums = new int[]{4, 1, 2, 1, 2};
        int res = singleNumber(nums);
        System.out.println(res);

        nums = new int[]{2, 2, 1};
        res = singleNumber(nums);
        System.out.println(res);

        nums = new int[]{1};
        res = singleNumber(nums);
        System.out.println(res);

    }

    public static int singleNumber(int[] nums) {
        Set<Integer> setOfUniques = new HashSet<>();
        for (int each : nums) {
            if (setOfUniques.contains(each)) {
                setOfUniques.remove(each);
            } else {
                setOfUniques.add(each);
            }
        }
        for (int eachUnique : setOfUniques) {
            return eachUnique;
        }
        return -1;
    }

    public static int singleNumberMap(int[] nums) {
        Map<Integer, Integer> timesOccured = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            timesOccured.put(nums[i], timesOccured.getOrDefault(nums[i], 0) + 1);
        }

        return timesOccured.entrySet().stream().filter(e -> e.getValue() == 1).findFirst().get().getKey();
    }
}

