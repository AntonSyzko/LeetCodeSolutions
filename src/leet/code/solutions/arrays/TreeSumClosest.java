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

        for (int i = 0; i < nums.length - 2; i++) {
            int a_pointer = i + 1; //next to i
            int b_pointer = nums.length - 1;//last in for loop

            while (a_pointer < b_pointer) {
                int current_sum = nums[i] + nums[a_pointer] + nums[b_pointer];//i, next to i , last

                if (current_sum > target) {//too high
                    b_pointer--;//reduce end window edge
                } else {// too low
                    a_pointer++;//raise bottom edge ( next to i )
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
