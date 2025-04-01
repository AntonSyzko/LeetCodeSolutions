package leet.code.solutions.blind75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://leetcode.com/problems/longest-consecutive-sequence/

Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.

You must write an algorithm that runs in O(n) time.

Example 1:
Input: nums = [100,4,200,1,3,2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

Example 2:

Input: nums = [0,3,7,2,5,8,4,6,0,1]
Output: 9
Example 3:

Input: nums = [1,0,1,2]
Output: 3

Constraints:

0 <= nums.length <= 105
-109 <= nums[i] <= 109
 */
public class LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums = {0,3,2,1};
        int longestConsecutiveSeq = longestConsecutiveSeq(nums);
        System.out.println(longestConsecutiveSeq);

    }

    private static int longestConsecutiveSeq(int[] nums) {

        int longestSubseq = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        for (int num : set) {

            //if curr num MINUS 1 is in SET -> means it cannot be a START of consecutive seq - thus we ignore it n other words if NUM MINUS 1 is there like 99 for 100 - . 100 itself c annot be a start of consec seq
            if(!set.contains(num-1)){

                int currentNum = num; //copy of num as we gonna increase it
                int ongoingConsecSeqLen = 1;

                while (set.contains(currentNum + 1)) {//utill smth +1 is in set

                    currentNum = currentNum + 1;//increase
                    ongoingConsecSeqLen = ongoingConsecSeqLen + 1;//reaise ongoing as we found smth +1

                }

                longestSubseq = Math.max(longestSubseq, ongoingConsecSeqLen);

            }
        }

        return longestSubseq;
    }

    // -------------- RECURSIVE --------------------

    private static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // Create a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Track visited numbers to avoid redundant work
        Set<Integer> visitedSequencesStarts = new HashSet<>();//marking visited starts of sequences
        int maxLength = 0;

        for (int num : numSet) {
            // Only start sequences at their beginning
            if (!numSet.contains(num - 1) // if -1 is in set - it cannot be a start of consec sequence
                    &&
                    !visitedSequencesStarts.contains(num)) {//was not yet treated as a  potential start of seq

                int currentFoundConsecSeqLen = findConsecutiveLength(num, numSet, visitedSequencesStarts);

                maxLength = Math.max(maxLength, currentFoundConsecSeqLen);
            }
        }

        return maxLength;
    }

    private  static int findConsecutiveLength(int num, Set<Integer> numSet, Set<Integer> visitedSequencesStarts) {
        // Base case: number is not in the set ( when we did +1 for each num - if it is not in set
        if (!numSet.contains(num)) {
            return 0;
        }

        // Mark current potential start of seq as visited
        visitedSequencesStarts.add(num);

        // Recursive case: add 1 for current number and check the next consecutive number
        return 1 + findConsecutiveLength(num + 1, numSet, visitedSequencesStarts);
    }

    /// ---------------- memo ---------------
    public int longestConsecutiveMemo(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        // Create a HashSet for O(1) lookups
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        // Memoization map to store computed results
        Map<Integer, Integer> memo = new HashMap<>();
        int maxLength = 0;

        for (int num : numSet) {
            // Only start sequences at their beginning
            if (!numSet.contains(num - 1)) {
                maxLength = Math.max(maxLength, getLengthOfSequenceMemo(num, numSet, memo));
            }
        }

        return maxLength;
    }

    private int getLengthOfSequenceMemo(int num, Set<Integer> numSet, Map<Integer, Integer> memo) {
        // Check if already computed
        if (memo.containsKey(num)) {
            return memo.get(num);
        }

        // Base case: number is not in the set
        if (!numSet.contains(num)) {
            return 0;
        }

        // Recursive case: current number plus length of sequence starting from next number
        int length = 1 + getLengthOfSequenceMemo(num + 1, numSet, memo);

        // Store result in memo
        memo.put(num, length);

        return length;
    }
}
