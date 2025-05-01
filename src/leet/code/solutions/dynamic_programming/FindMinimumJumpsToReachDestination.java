package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
https://www.techiedelight.com/find-minimum-jumps-required-reach-destination/

Given an array of non-negative integers, where each array element represents the maximum number of positions one can move forward from that element.
Find the minimum number of jumps required to reach a given destination from a given source within the array.

If any element has value zero in the array, the destination cannot be reached through that element.
 If the source itself has value zero, return infinity as the destination cannot be reached at all. To make the problem simpler, let’s assume the source and destination to be the start and end of the array.

 For example,

Input:  nums[] = { 4, 2, 0, 3, 2, 0, 1, 8 }
Output: Minimum jumps required to reach the destination are 3.

3 jumps: (4 —> 3 —> 1 —> 8) or (4 —> 2 —> 1 —> 8)
4 jumps: (4 —> 2 —> 3 —> 1 —> 8) or (4 —> 3 —> 2 —> 1 —> 8)
5 jumps: (4 —> 2 —> 3 —> 2 —> 1 —> 8)

Input:  nums[] = { 4, 2, 2, 1, 0, 8, 1 }
 Output: Minimum jumps required to reach the destination are infinity.
 This is because no matter what path we choose, we will always end up in a dead cell.
 4 —> 2 —> 2 —> 1 —> 04 —> 2 —> 1 —> 04 —> 1 —> 04 —> 0

 */
public class FindMinimumJumpsToReachDestination {

    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {4, 2, 0, 3, 2, 0, 1, 8};
        int result1 = minJumps(nums1);
        System.out.println("Minimum jumps required: " + (result1 == Integer.MAX_VALUE ? "Infinity" : result1));

