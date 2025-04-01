package leet.code.solutions.sliding_window;

/*
Find minimum sum subarray of size `k`
Given an integer array, find the minimum sum subarray of size k, where k is a positive integer.

For example,

Input:  {10, 4, 2, 5, 6, 3, 8, 1}, k = 3
 Output: Minimum sum subarray of size 3 is (1, 3)

 diff between elemenst of indexes 1 and 3 = 4 - 5 = 1 is minimal diff oa all
 */
public class MinimumSumSubarray {

    public static void main(String[] args) {
    int[] arr = {10, 4, 2, 5, 6, 3, 8, 1};
    int k = 3;
        findSubarray(arr, k);
    }

public static void findSubarray(int[] arr, int windowLimit){
        // base case
        if(arr == null || arr.length == 0 || arr.length <= windowLimit) {
            return;
        }

        // stores the sum of minimum sum subarray found so far
        int minSum = Integer.MAX_VALUE;

        // stores ending index of the minimum sum subarray found so far
            int lastIndexOfSubarray = 0;

             int ongoingSUm = 0;

            for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
                // add the current element to the window
                ongoingSUm += arr[windowEnd];

                // if the window size is more than equal to `windowLimit`
                if(windowEnd + 1 >= windowLimit){//hit window limit

                    if(ongoingSUm < minSum){
                        // update the minimum sum window
                        minSum = ongoingSUm;
                        lastIndexOfSubarray = windowEnd;
                    }

                    // remove a leftmost element from the window
                    ongoingSUm -= arr[windowEnd + 1 - windowLimit];
                }
            }
            System.out.printf("Minimum sum subarray of size %d is (%d, %d)", windowLimit, lastIndexOfSubarray - windowLimit + 1, lastIndexOfSubarray);
    }

}
