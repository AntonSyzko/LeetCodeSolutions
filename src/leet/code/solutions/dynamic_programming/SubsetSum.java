package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
https://www.callicoder.com/subset-sum-problem/

Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to the given sum.

it's a KNAPSACK typr of a  problem

Examples

Input: values[] = {3, 34, 4, 12, 5, 2}, sum = 9
Output: True
There is a subset (4, 5) with sum 9.

Input: values[] = {3, 34, 4, 12, 5, 2}, sum = 30
Output: False
There is no subset that add up to 30.
 */
public class SubsetSum {

    public static void main(String[] args) {
       int[] nums = {3, 34, 4, 12, 5, 2};
       int n = nums.length;
       int targetSum = 9;

       boolean exists = hasSubsetSumMemoized(nums, targetSum, n);

       System.out.println(exists);

//
        int [] nums2 = {3, 34, 4, 12, 5, 2};
        targetSum = 30;
        exists = hasSubsetSumBottomUp(nums2, targetSum);
        System.out.println(exists);

    }

    //time O(2^n) Exponential
    //space O(1) only call stack
    private static boolean hasSubsetSumRec(int[] values, int targetSum, int length) {
        //BASE: We can't obtain a positive sum with no items
        if(length == 0){
            return false;
        }

        //BASE:  We DO CAN obtain a zero sum by not choosing any items and having an empty subset(above passed)
        if(targetSum == 0) {
            return true;
        }

        if(values[length-1] > targetSum) {//current item itself is BIGGER that target sum
            return  hasSubsetSumRec(values, targetSum, length - 1);//repeat without it ( without last) - excluding
        }

        boolean includingCurrentItem = hasSubsetSumRec(values, targetSum - values[length - 1], length - 1);
        boolean excludingCurrentItem = hasSubsetSumRec(values, targetSum, length - 1);

        return includingCurrentItem || excludingCurrentItem;
    }

    //------------------- RECURSION with MEMO --------------------------------

    private static Boolean[][] subsetSumsMemo;

    private static boolean hasSubsetSumMemoized(int[] values, int targetSum, int length) {

       int ROWS = targetSum + 1;
       int COLS = length + 1;

       subsetSumsMemo = new Boolean[ROWS][COLS];

        for (int row = 0; row <= targetSum; row++) {
            for (int col = 0; col <= length; col++) {
                subsetSumsMemo[row][col] = null;
            }
        }

        return hasSubsetSumRecurWithMemo(values, targetSum, length);
    }


    private static boolean hasSubsetSumRecurWithMemo(int[] values, int targetSum, int length) {
       if(targetSum == 0) {
           return true;
       }


       if(length == 0){
           return false;
       }


       // if previously seen
        if(subsetSumsMemo[targetSum][length] != null) {
            return subsetSumsMemo[targetSum][length];
        }

        if(values[length-1] > targetSum) {
            subsetSumsMemo[targetSum][ length] =  hasSubsetSumRecurWithMemo(values, targetSum, length - 1);
        }else{
            subsetSumsMemo[targetSum][length] = hasSubsetSumRecurWithMemo(values, targetSum - values[length-1], length - 1)

                    ||//OR

            hasSubsetSumRecurWithMemo(values, targetSum, length - 1);
        }

        return subsetSumsMemo[targetSum][length];

    }

    private static boolean hasSubsetSumBottomUp(int[] values, int targetSum) {
        int len = values.length;
        boolean[][] subsetSum = new boolean[targetSum + 1][len + 1];
        subsetSum[0][0] = true;

        for (int numberInTargetSum = 1; numberInTargetSum <= targetSum; numberInTargetSum++) {//
            for (int numberInInput = 1; numberInInput <= len; numberInInput++) {

                if (values[numberInInput - 1] > numberInTargetSum) {//too high number itself comparing to number in target

                    subsetSum[numberInTargetSum][numberInInput] = subsetSum[numberInTargetSum][numberInInput - 1];

                } else {

                    subsetSum[numberInTargetSum][numberInInput] =

                            subsetSum[numberInTargetSum - values[numberInInput - 1]][numberInInput - 1]
                            ||
                            subsetSum[numberInTargetSum][numberInInput - 1];

                }
            }
        }

        return subsetSum[targetSum][len];
    }


    }

    /**
      boolean memo matrix is zero based and

     ROW: target sum each number as a row - but zero indexed
     COL: each number from nums input

     **/