package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/jump-game/

You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.

Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 105
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] jumps = new int[]{2, 3, 1, 1, 4};
        boolean isPossibleToReachEnd  = canJump(jumps);
        System.out.println(isPossibleToReachEnd);
    }


    private static boolean canJump(int[] jumps) {
      int finalTargetSoFar = jumps.length-1;//target is last index of array to start with by default

        //if we can reach final target ( whatever it will be at aby given time) - means we can reach that point

        for (int jumpIndex = jumps.length-1; jumpIndex >= 0; jumpIndex--) {//traverse backwards from obe before last 9 as we have marked last already above)  !!!

            //if jump from current index PLUS the jump LENGTH is MORE or EQUAL than current finall
            if(jumpIndex + jumps[jumpIndex] >= finalTargetSoFar){
                finalTargetSoFar = jumpIndex;//current index is new final target
            }
        }

        return finalTargetSoFar == 0;//if at the end final target became ZERO - means we can potentially reach end
    }

    private static boolean canJump2(int[] nums) {
        int maxJump = 0;

        for (int i = 0; i < nums.length; i++) {
            //so current index is MORE than the MAX we jump from any previous ( which was max over i + nums[i] )
            // , so it is impossible to reach it , since it's bigger than it's index - we could not ever reach it at all
            if (i > maxJump) {
                return false;
            }

            maxJump = Math.max(maxJump, i + nums[i]);
        }
        return true;
    }
}
