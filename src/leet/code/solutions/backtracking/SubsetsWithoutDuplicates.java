package leet.code.solutions.backtracking;

import java.util.*;

/*
https://neetcode.io/problems/subsets-ii

You are given an array nums of integers, which may contain duplicates.
 Return all possible subsets.

The solution must not contain duplicate subsets. You may return the solution in any order.

Example 1:

Input: nums = [1,2,1]

Output: [[],[1],[1,2],[1,1],[1,2,1],[2]]
Example 2:

Input: nums = [7,7]

Output: [[],[7], [7,7]]
Constraints:

1 <= nums.length <= 11
-20 <= nums[i] <= 20
 */
public class SubsetsWithoutDuplicates {

    public static void main(String[] args) {
        int[] nums = {1, 2, 1};
        List<List<Integer>> subsets = subsetsWithDup(nums);
        System.out.println(subsets);
    }


    private static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> ongoingSubset = new ArrayList<>();

        Arrays.sort(nums);//SOOORT fro skip duplicates

       subsetsWithDupHelper(nums, 0, res, ongoingSubset);
        //backtrackPickNotPick(nums, 0, res, ongoingSubset);

        return res;
    }

    // time O( n * 2^N)
    // space O(n)
    private  static void subsetsWithDupHelper(int[] nums, int startNumber,  List<List<Integer>> res, List<Integer> ongoingSubset) {

        res.add(new ArrayList<>(ongoingSubset));
        //no return !!!

        for (int currentNum = startNumber; currentNum < nums.length; currentNum++) {

                if(currentNum > startNumber && nums[currentNum] == nums[currentNum -1]){//if after sorting previous element == current
                    continue; // SKIP !!!
                }

                ongoingSubset.add(nums[currentNum]);//add to ongoing subset as we go

                subsetsWithDupHelper(nums, currentNum + 1,   res, ongoingSubset);//recur with curr elemnent raised to it's next

                 ongoingSubset.remove(ongoingSubset.size() - 1); //BACKTRACK remove last from ongoing subset
               // ongoingSubset.removeLast();
            }
        }


        // time O( n * 2^N)
    // space O(n)
    private static void backtrackPickNotPick(int[] nums,int startNumber, List<List<Integer>> res, List<Integer> ongoingSunset)  {
        if (startNumber == nums.length) {//we reached current array limit
            res.add(new ArrayList<>(ongoingSunset));
            return;
        }

        // MIND this solution unlike above one DOES not have FOR loop
        //Include current
        ongoingSunset.add(nums[startNumber]);

        backtrackPickNotPick(nums,startNumber + 1, res,ongoingSunset);

       // ongoingSunset.remove(ongoingSunset.size() - 1);
        ongoingSunset.removeLast();//BACKTRACK

        while (startNumber + 1 < nums.length //within boundaries
                && nums[startNumber] == nums[startNumber + 1]) {// two next to each other are same ==
            startNumber++; // skip
        }

        //Exclude current
        backtrackPickNotPick(nums,startNumber + 1, res,  ongoingSunset);
    }


}
