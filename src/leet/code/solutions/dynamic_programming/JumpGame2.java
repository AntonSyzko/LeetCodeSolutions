package leet.code.solutions.dynamic_programming;

/*
45

https://leetcode.com/problems/jump-game-ii/description/

You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i], you can jump to any nums[i + j] where:

0 <= j <= nums[i] and
i + j < n
Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000
It's guaranteed that you can reach nums[n - 1].
 */
public class JumpGame2 {

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        int minJumps =  jump(nums);
        System.out.println(minJumps);
    }

    private static int jump(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        int[] DP = new int[nums.length];//filled with zeroes

        int start = 0;
        int end = nums.length;

        return minJumps(nums, start, end, DP);
    }

    private static int minJumps(int[] nums, int start, int end, int[] DP) {
        //base
        if(start == end - 1 ){//reached very end
            return 0;
        }

        if(start >= end || nums[start] == 0){//not reacheable
            return Integer.MAX_VALUE;
        }

        if(DP[start] != 0){//DP hit
            return DP[start];
        }

        int minJumps = Integer.MAX_VALUE;

        for(int i = start + 1; i <= start + nums[start]; i++){//explore each possibility of jumps from START to (START + nums[start])

            int currCost = minJumps(nums, i, end, DP);

            if(currCost != Integer.MAX_VALUE){
                minJumps = Math.min( minJumps, currCost + 1 );
            }

        }

        DP[start] = minJumps;//set memo

        return DP[start] ;
    }
}