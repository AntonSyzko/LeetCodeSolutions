package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
198
https://leetcode.com/problems/house-robber/description/

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.


Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 400
 */
public class RobHouse {

    public static void main(String[] args) {
        int[] houses = new int[]{2,3,2};
        int max = robSpaceOpt(houses);
        System.out.println(max);
    }

    /*
     O(n) both
     */

    private static int rob(int[] nums) {
        int[] DP = new int[nums.length];
        Arrays.fill(DP, -1);//filled with -1 fake value

        int houseIndex = 0;//start at 0

        return robHelper(nums, houseIndex , DP);

    }

    private static int robHelper(int[] houses, int houseIndex, int[] DP){
        if(houseIndex >= houses.length){
            return 0;
        }

        if(DP[houseIndex] != -1){//memo hit
            return DP[houseIndex];
        }

        int robThisAndNextNext = houses[houseIndex] //money of current house
                + //plus
                robHelper(houses, houseIndex + 2, DP);//next next +2

        int robOnlyNext = robHelper(houses, houseIndex + 1 , DP);//not robbing this - just next +1

        int currMaxGain = Math.max(robThisAndNextNext,robOnlyNext );

        DP[houseIndex] = currMaxGain;

        return currMaxGain;
    }

    /*
          Time complexity: O(n)
        Space complexity: O(1)
     */
    private  static int robSpaceOpt(int[] nums) {
        int rob1 = 0;
        int rob2 = 0;

        for (int num : nums) {
            int temp = Math.max(num + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }

    /*
    O(n)
    O(n)
     */
    private static int robDP(int[] houses) {
        if(houses == null || houses.length == 0) return 0;

        if(houses.length == 1) return houses[0];

        int[] DP = new int[houses.length];
        DP[0] = houses[0];
        DP[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < houses.length; i++) {

            int robThisAndPrevNonAdjacent =  houses[i] + DP[i -2];

            int skipThisAndRobPrevOnly = DP[i-1];

            DP[i] = Math.max(robThisAndPrevNonAdjacent, skipThisAndRobPrevOnly);

        }
        return DP[houses.length - 1];
    }

    private static int robSpaceOptimal(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];

        // We only need to keep track of the previous two maximums
        int twoBack = houses[0];          // max at i-2
        int oneBack = Math.max(houses[0], houses[1]); // max at i-1
        int current = oneBack;            // initialize for n=2 case

        for (int i = 2; i < houses.length; i++) {
            current = Math.max(houses[i] + twoBack, oneBack);
            twoBack = oneBack;
            oneBack = current;
        }

        return current;
    }
}
