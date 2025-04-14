package leet.code.solutions.matrix;


/*

given  square matrix with positive and negative numbers

we can swap sign of any neighbour ( vertically or horizontally ) numbers to opposite ( + to - , or - to + )

count sum of possible MAX all matrix nums with swaps

| -2 | 2 | 4 |
| 6  |-3 | 2 |
| 1  | 5 | -6|
 */
public class CountMatrixSum {

    public static void main(String[] args) {
     int[][] matrix = { {-2,2,4},
                        {6,-3,2},
                        {1,5,-6}
     };

     int maxPossibleSumAfterSwaps  = countMatrixSum(matrix);

        System.out.println(maxPossibleSumAfterSwaps);

    }

    private static int countMatrixSum(int[][] matrix) {
        int closestToZeroNumInEntireMatrix = Integer.MAX_VALUE;
        int negativeNumbersCount = 0;
        int matrixSumRes = 0;

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int currCellValue = matrix[row][col];

                matrixSumRes += Math.abs(currCellValue);// mind ABS - we accumulate just numbers , no matter sign

                if(currCellValue < 0){
                    negativeNumbersCount++;
                }

                closestToZeroNumInEntireMatrix = Math.min(closestToZeroNumInEntireMatrix, Math.abs(currCellValue));//mind ABS
            }
        }

        if(negativeNumbersCount % 2  == 0){// negative numbers count is EQUAL ( 2,4,)

            return matrixSumRes;

        } else{// negative numbers count is ODD ( 3,5,....

            //substract closest to zero from total sum ,as closes to zero number could have been swapped to negative and would be the only remaining negative one
            return matrixSumRes  - (2 *  closestToZeroNumInEntireMatrix); // times * 2 since we have accumulated it before ( line 33)

        }
    }
}
