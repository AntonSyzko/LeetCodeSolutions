package leet.code.solutions.two_pointers;

import java.util.Arrays;

/*
https://leetcode.com/problems/squares-of-a-sorted-array/description/

Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

Example 1:

Input: nums = [-4,-1,0,3,10]
Output: [0,1,9,16,100]
Explanation: After squaring, the array becomes [16,1,0,9,100].
After sorting, it becomes [0,1,9,16,100].
Example 2:

Input: nums = [-7,-3,2,3,11]
Output: [4,9,9,49,121]


Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums is sorted in non-decreasing order.

Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        int[] nums = {-5,-2,-1,0,4,6};
        int[] squareSorted = sortedSquares2(nums);
        System.out.println(Arrays.toString(squareSorted));
    }

    public static int[] sortedSquares(int[] arr) {
        int [] res = new int[arr.length];
        int resIndex = res.length -1;
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int squareStart = arr[start] * arr[start];
            int squareEnd = arr[end] * arr[end];

            if (squareStart > squareEnd) {
                res[resIndex--] = squareStart;
                start++;
            }else{
                res[resIndex--] = squareEnd;
                end--;
            }
        }
        return res;
    }

    public static int[] sortedSquares2(int[] arr) {
        int[] res = new int[arr.length];
        int start = 0;
        int end = arr.length - 1;

        for (int i = res.length -1; i >=0 ; i--) {

            if(Math.abs(arr[start]) > arr[end]){

                res[i] = arr[start] * arr[start];
                start++;

            }else{

                res[i] = arr[end] * arr[end];
                end--;

            }
        }

        return res;
    }
    }
