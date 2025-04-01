package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://leetcode.com/problems/move-zeroes/
Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
Note that you must do this in-place without making a copy of the array.

Example 1:
Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]

Example 2:
Input: nums = [0]
Output: [0]

Constraints:
1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
Follow up: Could you minimize the total number of operations done?
 */
public class MoveZeros {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        moveZeroes2(nums);
        System.out.println(Arrays.toString(nums));

       nums = new int[]{0, 0,  1, 2};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));

       nums = new int[]{0};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

        public static void moveZeroes(int[] nums) {
        int nonZeroIndex = 0;//new nonZeroIndex to insert to

        for (int i = 0; i < nums.length; i++) {
           if(nums[i]!=0){
               nums[nonZeroIndex] = nums[i];//reassing NON ZERO to new nonZeroIndex
               nonZeroIndex++;// increment nonZeroIndex
           }
        }

        //all non zeros are set, nonZeroIndex is at last non zero
        for (int i = nonZeroIndex; i < nums.length ; i++) {
            nums[i]=0;//fill the rest with zeros
        }
    }

    public static void moveZeroes2(int[] nums) {

        int indexOfFirstNonZeroElementMet = 0 ;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[indexOfFirstNonZeroElementMet] = nums[i];
                indexOfFirstNonZeroElementMet++;
            }
        }

        while (indexOfFirstNonZeroElementMet < nums.length) {
            nums[indexOfFirstNonZeroElementMet] = 0;
            indexOfFirstNonZeroElementMet++;
        }
    }
}
