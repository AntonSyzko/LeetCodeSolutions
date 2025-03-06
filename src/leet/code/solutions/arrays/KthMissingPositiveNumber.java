package leet.code.solutions.arrays;
/*
https://leetcode.com/problems/kth-missing-positive-number/description/

Given an array arr of positive integers sorted in a strictly increasing order, and an integer k.

Return the kth positive integer that is missing from this array.


Example 1:

Input: arr = [2,3,4,7,11], k = 5
Output: 9

Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.
Example 2:

Input: arr = [1,2,3,4], k = 2

Output: 6
Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
 */

public class KthMissingPositiveNumber {

    public static void main(String[] args) {
          int[] arr = {2,3,4,7,11};
          int kth = 5;
          int missingNumber = findKthPositive(arr, kth);
          System.out.println(missingNumber);
    }


    private static int findKthPositive(int[] arr, int kthPosition) {
        int currentNumber = 1;//one is a start to compare missing number, not 0

        int missingNumberCount = 0;

       int arrayIndex = 0;

       while ( missingNumberCount < kthPosition) {

//exceeded array limits ( meaning all missing numbers are after array ended, OR curr number is smaller than in array
           if(arr[arrayIndex] >= arr.length || currentNumber < arr[arrayIndex]) {
               missingNumberCount++;//found missing
               if(missingNumberCount == kthPosition) {// hit of k
                   return currentNumber;
               }
           }else{
               arrayIndex++;//move on in array
           }
           currentNumber++;//no matter what increase curr
       }

       return currentNumber - 1;//array position
    }
}
