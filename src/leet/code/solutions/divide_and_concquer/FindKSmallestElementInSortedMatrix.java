package leet.code.solutions.divide_and_concquer;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
https://www.techiedelight.com/find-kth-smallest-value-sorted-matrix/

Given a row-wise and column-wise sorted square matrix and a positive integer k, find the kth smallest number in the matrix.

For example,

Input:mat = [
          [-3, 1, 3],
          [-2, 2, 4],
          [1, 3, 5]]

       k = 6

Output: 3

Explanation: The elements of the matrix in increasing order, are [-3, -2, 1, 1, 2, 3, 3, 4, 5].
 The sixth smallest element is 3.


 Input:mat = [
        [1, 3],
        [2, 4]]
  k = 5
  Output: NoneExplanation: k is more than the number of elements in the matrix.
 */
public class FindKSmallestElementInSortedMatrix {

    public static void main(String[] args) {

        int[][] mat =
                {
                        {-3, 1, 3},
                        {-2, 2, 4},
                        {1, 3, 5}
                };

        int k = 6;

        System.out.println(findkthSmallestElement(mat, k));
    }

    //The time complexity of the above solution is O(N^2) for an N × N matrix
    // and requires O(k) extra space for heap data structure.
    public static int findkthSmallestElement(int[][] mat, int minPosition){
        // invalid input
        if (mat.length == 0 || minPosition <= 0 ) {
            return Integer.MIN_VALUE;
        }
        //min hip
        PriorityQueue<Tuple> minHeap = new PriorityQueue<>(minPosition,(a,b) -> a.value - b.value);

        int kThSmallestElelement = Integer.MAX_VALUE;

        int COLS = mat[0].length;

        //store all values of FIRST row in min heap -> traverse COLS
        for (int col = 0; col < COLS; col++) {
                minHeap.offer(new Tuple(0, col, mat[0][col]));
        }


        while(minPosition-- > 0 && !minHeap.isEmpty()) {
            // remove root from the min-heap, so remove currently smallest element in min heap
            Tuple minvalue = minHeap.poll();

            // if k pop operations have been performed on the min-heap,
            // the last popped element contains the kth smallest element
            if (minPosition == 0) {
                return minvalue.value;
            }

            // replace the root with the next element from the same column of the matrix
            if (minvalue.row != mat.length - 1) {
                minHeap.add(new Tuple(minvalue.row + 1, minvalue.col, mat[minvalue.row + 1][minvalue.col]));
            }
        }

        // we reach here if k is more than the number of elements in the matrix
        return kThSmallestElelement;
    }

    //******************** BINARY SEARCH ********************************

    // Function to count elements in the matrix that are less than or equal to val
    public static int findLessOrEqual(int[][] mat, int val) {
        // start at the bottom-left corner of the matrix
        int i = mat.length - 1, j = 0;
        int count = 0;

        // loop till (i, j) cross the matrix boundary
        while (i >= 0 && j < mat.length) {
            // if the current element is more than the given value
            if (mat[i][j] > val) {
                i--;        // move up (towards smaller values)
            } else {
                // if the current element is less than the specified value,
                // then all values above the current element must also be less
                count += (i + 1);
                j++;        // move right (towards greater values)
            }
        }
        return count;
    }

    // Function to return the kth smallest value in a sorted matrix
    public static int findkthSmallestElementBinSearch(int[][] mat, int k) {
        int n = mat.length;

        // invalid input
        if (n == 0 || k <= 0 ) {
            return Integer.MIN_VALUE;
        }

        // initialize low with the top-left element of the matrix
        int low = mat[0][0];

        // initialize high with the bottom-right element of the matrix
        int high = mat[n-1][n-1];

        // loop till the search space is exhausted
        while (low <= high) {
            // find the mid-value in the search space
            int mid = low + ((high - low) >> 1);

            // find the count of elements that is less than or equal to the mid element
            int count = findLessOrEqual(mat, mid);

            // if count is less than k, the kth smallest element exists in the
            // range [mid+1…high]
            if (count < k) {
                low = mid + 1;
            }
            // otherwise, kth smallest element exists in the range [low…mid-1]
            else {
                high = mid - 1;
            }
        }

        return low;
    }


    // A class to store a heap node
    private  static class Tuple implements Comparable<Tuple> {
        int row;
        int col;
        int value;

        public Tuple(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        @Override
        public int compareTo(Tuple tuple) {
            return this.value - tuple.value;
        }
    }
}
