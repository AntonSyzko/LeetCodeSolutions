package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

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
        Arrays.fill(DP, -1);

        return robHelper(nums, 0 , DP);

    }

    private static int robHelper(int[] houses, int houseIndex, int[] DP){
        if(houseIndex >= houses.length){
            return 0;
        }

        if(DP[houseIndex] != -1){
            return DP[houseIndex];
        }

        int robThisAndNextNext = houses[houseIndex] + robHelper(houses, houseIndex + 2, DP);
        int robOnlyNext = robHelper(houses, houseIndex + 1 , DP);

        int currMaxGain = Math.max(robThisAndNextNext,robOnlyNext );

        DP[houseIndex] = currMaxGain;

        return currMaxGain;
    }

    private  static int robSpaceOpt(int[] nums) {
        int rob1 = 0, rob2 = 0;

        for (int num : nums) {
            int temp = Math.max(num + rob1, rob2);
            rob1 = rob2;
            rob2 = temp;
        }
        return rob2;
    }
}
