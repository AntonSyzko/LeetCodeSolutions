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

        for (int i = nums.length - 2; i >= 0; i--) {//backwards starting from one before last

            int howFarCanWeJumpUpFromCurrentPosition = i + nums[i];

            if (howFarCanWeJumpUpFromCurrentPosition >= goal) {//>= goal means we can reach curren END position somehow, so all we care is we CAN, >= more or equal mens landing on last index or beyond
                goal = i;//reset current gaol
            }
            //if i + nums[i]  is LESS than a GOAL it means we cannot reach end anyways since we won't be able to reach temporary goal ->
            // minding goal starts at the end means chaining updated goals would lead to end

        }

        return goal == 0;//if we ended at 0 -> on start of the nums - means we can reach from start to end
    }

    /*
    time: O(n^2)
    space:  O(n)
     */
    private static boolean canJumpBottomUp_DP(int[] nums) {

        int lastIndex = nums.length - 1;

        boolean [] reacheable = new boolean[nums.length];
        reacheable[lastIndex] = true;

        for (int i = nums.length -2; i >=0 ; i--) {

            // We only need to check if we can reach any position that's already reachable
            int furthestJump = Math.min(i + nums[i], lastIndex);

            for (int jump =  i + 1; jump <= furthestJump; jump++) {

                if(reacheable[jump]){
                    reacheable[i] = true;
                    break;
                }
            }

        }

        return reacheable[0];
    }
}
