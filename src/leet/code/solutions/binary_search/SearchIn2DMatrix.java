package leet.code.solutions.binary_search;

/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix
has properties:
1) Integers in each row are sorted from left to right. 2) The first integer of each row
is greater than the last integer of the previous row.
For example, consider the following matrix:
[
[1, 3, 5, 7],
[10, 11, 16, 20],
[23, 30, 34, 50]
]
Given target = 3, return true.

 */
public class SearchIn2DMatrix {

    public static void main(String[] args) {
         int[][] matrix = {
                 {1,3,5,7},
                 {10,11,16,20},
                 {23,30,34,50}
         };

         int target = 3;
         boolean exists = searchMatrix(matrix, target);
         System.out.println(exists);
    }


    private static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int start = 0;
        int end = ROWS * COLS -1;

        while(start <= end){

            int mid = start + (end - start)/2;

            int midX = mid / ROWS;
            int midY = mid % ROWS;

            if(matrix[midX][midY] == target) {

                return true;

            }else if (matrix[midX][midY] < target){//too low

                start = mid + 1;

            }else{

                end = mid - 1;

            }
        }

        return false;
    }
}
