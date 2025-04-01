package leet.code.solutions.dynamic_programming;

/*
https://www.callicoder.com/equal-subset-sum-partition-problem/

Equal Subset Sum Partition Problem
Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.


Problem Identification
Let’s say that the array nums can be broken down into two subsets S1 and S2 of equal sums. We can observe the following things

// Sum of all the numbers in subset S1 + Sum of all the numbers in subset S2 = Sum of all the numbers in the array
Sum(S1) + Sum(S2) = Sum(nums)

// If Sum(S1) and Sum(S2) is equal
2*Sum(S1) = Sum(nums)

Sum(S1) = Sum(nums)/2

The problem reduces to finding a subset of sum Sum(nums)/2 in the array.
If we can find a subset of sum Sum(nums)/2 then we can definitely partition the array into two subsets of equal sums.
 */
public class FindEqualSubsetSum {

    public static void main(String[] args) {
        int[] values = {1,5,11,5};// 1 + 5 + 5  = 11

        int sum = 0;
        for(int i = 0; i < values.length; i++) {
            sum += values[i];
        }

        //if sum/2 existsts - there is a subset equal
        boolean hasEqualSubsets = hasSubsetSum(values, sum / 2, values.length);
        System.out.println(hasEqualSubsets);
    }


    private static boolean hasSubsetSum(int[] values, int targetSum, int length) {
        //BASE case
        if(targetSum == 0){
            return true;
        }

        //len exhaused to zero - no result
        if(length == 0){
            return false;
        }

        if(values[length - 1] > targetSum){//if current last is itself BIGGER than target sum - EXCLUDE it !
            return hasSubsetSum(values, targetSum , length - 1);
        }

        boolean includeCurrent = hasSubsetSum(values, targetSum - values[length - 1], length - 1);
        boolean excludeCurrent = hasSubsetSum(values, targetSum , length - 1);

        return includeCurrent || excludeCurrent;

    }
    }
