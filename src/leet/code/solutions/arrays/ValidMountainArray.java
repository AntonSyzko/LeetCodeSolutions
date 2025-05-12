package leet.code.solutions.arrays;


/*
https://leetcode.com/problems/valid-mountain-array/description/

Given an array of integers arr, return true if and only if it is a valid mountain array.

Recall that arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]



Example 1:

Input: arr = [2,1]
Output: false
Example 2:

Input: arr = [3,5,5]
Output: false
Example 3:

Input: arr = [0,3,2,1]
Output: true
 */
public class ValidMountainArray {

    public static void main(String[] args) {
        int[] mountains = {0,3,2,1};
        boolean validMountainPeakExists =  validMountainArray(mountains);
        System.out.println(validMountainPeakExists);
    }


    private static boolean validMountainArray(int[] arr) {

        if(arr.length <=3) return false;

        int start = 0;
        while (start < arr.length && start+1 < arr.length
                && arr[start] < arr[start+1]) { // increasing check
            start++;
        }

        //if it has never increased or has been increasing till the END ( meaning never decreased )
        if(start == 0 || start+1 == arr.length) return false;

        while(start < arr.length && start +1 < arr.length) {
            if(arr[start] <= arr[start+1]){//decreasing check
                return false;//fail fast - not decreasing
            }
            start++;
        }

        return true ;//reached here all good
    }
}
