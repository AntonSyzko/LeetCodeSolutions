package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/container-with-most-water/

You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).
Find two lines that together with the x-axis form a container, such that the container contains the most water.
Return the maximum amount of water a container can store.
Notice that you may not slant the container.

Example 1:
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49
Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
In this case, the max area of water (blue section) the container can contain is 49.

Example 2:
Input: height = [1,1]
Output: 1

Constraints:
n == height.length
2 <= n <= 105
0 <= height[i] <= 104
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
//        int[] container = {1, 8, 6, 2, 5, 4, 8, 3, 7};
//        int maxArea = maxArea(container);
//        System.out.println(maxArea);

         int [] container2 = {1,4,2,3};
         int maxArea = maxArea(container2);
        System.out.println(maxArea);
    }

    //time O(n)
    //space O(1)
    private static int maxArea(int[] height) {
        int maxArea = 0;//res

        int leftPointer = 0;
        int rightPointer = height.length - 1;

        while (leftPointer < rightPointer) {

            int currentWidth = rightPointer - leftPointer;

            //we take lower pointer - for water not to overflow . ~ |
            if (height[leftPointer] < height[rightPointer]) {//if LEFT pointer is lower height

                //height[leftPointer] * currentWidth = current area with lower LEFT pointer and current Width
                maxArea = Math.max(maxArea, (height[leftPointer] * currentWidth));

                leftPointer++;

            }else {//if RIGHT pointer is lower heigth

                //height[rightPointer] * currentWidth = current area with lower RIGHT pointer and current Width
                maxArea = Math.max(maxArea, (height[rightPointer] * currentWidth));

                rightPointer--;
            }
        }
        return maxArea;
    }
}
