package leet.code.solutions.arrays;

/*
https://www.techiedelight.com/maximum-product-subset-problem/

Maximum Product Subset Problem
Given an integer array, find the maximum product of its elements among all its subsets.

For example,

Input:  nums[] = { -6, 4, -5, 8, -10, 0, 8 }
Output: The maximum product of a subset is 15360 The subset with the maximum product of its elements is { -6, 4, 8, -10, 8 }

  Input:  nums[] = { 4, -8, 0, 8 }
   Output: The maximum product of a subset is 32 The subset with the maximum product of its elements is { 4, 8 }
 */
public class FindMaxProductSubset {

    public static void main(String[] args) {

        int[] nums = { -6, 4, -5, 8, -10, 0, 8 };

        System.out.println("The maximum product of a subset is "  + findMaxProduct(nums, nums.length));
    }


    private static int findMaxProduct(int[] nums, int len){
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return 1;

        int product = 1;
        int negativeCount = 0;
        int positiveCount  = 0;

        boolean containsZero = false;

        int absMinValue = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {

            int curr = nums[i];

            if(curr < 0){
                negativeCount++;
                absMinValue = Math.min(absMinValue, Math.abs(curr));
            } else if (curr > 0){
                positiveCount++;

            }

            if(curr == 0){
                containsZero = true;
            }else{
                product *= curr;
            }

        }

        //case1: negative count is ODD
        if((negativeCount % 2) != 0){
            // subtract SMALLEST negative by division
            product  = product / - absMinValue;// MIND minus
        }

        //case2: 1 negative and some zeroes
        if(negativeCount == 1 && positiveCount == 0 && containsZero){
            product = 0;
        }

        //case3: all zeroes
        if(negativeCount == 0 && positiveCount == 0 && containsZero){
            product = 0;
        }

        return product;
    }
}
