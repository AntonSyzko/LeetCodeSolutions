package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/find-the-duplicate-number/

Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
There is only one repeated number in nums, return this repeated number.
You must solve the problem without modifying the array nums and uses only constant extra space.

Example 1:
Input: nums = [1,3,4,2,2]
Output: 2

Example 2:
Input: nums = [3,1,3,4,2]
Output: 3

Constraints:

1 <= n <= 105
nums.length == n + 1
1 <= nums[i] <= n
All the integers in nums appear only once except for precisely one integer which appears two or more times.


Follow up:

How can we prove that at least one duplicate number must exist in nums?
Can you solve the problem in linear runtime complexity?
 */
public class FindDuplicate {
    public static void main(String[] args) {
        int [] nums = {1,3,4,2,2};
        System.out.println("duplicate "+ findDuplicate(nums));
    }

    private static int findDuplicate(int[] array){
        if(array.length >1 ){
            int slow = array[0];
            int fast = array[array[0]];

            while (slow != fast) {
                slow = array[slow];
                fast = array[array[fast]];
            }//both stopped on SAME element

            fast = 0;//reset fast ( or slow - reset one of them )

            while (fast != slow) { //loop again till they are SAME
                fast = array[fast];
                slow = array[slow];
            }

            return slow;//slow is SAME as fast but different element in ARRAY
        }
        return -1;//no duplicate
    }
}
