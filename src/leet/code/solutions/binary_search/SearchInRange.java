package leet.code.solutions.binary_search;

/*
34

https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/

Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target value.

If target is not found in the array, return [-1, -1].

You must write an algorithm with O(log n) runtime complexity.

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
Example 3:

Input: nums = [], target = 0
Output: [-1,-1]

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums is a non-decreasing array.
-109 <= target <= 109
 */
public class SearchInRange {

    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        if (nums == null || nums.length == 0) {
            return result;
        }

        // Find the leftmost (first) position
        result[0] = findFirst(nums, target);

        // If target not found, return [-1, -1]
        if (result[0] == -1) {
            return result;
        }

        // Find the rightmost (last) position
        result[1] = findLast(nums, target);

        return result;
    }

    private static int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                res = mid; // Found target, but continue searching left
                right = mid - 1; // Search in left half for earlier occurrence

            }else if (nums[mid] < target) {

               left = mid + 1;

            }else{

                right = mid - 1;

            }
        }

        return res;
    }

    private static int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        int res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                res = mid; // Found target, but continue searching right
                left = mid + 1;  // Search in right half for later occurrence

            }else if ( nums[mid] < target) {

                left = mid + 1;

            }else{

                right = mid - 1;

            }
        }

        return res;
    }


    public static void main(String[] args) {
        // Test case 1: [5,7,7,8,8,10], target = 8
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int[] result1 = searchRange(nums1, 8);
        System.out.println("Test 1: [" + result1[0] + ", " + result1[1] + "]"); // Expected: [3, 4]

        // Test case 2: [5,7,7,8,8,10], target = 6
        int[] result2 = searchRange(nums1, 6);
        System.out.println("Test 2: [" + result2[0] + ", " + result2[1] + "]"); // Expected: [-1, -1]

        // Test case 3: [], target = 0
        int[] nums3 = {};
        int[] result3 = searchRange(nums3, 0);
        System.out.println("Test 3: [" + result3[0] + ", " + result3[1] + "]"); // Expected: [-1, -1]

        // Test case 4: [1], target = 1
        int[] nums4 = {1};
        int[] result4 = searchRange(nums4, 1);
        System.out.println("Test 4: [" + result4[0] + ", " + result4[1] + "]"); // Expected: [0, 0]
    }
}
