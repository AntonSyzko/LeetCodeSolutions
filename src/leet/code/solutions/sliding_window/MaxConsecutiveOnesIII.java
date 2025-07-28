package leet.code.solutions.sliding_window;


/*
https://leetcode.com/problems/max-consecutive-ones-iii/description/

Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

Example 1:

Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


Example 2:

Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

 */
public class MaxConsecutiveOnesIII {

    public static void main(String[] args) {
        // Test Case 1
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println("Test Case 1: " + longestOnes(nums1, k1)); // Expected output: 6

        // Test Case 2
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println("Test Case 2: " + longestOnes(nums2, k2)); // Expected output: 10
    }

    /*
      Time Complexity: O(n) where n is the length of the array

                We process each element at most twice (once when adding to the window, once when removing)

      Space Complexity: O(1)

                We only use a constant amount of extra space regardless of input size
     */

    private static int longestOnes(int[] nums, int maxAllowedZeros) {
     int longestRes = Integer.MIN_VALUE;

     int zerosCount = 0;

     int windowStart = 0;

     for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            if (nums[windowEnd] == 0) {
                 zerosCount++;
            }

            while (zerosCount > maxAllowedZeros){//WHILE

                int leftmostElement = nums[windowStart];

                if(leftmostElement == 0){
                    zerosCount--;
                }

                windowStart++;
            }

            longestRes = Math.max(longestRes, windowEnd - windowStart + 1);
        }

        return longestRes;
        }


        /*
        Direct JUMP
        This approach "jumps" over ones when shrinking the window.
        Once we exceed k zeros, we immediately skip to the next zero without examining each element.

Time Complexity: Still O(n) but with potentially better constant factors in practice.
         */
    private static int longestOnesOptimised(int[] nums, int maxAllowedZeros) {
        int longestRes = Integer.MIN_VALUE;

        int zerosCount = 0;

        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            if (nums[windowEnd] == 0) {
                zerosCount++;
            }

          if(zerosCount > maxAllowedZeros){

              // Keep moving window start until we remove a zero
              while (nums[windowStart] != 0){
                  windowStart++;
              }
              // Skip over that zero
              windowStart++;
              zerosCount = 0;
          }

            longestRes = Math.max(longestRes, windowEnd - windowStart + 1);
        }

        return longestRes;
    }
    }
