package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
1207

https://leetcode.com/problems/unique-number-of-occurrences/description/

Given an array of integers arr, return true if the number of occurrences of each value in the array is unique or false otherwise.

Example 1:

Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
Example 2:

Input: arr = [1,2]
Output: false
Example 3:

Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true

Constraints:

1 <= arr.length <= 1000
-1000 <= arr[i] <= 1000
 */
public class UniqueOccurrences {

    public static void main(String[] args) {
        int[] nums = {1,2,2,1,1,3,3};
        boolean occ = uniqueOccurrences2(nums);
        System.out.println(occ);
    }

    private  static boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : arr) {

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();

        for(Map.Entry<Integer,Integer> entry : map.entrySet()){

            if(!set.add(entry.getValue())){
                return false;//exit fast if duplicate occurred
            }
        }

        return true;
    }

    private  static boolean uniqueOccurrences2(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : arr) {

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Integer> set = new HashSet<>(map.values());

        if(set.size() != map.size()){
            return false;
        }

        return true;
    }
    }