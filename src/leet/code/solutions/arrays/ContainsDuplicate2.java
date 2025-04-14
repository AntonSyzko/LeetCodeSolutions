package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/contains-duplicate-ii/

Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.



Example 1:

Input: nums = [1,2,3,1], k = 3
Output: true
Example 2:

Input: nums = [1,0,1,1], k = 1
Output: true
Example 3:

Input: nums = [1,2,3,1,2,3], k = 2
Output: false


Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
0 <= k <= 105
 */
public class ContainsDuplicate2 {

    public static void main(String[] args) {

        int[] nums = {1,2,3,1};
        System.out.println(containsNearbyDuplicate(nums, 3));

        int[] nums2 = {1,0,1,1};
        System.out.println(containsNearbyDuplicate(nums2, 1));

        int[] nums3 = {1,2,3,1,2,3};
        System.out.println(containsNearbyDuplicate(nums3, 2));

        int[] nums4 = {99,99};
        System.out.println(containsNearbyDuplicate(nums4, 2));
    }


    private static boolean containsNearbyDuplicateMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int curr = nums[i];
            if (map.containsKey(curr) && i - map.get(curr) <= k) {
                return true;
            }
            map.put(curr, i);
        }

        return false;
    }


        // O(N) * ( O(k)
    //space O(1)
    private static boolean containsNearbyDuplicate(int[] nums, int k) {

        if(nums == null || nums.length == 0 || k == 0){
            return false;
        }

        for (int i = 0; i  < nums.length ; i++) {
            int first = nums[i];

            int secondOptIndex = 1;

            while(secondOptIndex <= k && (i + secondOptIndex) < nums.length){

                if(first == nums[i + secondOptIndex]){
                    return true;
                }

                secondOptIndex++;

            }
        }

        return false;
    }
}
