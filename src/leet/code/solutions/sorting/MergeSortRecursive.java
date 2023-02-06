package leet.code.solutions.sorting;

import java.util.Arrays;

/*
https://habr.com/ru/post/710620/
 */
public class MergeSortRecursive {

    public static void main(String[] args) {
       int[] unsorted = {1,6,4,5,3,-1,99};
       int[] sorted = mergeSort(unsorted);
        System.out.println(Arrays.toString(sorted));
    }

    public static int[] mergeSort(int[] src) {
        if (src.length <= 1) return src;

        int[] left = Arrays.copyOfRange(src, 0, src.length / 2);

        int[] right = Arrays.copyOfRange(src, left.length, src.length);

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int resIn = 0, leftIn = 0, rightIn = 0;

        int[] result = new int[left.length + right.length];

        //populating halves
        while (leftIn < left.length && rightIn < right.length){
            if(left[leftIn] < right[rightIn]){
                result[resIn++] = left[leftIn++];
            }else {
                result[resIn++] = right[rightIn++];
            }
        }



//left overs
        while (resIn < result.length){
            if(leftIn != left.length){
                result[resIn++] = left[leftIn++];
            }else {
                result[resIn++] = right[rightIn++];
            }
        }

        return result;
    }

}
