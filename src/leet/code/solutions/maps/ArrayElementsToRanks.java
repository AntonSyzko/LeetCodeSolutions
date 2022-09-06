package leet.code.solutions.maps;

import java.util.*;

/*
https://www.techiedelight.com/replace-each-element-corresponding-rank-array/

Given an array of distinct integers, replace each array element by its corresponding rank in the array.
The minimum array element has the rank 1; the second minimum element has a rank of 2, and so on… For example,

Input:  { 10, 8, 15, 12, 6, 20, 1 }
Output: { 4, 3, 6, 5, 2, 7, 1 }

 */
public class ArrayElementsToRanks {
    public static void main(String[] args) {
        int[] input = {10, 8, 15, 12, 6, 20, 1};
        System.out.println(Arrays.toString(input));

        // transform the array
        transformArrayToRanksMinHeap(input);

        // print the transformed array
        System.out.println(Arrays.toString(input));
    }

    //O(n.log(n))
    private static void transformArrayToRanks(int[] nums) {
        Map<Integer, Integer> sortedNumsMap = new TreeMap<>();//tree mpa - sorted !!!

        // store (element, index) pair in `TreeMap`
        for (int i = 0; i < nums.length; i++) {
            sortedNumsMap.put(nums[i], i);
        }
        // keys are stored in sorted order in `TreeMap`
        int rank = 1; //start with rank of 1 by default

        // iterate through the map and replace each element with its rank
        for (int index : sortedNumsMap.values()) {
            nums[index] = rank++;
        }
    }

    // Function to replace each array element by its rank in the array
    private static void transformArrayToRanksHeap(int[] nums) {
        // create a max-heap of `Pair` using the `PriorityQueue` class
        PriorityQueue<Pair<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> b.first - a.first);

        // push all input elements with their corresponding index in the priority queue
        for (int i = 0; i < nums.length; i++) {
            maxHeap.add( Pair.of(nums[i], i));//pair of Number to it Index
        }

        int rank = nums.length;//MAX Heap - so start from backwards

        // run until max-heap is empty
        while (!maxHeap.isEmpty()) {
            // take the next maximum element from the heap and replace its value
            // in the input array with its corresponding rank
            nums[maxHeap.poll().second] = rank;

            // decrement rank for the next maximum element
            rank--;
        }
    }

    private static void transformArrayToRanksMinHeap(int[] nums) {
        PriorityQueue<Pair<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.first));

        for (int i = 0; i < nums.length ; i++) {
            minHeap.add( Pair.of(nums[i], i));//pair of Number to it Index
        }

        int rank = 1;//[4, 3, 6, 5, 2, 7, 1]

        while (!minHeap.isEmpty()) {
            nums[minHeap.poll().second] = rank;
            rank++;
        }

    }



        // A Pair class
    static class Pair<U, V> {
        public final U first;       // first field of a pair
        public final V second;      // second field of a pair

        // Constructs a new Pair with specified values
        private Pair(U first, V second) {
            this.first = first;
            this.second = second;
        }

        // Factory method for creating a Typed Pair immutable instance
        public static <U, V> Pair<U, V> of(U a, V b) {
            // calls private constructor
            return new Pair<>(a, b);
        }
    }
}
