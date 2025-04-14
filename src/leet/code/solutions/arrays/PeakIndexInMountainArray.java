package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.Comparator;

/*
https://leetcode.com/problems/peak-index-in-a-mountain-array/

You are given an integer mountain array arr of length n where the values increase to a peak element and then decrease.

Return the index of the peak element.

Your task is to solve it in O(log(n)) time complexity.

Example 1:
Input: arr = [0,1,0]
Output: 1

Example 2:
Input: arr = [0,2,1,0]
Output: 1

Example 3:
Input: arr = [0,10,5,2]
Output: 1

Constraints:

3 <= arr.length <= 105
0 <= arr[i] <= 106
arr is guaranteed to be a mountain array.
 */
public class PeakIndexInMountainArray {

    public static void main(String[] args) {
        int[] mountains = {0,10,5,2};
        int peakIndex =  peakIndexInMountainArrayBinSearch(mountains);
        System.out.println(peakIndex);
    }

    private static int peakIndexInMountainArrayBinSearch(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            int currentMountainHeight = arr[mid];

            if(currentMountainHeight > arr[mid + 1]) {
                right = mid ;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }


        //O(n)
    private static int peakIndexInMountainArray(int[] arr) {
         int res = -1;

         int maxSoFar = Integer.MIN_VALUE;

         for(int i = 0; i < arr.length; i++){
            if(arr[i] > maxSoFar){
                maxSoFar = arr[i];
                res = i;
            }
         }

         return res;
    }


    private static int peakIndexInMountainArray2(int[] arr) {
        int res = -1;

        int peakMountain =  Arrays.stream(arr).max().getAsInt();//O(n)

        for(int i = 0; i < arr.length; i++){//O(n)
            if(peakMountain == arr[i]){
                res = i;
            }
        }
        return res;
    }

    }
