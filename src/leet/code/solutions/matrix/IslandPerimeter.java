package leet.code.solutions.matrix;

/*
https://leetcode.com/problems/island-perimeter/description/

You are given row x col grid representing a map where grid{i}{j} = 1 represents land and grid{i}{j} = 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example 1:

Input: grid = {{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}}
Output: 16
Explanation: The perimeter is the 16 yellow stripes in the image above.
Example 2:

Input: grid = {{1}}
Output: 4
Example 3:

Input: grid = {{1,0}}
Output: 4

Constraints:

row == grid.length
col == grid{i}.length
1 <= row, col <= 100
grid{i}{j} is 0 or 1.
There is exactly one island in grid.
 */
public class IslandPerimeter {

    public static void main(String[] args) {
        int[][] grid = {
                        {0,1,0,0},
                        {1,1,1,0},
                        {0,1,0,0},
                        {1,1,0,0}
                        };

        int perimeter = islandPerimeter(grid);
        System.out.println(perimeter);
    }

    //O (m * n)
    //O (1)
    private static int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int perimeterRes = 0;
        int ROWS = grid.length;
        int COLS = grid[0].length;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                if (grid[row][col] == 1) {//island begins

                    perimeterRes += 4;//start with counting in all 4 sides

                    if (row > 0 && grid[row - 1][col] == 1) {//if neighbor to LEFT row ==1
                        perimeterRes -= 2; //extract 2 as both current and neighbor SHARE same side, hence BOTH loose 1, 2 * 1 = 2
                    }

                    if (col > 0 && grid[row][col - 1] == 1) {//if neighbor at UP col ==1
                        perimeterRes -= 2;//exract 2
                    }
                }
            }

        }
        return perimeterRes;
        }
    }
