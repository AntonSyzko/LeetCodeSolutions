package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://leetcode.com/problems/sort-colors/description/


Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.



Example 1:

Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Example 2:

Input: nums = [2,0,1]
Output: [0,1,2]


Constraints:

n == nums.length
1 <= n <= 300
nums[i] is either 0, 1, or 2.


Follow up: Could you come up with a one-pass algorithm using only constant extra space?
 */
public class SortColors {

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        System.out.println(Arrays.toString(nums));

        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }


    private  static  void sortColors(int[] nums) {
        if(nums.length == 0 || nums.length == 1) return;

        int zeros = 0;
        int twos = nums.length -1;

        int index = 0;

        while( index <= twos && zeros < twos){
            if(nums[index] == 0){
                nums[index] = nums[zeros];
                nums[zeros] = 0;
                zeros++;
                index++;
            }else if ( nums[index] == 2){
                nums[index] = nums[twos];
                nums[twos] = 2;
                twos--;
                //mind no index moving up
            }else{
                index++;
            }
        }
    }
}
