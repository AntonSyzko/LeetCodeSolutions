package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/combination-sum/
Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen numbers sum to target.
 You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.
It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

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
1 <= candidates[i] <= 200
All elements of candidates are distinct.
1 <= target <= 500
 */
public class CombinationSum {
    public static void main(String[] args) {
    int[] candidates = {2,3,6,7};
    int target = 7;
        List<List<Integer>> combinationSum = combinationSum(candidates, target);
        System.out.println(combinationSum);

    }

    private static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if (candidates == null || candidates.length == 0) {
            return result;
        }

        ArrayList<Integer> current = new ArrayList<Integer>();
        Arrays.sort(candidates);

        combinationSumHelper(candidates, target, 0, current, result);

        return result;
    }

    private static void combinationSumHelper(int[] candidates, int target, int startingCandidate, ArrayList<Integer> curr, List<List<Integer>> result) {
        if (target == 0) {
            ArrayList<Integer> temp = new ArrayList<Integer>(curr);
            result.add(temp);
            return;
        }

        for (int i = startingCandidate; i < candidates.length; i++) {
            //candidates are sorted so target does not match even the lowest candidate
            if (target < candidates[i]){
                return;
            }

            curr.add(candidates[i]);

            combinationSumHelper(candidates, target - candidates[i], i, curr, result);

            curr.remove(curr.size() - 1);//remove last

        }//for i
    }//combinationSum
}
