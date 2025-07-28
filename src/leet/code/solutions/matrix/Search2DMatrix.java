package leet.code.solutions.matrix;

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
public class Search2DMatrix {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,50}
        };

        System.out.println(searchMatrix(matrix, 3));

    }

    /*
            Time Complexity: O(log(m×n)) - Optimal for this problem

            Space Complexity: O(1) - Constant extra space

            Efficiency: Much better than row-by-row search O(m×n) or even row-wise binary search O(m×log(n))
     */
    private static boolean searchMatrix(int[][] matrix, int target) {

        if(matrix== null || matrix.length == 0 || matrix[0].length == 0) return false;

        int ROWS = matrix.length;
        int COLS = matrix[0].length;

        int start = 0;
        int end = ROWS * COLS - 1;

        while (start <= end) {// mind <=

            int mid = start + (end - start)/2;

            int midX = mid / COLS;//crux
            int midY = mid % COLS;

            if(matrix[midX][midY] == target) {
                return true;
            }

            else if(matrix[midX][midY] < target) {

                start = mid + 1;

            }else if (matrix[midX][midY] > target) {

                end = mid - 1;

            }
        }

        return false;
    }
}
