package leet.code.solutions.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Problem:
Given a set of candidate numbers (C) and a target number (T), find all UNIQUE combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers. Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations. For example, given candidate set 2,3,6,7 and target 7, A solution set is: [7] [2, 2, 3]
Thoughts:
Idea is to use a modified DFS.
 */
public class CombinationSum {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4};
        List<List<Integer>> res =  combinationSum(nums,3);
        System.out.println(res);
    }

    private static  List<List<Integer>> combinationSum(int[] candidates, int targetSum) {

        if(candidates == null || candidates.length == 0){
            return Collections.emptyList();
        }

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();

        Arrays.sort(candidates);//SOORT !!! for not to proceed with higher values than a target

        int index = 0;

        combinationSum(candidates, targetSum, index, combo, res);

        return res;
    }

    private static void combinationSum(int[] candidates, int targetSum, int index, List<Integer> combo, List<List<Integer>> res) {
        if(targetSum == 0) {//BASE
            res.add(new ArrayList<>(combo));
            return;
        }

        for (int i = index; i < candidates.length ; i++) {

            if(candidates[i] > targetSum){//target itself is LESS than this candidate
                return;//since sorted no sense to proceed with higher values
            }

            combo.add(candidates[i]);

            combinationSum(candidates, targetSum - candidates[i], i, combo, res);

            combo.remove(combo.size()-1);//backtrack
        }
    }
}
