package leet.code.solutions.sliding_window;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
Given an array of integers a, and an integer k, find the maximum for each and every contiguous subarray of size k.

Example

Input: a[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, k = 3
Output: 3 3 4 5 5 5 6
Explanation:
Maximum of subarray {1, 2, 3} is 3
Maximum of subarray {2, 3, 1} is 3
Maximum of subarray {3, 1, 4} is 4
Maximum of subarray {1, 4, 5} is 5
Maximum of subarray {4, 5, 2} is 5
Maximum of subarray {5, 2, 3} is 5
Maximum of subarray {2, 3, 6} is 6
 */
public class MaximumOfAllSubarraysOfSizeK {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 4, 5, 2, 3, 6};
        //int[] nums = {4,3,2,1};
        int[] max =  maxOfAllSubarrayPriorityQueue(nums,3);
        System.out.println(Arrays.toString(max));
    }

    private static int[] maxSubArrayMy( int[] nums, int k) {

        int resLen = nums.length - k + 1;
        int[] res = new int[resLen];

        int windowStart = 0;
        int currWindowMax = Integer.MIN_VALUE;
        int resIndex = 0;

        for (int windowEnd = windowStart; windowEnd < nums.length; windowEnd++) {
            currWindowMax = Math.max(currWindowMax, nums[windowEnd]);
            if( windowEnd - windowStart + 1 >= k ){
                res[resIndex++] = currWindowMax;
                currWindowMax = Integer.MIN_VALUE;// ??? is needed ?
            }
        }
        return res;
    }

    private static int[] maxOfAllSubarrayPriorityQueue(int[] nums, int k) {
        int resLen = nums.length - k + 1;
        int[] res = new int[resLen];
        int resIndex = 0;

        int windowStart = 0;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());//max heap

        for (int windowEnd = windowStart; windowEnd < nums.length; windowEnd++) {
              priorityQueue.add(nums[windowEnd]);

              if( windowEnd - windowStart + 1 >= k ){//hit window

                  int maxElementFromQueue = priorityQueue.peek();//get max in Q and store in res
                  res[resIndex++] = maxElementFromQueue;

                  if(nums[windowStart] == maxElementFromQueue){ // Discard a[windowStart] since we are sliding the window now. So it is going out of the window.
                      priorityQueue.remove();//remove one on top of max heap
                  }
                  windowStart++;
              }
        }
        return res;
    }
    }
