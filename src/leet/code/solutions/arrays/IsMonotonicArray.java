package leet.code.solutions.arrays;

/*

https://leetcode.com/problems/monotonic-array/description/


An array is monotonic if it is either monotone increasing or monotone decreasing.

An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].

Given an integer array nums, return true if the given array is monotonic, or false otherwise.



Example 1:

Input: nums = [1,2,2,3]
Output: true
Example 2:

Input: nums = [6,5,4,4]
Output: true
Example 3:

Input: nums = [1,3,2]
Output: false


Constraints:

1 <= nums.length <= 105
-105 <= nums[i] <= 105
 */
public class IsMonotonicArray {

    public static void main(String[] args) {
   int[] numsDecreasing = {6,5,5,4};
   System.out.println(isMonotonic(numsDecreasing));

        int[] numsIncreasing = {1,2,2,3};
        System.out.println(isMonotonic(numsIncreasing));

        int[] numsIncreaseDecrease = {1,3,2};
        System.out.println(isMonotonic(numsIncreaseDecrease));

        int[] numsPlain = {1,1,1};
        System.out.println(isMonotonic(numsPlain));


    }

    private static boolean isMonotonic(int[] nums) {

       boolean increase = true;
       boolean decrease = true;

        for (int i = 1; i < nums.length ; i++) {
            if(nums[i] > nums[i-1]){
                decrease = false;
            }

            if(nums[i] < nums[i-1]){
                increase = false;
            }
        }

        return increase || decrease;
    }

}
