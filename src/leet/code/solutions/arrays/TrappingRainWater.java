package leet.code.solutions.arrays;

/*
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

Example 1:

Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example 2:

Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:

n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
 */
public class TrappingRainWater {

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trappedWater = trap(height);
        System.out.println(trappedWater);
    }

    private static int trap(int[] height) {
        int len = height.length;
        int res = 0;

        int[] left = new int[len];
        int[] right = new int [len];

        //1. forward sweep
        for(int i = 0; i < len; i++){
            if(i == 0){
                left[i] = height[i];
            }else{
                left[i] = Math.max(height[i], left[i - 1]);
            }
        }

        //2.backward sweep
        for(int i = len - 1; i >= 0; i--){
            if(i == len - 1){
                right[i] = height[i];
            }else{
                right[i] = Math.max(height[i], right[i + 1]);
            }
        }

        //3. final forward sweep for res
        for(int i = 0 ; i < len; i++){
            res += Math.min(left[i], right[i])  - height[i];
        }

        return res;
}
}
