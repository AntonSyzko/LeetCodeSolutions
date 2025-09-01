package leet.code.solutions.arrays;

/*
4

https://leetcode.com/problems/median-of-two-sorted-arrays/description/

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
 */
public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {

        // Example 1: [1,3], [2] -> 2.0
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Example 1: " + findMedianSortedArrays(nums1, nums2));

        // Example 2: [1,2], [3,4] -> 2.5
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Example 2: " + findMedianSortedArrays(nums3, nums4));

        // Additional test: [1,2,3], [4,5,6] -> 3.5
        int[] nums5 = {1, 2, 3};
        int[] nums6 = {4, 5, 6};
        System.out.println("Additional test: " + findMedianSortedArrays(nums5, nums6));
    }

    //O(log(min(m,n)))
    //O(1)
    private static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array for optimization
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {
            // Partition nums1 at position i
            int partitionX = (left + right) / 2;
            // Partition nums2 at position j such that left half has (m+n+1)/2 elements
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Get boundary elements
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // Perfect partition found
                if ((m + n) % 2 == 0) {
                    // Even total length - median is average of two middle elements
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    // Odd total length - median is the maximum of left partition
                    return Math.max(maxLeftX, maxLeftY);
                }
            }
            // Adjust partition boundaries
            else if (maxLeftX > minRightY) {
                // We are too far right in nums1, move left
                right = partitionX - 1;
            } else {
                // We are too far left in nums1, move right
                left = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted");
    }

    //O(n) solution
    private static double findMedianSortedArraysO_N(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        int [] merged = new int[n + m];

        int i = 0;
        int j = 0;
        int mergedIndex = 0;

        //merge
        while ( i < n && j < m) {//AND
            if(nums1[i] < nums2[j]) {
                merged[mergedIndex++] = nums1[i++];
            }else{
                merged[mergedIndex++] = nums2[j++];
            }
        }

        //left overs
        while (i < n) {
            merged[mergedIndex++] = nums1[i++];
        }
        while (j < m) {
            merged[mergedIndex++] = nums2[j++];
        }

        int totalElements = n + m;

        if(totalElements % 2 == 0) {//even size

            return (merged[(totalElements / 2)] + merged[(totalElements / 2) -1] )/ 2.00;//(middle element + one before ) / 2.00

        }else{//odd size

            return merged[(totalElements / 2)];//just the very middle element of ODD size array is a median

        }
    }
}

/**

Key Insights:

Partition Strategy: We partition both arrays such that the left half contains exactly (m+n+1)/2 elements
Binary Search: We only search on the smaller array to optimize performance
Boundary Conditions: Use Integer.MIN_VALUE and Integer.MAX_VALUE for edge cases
Validation: Check if maxLeft ≤ minRight for both arrays to ensure correct partition

Example 1: [1,3] and [2]

Total length = 3 (odd), so median is middle element
Partition: [1] | [3] and [] | [2]
Left partition: [1], Right partition: [2,3]
Median = max(1) = 2 ❌
Try again: [] | [1,3] and [2] | []
Left partition: [2], Right partition: [1,3]
This gives us median = 2 ✓

Example 2: [1,2] and [3,4]

Total length = 4 (even), so median is average of two middle elements
Correct partition gives us left: [1,2], right: [3,4]
Median = (2 + 3) / 2 = 2.5 ✓



 **/

//#hard