        // Test case 2
        int[] nums2 = {4, 2, 2, 1, 0, 8, 1};
        int result2 = minJumps(nums2);
        System.out.println("Minimum jumps required: " + (result2 == Integer.MAX_VALUE ? "Infinity" : result2));
    }

    /*
    Complexity Analysis

                Time Complexity: O(n²) in the worst case

                We have a nested loop where for each position, we might update all future positions.
                The outer loop runs n times.
                The inner loop could run up to n times in the worst case.

                Space Complexity: O(n)

                We use an additional dp array of size n.
                     */

    // Bottom-up dynamic programming approach
    public static int minJumps(int[] nums) {
        int n = nums.length;

        // If array is empty or has only one element
        if (n <= 1) {
            return 0;
        }

        // If starting position is 0, we can't move
        if (nums[0] == 0) {
            return Integer.MAX_VALUE;
        }

        // DP[i] will store the minimum number of jumps needed to reach index i
        int[] DP = new int[n];

        // Initialize DP array with infinity
        Arrays.fill(DP, Integer.MAX_VALUE);

        // Base case: no jumps needed to reach the starting position
        DP[0] = 0;

        // Fill the DP array
        for (int i = 0; i < n - 1; i++) {
            // If current position is unreachable, skip
            if (DP[i] == Integer.MAX_VALUE) {
                continue;
            }

            // Try all possible jumps from current position
            int maxJump = Math.min(i + nums[i], n - 1);//choose min of possible jump from i or the END - to prevent Out of bounds

            for (int j = i + 1; j <= maxJump; j++) {
                // Update DP[j] if we found a shorter path

                /*
                !!! Recurrence Relation
                so we compare if getting to [j] is shorter by what's already been calculated for [j] or +1 step from current steps to reach [i] ( stored already in DP[i]),
                since we know we can get to i (by DP[i] steps), and getting to J is possible, then it is just ONE more step from whats been calcluated from [i] before
                 */

                DP[j] = Math.min(DP[j], DP[i] + 1);
            }
        }

        // If DP[n-1] is still infinity, destination is unreachable
        return DP[n-1] == Integer.MAX_VALUE ? Integer.MAX_VALUE : DP[n-1];
    }

    /**
    Step-by-Step Explanation of the Dynamic Programming Approach
1. Problem Understanding
    The problem asks us to find the minimum number of jumps needed to reach the end of an array, where each element represents the maximum distance we can jump from that position. If we encounter a zero, we cannot move forward from that position.
2. Defining the DP State
    For this problem, we'll define:

    dp[i] = the minimum number of jumps needed to reach index i from the start.

3. Base Case

    dp[0] = 0 (0 jumps needed to reach the starting position)
    For all other positions, initialize dp[i] = Integer.MAX_VALUE (infinity, indicating they're unreachable initially)

            4. Recurrence Relation
    For each position i we can reach, we update all the positions j that are reachable from i:
    dp[j] = min(dp[j], dp[i] + 1)
    Where j ranges from i+1 to i+nums[i] (all possible jump destinations).
            5. Algorithm Breakdown

    Edge Cases:

    If the array has 0 or 1 elements, return 0 (already at destination).
    If the first element is 0, return infinity (can't move).


            DP Initialization:

            Create a DP array of size n.
            Set dp[0] = 0 (starting point).
    Set all other values to infinity.


    Fill the DP Array:

    For each position i:

    If dp[i] is infinity, skip (unreachable position).
    Otherwise, try all possible jumps from i.
    For each reachable position j, update dp[j] if we found a shorter path.




            Result:

    Return dp[n-1] (minimum jumps to reach the end).
    If dp[n-1] is still infinity, return infinity (unreachable).



            6. Tracing Through Example
    Let's trace through the example: nums[] = { 4, 2, 0, 3, 2, 0, 1, 8 }

    Initialize: dp = [0, ∞, ∞, ∞, ∞, ∞, ∞, ∞]
    i = 0, nums[0] = 4:

    We can jump to indices 1, 2, 3, 4 from here.
    Update: dp = [0, 1, 1, 1, 1, ∞, ∞, ∞]


    i = 1, nums[1] = 2:

    We can jump to indices 2, 3 from here.
    Update: dp = [0, 1, 1, 1, 1, ∞, ∞, ∞] (no change as jumping from 1 doesn't improve)


    i = 2, nums[2] = 0:

    Can't jump from here (0 jump distance).
    No updates.


    i = 3, nums[3] = 3:

    We can jump to indices 4, 5, 6 from here.
    Update: dp = [0, 1, 1, 1, 1, 2, 2, ∞]


    i = 4, nums[4] = 2:

    We can jump to indices 5, 6 from here.
    Update: dp = [0, 1, 1, 1, 1, 2, 2, ∞] (no improvement)


    i = 5, nums[5] = 0:

    Can't jump from here.
    No updates.


    i = 6, nums[6] = 1:

    We can jump to index 7 from here.
    Update: dp = [0, 1, 1, 1, 1, 2, 2, 3]


    Final dp array: [0, 1, 1, 1, 1, 2, 2, 3]
    Answer: 3 jumps

    Complexity Analysis

    Time Complexity: O(n²) in the worst case

    We have a nested loop where for each position, we might update all future positions.
    The outer loop runs n times.
    The inner loop could run up to n times in the worst case.


    Space Complexity: O(n)

    We use an additional dp array of size n.



            Optimization
    We could optimize this to O(n) time using a greedy approach for this specific problem, but the dynamic programming solution is more intuitive for understanding DP concepts.
    Key Takeaways for Dynamic Programming

    Identify the state: Define what dp[i] represents clearly.
    Base cases: Initialize your DP array with base cases.
    Transition function: Determine how to calculate dp[i] from previous states.
    Order of computation: Ensure you compute states in the right order (bottom-up or top-down).
    Final answer: Interpret the final state correctly.

    This problem demonstrates a classic DP pattern where we build solutions to larger problems from solutions to smaller problems by memoizing intermediate results.
     **/
}
