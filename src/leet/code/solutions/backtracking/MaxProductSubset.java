package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.techiedelight.com/maximum-product-subset-problem/

Given an integer array, find the maximum product of its elements among all its subsets.

For example,

Input:  nums[] = { -6, 4, -5, 8, -10, 0, 8 }
Output: The maximum product of a subset is 15360 The subset with the maximum product of its elements is { -6, 4, 8, -10, 8 }


 Input:  nums[] = { 4, -8, 0, 8 }
  Output: The maximum product of a subset is 32 The subset with the maximum product of its elements is { 4, 8 }
 */
public class MaxProductSubset {

    public static void main(String[] args) {
//        int[] nums = {4,-8,0,8};
//        System.out.println("The maximum product of a subset is "
//                + findMaxProductRecursive(nums, nums.length));

        List<Integer> nums = Arrays.asList(-6, 4, -5, 8, -10, 0, 8);
        int numsLen = nums.size();

        int startingMax = Integer.MIN_VALUE;
        List<Integer> currentSubset = new ArrayList<>();
        int maximum = findPowerSet(nums, currentSubset, numsLen, startingMax);

        System.out.println("The maximum product of a subset is " + maximum);
    }

    // Function to generate a power set of a given set `S`
    public static int findPowerSet(List<Integer> nums, List<Integer> currentSubset, int indextOfNumFormBack, int maximumSoFar) {
        // if we have considered all elements, we have generated a subset
        if (indextOfNumFormBack == 0) {
            // compute its product of elements and update the maximum product found so far
            return findMaxProductRecursive(currentSubset, maximumSoFar);
        }

        // consider the n'th element - PICK
        currentSubset.add(nums.get(indextOfNumFormBack - 1));

        maximumSoFar = findPowerSet(nums, currentSubset, indextOfNumFormBack - 1, maximumSoFar);//recur

        currentSubset.remove(currentSubset.size() - 1);// backtrack

        // or don't consider the n'th element  -> NOT PICK
        return findPowerSet(nums, currentSubset, indextOfNumFormBack - 1, maximumSoFar);
    }



    public static int findMaxProductRecursive(List<Integer> set, int maximumSoFar) {
        int product = 1;

        for (int j: set) {
            product = product * j;
        }

        // if the set is not empty, then update the product
        if (set.size() != 0) {
            maximumSoFar = Integer.max(maximumSoFar, product);
        }

        return maximumSoFar;
    }




    private static int findMaxProduct(int[] nums, int length) {
            // base case
            if (length == 0) {//length exhausted
                return 0;
            }

            // if the array contains only one element
            if (length == 1) {
                return nums[0];
            }

            int product = 1;    // to store the maximum product subset

            // stores the negative element having a minimum absolute value in the set
            int abs_min_so_far = Integer.MAX_VALUE;

            // maintain the count of negative elements in the set
            int negative = 0;

            // maintain the count of positive elements in the set
            int positive = 0;

            boolean contains_zero = false;

            // traverse the given array
            for (int i = 0; i < length; i++) {

                // if the current element is negative
                if (nums[i] < 0) {

                    negative++;
                    abs_min_so_far = Integer.min(abs_min_so_far, Math.abs(nums[i]));

                } else if (nums[i] > 0) { // if the current element is positive
                    positive++;
                }

                // if the current element is zero
                if (nums[i] == 0) {
                    contains_zero = true;
                } else {
                    product = product * nums[i];
                }
            }

            // if an odd number of negative elements are present, exclude the
            // negative element having a minimum absolute value from the subset
            if ((negative & 1) != 0) {
                product = product / -abs_min_so_far;
            }

            // special case – set contains one negative element and one or more zeros
            if (negative == 1 && positive == 0 && contains_zero) {
                product = 0;
            }

            // special case – set contains all zeros
            if (negative == 0 && positive == 0 && contains_zero) {
                product = 0;
            }

            // return maximum product
            return product;
        }

}
