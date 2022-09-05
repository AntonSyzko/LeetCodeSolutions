package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
https://leetcode.com/problems/unique-paths-ii/

https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/62_unique_paths_ii__medium.html

Problem:
Follow up for “Unique Paths”:
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and empty space is marked as 1 and 0 respectively in the grid.
For example,
There is one obstacle in the middle of a 3×3 grid as illustrated below.
[ [0,0,0], [0,1,0], [0,0,0] ] The total number of unique paths is 2.

Note: m and n will be at most 100.

Thoughts:
This problem is almost identical to Unique Paths problem. The only difference is that there are conditions when assigning value to d[i][j].

d[i][j] = 0 if board[i][j] == 1
d[i][j] = d[i-1][j] +d[i][j-1] othwerwise
Plus, now d[i][0] and d[0][j] is not sure to be 1.
 */
public class UniquePaths2 {
    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0},
                        {0, 1, 0},
                        {0, 0, 0}};

        System.out.println(Arrays.deepToString(grid));

        int uniquePaths = uniquePathsWithObstacles(grid);
        System.out.println(uniquePaths);
    }

    private static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;

        int[][] paths = new int[rows][cols];//res

        //start cell setup - top left corner
        if (obstacleGrid[0][0] == 0){//not an obstacle
            paths[0][0] = 1;
        }
        else{
            paths[0][0] = 0;
        }

        //-----PATH ARRAY EDGES FILLING -----
        // 0 0 0 0
        // 0
        // 0

        //edge rows filling
        for (int i = 1; i < rows; i ++){
            //current cell is NOT an obstacle(==0) in original array  AND previous cell is a path in res (==1)
            if (obstacleGrid[i][0] == 0 && paths[i-1][0] == 1){
                paths[i][0] = 1;

            } else{
                //current cell IS obstacle in original grid OR previous cell in res was NOT a path
                paths[i][0] = 0;
            }
        }


        //edge columns filling
        for (int j = 1; j < cols; j ++){
            //current cell in original grid is NOT an obstacle(==0) AND previous cell is A path in res (==1)
            if (obstacleGrid[0][j] == 0 && paths[0][j-1] == 1){
                paths[0][j] = 1;

            } else{
                //current cell in original IS obstacle OR previous cell was NOT a path in res
                paths[0][j] = 0;
            }
        }

        //traverse original [][] grid from first (not zero!!! )left top cell
        for (int i = 1; i < rows ; i ++){
            for (int j = 1; j < cols; j ++){
                if (obstacleGrid[i][j] == 1){//if an obstacle in original

                    paths[i][j] = 0;//will have 0 in res

                } else{
                    //normal DP calc for path = sum left and bottom cells
                    paths[i][j] = paths[i-1][j] + paths[i][j-1];

                }
            }
        }

        return paths[rows-1][cols-1];//res is at the end
    }
}
