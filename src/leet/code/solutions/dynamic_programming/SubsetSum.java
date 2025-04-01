package leet.code.solutions.dynamic_programming;

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

       boolean exists = hasSubsetSum(nums, targetSum, n);

       System.out.println(exists);
    }

    //time O(2^n) Exponential
    //space O(1) only call stack
    private static boolean hasSubsetSum(int[] values, int targetSum, int length) {
        //BASE: We can't obtain a positive sum with no items
        if(length == 0){
            return false;
        }

        //BASE:  We DO CAN obtain a zero sum by not choosing any items and having an empty subset(above passed)
        if(targetSum == 0) {
            return true;
        }

        if(values[length-1] > targetSum) {//current item itself is BIGGER that target sum
            return  hasSubsetSum(values, targetSum, length - 1);//repeat without it ( without last) - excluding
        }

        boolean includingCurrentItem = hasSubsetSum(values, targetSum - values[length - 1], length - 1);
        boolean excludingCurrentItem = hasSubsetSum(values, targetSum, length - 1);

        return includingCurrentItem || excludingCurrentItem;

    }
    }
