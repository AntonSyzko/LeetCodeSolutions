package leet.code.solutions.matrix;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrixTraversal {

    public static void main(String[] args) {
          int[][] matrix = {
                  {1, 2, 3},
                  {4, 5, 6},
                 {7,8,9 }};
          List<Integer> spiralOrder = spiralOrder(matrix);
          System.out.println(spiralOrder);

        int[][] matrix2 = {
                {1, 2, 3,4},
                {5, 6, 7,8},
                {9,10,11,12}};

      List<Integer> spiralOrder2 = spiralOrder(matrix2);
        System.out.println(spiralOrder2);
    }


    private static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();


        if(matrix.length == 0 ){
            return res;
        }

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int startingRow  = 0;
        int endingRow  = ROWS - 1;
        int startingCol = 0;
        int endingCol = COLS - 1;

        while(startingRow  <= endingRow && startingCol <=  endingCol ) {

            //right
            for(int col = startingCol; col <= endingCol; col++) {
                res.add(matrix[startingRow][col]);
            }
            startingRow++;


            //down
            for(int row = startingRow; row <= endingRow; row++) {
                res.add(matrix[row][endingCol]);
            }
            endingCol--;

            if( startingRow <= endingRow){
                //left
                for(int col = endingCol; col >= startingCol ; col--) {
                    res.add(matrix[endingRow][col]);
                }
                endingRow--;
            }

            if(startingCol <= endingCol){
                //up
                for(int row = endingRow; row >= startingRow; row--) {
                    res.add(matrix[row][startingCol]);
                }
                startingCol++;
            }
        }

        return res;
    }
}
