package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
Partition an array into two subarrays with the same sum
Given an integer array, partition it into two subarrays having the same sum of elements.

Input:  {6, -4, -3, 2, 3}
Output: The two subarrays are {6, -4} and {-3, 2, 3} having equal sum of 2

Input:  {6, -5, 2, -4, 1}
Output: The two subarrays are {} and {6, -5, 2, -4, 1} having equal sum of 0
 */
public class PartitionSubarraysWithSameSum {
    public static void main(String[] args) {
        int[] A = { 6, -4, -3, 2, 3 };

        // get index `i` that points to the starting of the second subarray
        int i = partition(A);

        if (i != -1) {
            // print the first subarray, `A[0, i-1]`
            printArray(A, 0, i - 1);

            // print the second subarray, `A[i, n-1]`
            printArray(A, i, A.length - 1);
        }
        else {
            System.out.print("The array can't be partitioned");
        }
    }



    // Partition the array into two subarrays with the same sum
    //O(n), space O(1)
    public static int partition(int[] arr) {
        // calculate the sum of all array elements
        int total_sum = Arrays.stream(arr).sum();

        // variable to maintain the sum of processed elements
        int sum_so_far = 0;

        // do for each array element
        for (int i = 0; i < arr.length; i++) {
            // if sum of `A[0…i-1]` is equal to `A[i, n-1]`
            if (sum_so_far == total_sum - sum_so_far) {
                return i;
            }

            // update `sum_so_far` by including the value of the current element
            sum_so_far += arr[i];
        }

        return -1;
    }

    // Partition the array into two subarrays with the same sum
    //O(n^2)
    public static int partition2(int[] A) {
        // do for each array element
        for (int i = 0; i < A.length; i++) {
            int left_sum = 0;
            for (int j = 0; j < i; j++) {
                left_sum += A[j];
            }

            int right_sum = 0;
            for (int j = i; j < A.length; j++) {
                right_sum += A[j];
            }

            // if sum of `A[0…i-1]` is equal to `A[i, n-1]`
            if (left_sum == right_sum) {
                return i;
            }
        }

        // invalid input
        return -1;
    }

    // Utility function to print the array between specified indices
    public static void printArray(int[] nums, int left, int right){
        System.out.println(IntStream.rangeClosed(left,right).mapToObj(n -> nums[n]).collect(Collectors.toList()));
    }
}
