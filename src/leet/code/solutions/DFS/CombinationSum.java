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

    private static  List<List<Integer>> combinationSum(int[] candidates, int target) {

        if(candidates == null || candidates.length == 0){
            return Collections.emptyList();
        }

        List<List<Integer>> resultedCombinations = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();

        Arrays.sort(candidates);//SOORT !!! for not to proceed with higher values than a target

        combinationSum(candidates, target, 0, currentCombination, resultedCombinations);

        return resultedCombinations;
    }

    private static void combinationSum(int[] candidates, int target, int index, List<Integer> currentCombination, List<List<Integer>> resultedCombinations) {
        if(target == 0) {//BASE
            resultedCombinations.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = index; i < candidates.length ; i++) {

            if(target < candidates[i]){//target itself is LESS than this candidate
                return;//since sorted no sense to proceed with higher values
            }

            currentCombination.add(candidates[i]);

            combinationSum(candidates, target - candidates[i], i, currentCombination, resultedCombinations);

            currentCombination.remove(currentCombination.size()-1);//backtrack
        }
    }
}
