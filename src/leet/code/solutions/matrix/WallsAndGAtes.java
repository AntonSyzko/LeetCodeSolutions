package leet.code.solutions.matrix;

import java.util.Arrays;

/*
2D grid, 
0 is GATE
-1 - is WALL ( no way to proceed)
INF - empty room ( to alter in solution)

Fill empty rooms with SHORTEST path to any closes gate ( 0) 

 [[INF, -1, 0, INF],
 [INF, INF, INF, -1],
 [INF, -1, INF, -1], 
 [0, -1, INF, INF]]


[[3, -1, 0, 1],
[2, 2, 1, -1],
[1, -1, 2, -1],
[0, -1, 3, 4]]
 */
public class WallsAndGAtes {

    public static void main(String[] args) {
        int[][] rooms = {
                {Integer.MAX_VALUE, -1, 0,Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE,Integer.MAX_VALUE,-1},
                {Integer.MAX_VALUE, -1, Integer.MAX_VALUE,-1},
                {0,-1,Integer.MAX_VALUE,Integer.MAX_VALUE},

        };

        System.out.println(Arrays.deepToString(rooms));

        wallsAndGAtes(rooms);

        System.out.println(Arrays.deepToString(rooms));
    }

    private static void wallsAndGAtes(int[][] rooms){
        if(rooms == null || rooms.length == 0) return;

        int ROWS = rooms.length;
        int COLS = rooms[0].length;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (rooms[row][col] == 0) {
                    dfs(rooms, row,col,0);
                }
            }
        }
    }

    private static void dfs(int[][] rooms, int row, int col, int distance) {

        //BASE
        if(row < 0 || col < 0
                || row >= rooms.length || col >= rooms[0].length
                || rooms[row][col] < distance) {//current distance already previously saved in this room is SMALLER than distance we are trying to set

            return;

        }

        rooms[row][col] = distance;//set new distance in this cell
        //up
        dfs(rooms,row - 1 ,col,distance+1);
        //down
        dfs(rooms,row +1 ,col,distance+1);
        //left
        dfs(rooms,row  ,col -1,distance+1);
        //right
        dfs(rooms,row  ,col +1,distance+1);
    }
}
