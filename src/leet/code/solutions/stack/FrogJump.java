package leet.code.solutions.stack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

     **/
    private static boolean canCross(int[] stones) {
        // Create a hashmap to store each stone's position and possible jump distances
        Map<Integer, Set<Integer>> DP = new HashMap<>();

        // Initialize the hashmap with empty sets for each stone
        for (int stone : stones) {
            DP.put(stone, new HashSet<>());
        }

        // The first stone can be reached with 0 jump (starting position)
        DP.get(0).add(0);

        // For each stone position
        for (int currentStone : stones) {

            // Create a copy of the set to avoid ConcurrentModificationException
            HashSet<Integer> possibleJumps = new HashSet<>(DP.get(currentStone));

            // Check all possible jumps that can reach this stone
            for (int jumpDistance : possibleJumps) {

                // Try the three possible next jumps: k-1, k, k+1
                for (int nextJump = jumpDistance - 1; nextJump <= jumpDistance + 1; nextJump++) {

                    // Skip if nextJump <= 0 (can't jump backwards or stay in place)
                    if (nextJump <= 0) continue;

                    // Calculate the position after the jump
                    int nextPosition = currentStone + nextJump;

                    // If this position has a stone, add this jump to the possibilities
                    if (DP.containsKey(nextPosition)) {
                        DP.get(nextPosition).add(nextJump);
                    }
                }
            }
        }

        // If the last stone has any possible jumps that can reach it, return true
        return !DP.get(stones[stones.length - 1]).isEmpty();
    }
}
