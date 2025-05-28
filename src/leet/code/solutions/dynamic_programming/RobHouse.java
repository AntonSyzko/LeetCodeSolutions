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
