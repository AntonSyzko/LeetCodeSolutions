package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
https://www.techiedelight.com/find-duplicates-within-given-range-array/

Given an array and a positive number k, check whether the array contains any duplicate elements within the range k.
If k is more than the array’s size, the solution should check for duplicates in the complete array.

Input:
nums[] = { 5, 6, 8, 2, 4, 6, 9 }
k = 4
Output: Duplicates found
(element 6 repeats at distance 4 which is <= k)

Input:
nums[] = { 5, 6, 8, 2, 4, 6, 9 }
k = 2
Output: No duplicates were found
(element 6 repeats at distance 4 which is > k)

Input:
nums[] = { 1, 2, 3, 2, 1 }
k = 7
Output: Duplicates found
(element 1 and 2 repeats at distance 4 and 2, respectively which are both <= k)
 */
public class FindDuplicatesWithinRange {
    public static void main(String[] args) {
        int[] nums = { 5, 6, 8, 2, 4, 6, 9 };
        int k = 4;

        if (hasDuplicateWithinRangeOpt(nums, k)) {
            System.out.println("Duplicates found");
        }
        else {
            System.out.println("No duplicates were found");
        }
    }

    // O(n )
    public static boolean hasDuplicateWithinRange1(int[] nums, int k) {
        // stores (element, index) pairs as (key, value) pairs
        Map<Integer, Integer> map = new HashMap<>();
        // traverse the array
        for (int i = 0; i < nums.length; i++) {
            // if the current element already exists in the map
            if (map.containsKey(nums[i])) {
                // return true if the current element repeats within range of `k`
                if (i - map.get(nums[i]) <= k) {
                    return true;
                }
            }
            // store elements along with their indices
            map.put(nums[i], i);
        }
        // we reach here when no element repeats within range `k`
        return false;
    }

    //sliding window
    public static boolean hasDuplicateWithinRangeOpt(int[] nums, int range) {
        // create an empty set to store elements within range `k`
        Set<Integer> window = new HashSet<>();

        // traverse the array
        for (int i = 0; i < nums.length; i++) {
            // if the current element already exists in the window, then it repeats within range of `k`
            if (window.contains(nums[i])) {
                return true;
            }

            // add the current element to the window
            window.add(nums[i]);

            // remove the element at k'th range from the current element
            if (i >= range) {
                window.remove(nums[i - range]);
            }
        }
        // we reach here when no element repeats within range `k`
        return false;
    }

    }
