package leet.code.solutions.backtracking;

import java.util.*;

/*
https://neetcode.io/problems/combination-target-sum-ii

You are given an array of integers candidates, which may contain duplicates, and a target integer target.
 Your task is to return a list of all unique combinations of candidates where the chosen numbers sum to target.

Each element from candidates may be chosen at most once within a combination. The solution set must not contain duplicate combinations.

You may return the combinations in any order and the order of the numbers in each combination can be in any order.

Example 1:

Input: candidates = [9,2,2,4,6,1,5], target = 8

Output: [
  [1,2,5],
  [2,2,4],
  [2,6]
]
Example 2:

Input: candidates = [1,2,3,4,5], target = 7

Output: [
  [1,2,4],
  [2,5],
  [3,4]
]
Constraints:

1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30

 */
public class CombinationSumWithoutDuplicates {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int target = 7;

        List<List<Integer>> combinations = combinationSumWithoutDuplicates(nums, target);
        System.out.println(combinations);
    }

    private static List<List<Integer>> combinationSumWithoutDuplicates(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);

        int startNum = 0;
        int ongoingSum = 0;

        DFS(startNum, new ArrayList<>(), ongoingSum, candidates, target, res);

        return res;
    }

    private static void DFS(int startNum, List<Integer> ongoingCombo, int ongoingSum, int[] candidates, int target, List<List<Integer>> res) {
        if (ongoingSum == target) {// base
            res.add(new ArrayList<>(ongoingCombo));
            return;
        }

        for (int currNum = startNum; currNum < candidates.length; currNum++) {

            if (currNum > startNum //if curr num after start ( we recur so we move ) - this is for (curr -1) not to fall out of boundaries
                    &&
                    candidates[currNum] == candidates[currNum - 1]) {//skip duplicates

                continue;

            }

            if (ongoingSum + candidates[currNum] > target) {//exceeded target - break for loop
                break;//sum is exceeding so no need to accumulate more
            }

            ongoingCombo.add(candidates[currNum]);

            //DFS with next start and accumulated sum (ongoingSum + candidates[currNum])
            DFS(startNum + 1, ongoingCombo, ongoingSum + candidates[currNum], candidates, target, res);

            ongoingCombo.remove(ongoingCombo.size() - 1);//BACKTRACK
           // path.removeLast();//alt

        }
    }






    //----- not optimal


    private static List<List<Integer>> combinationSum22(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);//SORT !!!!!!!!!!!!!!!!

        dfs(candidates, target, 0, new ArrayList<>(), 0, res);

        return res;
    }

    private  static void dfs(int[] candidates, int target, int startNum, List<Integer> cur, int total,   List<List<Integer>> res ) {
        if (total == target) {//BASE
            res.add(new ArrayList<>(cur));
            return;
        }

        if (total > target || startNum == candidates.length) {//exceeded target or exhaused all array
            return;
        }

        cur.add(candidates[startNum]);

        // PICK
        dfs(candidates, target, startNum + 1, cur, total + candidates[startNum], res);//recur

        cur.remove(cur.size() - 1);//BACKTRACK

        while (startNum + 1 < candidates.length //withing boundary
                &&
                candidates[startNum] == candidates[startNum + 1]) {//SKip duplicates

            startNum++;

        }

        //Not PICK
        dfs(candidates, target, startNum + 1, cur, total, res);

    }
}
