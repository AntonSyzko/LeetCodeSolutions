package leet.code.solutions.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/queens-that-can-attack-the-king/description/

On a 0-indexed 8 x 8 chessboard, there can be multiple black queens and one white king.

You are given a 2D integer array queens where queens[i] = [xQueeni, yQueeni] represents the position of the ith black queen on the chessboard.
 You are also given an integer array king of length 2 where king = [xKing, yKing] represents the position of the white king.

Return the coordinates of the black queens that can directly attack the king. You may return the answer in any order.

Example 1:

Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
Output: [[0,1],[1,0],[3,3]]

Explanation: The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).
Example 2:

Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
Output: [[2,2],[3,4],[4,4]]
Explanation: The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).

Constraints:

1 <= queens.length < 64
queens[i].length == king.length == 2
0 <= xQueeni, yQueeni, xKing, yKing < 8
All the given positions are unique.
 */
public class QueenThatCanAttackKIng {

    public static void main(String[] args) {
        int[][] queens = {{0,1},{1,0},{4,0},{0,4},{3,3},{2,4}};
        int[] king = {0,0};

        List<List<Integer>> queensThatCanAttack = queensAttacktheKing(queens, king);
        System.out.println(queensThatCanAttack);
    }

    /*

    Time Complexity: O(1) - Constant time

        The chessboard is fixed at 8×8, so all operations are bounded by constants
        Building seenQueens array: O(Q) where Q ≤ 64, so O(1)
        Direction loops: 8 directions × at most 7 steps each = O(56) = O(1)
        Total: O(1) since the board size is fixed

Space Complexity: O(1) - Constant space

        seenQueens array: 8×8 = 64 boolean values = O(1)
        Result list: at most 8 queens can attack (one per direction) = O(1)
        Other variables: O(1)
     */

    private static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();

        boolean[][] seenQueens = new boolean[8][8];//board is 8 * 8

        for(int[] row : queens){
            int queenX = row[0];
            int queenY = row[1];

            seenQueens[queenX][queenY] = true;
        }

        // 8 directions: N, NE, E, SE, S, SW, W, NW
        final int[][] directions = {
                {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
                {1, 0}, {1, -1}, {0, -1}, {-1, -1}
        };

        // these 2 for loops cover all horizontal, vertical, diagonal moves from obe cell
        for (int[] move : directions) {

                int king_x = king[0] +  move[0] ;//we will move from original kings position
                int king_y = king[1]  + move[1];

                //boundary checks within while, there are 8 cells ( 0 to 7 )
                while ( isValidPosition(king_x, king_y)){

                    if(seenQueens[king_x][king_y]){//already seen
                        res.add(Arrays.asList(king_x, king_y));
                        break;//we cannot step over another QUEEN, and ONE queen we saw we know - break
                    }

                    king_x += move[0];//expand moves
                    king_y += move[1];

                }
            }

        return res;
    }

    private static boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
