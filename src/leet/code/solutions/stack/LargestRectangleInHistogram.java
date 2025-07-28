package leet.code.solutions.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
84

https://leetcode.com/problems/largest-rectangle-in-histogram/description/


Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
Input: heights = [2,4]
Output: 4
 */
public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        int largestRectangleARea = largestRectangleArea(heights);
        System.out.println(largestRectangleARea);
    }

    /*
         Time: O(n) - each element pushed/popped once
        Space: O(n) - stack can hold all indices
     */
    private static int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0) return 0;

        Stack<Integer> stack = new Stack<>();//contains indices
        int maxArea   = 0;
        int len = heights.length;

        for (int i = 0; i <= len ; i++) {

            // Use 0 as sentinel value after array ends
            int currentHeights = (i == len) ? 0 : heights[i];

            // Process all bars that are taller than current bar
            while (!stack.isEmpty() && heights[stack.peek()] > currentHeights) {

                int height = heights[stack.pop()];
                int width = (stack.isEmpty())? i : i - stack.peek() - 1;
                int area = height * width;
                maxArea = Math.max(maxArea, area);

            }

            stack.push(i);
        }
        return maxArea;
    }

    private static int largestRectangleArea2(int[] heights) {
        int ans = 0;

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i <= heights.length; ++i) {

            while (!stack.isEmpty()
                    &&
                    (i == heights.length || heights[stack.peek()] > heights[i])) {

                final int h = heights[stack.pop()];
                final int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                ans = Math.max(ans, h * w);

            }

            stack.push(i);
        }

        return ans;
    }
    }
