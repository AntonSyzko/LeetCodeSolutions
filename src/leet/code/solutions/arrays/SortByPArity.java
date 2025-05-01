package leet.code.solutions.arrays;

import java.util.Arrays;

/*

https://leetcode.com/problems/sort-array-by-parity/description/
Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition.

Example 1:

Input: nums = [3,1,2,4]
Output: [2,4,3,1]
Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
Example 2:

Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 5000
0 <= nums[i] <= 5000
 */
public class SortByPArity {

    public static void main(String[] args) {
        int[] nums   = {3,1,2,4};
        System.out.println(Arrays.toString(sortArrayByParity(nums)));

        ///  sort by parity 2

        int[] nums2 = {4,2,5,7};
        sortArrayByParityII(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {3,1,4,2};
        sortArrayByParityII(nums3);
        System.out.println(Arrays.toString(nums3));
    }


    private static int[] sortArrayByParity(int[] nums) {
        int[] result = new int[nums.length];

        int left = 0;
        int right = nums.length - 1;

        int indexInOriginaL = 0;

        while(left <= right){
            if(nums[indexInOriginaL] % 2 == 0){
                result[left] = nums[indexInOriginaL];
                left++;
            }else if (nums[indexInOriginaL] % 2 != 0){
                result[right] = nums[indexInOriginaL];
                right--;
            }
            indexInOriginaL++;
        }

        return result;
    }

    /*
    Given an array of integers nums, half of the integers in nums are odd, and the other half are even.

Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.

Return any answer array that satisfies this condition.



Example 1:

Input: nums = [4,2,5,7]
Output: [4,5,2,7]
Explanation: [4,7,2,5], [2,5,4,7], [2,7,4,5] would also have been accepted.
Example 2:

Input: nums = [2,3]
Output: [2,3]
     */

    private static int[] sortArrayByParityII(int[] nums) {

        int oddPointer = 1;
        int evenPointer = 0;
        int len = nums.length;

        while (evenPointer < len && oddPointer < len) {
            while(evenPointer < len && nums[evenPointer] % 2 == 0) {
                evenPointer +=2;
            }
            while(oddPointer < len && nums[oddPointer] % 2 == 1) {
                oddPointer +=2;
            }

            if( evenPointer < len && oddPointer < len){
                int temp = nums[evenPointer];
                nums[evenPointer] = nums[oddPointer];
                nums[oddPointer] = temp;
            }

        }

        return nums;
    }
}
