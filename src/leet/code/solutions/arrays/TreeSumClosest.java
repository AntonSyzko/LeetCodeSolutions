package leet.code.solutions.arrays;

import java.util.Arrays;


/*
https://leetcode.com/problems/3sum-closest/
Given an integer array nums of length n and an integer target,
find three integers in nums such that the sum is closest to target.

Return the sum of the three integers.

You may assume that each input would have exactly one solution.

Example 1:

Input: nums = [-1,2,1,-4], target = 1
Output: 2
Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
Example 2:

Input: nums = [0,0,0], target = 1
Output: 0
 */
public class TreeSumClosest {

    public static void main(String[] args) {
    int[] nums = new int [] {-1,2,1,-4};
    int res = threeSumClosest(nums, 1);
        System.out.println(res);
    }

    public static int threeSumClosest(int[] nums, int target) {
        int res = nums[0] + nums[1] + nums[nums.length - 1];//first, second, last

        Arrays.sort(nums);//SORT !!!

        for (int firstPointer = 0; firstPointer < nums.length - 2; firstPointer++) {
            int secondPointer = firstPointer + 1; //next to i
            int thirdPointer = nums.length - 1;//last in for loop

            while (secondPointer < thirdPointer) {
                int current_sum = nums[firstPointer] + nums[secondPointer] + nums[thirdPointer];//i, next to i , last

                if (current_sum > target) {//too high
                    thirdPointer--;//reduce end window edge
                } else {// too low
                    secondPointer++;//raise bottom edge ( next to i )
                }

                if (Math.abs(current_sum - target) < Math.abs(res - target)) {//choose between two summs, initial and current
                    res = current_sum;//reset if current is lower than initial
                }
                //and continue in while
            }
            //and for
        }
        return res;
    }
}
