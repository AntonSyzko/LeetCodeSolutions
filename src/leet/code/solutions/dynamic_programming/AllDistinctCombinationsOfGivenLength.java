package leet.code.solutions.dynamic_programming;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
https://www.techiedelight.com/find-distinct-combinations-given-length-2/

Given an integer array, find all distinct combinations of a given length k.

For example,

Input:  {2, 3, 4}, k = 2
Output: {2, 3}, {2, 4}, {3, 4}

Input:  {1, 2, 1}, k = 2
Output: {1, 2}, {1, 1}, {2, 1}
The program should print all the distinct combinations, while preserving the relative order of elements as they appear in the array.

 */
public class AllDistinctCombinationsOfGivenLength {

    public static void main(String[] args) {
        int[] nums = {2, 3, 4};
        int combinationLimit = 2;

        Set<List<Integer>> res = findCombinations(nums, combinationLimit);

        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

        public static Set<List<Integer>> findCombinations(int[] nums, int k) {
            Set<List<Integer>> res = new HashSet<>();
            List<Integer> combo = new ArrayList<>();

            findCombinationsDFS(nums, 0, k, combo, res);

            return res;
        }

    private static void findCombinationsDFS(int[] nums, int start, int remainingLength, List<Integer> current, Set<List<Integer>> result) {
        // Base case: combination is complete
        if (remainingLength == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try each element from current position onwards
        for (int i = start; i < nums.length; i++) {
            // Pruning: check if we have enough elements left
            if (nums.length - i < remainingLength) {
                break; // No point continuing, not enough elements
            }

            // Include current element
            current.add(nums[i]);

            // Recurse with next position and one less element needed
            findCombinationsDFS(nums, i + 1, remainingLength - 1, current, result);

            // Backtrack: remove the element we just added
            current.remove(current.size() - 1);
        }
    }

    private static void findCombinationsPickNotPick(int[] nums, int start, int remainingLength,  List<Integer> combo, Set<List<Integer>> res) {
        // Base case: combination is complete
        if (remainingLength == 0) {
            res.add(new ArrayList<>(combo));
            return;
        }

        // Pruning: not enough elements left
        if (nums.length - start < remainingLength) {
            return;
        }

        // include the current element in the current combination res and recur
        combo.add(nums[start]);

        //include means - retrieve from distinctCombinations ( by extracting -1 = adding to result )
        findCombinationsPickNotPick(nums, start + 1, remainingLength - 1, combo,res);

        // exclude the lastly added element from the current combination
        combo.remove(combo.size() - 1); // BACKTRACK

        // exclude the current element from the current combination and recur
        //exclude means keep it in distinctCombinations not accounting for it in res
        findCombinationsPickNotPick(nums, start + 1, remainingLength ,combo,  res);
    }
}
