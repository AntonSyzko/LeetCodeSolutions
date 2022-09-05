package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://www.techiedelight.com/sort-binary-array-linear-time/

Given a binary array, sort it in linear time and constant space. The output should print all zeroes, followed by all ones.

Input:  { 1, 0, 1, 0, 1, 0, 0, 1 }
Output: { 0, 0, 0, 0, 1, 1, 1, 1 }
 */
public class SortBinaryArray {
    public static void main(String[] args) {
       int[] nums = { 1, 0, 1, 0, 1, 0, 0, 1 };
       sort(nums);
    }

    // Function to sort a binary array in linear time
    public static void sort(int[] A) {
        System.out.println(Arrays.toString(A));

        int zeros = 0;
        // count number of 0's
        for (int each : A){
            if(each == 0){
                zeros++;
            }
        }

        // put 0's at the beginning
        int k = 0;
        while (zeros-- != 0){
            A[k++]=0;
        }
// fill all remaining elements by 1
        while (k< A.length){
            A[k++] = 1;
        }
        System.out.println(Arrays.toString(A));
    }

    // Function to sort a binary array in linear time
    public static void sort2(int[] A) {
        //stores index of next available position
        int nextPosition = 0;

        for (int each : A){
            // if the current element is zero, put 0 at the next free position in the array
            if(each == 0){
                A[nextPosition++] = 0;
            }
        }
        // fill all remaining indices by 1
        for (int i = nextPosition; i < A.length ; i++) {
            A[i] = 1;
        }
    }

    // Function to move all zeros present in the array to the end
    public static void reorder(int[] A) {
        int nextNonZeroIndex = 0;
        for (int each : A){
            if(each != 0) {
                A[nextNonZeroIndex++] = each;
            }
        }

        for (int i = nextNonZeroIndex; i < A.length; i++) {
            A[i] = 0;
        }
    }
}
