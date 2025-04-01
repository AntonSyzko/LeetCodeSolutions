package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;;

/*
https://www.techiedelight.com/partition-array-into-two-sub-arrays-with-same-sum/

Given an integer array, partition it into two subarrays having the same sum of elements.

For example,

Input:  {6, -4, -3, 2, 3} Output: The two subarrays are {6, -4} and {-3, 2, 3} having equal sum of 2
 Input:  {6, -5, 2, -4, 1} Output: The two subarrays are {} and {6, -5, 2, -4, 1} having equal sum of 0
 */
public class PartitionArrayEqualSums {

    public static void main(String[] args) {
        int[] A = { 6, -4, -3, 2, 3 };

        // get index `i` that points to the starting of the second subarray
        int i = partition(A);

        if (i != -1) {
            // print the first subarray, `A[0, i-1]`
            printArray(A, 0, i - 1);

            // print the second subarray, `A[i, n-1]`
            printArray(A, i, A.length - 1);
        } else {
            System.out.print("The array can't be partitioned");
        }
    }

    private  static int partition(int[] arr){

        int totalSum = Arrays.stream(arr).sum();

        int sumSoFar = 0;

        for (int i = 0; i <  arr.length; i++) {

            if( sumSoFar == totalSum - sumSoFar){ // if diff between TOTAL and ongoing is EQUAL

                return i;//return index cause here equality got hit

            }
            sumSoFar += arr[i];//aggregate sum
        }

        return  -1;
    }

    public static void printArray(int[] A, int i, int j) {
        System.out.println(IntStream.rangeClosed(i, j)
                .mapToObj(k -> A[k])
                .collect(Collectors.toList()));
    }

}
