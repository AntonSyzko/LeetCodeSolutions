package leet.code.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/*
https://www.techiedelight.com/find-largest-sub-array-formed-by-consecutive-integers/

Given an integer array, find the largest subarray formed by consecutive integers. The subarray should contain all distinct values.

For example,

Input:                                  { 2, 0, 2, 1, 4, 3, 1, 0 }
Output: The largest subarray is            { 0, 2, 1, 4, 3 }
 */
public class LargestArrayFormedByConsecutiveIntegers {

    public static void main(String[] args) {
        int[] nums = { 2, 0, 2, 1, 4, 3, 1, 0 };
        int[] result = findLargestConsecutiveSubarray(nums);

        System.out.print("The largest subarray is { ");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
    }


    public static int[] findLargestConsecutiveSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int maxLength = 0;
        int startIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            // Set to track unique elements in current subarray
            Set<Integer> uniqueElements = new HashSet<>();

            // Variables to track min and max values in current subarray
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;

            // Extend current subarray as far as possible
            for (int j = i; j < nums.length; j++) {
                // If element is already seen, stop extending
                if (uniqueElements.contains(nums[j])) {
                    break;
                }

                // Add current element to set
                uniqueElements.add(nums[j]);

                // Update min and max
                min = Math.min(min, nums[j]);
                max = Math.max(max, nums[j]);

                // Check if the current subarray contains consecutive integers
                // For a subarray with consecutive integers, the difference between
                // max and min should be equal to the number of unique elements minus 1
                if (max - min == j - i) {
                    // This is a valid subarray with consecutive integers
                    int currentLength = j - i + 1;

                    // Update result if this is longer than our previous best
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        startIndex = i;
                    }
                }
            }
        }

        // Extract the largest subarray
        int[] result = new int[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = nums[startIndex + i];
        }

        return result;
    }
}
