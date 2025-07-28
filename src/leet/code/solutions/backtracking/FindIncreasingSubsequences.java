package leet.code.solutions.backtracking;

import java.util.*;

/*
https://www.techiedelight.com/find-all-increasing-subsequences-array/

Find all increasing subsequences of an array
Given an integer array, find all distinct increasing subsequences of length two or more.

For example,

Input: [2, 4, 5, 4]
Output: [[2, 4, 5], [2, 5], [2, 4], [4, 5]]

 Input: [3, 2, 1]
 Output: []
 */
public class FindIncreasingSubsequences {

    public static void main(String[] args) {
        int[] nums = {2, 4, 5, 4};
        System.out.println(findSubsequences2(nums));
    }

    public static List<List<Integer>> findSubsequences2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();

        findIncreasing(nums, 0,combo, res);

        return res;
    }

    private static void findIncreasing(int[] nums, int index, List<Integer> currCombo, List<List<Integer>> res){
        if(currCombo.size() >= 2){//sort of BASE to have result
            res.add(new ArrayList<>(currCombo));
            // return; // mind NO return here - otherwise only length 2 will be in res
        }

        for(int i = index; i < nums.length; i++){

            if(currCombo.isEmpty() || nums[i] > currCombo.getLast()){//if combo is EMPTY OR curr element is BIGGER that last in combo

                currCombo.addLast(nums[i]);

                findIncreasing(nums, i + 1, currCombo, res);//recur

                currCombo.removeLast();//BACKTRACK
            }
        }
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Stack<Integer> currCombo = new Stack<>();

        findSubsequencesWithoutDuplicates(nums, result, currCombo, 0);//using stack to keep last elements on top

        return result;
    }

    /*
    The time complexity of all above-discussed methods is exponential and requires additional space for call stack.
     */
    private static void findSubsequencesWithoutDuplicates(int[] nums, List<List<Integer>> result, Stack<Integer> currCombo, int startIndex) {
        // if the current sequence has length of two or more, push it to the result
        if (currCombo.size() >= 2) {
            result.add(new ArrayList<>(currCombo));
            //mind NOOOO return for not to have combos of size 2 only
        }

        // take a seen to keep track of the processed elements
        Set<Integer> seen = new HashSet<>();//at each recursive iteration will be reset to new empty set

        // startIndex from the next index till the last
        for (int index = startIndex; index < nums.length; index++) {

            int currentNumber = nums[index];
            // proceed only if the current element is not processed before
            // and is BIGGER  than the previous element in the sequence 9 and in case sequence is empty also just process)
            if (!seen.contains(currentNumber)
                    &&
                    (currCombo.isEmpty() || currentNumber > currCombo.peek())) {//curr num is bigger than previously stored

                // mark current element as processed
                seen.add(currentNumber);

                // include current element to the ongoing  sequence
                currCombo.add(currentNumber);

                // recur for the next index `index+1`
                findSubsequencesWithoutDuplicates(nums, result, currCombo, index + 1);

                currCombo.removeLast(); //BACKTRACK  exclude current element from the sequence
            }
        }
    }
}
