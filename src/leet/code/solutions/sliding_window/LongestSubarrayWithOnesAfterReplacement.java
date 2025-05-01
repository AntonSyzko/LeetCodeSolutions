package leet.code.solutions.sliding_window;

/*

https://www.callicoder.com/longest-subarray-with-ones-after-replacement/

Given an array A of 0's and 1's, and a value K which indicates that you may change up to K values from 0 to 1. Return the length of the longest (contiguous) subarray that contains only 1’s.

Example 1:

Input: A = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0], K = 2
Output: 6

To obtain the longest contiguous subarray of 1s, you can flip the 0s at index 5 and 10 to 1s:
[1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1]
Example 2:

Input: A = [0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k=3
Output: 9
Explanation: Replace the 0s at index 6, 9, and 10 with 1s to get the longest contiguous subarray of 1s
 */
public class LongestSubarrayWithOnesAfterReplacement {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        System.out.println(findMaxConsecutiveOnes(nums, k));

        int[] nums2 = {0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1};
        int k2 = 3;
        System.out.println(findMaxConsecutiveOnes(nums2, k2));

    }

    private static int findMaxConsecutiveOnes(int[] nums, int k) {
       int res = Integer.MIN_VALUE;
       int zeroCount = 0;
       int windowStart = 0;

        for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
            if(nums[windowEnd] == 0){
                zeroCount++;
            }

            while(zeroCount > k){//if zeros count exceeded limit

                int numberAtWindowStart = nums[windowStart];

                if(numberAtWindowStart ==0){//at start of the window is zero
                    zeroCount--;//decrease count
                }
                windowStart++; //shrink window
            }

            res = Math.max(res, windowEnd - windowStart + 1);//update res
        }
        return res;
    }
    }
