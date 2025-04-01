package leet.code.solutions.blind75;

public class ClimbingStairs {

    public static void main(String[] args) {
        int steps = 3;
        int numberOfDistinctWaysToGetToTop = climbStairsDP(steps);
        System.out.println(numberOfDistinctWaysToGetToTop);
    }

    private static int climbStairsDP(int steps) {
        int[] DP = new int[steps + 1];
        DP[0] = 1;
        DP[1] = 1;

        for (int i = 2; i <= steps; i++) {
            DP[i] = DP[i-2] + DP[i-1];
        }

        return DP[steps];
    }

    //slower than DP
    private static int climbStairsRecuirsive(int steps) {
         if(steps == 0){
             return 1;
         }
         if(steps == 1){
             return 1;
         }

        return climbStairsRecuirsive(steps - 1) + climbStairsRecuirsive(steps - 2);

    }
}
