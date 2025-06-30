package leet.code.solutions.arrays;

/*
581

https://leetcode.com/problems/shortest-unsorted-continuous-subarray/description/

Given an integer array nums, you need to find one continuous subarray such that if you only sort this subarray in non-decreasing order, then the whole array will be sorted in non-decreasing order.

Return the shortest such subarray and output its length.

Example 1:

Input: nums = [2,6,4,8,10,9,15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Example 2:

Input: nums = [1,2,3,4]
Output: 0
Example 3:

Input: nums = [1]
Output: 0

Constraints:

1 <= nums.length <= 104
-105 <= nums[i] <= 105

Follow up: Can you solve it in O(n) time complexity?
 */
public class FindUnsortedSubarray {

    public static void main(String[] args) {
        int[] nums = {1,3,2,2,2};
        int res =  findUnsortedSubarray(nums);
        System.out.println(res);
    }

    private static int findUnsortedSubarray(int[] nums) {
        int len = nums.length;

        int rightmostViolation = -1;
        int leftmostViolation = -1;

        int max = nums[0];

        for(int i = 0 ; i < len; i++){

            if(nums[i]< max){//nums[i] has to be BIGGER than prev MAX
                rightmostViolation = i;
            }else{
                max = nums[i];
            }
        }

        int min = nums[len-1];

        for(int i = len - 2; i >= 0;i--){

            if(nums[i] > min){//nums[i] has to be SMALLER than prev min
                leftmostViolation = i;
            }else{
                min = nums[i];
            }
        }

        return leftmostViolation == -1 ? 0 : rightmostViolation - leftmostViolation + 1;
    }
    }
