package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://www.educative.io/courses/grokking-the-coding-interview/7D5NNZWQ8Wr

Given an array, find the average of all subarrays of ‘K’ contiguous elements in it.

Let’s understand this problem with a real input:

Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
Here, we are asked to find the average of all subarrays of ‘5’ contiguous elements in the given array. Let’s solve this:

For the first 5 numbers (subarray from index 0-4), the average is: (1+3+2+6-1)/5 => 2.2
(1+3+2+6−1)/5=>2.2
The average of next 5 numbers (subarray from index 1-5) is: (3+2+6-1+4)/5 => 2.8
(3+2+6−1+4)/5=>2.8
For the next 5 numbers (subarray from index 2-6), the average is: (2+6-1+4+1)/5 => 2.4
(2+6−1+4+1)/5=>2.4

…
Here is the final output containing the averages of all subarrays of size 5:

Output: [2.2, 2.8, 2.4, 3.6, 2.8]
 */
public class AverageOfSubarrayOfSizeK {

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 6, -1, 4, 1, 8, 2};
        double[] averages = findAveragesBruteForce(5, nums);
        System.out.println("Averages \r\n " + Arrays.toString(averages));

        averages = findAveragesSlidingWindow(5, nums);
        System.out.println("Averages sliding window \r\n " + Arrays.toString(averages));
    }

    // O( N + K ) N = number of elements in array, K
    public static double[] findAveragesBruteForce(int K, int[] arr) {

        double[] result = new double[arr.length - K + 1]; // size = (size of original) - K  +1

        outer:
        for (int i = 0; i <= arr.length - K; i++) { // traversing all nums, - K since next inner loop will take it

            double currentSum = 0; // aggregating sum

            inner:
            for (int j = i; j < i + K; j++) { // current block of K traversal , i is an offset (so i + K) , to take every next K from i of outer loop
                currentSum += arr[j]; // aggregate sum for current block of K
            }

            result[i] = currentSum / K; // current outer iteration average
        }
        return result;
    }

    // O(N)
    public static double[] findAveragesSlidingWindow(int K, int[] arr) {

        double[] averages = new double[arr.length - K + 1]; // res

        double windowSum = 0; // sliding window sum aggregator

        int windowStart = 0;

        for (int widowEnd = 0; widowEnd < arr.length; widowEnd++) { // traverse all
            windowSum += arr[widowEnd]; // add the next element

            // slide the window, we don't need to slide if we've not hit the required window size of 'K'
            if (widowEnd >= K - 1) { // K -1 cause we are zero based

                averages[windowStart] = windowSum / K; // calculate the average
                windowSum = windowSum - arr[windowStart]; // subtract the element going out
                windowStart++; // slide the window ahead
            }
        }
        return averages;
    }
}
