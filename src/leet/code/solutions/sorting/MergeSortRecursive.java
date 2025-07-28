package leet.code.solutions.sorting;

import java.util.Arrays;

/*
https://habr.com/ru/post/710620/
 */
public class MergeSortRecursive {

    public static void main(String[] args) {
       int[] unsorted = {1,6,4,5,3,-1,99};
        System.out.println(Arrays.toString(unsorted));

        int[] sorted = mergeSort(unsorted);
        System.out.println(Arrays.toString(sorted));
    }

    public static int[] mergeSort(int[] src) {
        //BASE
        if (src.length <= 1) return src;

        int[] left = Arrays.copyOfRange(src, 0, src.length / 2);

        int[] right = Arrays.copyOfRange(src, left.length, src.length);

        return merge(
                mergeSort(left),
                mergeSort(right)
        );//recursive halves
    }

    private static int[] merge(int[] left, int[] right) {
        int resIndex = 0;

        int leftIndex = 0;
        int rightIndex = 0;

        int[] result = new int[left.length + right.length];

        //populating halves
        while (leftIndex < left.length && rightIndex < right.length){//AND

            if(left[leftIndex] < right[rightIndex]){

                result[resIndex] = left[leftIndex];
                leftIndex++;

            }else {

                result[resIndex] = right[rightIndex];
                rightIndex++;

            }

            resIndex++;

        }

        //left overs
        while (resIndex < result.length){

            if(leftIndex != left.length){

                result[resIndex] = left[leftIndex];
                leftIndex++;

            }else {

                result[resIndex] = right[rightIndex];
                rightIndex++;

            }

            resIndex++;
        }

        return result;
    }
}
