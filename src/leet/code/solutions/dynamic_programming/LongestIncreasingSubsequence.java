package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
https://www.callicoder.com/longest-increasing-subsequence/

Given an array of integers A, find the length of the longest strictly increasing subsequence of A.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.

 For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7]. but for this example answer is [0,1,2,7]

Example 1:

Input: A = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
 */
public class LongestIncreasingSubsequence {

    public static void main(String[] args) {

        int[]a = {0,3,1,6,2,2,7};
        System.out.printf("Length of longest increasing subsequence = %d%n", findLengthOfLongestIncreasingSubsequenceMemo(a));//4


        int[]a2 = {10,9,2,5,3,7,101,18};
        System.out.printf("Length of longest increasing subsequence = %d%n", findLengthOfLongestIncreasingSubsequenceMemo(a2));//3


        int[]a3 = {0,1,0,3,2,3};
        System.out.printf("Length of longest increasing subsequence = %d%n", findLengthOfLongestIncreasingSubsequenceMemo(a3));//4

        int[]a4 = {7,7,7,7,7,7,7};
        System.out.printf("Length of longest increasing subsequence = %d%n", findLengthOfLongestIncreasingSubsequenceMemo(a4));//1
    }

    private  static   int[] LIS_DP ;
    private static int findLengthOfLongestIncreasingSubsequenceMemo(int[] arr) {

        LIS_DP = new int[arr.length];

        Arrays.fill(LIS_DP, -1);

        int longestSoFar = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++ ){
            int subsequenceLength = calculateSubsequenceLengthMemoHelper(arr, i);

            longestSoFar = Math.max(longestSoFar, subsequenceLength);

        }

        return longestSoFar;
    }

    private static int calculateSubsequenceLengthMemoHelper(int[] arr, int index) {
        //BASE
        if(LIS_DP[index] != -1) {//res is calculated before
            return LIS_DP[index];//just return
        }

        //BASE
        if(index == 0){//for index 0
            LIS_DP[index] = 1;//one possible sunsequence length is 1
            return LIS_DP[index];
        }

        int currentLength = 0;

        for (int i = 0; i < index; i++) {//iterate up to index

            if(arr[i] < arr[index]){//increasing subsequence condition

                currentLength  = Math.max(currentLength,
                        1 + calculateSubsequenceLengthMemoHelper(arr, i));//+1 since we found increasing ( line 84)
            }
        }

        LIS_DP[index] = currentLength;//MEMOIZE result

        return currentLength;
    }




    private static int findLengthOfLongestIncreasingSubsequenceReq(int[] arr) {

            int longestSoFar = Integer.MIN_VALUE;

        for(int i = 0; i < arr.length; i++ ){
            int subsequenceLength = calculateSubsequenceLengthReqHelper(arr, i);

            longestSoFar = Math.max(longestSoFar, subsequenceLength);

        }

        return longestSoFar;
    }

    /*
    Time Complexity: O(2^n) , Exponential

    Space Complexity: O(1)
     */

    private static int calculateSubsequenceLengthReqHelper(int[] arr, int index) {
        if(index == 0){//BASE

            return 1;//if there is only 1 element in a bag - it's the longest possible subsequence
        }

        int currentSubsequenceLength = 0;

        for (int start = 0; start < index; start++) {//iterate from start upt to current index

              if(arr[start] < arr[index]){//for increasing subsequence

                  currentSubsequenceLength = Math.max(
                          currentSubsequenceLength,
                          calculateSubsequenceLengthReqHelper(arr, start) +  1);//+1 because we found increasing spot

              }

        }

        return currentSubsequenceLength;
    }

    private static int lengthOfLIS(int[] nums) {
        int res = 1;//minimal subsequence length for bag of 1 is the very 1

        int[] DP = new int[nums.length];
        Arrays.fill(DP, 1);//pre-fill with 1


        for(int index = 1; index < nums.length; index++){//start from index 1 ( since 1 in DP is set for index 0, as minimal subsequence length for bag of 1 is the very 1

            for(int beforeIndex = 0; beforeIndex < index; beforeIndex++){//iterate up to index from above loop

                if(nums[index] > nums[beforeIndex]){//increasing rule

                    DP[index] = Math.max(DP[index], 1 + DP[beforeIndex]);

                    res =Math.max(res, DP[index]);

                }
            }
        }
        return res;
    }
}
