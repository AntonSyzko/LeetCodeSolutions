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
        System.out.println(findSubsequences(nums));
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Stack<Integer> ongoingSequence = new Stack<>();
        recur(nums, result, ongoingSequence, 0);//using stack to keep last elements on top
        return result;
    }

    /*
    The time complexity of all above-discussed methods is exponential and requires additional space for call stack.
     */
    private static void recur(int[] nums, List<List<Integer>> result, Stack<Integer> ongoingSequence, int startIndex) {
        // if the current sequence has length of two or more, push it to the result
        if (ongoingSequence.size() >= 2) {
            result.add(new ArrayList<>(ongoingSequence));
        }

        // take a visitedSetForThisSequnece to keep track of the processed elements
        Set<Integer> visitedSetForThisSequnece = new HashSet<>();//at each recursive iteration will be reset to new empty set

        // startIndex from the next index till the last
        for (int currentIndex = startIndex; currentIndex < nums.length; currentIndex++) {

            int currentNumber = nums[currentIndex];
            // proceed only if the current element is not processed before
            // nad is BIGGER  than the previous element in the sequence
            if (!visitedSetForThisSequnece.contains(currentNumber)
                    &&
                    (ongoingSequence.isEmpty() || currentNumber > ongoingSequence.peek())) {//curr num is bigger than previously stored

                // mark current element as processed
                visitedSetForThisSequnece.add(currentNumber);

                // include current element to the ongoing  sequence
                ongoingSequence.add(currentNumber);

                // recur for the next index `currentIndex+1`
                recur(nums, result, ongoingSequence, currentIndex + 1);

                ongoingSequence.removeLast(); //BACKTRACK  exclude current element from the sequence
            }
        }
    }
}
