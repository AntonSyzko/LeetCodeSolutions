package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Given an unsorted integer array, print all pairs with a given difference k in it.

Input:
arr = [1, 5, 2, 2, 2, 5, 5, 4]
k = 3

Output:
(2, 5) and (1, 4)
 */
public class PairsWithDifferenceK {
    public static void main(String[] args) {
        int[] A = { 1, 5, 2, 2, 2, 5, 5, 4};
        int diff = 3;

        findPairNoDuplicates(A, diff);
    }

    // Function to find a pair with the given difference in the array.
    // This method does not handle duplicates in the array
    public static void findPair(int[] nums, int diff) {
        // array is unsorted
        // take an empty set
        Set<Integer> set = new HashSet<>();

        // do for every array element
        for (int each: nums) {
            // check if pair with the given difference `(each, each-diff)` exists
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


    // Function to find a pair with the given difference in the array.
    // This method handles duplicates in the array
    public static void findPairNoDuplicates(int[] A, int diff) {
        // sort array in ascending order
        Arrays.sort(A);

        // take an empty set
        Set<Integer> set = new HashSet<>();

        // do for every array element
        for (int i = 0; i < A.length; i++) {
            // to avoid printing duplicates (skip adjacent duplicates)
            while (i + 1 < A.length && A[i] == A[i+1]) {
                i++;
            }

            // check if pair with the given difference `(A[i], A[i]-diff)` exists
            if (set.contains(A[i] - diff)) {
                System.out.println("(" + A[i] + ", " + (A[i] - diff) + ")");
            }

            // check if pair with the given difference `(A[i]+diff, A[i])` exists
            if (set.contains(A[i] + diff)) {
                System.out.println("(" + (A[i] + diff) + ", " + A[i] + ")");
            }

            // insert the current element into the set
            set.add(A[i]);
        }
    }
}
