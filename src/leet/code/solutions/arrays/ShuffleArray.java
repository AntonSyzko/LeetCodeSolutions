package leet.code.solutions.arrays;

import java.util.Arrays;

/*
https://leetcode.com/problems/shuffle-the-array/

Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

Return the array in the form [x1,y1,x2,y2,...,xn,yn].

Example 1:
Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7]
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].

Example 2:
Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]

Example 3:
Input: nums = [1,1,2,2], n = 2
Output: [1,2,1,2]

Constraints:
1 <= n <= 500
nums.length == 2n
1 <= nums[i] <= 10^3
 */
public class ShuffleArray {

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 2, 4, 6};
        int pivot = 3;
        int[] shuffled = shuffleMy(nums, pivot);

        System.out.println(Arrays.toString(nums));

        System.out.println(Arrays.toString(shuffled));
    }

    private static int[] shuffleMy(int[] nums, int n) {
        int[] shuffledRes = new int[nums.length];

        int indexInShuffled = 0;

        for (int i = 0; i < n ; i++) {//iterate up to N !!!

            shuffledRes[indexInShuffled++] = nums[i];
            shuffledRes[indexInShuffled++] = nums[i + n];
        }

        return shuffledRes;

    }


    private static int[] shuffle(int[] nums, int n) {
        int[] res = new int[nums.length];

        int leftSubsetPointer = 0;
        int rightSubsetPointer = n;//n is where second set starts ( pivot division point )

        for (int i = 1; i < nums.length; i = i + 2) {//jum of 2 ( i +=2 )
            res[i - 1] = nums[leftSubsetPointer++];
            res[i] = nums[rightSubsetPointer++];
        }
        return res;
    }

    private static int[] shuffle2(int[] nums, int n) {
        int[] res = new int[nums.length];

        int leftSubsetPointer = 0;
        int rightSubsetPointer = n;//n is where second set starts ( pivot division point )

        for (int i = 0; i < nums.length-1; i = i + 2) {//jum of 2 ( i +=2 )
            res[i] = nums[leftSubsetPointer++];
            res[i+1] = nums[rightSubsetPointer++];
        }
        return res;
    }
}
