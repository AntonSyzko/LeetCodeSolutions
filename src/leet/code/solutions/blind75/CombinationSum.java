package leet.code.solutions.blind75;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum/

Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.



Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []


Constraints:

1 <= candidates.length <= 30
2 <= candidates[i] <= 40
All elements of candidates are distinct.
1 <= target <= 40
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = combinationSum(candidates, target);
        System.out.println(res);
    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ongoingCombination = new ArrayList<>();
        int startNum = 0;

        combinationSumHelper(candidates, startNum,  target,ongoingCombination, res);

        //combinationSumPickNotPick(candidates, startNum, target, ongoingCombination, res);

        return res;
    }

    private static void combinationSumHelper(int[] candidates, int startNum, int target, List<Integer> ongoingCombination, List<List<Integer>> res) {
        if ( target == 0) {//BASE target reached

            res.add(new ArrayList<>(ongoingCombination));
            return;

        }else if(target < 0){//base case target below zero

            return;//no need to go further -anyways wrong way as we below 0

        }

        for (int currNum = startNum; currNum < candidates.length; currNum++) {

                ongoingCombination.add(candidates[currNum]);//add to ongoing combo

                combinationSumHelper(candidates, currNum ,target - candidates[currNum],ongoingCombination, res);

                ongoingCombination.remove(ongoingCombination.size() - 1);//backtrack remove last

        }
    }

    private static void combinationSumPickNotPick(int[] candidates, int startNum, int target, List<Integer> ongoingCombination, List<List<Integer>> res) {
        // Base case: target reached
        if (target == 0) {
            res.add(new ArrayList<>(ongoingCombination));
            return;
        }

        // Base case: target exceeded or no more candidates
        if( target < 0 || startNum >= candidates.length){
            return;
        }

        // Decision 1: Include current candidate
        ongoingCombination.add(candidates[startNum]);

        // We can reuse the current element, so we don't increment startIndex, but substract curr value from target
        combinationSumPickNotPick(candidates, startNum , target - candidates[startNum], ongoingCombination, res);

        // Backtrack by removing the last added element
        ongoingCombination.remove(ongoingCombination.size() - 1);//BACKTRACK

        // Decision 2: Skip current candidate, but keep target same
        combinationSumPickNotPick(candidates, startNum + 1, target, ongoingCombination, res);
    }
}
