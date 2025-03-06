package leet.code.solutions.sliding_window;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
https://www.callicoder.com/first-negative-number-in-every-window-of-size-k/

Given an array of integers a, and a positive integer k, find the first negative integer for each and every window (contiguous subarray) of size k. If a window does not contain a negative integer, then print 0 for that window.

Example 1

Input: a[] = {-5, 1, 2, -6, 9}, k = 2
Output : -5 0 -6 -6

Explanation: First negative integer in every window of size 2
{-5, 1} = -5
{1, 2} = 0 (does not contain a negative integer)
{2, -6} = -6
{-6, 9} = -6
Example 2

Input : a[] = {10, -1, -5, 7, -15, 20, 18, 24} , k = 3
Output : -1 -1 -5 -15 -15 0
Explanation: First negative integer in every window of size 3
{10, -1, -5} = -1
{-1, -5, 7} = -1
{-5, 7, -15} = -5
{7, -15, 20} = -15
{-15, 20, 18} = -15
{20, 18, 24} = 0 (does not contain a negative integer)
 */
public class FirstNegativeNumber {

    public static void main(String[] args) {
        int[] array = {-5, 1, 2, -6, 9};
        int k = 2;
        System.out.println(Arrays.toString(findFirstNegativeNumberInSubarrayOfSizeKWithQueue(array, k)));

        int[] array2 = {10, -1, -5, 7, -15, 20, 18, 24};
         k = 3;
        System.out.println(Arrays.toString(findFirstNegativeNumberInSubarrayOfSizeKWithQueue(array2, k)));


    }
    private static int[] findFirstNegativeNumberInSubarrayOfSizeKWithQueue(int[] arr, int k) {
        int len = arr.length;
        int [] res = new int[len-k+1];
        int resIndex = 0;
        Queue<Integer> queue = new LinkedList<>();//FIFO
        int windowStart = 0;


        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

            if(arr[windowEnd] < 0){
                queue.add(arr[windowEnd]);//add negative to Q as they first occur
            }

            // Calculate result and Slide the window
            if( windowEnd - windowStart + 1 == k){//hit K

                if(!queue.isEmpty()){//Q is filled
                    int firstOccurredNegativeNumFromQ = queue.peek();//FIFO ->

                     res[resIndex++] = firstOccurredNegativeNumFromQ;//-> first out to RES

                    // Remove a[windowStart] from the queue since we need to slide the window now.
                    // But only if it was added to the queue previously
                     if(firstOccurredNegativeNumFromQ == arr[windowStart]){
                         queue.remove();
                     }
                }else{
                    res[resIndex++] = 0;
                }

                windowStart++;// Slide the window ahead
            }
        }
        return res;
    }


    //My
    private static int[] findFirstNegativeNumberInSubarrayOfSizeK(int[] arr, int k) {
        int [] res = new int[arr.length-k+1];
        int windowStart = 0;

        for (int windowEnd = windowStart; windowEnd < arr.length; windowEnd++) {

            if(arr[windowStart] < 0 ){

                res[windowStart] = arr[windowStart];

            }else if(arr[windowStart] > 0 && arr[windowEnd] < 0){
                res[windowStart] = arr[windowEnd];
            }

            if( windowEnd - windowStart  + 1 == k){
                windowStart++;
            }
        }
        return res;
    }
    }
