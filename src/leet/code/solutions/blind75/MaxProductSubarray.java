package leet.code.solutions.blind75;

/*
152

https://leetcode.com/problems/maximum-product-subarray/description/

Given an integer array nums, find a subarray that has the largest product, and return the product.

The test cases are generated so that the answer will fit in a 32-bit integer.

Example 1:

Input: nums = [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.

Example 2:

Input: nums = [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

Constraints:

1 <= nums.length <= 2 * 104
-10 <= nums[i] <= 10
The product of any subarray of nums is guaranteed to fit in a 32-bit integer.
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
      int[] nums = {3,-1,0,4};
      int maxProd = maxProduct(nums);
      System.out.println(maxProd);

        int[] nums2 = {-2,3,0,-4};
        int maxProduct = maxProduct(nums2);
        System.out.println(maxProduct);

        int[] nums3 = {2,3,-2,4};
        int maxProduct2 = maxProduct(nums3);
        System.out.println(maxProduct2);
    }

    /*
            Time complexity:
                 O(n)
            Space complexity:
                 O(1)
     */
    private static int maxProduct(int[] nums) {
        int maxSoFar = nums[0];

        int maxEndingHere = nums[0];
        int minEndingHere = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int curr = nums[i];
            // Store maxEndingHere temporarily since we'll modify it
            int tempMax = Math.max(maxEndingHere, Math.max(maxEndingHere * curr, minEndingHere * curr));

            minEndingHere = Math.min(minEndingHere, Math.min(maxEndingHere * curr, minEndingHere * curr));

            maxEndingHere  = tempMax;

            maxSoFar = Math.max(maxSoFar, maxEndingHere);

        }

        return maxSoFar;
    }

    /**
            For each element, we have 3 choices:

                Start a new subarray from current element
                Extend the previous maximum subarray
                Extend the previous minimum subarray (important for negative numbers!)
     **/


    private static int maxProductSubarray(int[] nums) {
        if(nums.length==0) return 0;

        int min = nums[0];
        int max = nums[0];
        int res = max;

        for(int i = 1; i < nums.length;i++){

            int curr = nums[i];

            //for MAX we will chose  max of 3: curr itself , product of curr AND max, product of curr AND min

            //if we use max directly we will override it and overridden obe will be used for MIn calc, which is wrong -> hence temp
            int tempMax = Math.max(curr, Math.max((max * curr), (min * curr)));

            //for MIN we will chose  MIN of 3: curr, product of curr AND previous ( not temp) max, product of curr AND min
            min = Math.min(min, Math.min((min * curr), (max * curr)));

            //update max to previously calculated
            max = tempMax;

            res = Math.max(res, max);
        }

        return res;
    }
}
