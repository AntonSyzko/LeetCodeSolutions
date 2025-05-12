package leet.code.solutions.two_pointers;

import java.util.Arrays;

/*
https://www.callicoder.com/triplet-sum-closest-to-target/

Given an array of unsorted integers a and a target, find a triplet in the array whose sum is closest to the target value. Return the sum of the triplet.

Example 1:

Input: a[] = [-2, -4, 6, 3, 7], target = 2
Output: 1
Explanation: Triplet with sum closest to target is [-2, -4, 7], sum of the triplets = 1
Example 2:

Input: a[] = [10, 2, 30, 49, 8], target = 50
Output: 48
Explanation: Triplet with sum closest to target is [10, 30, 8], sum of the triplets = 48
Example 2:

Input: a[] = [1, 0, 5, 0, 3], target = 100
Output: 9
 */
public class TripletSumClosestToTarget {

    public static void main(String[] args) {
       int[] nums = {-2, -4, 6, 3, 7};
       int targetSum = 2;
       int closestDistanceToTargetSum = findTripletSumClosestToTarget(nums, targetSum);
       System.out.println(closestDistanceToTargetSum);

    }

    private static int findTripletSumClosestToTarget(int[] arr, int targetSum) {
       int closestSum = Integer.MAX_VALUE;
       Arrays.sort(arr);// SORT !!!!!!!!!!!!!!!

       for(int first = 0; first < arr.length -2; first++){

           if( first > 0 && arr[first] == arr[first - 1]){
               continue;
           }
           int firstNum = arr[first];//fix first num

           int start = first+1;
           int end = arr.length-1;

           while(start < end){//two pointers

               int currSum = firstNum + arr[start] + arr[end];

               int currDistanceToTarget = targetSum - currSum;

               if(currDistanceToTarget == 0){//exact match
                   return currSum;//fast return
               }

               if(Math.abs(currDistanceToTarget) < Math.abs(closestSum)){
                   closestSum = currSum;
               }

               if(currDistanceToTarget < 0){
                   start++;//raise left pointer
               }else{
                   end--;//raise right pointer
               }
           }
       }
       return closestSum;
    }
    }
