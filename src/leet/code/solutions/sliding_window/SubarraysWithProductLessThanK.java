package leet.code.solutions.sliding_window;

/*
https://leetcode.com/problems/subarray-product-less-than-k/description/

Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.

Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0

Constraints:

1 <= nums.length <= 3 * 104
1 <= nums[i] <= 1000
0 <= k <= 106
 */
public class SubarraysWithProductLessThanK {

    public static void main(String[] args) {
        int[] nums = {10,5,2,6};
        int k = 100;

        int subarrays = numSubarrayProductLessThanK(nums, k);
        System.out.println(subarrays);
    }

    /*
            Time and Space Complexity
                Time Complexity: O(n)

                Each element is visited at most twice (once by right pointer, once by left pointer)
                The while loop doesn't create nested iterations because left only moves forward

        Space Complexity: O(1)

            Only using a few variables regardless of input size
            No additional data structures needed
     */
    private static int numSubarrayProductLessThanK(int[] nums, int k) {

        if(k <= 1) return 0;

        int windowStart = 0;

         int product = 1;//tostart with 1 as *1 is same in the beginning
        int res = 0;


        for(int windowEnd = 0; windowEnd < nums.length; windowEnd++) {

            product *= nums[windowEnd];//aggregate product

            while (product >= k) {//exceeded product

                product /= nums[windowStart];//decrease product
                windowStart++;//shrink window

            }

            /*
            The crucial insight is:
            when we have a valid window from left to right, the number of new subarrays ending at right is (right - left + 1).
             */
            res += windowEnd - windowStart + 1;

        }

        return res;
    }
    /**
     Key Insights for Learning

      Sliding Window Pattern: When you need to find contiguous subarrays with certain properties, consider sliding window

      Two Pointers: Use left and right pointers to maintain a valid window

      Counting Logic: right - left + 1 gives us all subarrays ending at the current position

      Edge Cases: Always check constraints (here: k ≤ 1 means no valid subarrays)

      This technique is commonly used in problems involving contiguous subarrays with product/sum constraints!
     **/
}
