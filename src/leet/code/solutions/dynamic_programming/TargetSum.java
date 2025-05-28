package leet.code.solutions.dynamic_programming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://neetcode.io/problems/target-sum

You are given an array of integers nums and an integer target.

For each number in the array, you can choose to either add or subtract it to a total sum.

For example, if nums = [1, 2], one possible sum would be "+1-2=-1".
If nums=[1,1], there are two different ways to sum the input numbers to get a sum of 0: "+1-1" and "-1+1".

Return the number of different ways that you can build the expression such that the total sum equals target.

Example 1:

Input: nums = [2,2,2], target = 2

Output: 3
Explanation: There are 3 different ways to sum the input numbers to get a sum of 2.
+2 +2 -2 = 2
+2 -2 +2 = 2
-2 +2 +2 = 2

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
-1000 <= target <= 1000
 */
public class TargetSum {

    public static void main(String[] args) {
        int[] nums = {2,2,2};
        int target = 2;
        int ways = findTargetSumWays_DP(nums, target);
        System.out.println(ways);
    }

    private static int findTargetSumWays(int[] nums, int target) {
        int startingNumberIndex = 0;
        int currentSum = 0;
        return  dfs(nums, startingNumberIndex , currentSum, target);
    }

    private static int dfs(int[] nums, int index, int currentSum, int target) {
        //BASE
        if(index == nums.length ){
            return currentSum == target ? 1 : 0;

        }

        int sumWithPlusSign =   dfs(nums, index+1,  currentSum + nums[index], target);

        int sumWithMinusSign =   dfs(nums, index+1, currentSum -  nums[index], target);

        return sumWithPlusSign + sumWithMinusSign;
    }

    private static int findTargetSumWays_DP(int[] nums, int target) {
        int startingNumberIndex = 0;
        int currentSum = 0;
        Map<String, Integer> DP = new HashMap<>();

        return  dfs_DP(nums, startingNumberIndex , currentSum, target, DP);
    }

    private static int dfs_DP(int[] nums, int index, int currentSum, int target,  Map<String, Integer> DP) {
        //BASE
        if(index == nums.length){
            return currentSum == target ? 1 : 0;
        }

        String memoKey = index + "|" + currentSum;
        //memo hit
        if(DP.containsKey(memoKey)){
            return DP.get(memoKey);
        }

        int sumWithPlusSign = dfs_DP(nums, index+1, currentSum + nums[index], target, DP);
        int sumWithMinusSign = dfs_DP(nums, index+1, currentSum - nums[index], target, DP);

        int bothWaysResultsCombined = sumWithPlusSign + sumWithMinusSign;

        DP.put(memoKey, bothWaysResultsCombined);//add to memo

        return bothWaysResultsCombined;
    }

}
