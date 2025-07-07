package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
1200

https://leetcode.com/problems/minimum-absolute-difference/description/

Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.

Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows

a, b are from arr
a < b
b - a equals to the minimum absolute difference of any two elements in arr

Example 1:

Input: arr = [4,2,1,3]
Output: [[1,2],[2,3],[3,4]]
Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
Example 2:

Input: arr = [1,3,6,10,15]
Output: [[1,3]]
Example 3:

Input: arr = [3,8,-10,23,19,-4,-14,27]
Output: [[-14,-10],[19,23],[23,27]]

Constraints:

2 <= arr.length <= 105
-106 <= arr[i] <= 106
 */
public class MinimumAbsoluteDifference {

    public static void main(String[] args) {
        int[] nums = {-25,-51,-29,-23,57,-18};
        List<List<Integer>> res = minimumAbsDifference(nums);
        System.out.println(res);
    }

    private static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        //1. SORT
        Arrays.sort(arr);//SOOORT ! cause min is looked up for ANY two nums

       // 2. find abs min across all elements
        for (int i = 1; i < arr.length; i++) {
            int currDiff = Math.abs(arr[i] - arr[i - 1]);
            minDiff = Math.min(currDiff, minDiff);
        }

        //3. include pairs of ABS diff == previously found MIN
        for (int i = 0; i < arr.length - 1 ; i++) {

            int currDiff = Math.abs(arr[i + 1]  - arr[i]);

            if(currDiff == minDiff){
                List<Integer> list = List.of(arr[i], arr[i + 1]);
                res.add(list);
            }
        }

        return res;
    }
}