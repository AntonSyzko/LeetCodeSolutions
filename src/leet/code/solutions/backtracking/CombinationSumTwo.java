package leet.code.solutions.backtracking;

import java.util.*;

/*

https://neetcode.io/problems/combination-target-sum-ii


You are given an array of integers candidates, which may contain duplicates, and a target integer target. Your task is to return a list of all unique combinations of candidates where the chosen numbers sum to target.

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
public class CombinationSumTwo {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        int target = 7;

        List<List<Integer>> res = combinationSum2(nums, target);

        System.out.println(res);
    }

    private static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ongoingCombo = new ArrayList<>();
        int start = 0;

        Arrays.sort(candidates);  // Sort to handle duplicates efficiently

        dfs(candidates, start, target, ongoingCombo, res);

        return res;
    }

    /*
    Time and Space Complexity Analysis
            Time Complexity

            Sorting the input array: O(n log n) where n is the length of candidates.
            DFS traversal: In the worst case, we explore all possible combinations.

            For each element, we have two choices: include it or don't include it.
            This leads to O(2^n) possible combinations.
            However, due to our pruning strategies, the actual number of combinations explored is much less.



            Overall time complexity: O(2^n) in the worst case, but significantly better in practice due to pruning.
            Space Complexity

            Recursion stack: O(target) in the worst case, as the maximum depth of recursion is bounded by the target (assuming minimum candidate value is 1).
            Result storage: O(k) where k is the number of valid combinations found.
            Temporary storage (ongoingCombo): O(target) in the worst case.

            Overall space complexity: O(target + k)
     */
    private static void dfs(int[] candidates, int start, int target, List<Integer> ongoingCombo, List<List<Integer>> res) {
        // If target is reached, add the current combination to results
        if (target == 0) {
            res.add(new ArrayList<>(ongoingCombo));
            return;
        }

        // If target becomes negative, backtrack
        if (target < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            int currentCandidate = candidates[i];

            // Skip duplicates - this prevents duplicate combinations
            if (i > start && currentCandidate == candidates[i - 1]) {
                continue;
            }

            // Early pruning - if adding current number exceeds target, no point in continuing
            if (currentCandidate> target) {
                break;  // Since array is sorted, all subsequent numbers will be larger
            }

            // Add current element to combination
            ongoingCombo.add(currentCandidate);

            // Recurse with updated target and start index
            dfs(candidates, i + 1, target - currentCandidate, ongoingCombo, res);

            // Backtrack - remove the current element
            ongoingCombo.remove(ongoingCombo.size() - 1);//BACKTRACK - removeLast()
        }
    }
}
