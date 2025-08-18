package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Given a collection of numbers that might contain duplicates, return all possible unique
permutations.
For example, [1,1,2] have the following unique permutations:
[1,1,2], [1,2,1], and [2,1,1].
 */
public class UniqueNumberPermutaions {

    public static void main(String[] args) {
        int[] nums = { 1,1,2};

        List<List<Integer>> res = permuteUnique(nums);
        System.out.println(res);

    }

    private static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();

        boolean[] used = new boolean[nums.length];

        Arrays.sort(nums);//SOOOORT !!!

        dfs(nums, combo, used, res);

        return res;
    }

      /*
    Time: O(n! × n) - eliminates the O(n) duplicate check
    Space: O(n! × n) - same space for results, but no duplicate storage
     */

    private static void dfs(int[] nums,  List<Integer> combo, boolean[] used, List<List<Integer>> res) {
        if(combo.size() == nums.length){//BASE
            res.add(new ArrayList<>(combo));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if(used[i]) {//prune
                continue;
            }


            // Skip duplicates: if current element equals previous element
            // and previous element hasn't been used in current recursion level
            if(i > 0 && nums[i] == nums[ i -1] && !used[i -1]){
                continue;
            }

            combo.add(nums[i]);
            used[i] = true;

            dfs(nums, combo, used, res);

            combo.remove(combo.size()-1);//BACKTRACK !!!
            used[i] = false;
        }
    }
}