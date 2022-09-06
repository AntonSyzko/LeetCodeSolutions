package leet.code.solutions.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Problem:
Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.
Note:
All numbers (including target) will be positive integers. Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
The solution set must not contain duplicate combinations. For example, given candidate set 2,3,6,7 and target 7, A solution set is: [7] [2, 2, 3]
Thoughts:
Idea is to use a modified DFS.
 */
public class CombinationSum {

    public static void main(String[] args) {//todo 

    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> resultedCombinations = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0){
            return resultedCombinations;
        }
        
        List<Integer> currentCombination = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, 0, currentCombination, resultedCombinations);
        return resultedCombinations;
    }

    private static void combinationSum(int[] candidates, int target, int j, List<Integer> currentCombination, List<List<Integer>> resultedCombinations) {
        if(target == 0) {
            List<Integer> temp = new ArrayList<>(currentCombination);
            resultedCombinations.add(currentCombination);
            return;
        }

        for (int i = j; i < candidates.length ; i++) {
            if(target < candidates[i]){
                return;
            }

            currentCombination.add(candidates[i]);
            combinationSum(candidates, target - candidates[i], i, currentCombination, resultedCombinations);
            currentCombination.remove(currentCombination.size()-1);
        }
    }
}
