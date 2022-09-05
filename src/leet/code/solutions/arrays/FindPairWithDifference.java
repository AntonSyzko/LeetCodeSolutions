package leet.code.solutions.arrays;

import java.util.HashSet;
import java.util.Set;

/*
Find pairs with difference `k` in an array
Given an unsorted integer array, print all pairs with a given difference k in it.
For example,

Input:
arr = [1, 5, 2, 2, 2, 5, 5, 4]
k = 3
Output:
(2, 5) and (1, 4)
 */
public class FindPairWithDifference {
    public static void main(String[] args) {
   int[] nums = { 1, 5, 2, 2, 2, 5, 5, 4};
   findPair(nums, 3);
    }

    // Function to find a pair with the given difference in the array.
    // This method does not handle duplicates in the array
    public static void findPair(int[] A, int diff) {
        Set<Integer> set = new HashSet<>();

        for (int each : A){

            // check if pair with the given difference `(each, i-diff)` exists
            if (set.contains(each - diff)) {
                System.out.println("(" + each + ", " + (each - diff) + ")");
            }

            // check if pair with the given difference `(each + diff, each)` exists
            if (set.contains(each + diff)) {
                System.out.println("(" + (each + diff) + ", " + each + ")");
            }

            // insert the current element into the set
            set.add(each);
        }
    }
}
