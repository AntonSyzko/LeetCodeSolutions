package leet.code.solutions.greedy;

/*
You are given an integer array nums where each element nums[i] indicates your maximum jump length at that position.

Return true if you can reach the last index starting from index 0, or false otherwise.

Example 1:

Input: nums = [1,2,0,1,0]

Output: true
Explanation: First jump from index 0 to 1, then from index 1 to 3, and lastly from index 3 to 4.

Example 2:

Input: nums = [1,2,1,0,1]

Output: false
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 1000


 */
public class JumpGame {

    public static void main(String[] args) {
          int[] jumps = new int[]{1,2,0,1,0};

          boolean canJump = canJump(jumps);

         System.out.println(canJump);

        int[] jumps2 = new int[]{1,2,1,0,1};

         canJump = canJump(jumps2);

        System.out.println(canJump);

    }

    private static boolean canJump(int[] nums) {
        int goal = nums.length - 1;//goal is last element ( or beyond )

        for (int currentPostition = nums.length - 2; currentPostition >= 0; currentPostition--) {//backwards starting from one before last

            int howFarCanWeJumpUpFromCurrentPosition = currentPostition + nums[currentPostition];

            if (howFarCanWeJumpUpFromCurrentPosition >= goal) {//>= goal means we can reach curren END position somehow, so all we care is we CAN, >= more or equal mens landing on last index or beyond
                goal = currentPostition;//reset current gaol
            }
            //if currentPostition + nums[currentPostition]  is LESS than a GOAL it means we cannot reach end anyways since we won't be able to reach temporary gaol ->
            // minding gola starts at the end means chaining updated goals would lead to end

        }

        return goal == 0;//if we ended at 0 -> on start of the nums - means we can reach from start to end
    }
}
