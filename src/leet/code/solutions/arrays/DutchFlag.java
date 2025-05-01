package leet.code.solutions.arrays;

import java.util.Arrays;

/*

https://www.techiedelight.com/sort-array-containing-0s-1s-2s-dutch-national-flag-problem/

Given an array containing only 0’s, 1’s, and 2’s, sort it in linear time and using constant space.

For example,

Input:  { 0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0 }
 Output: { 0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2 }
 */
public class DutchFlag {

    public static void main(String[] args) {
        int[] array = {0, 1, 2, 2, 1, 0, 0, 2, 0, 1, 1, 0};//0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2
        threeWayPartition(array);
        System.out.println(Arrays.toString(array));
    }

    public static void threeWayPartition(int[] array){
        int start = 0;
        int end = array.length - 1;
        int mid = 0;

        int PIVOT = 1;

        while (mid <= end){

            if(array[mid] < PIVOT){ // current element is 0

                swap(array, start, mid);
                start++;
                mid++;

            }else if (array[mid] > PIVOT){ // current element is 2

                swap(array,mid, end);
                end--;

            }else{ // current element is 1
                //don't swap just move
                mid++;

            }
        }
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


        //O(n) - but two traversals
    public static void sortArray(int[] array){
        int zeroCount = 0;
        int oneCount = 0;
        int twoCount = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                zeroCount++;
            }else if (array[i] == 1) {
                oneCount++;
            }else{
                twoCount++;
            }
        }

        int index = 0;

       while(zeroCount-- > 0){
           array[index++] = 0;
       }
       while(oneCount-- > 0){
           array[index++] = 1;
       }
       while(twoCount-- > 0){
           array[index++] = 2;
       }
    }
}
