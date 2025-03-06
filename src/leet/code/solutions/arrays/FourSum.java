package leet.code.solutions.arrays;

import java.util.*;

/*
Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c+ d = target?

Find all unique quadruplets in the array which gives the sum of target.

Note: Elements in a quadruplet (a,b,c,d) must be in non-descending order.
 (ie, a ≤b ≤ c ≤ d)

 The solution set must not contain duplicate quadruplets.


For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
A solution set is:
(-1, 0, 0, 1)
(-2, -1, 1, 2)
(-2, 0, 0, 2)
 */
public class FourSum {

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int targetSum = 0;
        List<List<Integer>> result = fourSum(nums, targetSum);
        System.out.println(result);

    }

    private static List<List<Integer>> fourSum(int[] num, int target) {
        Arrays.sort(num);//important for window shrinking

        List<List<Integer>> result = new ArrayList<>();

        Set<List<Integer>> uniqueCombinations = new HashSet<>();

        for (int i = 0; i < num.length; i++) {

            for (int j = i + 1; j < num.length; j++) {

                      int left = j + 1;
                      int right = num.length - 1;

                      while (left < right) {

                          int currentSum = num[i] + num[j] + num[left] + num[right];

                          if(currentSum  < target){

                              left++;

                          }else if(currentSum > target){

                              right--;

                          }else if(currentSum == target){

                              List<Integer> currentCombinationResult = List.of(num[i], num[j], num[left], num[right]);

                              if(!uniqueCombinations.contains(currentCombinationResult)){//not present in previous results
                                  uniqueCombinations.add(currentCombinationResult);
                                  result.add(currentCombinationResult);
                              }

                              left++;
                              right--;
                          }
                      }
            }
        }
        return result;
    }
}
