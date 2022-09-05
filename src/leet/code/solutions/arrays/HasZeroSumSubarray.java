package leet.code.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/*
https://www.techiedelight.com/check-subarray-with-0-sum-exists-not/

Given an integer array, check if it contains a subarray having zero-sum.

For example,
Input:  { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 }
Output: Subarray with zero-sum exists
The subarrays with a sum of 0 are:

{ 3, 4, -7 }
{ 4, -7, 3 }
{ -7, 3, 1, 3 }
{ 3, 1, -4 }
{ 3, 1, 3, 1, -4, -2, -2 }
{ 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 }
Note that the problem deals with subarrays that are contiguous, i.e., whose elements occupy consecutive positions in the array.
 */
public class HasZeroSumSubarray {
    public static void main(String[] args) {
        int[] nums = {3, 4, -7, 3, 1, 3, 1, -4, -2, -2};
        System.out.println(hasZeroSumSubarray(nums));
    }

    // Function to check if subarray with zero-sum is present in a given array or not
    public static Boolean hasZeroSumSubarray(int[] nums) {
        // create an empty setOfSubarraySums to store the sum of elements of each subarray `nums[0…i]`, where `0 <= i < nums.length`
        Set<Integer> setOfSubarraySums = new HashSet<>();

        // insert 0 into the setOfSubarraySums to handle the case when subarray with zero-sum starts from index 0
        setOfSubarraySums.add(0);

        int sum = 0;

        // traverse the given array
        for (int value: nums) {
            // sum of elements so far
            sum += value;

            // if the sum is seen before, we have found a subarray with zero-sum
            if (setOfSubarraySums.contains(sum)) {
                return true;
            }

            // insert sum so far into the setOfSubarraySums
            setOfSubarraySums.add(sum);
        }

        // we reach here when no subarray with zero-sum exists
        return false;
    }

}
