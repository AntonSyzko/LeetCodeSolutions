package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/max-consecutive-ones/
Given a binary array nums, return the maximum number of consecutive 1's in the array.

Example 1:
Input: nums = [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.

Example 2:
Input: nums = [1,0,1,1,0,1]
Output: 2

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 1, 1, 1};
        int maxOnes = findMaxConsecutiveOnes(nums);
        System.out.println(maxOnes);
    }

    public static int findMaxConsecutiveOnes(int[] nums) {
        int current_max = 0;
        int max_so_far = 0;

        for (int num : nums) {
            if (num == 1) {
                current_max++;
                max_so_far = Math.max(max_so_far, current_max);//update MAX straight away for current ongoing MAX
            } else {
                current_max = 0;//reset current max to 0 when 0 int found as we go
            }
        }
        return max_so_far;//max at the end of all
    }

    public static int findMaxConsecutiveOnesSlick(int[] nums) {
        int current_max = 0, max_so_far = 0;//oneliner

        for (int each : nums) {
            // single = - assign //double==? ternary question
            max_so_far = Math.max(max_so_far, current_max = (each == 0) ? 0 : current_max + 1);
            //max_so_far = Math.max(max_so_far, current_max = each == 0 ? 0 : current_max + 1); //without brackets over ternary question
        }
        return max_so_far;
    }
}