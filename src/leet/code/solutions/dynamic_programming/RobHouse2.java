package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
ou are given an integer array nums where nums[i] represents the amount of money the ith house has. The houses are arranged in a circle, i.e. the first house and the last house are neighbors.

You are planning to rob money from the houses, but you cannot rob two adjacent houses because the security system will automatically alert the police if two adjacent houses were both broken into.

Return the maximum amount of money you can rob without alerting the police.

Example 1:

Input: nums = [3,4,3]

Output: 4
Explanation: You cannot rob nums[0] + nums[2] = 6 because nums[0] and nums[2] are adjacent houses. The maximum you can rob is nums[1] = 4.

Example 2:

Input: nums = [2,9,8,3,6]

Output: 15
Explanation: You cannot rob nums[0] + nums[2] + nums[4] = 16 because nums[0] and nums[4] are adjacent houses. The maximum you can rob is nums[1] + nums[4] = 15.

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 100

 */
public class RobHouse2 {

    public static void main(String[] args) {
            int [] houses = {2,9,8,3,6};
            int maxGain = rob(houses);
            System.out.println(maxGain);

        int [] houses2 = {1,2,3,1};
        int maxGain2 = rob(houses2);
        System.out.println(maxGain2);
    }

    private static int rob(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];
        if(nums.length==2) return Math.max(nums[0],nums[1]);

        return Math.max(
                robHelper(Arrays.copyOfRange(nums, 0, nums.length -1)),//from first to second to last
                robHelper(Arrays.copyOfRange(nums, 1, nums.length ))//from second to very last
        );
    }

     /*
    O(n)
    O(n)
     */

    private static int robHelper(int[] nums) {
        if(nums.length==0) return 0;
        if(nums.length==1) return nums[0];

        int[] DP = new int[nums.length];
        DP[0] = nums[0];
        DP[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {

            int robThisAndTwoBack = nums[i] + DP[i-2];//rob current house + next next -2
            int skipThisAndRobOneBack = DP[i-1];//do not rob this - move to next -1

            DP[i] = Math.max( robThisAndTwoBack, skipThisAndRobOneBack);

        }

        return DP[nums.length-1];
    }

    /*
   O(n)
   O(1)
    */
    private  static int helper(int[] nums) {
        int rob1 = 0;
        int rob2 = 0;

        for (int num : nums) {
            int newRob = Math.max(rob1 + num, rob2);
            rob1 = rob2;
            rob2 = newRob;
        }
        return rob2;
    }


    private static int robTwoArraysMy(int[] nums) {//convoluted
          if(nums.length==0) return 0;
          if(nums.length==1) return nums[0];
          if(nums.length==2) return Math.max(nums[0],nums[1]);

          int len = nums.length;
          int dpLen = len - 1;

           int[] DP_first_to_sec_to_last = new int[dpLen];
            DP_first_to_sec_to_last[0] = nums[0];
            DP_first_to_sec_to_last[1] = Math.max(nums[0], nums[1]);

           int[] DP_sec_to_last = new int[dpLen];
           DP_sec_to_last[0] = nums[1];
           DP_sec_to_last[1] = Math.max(nums[1], nums[2]);

           for(int i = 2; i < len - 1 ; i++){
               DP_first_to_sec_to_last[i] = Math.max(nums[i] + DP_first_to_sec_to_last[i - 2 ], DP_first_to_sec_to_last[i - 1]);
           }


           for (int i = 2; i < len -1 ; i++){
               DP_sec_to_last[i] = Math.max( nums[i+1] + DP_sec_to_last[i-2], DP_sec_to_last[i-1]);
           }

           return Math.max(DP_first_to_sec_to_last[dpLen-1], DP_sec_to_last[dpLen-1]);
    }
}
