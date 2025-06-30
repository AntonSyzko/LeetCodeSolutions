package leet.code.solutions.arrays;

/*
238

Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.



Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]


Constraints:

2 <= nums.length <= 105
-30 <= nums[i] <= 30
The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.


Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {

    public static void main(String[] args) {
        // Test case 1: Normal case
        int[] nums1 = {1, 2, 3, 4};
        System.out.println("=== TEST CASE 1 ===");
        System.out.println("Input: " + java.util.Arrays.toString(nums1));
        System.out.println("Expected: [24, 12, 8, 6]");
        System.out.println("Two Arrays: " + java.util.Arrays.toString(productExceptSelfTwoArrays(nums1)));
        System.out.println("Optimized: " + java.util.Arrays.toString(productExceptSelfOptimized(nums1)));
        System.out.println();
    }

    /*
      APPROACH 1: Two Separate Arrays (Most Intuitive)

      Create two arrays:
      - leftProducts[i] = product of all elements to the left of i
      - rightProducts[i] = product of all elements to the right of i
      - result[i] = leftProducts[i] × rightProducts[i]

      Time: O(n), Space: O(n) extra space
     */
    public static int[] productExceptSelfTwoArrays(int[] nums) {
        int n = nums.length;

        // leftProducts[i] = product of nums[0] * nums[1] * ... * nums[i-1]
        int[] leftProducts = new int[n];

        // rightProducts[i] = product of nums[i+1] * nums[i+2] * ... * nums[n-1]
        int[] rightProducts = new int[n];

        int[] result = new int[n];

        // Build left products array
        leftProducts[0] = 1; // No elements to the left of index 0

        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        // Build right products array
        rightProducts[n - 1] = 1; // No elements to the right of last index

        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        // Combine left and right products
        for (int i = 0; i < n; i++) {
            result[i] = leftProducts[i] * rightProducts[i];
        }

        return result;
    }

    /*
      APPROACH 2: Space Optimized (Use result array for left products)

      Optimization: Instead of separate leftProducts array,
      store left products directly in the result array.
      Then multiply with right products on-the-fly.

      Time: O(n), Space: O(1) extra space (not counting output array)
     */
    public static int[] productExceptSelfOptimized(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Step 1: Store left products in result array
        // result[i] = product of all elements to the left of i
        result[0] = 1; // No elements to the left of index 0
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply with right products on-the-fly
        // rightProduct keeps track of product of elements to the right
        int rightProduct = 1; // Start with 1 (no elements to the right of last index)
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * rightProduct; // left × right
            rightProduct *= nums[i]; // Update rightProduct for next iteration
        }

        return result;
    }
    }
