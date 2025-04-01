package leet.code.solutions.blind75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/pacific-atlantic-water-flow/

There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height.
Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

Explanation: The following cells can flow to the Pacific and Atlantic oceans, as shown below:
[0,4]: [0,4] -> Pacific Ocean
       [0,4] -> Atlantic Ocean
[1,3]: [1,3] -> [0,3] -> Pacific Ocean
       [1,3] -> [1,4] -> Atlantic Ocean
[1,4]: [1,4] -> [1,3] -> [0,3] -> Pacific Ocean
       [1,4] -> Atlantic Ocean
[2,2]: [2,2] -> [1,2] -> [0,2] -> Pacific Ocean
       [2,2] -> [2,3] -> [2,4] -> Atlantic Ocean
[3,0]: [3,0] -> Pacific Ocean
       [3,0] -> [4,0] -> Atlantic Ocean
[3,1]: [3,1] -> [3,0] -> Pacific Ocean
       [3,1] -> [4,1] -> Atlantic Ocean
[4,0]: [4,0] -> Pacific Ocean
       [4,0] -> Atlantic Ocean
Note that there are other possible paths for these cells to flow to the Pacific and Atlantic oceans.
Example 2:

Input: heights = [[1]]
Output: [[0,0]]
Explanation: The water can flow from the only cell to the Pacific and Atlantic oceans.

 */
public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
         int [][] heights = new int [][]
                        {{1,2,2,3,5},
                         {3,2,3,4,4},
                         {2,4,5,3,1},
                         {6,7,1,4,5},
                         {5,1,1,2,4}};

         List<List<Integer>> res = pacificAtlantic(heights);
         System.out.println(res);

    }

    private static List<List<Integer>> pacificAtlantic(int[][] heights) {

        if(heights.length == 0 || heights[0].length == 0) return new ArrayList<>();

        int ROWS = heights.length;
        int COLS = heights[0].length;

        boolean[][] pacificPossible = new boolean[ROWS][COLS];//possible cells where water can reach shore
        boolean[][] atlanticPossible = new boolean[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                dfs(row, 0,pacificPossible, heights);//pacific is top row
                dfs(0, col,pacificPossible, heights);//pacific is leftmost col

                dfs(row, COLS - 1, atlanticPossible,heights);//atlantic rightmost col
                dfs(ROWS - 1, col, atlanticPossible,heights);//atlantic is bottom row

            }
        }


        List<List<Integer>> res = new ArrayList<>();
        for(int row = 0; row < ROWS; row++) {
            for(int col = 0; col < COLS; col++) {

                if(pacificPossible[row][col] && atlanticPossible[row][col]){//if both oceans are TRUE

                    res.add(Arrays.asList(row,col));

                }
            }
        }

        return res;
    }

    private static void dfs(int row, int col, boolean[][] oceanPossible, int[][] heights) {

        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//four possible directions

        oceanPossible[row][col] = true;//mark as possible - as we start with top,bottom,rightmost,leftmost rows cols that are true by definitipn of ocean
        //further algo will mark TRUE by comparing heights or skip keeping FALSE where skipped

       for(int[] direction : directions) {

           int newRow = row + direction[0];
           int newCol = col + direction[1];

           if(newRow < 0 || newRow >= heights.length || newCol < 0 || newCol >= heights[0].length //outside boundaries
                   ||
              oceanPossible[newRow][newCol] //already visited
                 ||
              heights[newRow][newCol] < heights[row][col]) {//if NEW height is LOWER  than the one we are investigating ( water can flow from higher to lower or same )

               continue;//skip / skipping keep default matrix boolean value as FALSE

           }

           dfs(newRow,newCol,oceanPossible,heights);//recur for it

       }
    }
}
