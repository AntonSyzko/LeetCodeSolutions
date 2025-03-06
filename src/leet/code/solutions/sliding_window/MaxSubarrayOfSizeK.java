package leet.code.solutions.sliding_window;

/*
https://www.educative.io/courses/grokking-the-coding-interview/JPKr0kqLGNP

Given an array of positive numbers and a positive number ‘k,’ find the maximum sum of any contiguous subarray of size ‘k’.

Example 1:
Input: [2, 1, 5, 1, 3, 2], k=3
Output: 9
Explanation: Subarray with maximum sum is [5, 1, 3].

Example 2:
Input: [2, 3, 4, 1, 5], k=2
Output: 7
Explanation: Subarray with maximum sum is [3, 4].
 */
public class MaxSubarrayOfSizeK {

    public static void main(String[] args) {
        int[] arra = {2, 1, 5, 1, 3, 2};

        int subarrayMax = findMaxSumSubArray2(3, arra);
        System.out.println(subarrayMax);
      //  int subarrayMin = findMinSumSubArray(3, arra);
       // System.out.println(subarrayMin);
    }

    // O(N)
    public static int findMaxSumSubArray(int k, int[] arr) {
        int res = Integer.MIN_VALUE;
        int currentSum = 0;
        int windowStart = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {

            currentSum += arr[windowEnd]; // aggregate sum

            if (windowEnd >= k - 1) { // when hit the K block ( k-1 - we are zero based )
                res = Math.max(res, currentSum); // find max
                currentSum = currentSum - arr[windowStart]; // extract what's left behind the moving window ( extract starting from beginning as we  go )
                windowStart++; // move start of sliding window
            }
        }
        return res;
    }

    public static int findMinSumSubArray(int k, int[] arr) {
        int res = Integer.MAX_VALUE;
        int windowStart = 0;
        int currentSum = 0;

        for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            currentSum += arr[windowEnd];//aggregate sum

            if (windowEnd >= k - 1) {//if hit the block of K, ( -1 since we are zero based )
                res = Math.min(res, currentSum);//find min
                currentSum = currentSum - arr[windowStart]; //extract from window start since we move our window onward
                windowStart++;//move window start
            }
        }
        return res;
    }

    // O(N*K)
    public static int findMaxSumSubArrayBruteForce(int k, int[] arr) {

        int subarrayMax = 0;
        int currentSum = 0;

        for (int i = 0; i <= arr.length - k; i++) {
            currentSum = 0; // reset aggregated for each block of K
            for (int j = i; j < i + k; j++) {
                currentSum += arr[j]; // aggregate sum of K
            }
            subarrayMax = Math.max(subarrayMax, currentSum); // find max
        }

        return subarrayMax;
    }


    public static int findMaxSumSubArray2(int k, int[] arr) {
        int subarrayMaxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        int windowStart = 0;
        int windowEnd = 0;

        for (int i = 0; i <= arr.length - k; i++) {
            windowStart = i;
            windowEnd = i + 1;
            currentSum += arr[windowStart];
            while (windowEnd - windowStart + 1  == k) {
                currentSum +=  arr[windowEnd];//2+1=3
                windowEnd++;
            }
            subarrayMaxSum = Math.max(subarrayMaxSum, currentSum);
            currentSum = 0;
            //currentSum -= arr[windowStart];
        }
        return subarrayMaxSum;

    }
}
