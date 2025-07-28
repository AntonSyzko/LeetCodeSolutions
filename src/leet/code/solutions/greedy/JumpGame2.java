package leet.code.solutions.greedy;

/*
https://neetcode.io/problems/jump-game-ii

You are given an array of integers nums, where nums[i] represents the maximum length of a jump towards the right from index i.
For example, if you are at nums[i], you can jump to any index i + j where:

j <= nums[i]
i + j < nums.length
You are initially positioned at nums[0].

Return the minimum number of jumps to reach the last position in the array (index nums.length - 1).
 You may assume there is always a valid answer.

Example 1:

Input: nums = [2,4,1,1,1,1]

Output: 2
Explanation: Jump from index 0 to index 1, then jump from index 1 to the last index.

Example 2:

Input: nums = [2,1,2,1,0]

Output: 2
Constraints:

1 <= nums.length <= 1000
0 <= nums[i] <= 100
 */
public class JumpGame2 {

    public static void main(String[] args) {
        int[] positions = {2,1,2,1,0};
        int minJumps = jump(positions);
        System.out.println(minJumps);
    }

    /*
    Time Complexity:
        O(n) - we process each element once
    Space Complexity:
        O(1) - we only use a constant amount of extra space
     */
    private static int jump(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;

        int minJumpsRes = 0;

        int currentEnd = 0;  // The furthest position that can be reached with current minJumpsRes

        int farthest = 0;    // The furthest position that can be reached with one more jump

        // We don't need to process the last element
        for (int i = 0; i < len - 1; i++) {
            // Update the farthest position we can reach
            farthest = Math.max(farthest, i + nums[i]);

            // If we've reached the end of current jump range
            if (i == currentEnd) {
                // We must make another jump
                minJumpsRes++;
                // Update our jump range
                currentEnd = farthest;

                // If we can already reach the end
                if (currentEnd >= len - 1) {
                    return minJumpsRes;//fast return
                }
            }
        }

        return minJumpsRes;
    }

}
