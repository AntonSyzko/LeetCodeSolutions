package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/two-sum/
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
Input: nums = [3,2,4], target = 6
Output: [1,2]
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};//0,1
        int[] res = twoSum(nums, 9);
        System.out.println(Arrays.toString(res));

        nums = new int[]{3, 2, 4};//1,2
        res = twoSum(nums, 6);
        System.out.println(Arrays.toString(res));

        nums = new int[]{3, 2, 3}; // 0,2
        res = twoSum(nums, 6);
        System.out.println(Arrays.toString(res));

        System.out.println("==================");
        int[] nums3 = new int[]{1, 0, 0, 1, 0,0,1,0,0};
        int[] res3 = treeSum(nums3, 3);
        System.out.println(Arrays.toString(res3));
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        //map of number to it's index position in array
        Map<Integer, Integer> numToIndex = new HashMap<>();// for [3, 2, 3] map will be { {3,0} , {2,1} , {3,2} }

        for (int i = 0; i < nums.length; i++) {

            int differenceFromCurrentNumToTarget = target - nums[i];

            if (numToIndex.containsKey(differenceFromCurrentNumToTarget)) {
                res[0] = numToIndex.get(differenceFromCurrentNumToTarget);
                res[1] = i;
                return res; // break;
            } else {
                numToIndex.put(nums[i], i);
            }
        }
        return res;
    }

    public static int[] treeSum(int[] nums, int target) {
        int[] res = new int[3];

        Map<Integer, Integer> mapa = new HashMap<>();
        mapa.put(nums[0], 0);

        for (int i = 1; i < nums.length; i++) {
            int diff = target - nums[i] - nums[i - 1];
            if(mapa.containsKey(diff)){
                res[0] = i;
                res[1] = i-1;
                res[2] = mapa.get(diff);
            } else {
                mapa.put(nums[i], i);
            }
        }

        return res;
    }


}
