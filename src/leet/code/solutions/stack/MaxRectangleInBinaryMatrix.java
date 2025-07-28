package leet.code.solutions.stack;

import java.util.Stack;

/*
85
https://leetcode.com/problems/maximal-rectangle/description/

Given a rows x cols binary matrix filled with '0''s and '1''s, find the largest rectangle containing only '1''s and return its area.

Example '1':

Input: matrix = [['1','0','1','0','0'],
                ['1','0','1','1','1'],
                ['1','1','1','1','1'],
                ['1','0','0','1','0']]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:

Input: matrix = [['0']]
Output: '0'

Example 3:

Input: matrix = [['1']]
Output: '1'
 */
public class MaxRectangleInBinaryMatrix {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','1','0','0'},
                {'1','1','0','0'},
                {'0','0','1','1'},//here is max rectangle of '1''s
                {'0','0','1','1'},
                {'0','0','1','1'}
        };

        int maxArea = maximalRectangle(matrix);
        System.out.println(maxArea);
    }

    private static int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length== 0 ) return 0;

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int maxARea = 0;
        int [] heights = new int[COLS];

        for (int row = 0; row < ROWS; row++) {
            // Update heights array for current row

            for (int col = 0; col < COLS; col++) {

                if(matrix[row][col] == '1'){
                    heights[col]++;// Extend height
                }else{
                    heights[col] = 0;// Reset height
                }

            }

            // Find largest rectangle in current histogram
            maxARea = Math.max(maxARea, largestRectangleARea(heights));

        }

        return maxARea;
    }

    private  static int largestRectangleARea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int len = heights.length;

        for (int i = 0; i <= len; i++) {
            int currHeight = (i == len) ? 0 : heights[i];

            while (!stack.isEmpty() && heights[stack.peek()] > currHeight) {

                int height = heights[stack.pop()];
                int width = (stack.isEmpty()) ? i : i - stack.peek() - 1;
                int area = height * width;
                maxArea = Math.max(maxArea, area);

            }

            stack.push(i);
        }

        return maxArea;
    }
}