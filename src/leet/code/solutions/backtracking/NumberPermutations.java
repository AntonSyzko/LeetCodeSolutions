package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://neetcode.io/problems/permutations

Given an array nums of unique integers, return all the possible permutations. You may return the answer in any order.

Example 1:

Input: nums = [1,2,3]

Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:

Input: nums = [7]

Output: [[7]]
Constraints:

1 <= nums.length <= 6
-10 <= nums[i] <= 10

 */
public class NumberPermutations {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> result = permute(nums);
        System.out.println(result);
    }


    private  static List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> result = new ArrayList<>();

       boolean[] visited = new boolean[nums.length];

        doPermutationBacktracking(nums, result, new ArrayList<>(),  visited);

       return result;
    }

    /*
    Time O( n! * n)
    Space O(n1 * n) for output list
    n1 -s is factorial complexity
     */
    private static void doPermutationBacktracking(int[] nums, List<List<Integer>> result, ArrayList<Integer> ongoingPermutation, boolean[] visited) {
        if(ongoingPermutation.size() == nums.length) {//each permutation is anyway size of all numbers and not more
            result.add(new ArrayList<>(ongoingPermutation));
            return;//from recursion stack
        }

        for (int number = 0; number < nums.length; number++) {

            if(!visited[number]) {

                ongoingPermutation.add(nums[number]);//add to ongoing result

                visited[number] = true;//mark as visited

                doPermutationBacktracking(nums, result, ongoingPermutation, visited); //recur for the rest of numbers which are not visited

                ongoingPermutation.remove(ongoingPermutation.size()-1);//BACKTRACK remove last
               // ongoingPermutation.removeLast();//alternative
                visited[number] = false; // BACKTRACK
            }
        }
    }





    public List<List<Integer>> permuteRecursive(int[] nums) {
        if (nums.length == 0) {
            return Arrays.asList(new ArrayList<>());
        }

        List<List<Integer>> perms = permute(Arrays.copyOfRange(nums, 1, nums.length));
        List<List<Integer>> res = new ArrayList<>();

        for (List<Integer> p : perms) {
            for (int i = 0; i <= p.size(); i++) {
                List<Integer> p_copy = new ArrayList<>(p);
                p_copy.add(i, nums[0]);
                res.add(p_copy);
            }
        }
        return res;
    }


    public List<List<Integer>> permuteIteratively(int[] nums) {
        List<List<Integer>> perms = new ArrayList<>();
        perms.add(new ArrayList<>());

        for (int num : nums) {
            List<List<Integer>> new_perms = new ArrayList<>();
            for (List<Integer> p : perms) {
                for (int i = 0; i <= p.size(); i++) {
                    List<Integer> p_copy = new ArrayList<>(p);
                    p_copy.add(i, num);
                    new_perms.add(p_copy);
                }
            }
            perms = new_perms;
        }
        return perms;
    }

}
