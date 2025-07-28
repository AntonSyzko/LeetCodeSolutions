package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/frog-jump/

A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

Example 1:
Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.

Example 2:
Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.


Constraints:

2 <= stones.length <= 2000
0 <= stones[i] <= 231 - 1
stones[0] == 0
stones is sorted in a strictly increasing order.
 */
public class FrogJump {

    public static void main(String[] args) {
         int [] stones = {0,1,3,5,6,8,12,17};
         boolean canReachEnd = canCross(stones);
         System.out.println(canReachEnd);
    }


    /**

    Time Complexity: O(n²)

            We iterate through all n stones.
            For each stone, we consider at most n-1 possible jump distances (worst case).
            For each jump distance, we try 3 possible next jumps.
            This gives us O(n * n * 3) = O(n²).


   Space Complexity: O(n²)

        We create a HashMap with n entries (one for each stone).
        In the worst case, each stone can be reached by O(n) different jump distances.
        Therefore, the total space required is O(n * n) = O(n²).

     Time: O(n × k) where n = number of stones, k = max possible jump size
     Space: O(n × k) for memoization + O(n) for stone mapping

     **/
    private static boolean canCross(int[] stones) {
        Map<Integer, Integer> stoneToIndex = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            stoneToIndex.put(stones[i], i);
        }

        Map<String, Boolean> memo = new HashMap<>();
        return dfs(stones, stoneToIndex, memo, 0, 1); // Start at index 0 with jump size 1
    }

    private  static boolean dfs(int[] stones, Map<Integer, Integer> stoneToIndex, Map<String, Boolean> memo, int currentIndex, int lastJump) {

        // Base case: reached the last stone
        if (currentIndex == stones.length - 1) {
            return true;
        }

        // Memoization key
        String key = currentIndex + "," + lastJump;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Try all three possible jump sizes: k-1, k, k+1
        for (int jump = lastJump - 1; jump <= lastJump + 1; jump++) {
            if (jump <= 0){
                continue; // Can't jump backwards or stay
            }

            int nextPosition = stones[currentIndex] + jump;

            // Check if there's a stone at this position
            if (stoneToIndex.containsKey(nextPosition)) {
                int nextIndex = stoneToIndex.get(nextPosition);
                if (dfs(stones, stoneToIndex, memo, nextIndex, jump)) {
                    memo.put(key, true);
                    return true;
                }
            }
        }

        memo.put(key, false);//false if reached here
        return false;
    }
}
