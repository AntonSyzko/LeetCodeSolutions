package leet.code.solutions.matrix;

import java.util.Arrays;

/*


[
[1,1,0]
[1,0,1]
[0,0,0]
 ]

 first reverse
 [0,1,1]
 [1,0,1]
 [0,0,0]


 then invert
 [1,0,0]
 [0,1,0]
 [1,1,1]
 */
public class FlipImage {

    public static void main(String[] args) {
     int[][] grid = {
             {1,1,0},
             {1,0,1},
             {0,0,0}
     };

     int[][] flipped = flipImage(grid);

        System.out.println(Arrays.deepToString(flipped));

    }


    private static int[][] flipImage(int[][] nums){
        int ROWS = nums.length;
        int COLS = nums[0].length;

        for (int row = 0; row < ROWS ; row++) {
            int colStart = 0;
            int colEnd = nums[row].length - 1;

            while(colStart < colEnd ){//they will meet at the middle

                //swap
                int tempStartHolder = nums[row][colStart];
                nums[row][colStart] = nums[row][colEnd] ;
                nums[row][colEnd]   = tempStartHolder;

                colStart++;
                colEnd--;
            }

            for (colStart = 0;  colStart < COLS; colStart++) {
                nums[row][colStart] = nums[row][colStart] == 1 ? 0 : 1;
            }
        }

        return nums;
    }


        private static int[][] flipImageMy(int[][] nums){
        int ROWS = nums.length;
        int COLS = nums[0].length;

        int[][] result = new int[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                int currValue = nums[row][col];
                result[row][COLS - 1 - col] = currValue ^ 1;
            }
        }

        return result;
    }
}
