package leet.code.solutions.cyclic_sort;

import java.util.Arrays;

/*
https://www.geeksforgeeks.org/find-the-smallest-positive-number-missing-from-an-unsorted-array/

Given an unsorted array arr[] with both positive and negative elements, the task is to find the smallest positive number missing from the array.

Note: You can modify the original array.

Examples:

Input: arr[] = {2, -3, 4, 1, 1, 7}
Output: 3
Explanation: 3 is the smallest positive number missing from the array.


Input: arr[] = {5, 3, 2, 5, 1}
Output: 4
Explanation: 4 is the smallest positive number missing from the array.


Input: arr[] = {-8, 0, -1, -4, -3}
Output: 1
Explanation: 1 is the smallest positive number missing from the array.


 */
public class SmallestMissingPositive {

    public static void main(String[] args) {
      int[] arr = {2, -3, 4, 1, 1, 7};
      int smallestMissing = missingNumberArray(arr);
        System.out.println(smallestMissing);
    }

    private static int missingNumber(int[] arr) {
        Arrays.sort(arr);

        // res will hold the current smallest missing number, initially set to 1
        int res = 1;

        for (int i = 0; i < arr.length; i++) {


            // If we have found 'res' in the array, 'res' is no longer missing, so increment it
            if( arr[i] == res){

                res++;

            } else if(arr[i] > res){  // If the current element is larger than 'res', 'res' cannot be found in the array, so it is our final answer

            break;

            }
        }

        return res;
    }

    static int missingNumberArray(int[] arr) {
        int len = arr.length;

        // To mark the occurrence of elements
        boolean[] vis = new boolean[len];

        for (int i = 0; i < len; i++) {

            // if element is in range from 1 to n then mark it as visited
            if (arr[i] > 0 && arr[i] <= len)
                vis[arr[i] - 1] = true;
        }

        // Find the first element which is unvisited in the original array
        for (int i = 1; i <= len; i++) {
            if (!vis[i - 1]) {
                return i;
            }
        }

        // if all elements from 1 to n are visited
        // then n+1 will be first positive missing number
        return len + 1;
    }


    // Function for finding the first missing positive number
    static int missingNumberCyclicSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {

            // if arr[i] is within the range [1, n] and arr[i]
            // is not placed at (arr[i]-1)th index in arr
            while (arr[i] >= 1 && arr[i] <= n
                    && arr[i] != arr[arr[i] - 1]) {

                // then swap arr[i] and arr[arr[i]-1] to
                // place arr[i] to its corresponding index
                int temp = arr[i];
                arr[i] = arr[arr[i] - 1];
                arr[temp - 1] = temp;
            }
        }

        // If any number is not at its corresponding index
        // then it is the missing number
        for (int i = 1; i <= n; i++) {
            if (i != arr[i - 1]) {
                return i;
            }
        }

        // If all number from 1 to n are present then n+1
        // is smallest missing number
        return n + 1;
    }
    }
