package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://neetcode.io/problems/subsets

Given an array nums of unique integers, return all possible subsets of nums.

The solution set must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
Example 2:

Input: nums = [7]

Output: [[],[7]]
Constraints:

1 <= nums.length <= 10
-10 <= nums[i] <= 10

 */
public class Subsets {

    public static void main(String[] args) {
      int[] nums = {1,2,3};
      List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
        //[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    }

    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ongoingSubset = new ArrayList<>();//list cause can contain diplicates

      //  subsetHelper(nums, 0 , ongoingSubset, res);

        subsetPickNotPick(nums,0, ongoingSubset, res);

        return res;
    }

    private static void subsetHelper(int[] nums, int start, List<Integer> ongoingSubset, List<List<Integer>> res) {
         res.add(new ArrayList<>(ongoingSubset));

         for (int i = start; i < nums.length; i++) {
             ongoingSubset.add(nums[i]);

             subsetHelper(nums, i + 1, ongoingSubset, res);

             ongoingSubset.remove(ongoingSubset.size() - 1);// BACKTRACK
             //ongoingSubset.removeLast();//alternative
         }
    }

    private static void subsetPickNotPick(int[] nums, int start, List<Integer> ongoingSubset, List<List<Integer>> res) {
        if(start==nums.length){
            res.add(new ArrayList<>(ongoingSubset));
            return;
        }

        // MIND this solution unlike above one DOES not have FOR loop

           // PICK !!! -> include element
           //add to current res
           ongoingSubset.add(nums[start]);

            subsetHelper(nums, start + 1, ongoingSubset, res);//recur

            ongoingSubset.remove(ongoingSubset.size() - 1);//BACKTRACK - remove last

            subsetHelper(nums, start + 1, ongoingSubset, res);// recur EXCLUDING ( as we removed above )
    }

    private static List<List<Integer>> subsetsBackwards(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();
        int start = nums.length - 1;

        dfsBackwards(nums, start, combo, res);
        return res;
    }

    private static void dfsBackwards(int[] nums, int start, List<Integer> combo, List<List<Integer>> res) {

        res.add(new ArrayList<>(combo));

        for(int i = start; i >= 0; i--) {
            combo.add(nums[i]);
            dfsBackwards(nums, i -1, combo, res);
            combo.remove(combo.size() - 1);
        }

    }

}
