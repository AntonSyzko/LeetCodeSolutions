package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
You are given an array of distinct integers nums and a target integer target. Your task is to return a list of all unique combinations of nums where the chosen numbers sum to target.

The same number may be chosen from nums an unlimited number of times. Two combinations are the same if the frequency of each of the chosen numbers is the same, otherwise they are different.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input:
nums = [2,5,6,9]
target = 9

Output: [[2,2,5],[9]]
Explanation:
2 + 2 + 5 = 9. We use 2 twice, and 5 once.
9 = 9. We use 9 once.

Example 2:

Input:
nums = [3,4,5]
target = 16

Output: [[3,3,3,3,4],[3,3,5,5],[4,4,4,4],[3,4,4,5]]
Example 3:

Input:
nums = [3]
target = 5

Output: []
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] nums = {2,5,6,9};
        int targetSUm = 9;

        List<List<Integer>> res = combinationSumOptimised(nums, targetSUm);

        System.out.println(res);
    }

    private static List<List<Integer>> combinationSumOptimised(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);// !!!!!!!!!!! SORT

        int startNum = 0;
        int ongoingSumAggregation = 0;
        List<Integer> ongoingCOmbination = new ArrayList<>();

        dfs(nums, startNum, ongoingSumAggregation,target, ongoingCOmbination,res);

        return res;
    }

    private static void dfs(int[] nums, int startNum, int ongoingSumAggregation, int target, List<Integer> ongoingCombination, List<List<Integer>> res) {
        //BASE
        if (ongoingSumAggregation == target) {//reached target
            res.add(new ArrayList<>(ongoingCombination));
            return;
        }

        for (int i = startNum; i < nums.length; i++) {

            int currentSumSoFar = ongoingSumAggregation + nums[i];

            if (currentSumSoFar > target) {//exceeding target - skip
                return;
            }

            ongoingCombination.add(nums[i]);//add to ongoing

            dfs(nums, i,  currentSumSoFar, target,ongoingCombination, res);//recur, mind i is NOT i+1 -> we can REUSE elements

            ongoingCombination.remove(ongoingCombination.size() - 1);//BACKTRACK

        }
    }


    //*************** PICK NOT PICK **********************
    private static List<List<Integer>> combinationSum(int[] nums, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ongoingCombination = new ArrayList<>();
        int startNum = 0;

        combinationSumPickNotPick(nums, startNum, targetSum, ongoingCombination, res);

        return res;

    }

    /*
    Time O(2  t/m)
    Space O(2  t/m)

    t = target m = min val in nums
     */
    private static void combinationSumPickNotPick(int[] nums, int startNum,  int targetSum, List<Integer> ongoingCombination, List<List<Integer>> res) {
        if(targetSum == 0) {
            res.add(new ArrayList<>(ongoingCombination));
            return;
        }

        if( targetSum < 0 || startNum >= nums.length) {
            return;
        }

        ongoingCombination.add(nums[startNum]);

        // pick the very start num
        combinationSumPickNotPick(nums, startNum, targetSum - nums[startNum], ongoingCombination, res);

        ongoingCombination.remove(ongoingCombination.size()-1);//BACKTRACK
        //ongoingCombination.removeLast(); alternative

        //don't pick start num
        combinationSumPickNotPick(nums, startNum + 1, targetSum, ongoingCombination, res);
    }
}
