package leet.code.solutions.priority_queue;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
https://www.techiedelight.com/sort-k-sorted-array/

Given a k–sorted array that is almost sorted such that each of the n elements may be misplaced by no more than k positions from the correct sorted order.
Find a space-and-time efficient algorithm to sort the array.

For example,

Input: arr = [1, 4, 5, 2, 3, 7, 8, 6, 10, 9]
k = 2
Output:[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 */
public class SortKSortedArray {

    public static void main(String[] args) {
            List<Integer> nums = Arrays.asList(1, 4, 5, 2, 3, 7, 8, 6, 10, 9);
            int k = 2;

            sortKSortedArray(nums, k);
            System.out.println(nums);
    }

    // The time complexity of this algorithm is O(n log k) where n is the number of elements and k is the maximum displacement.
    public static void sortKSortedArray(List<Integer> nums, int k) {
        // create an empty min-heap and insert the first `k+1` elements into it
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(nums.subList(0, k + 1));

        int indexOfNewlySortedArray = 0;

        // do for remaining elements in the array
        for (int i = k + 1; i < nums.size(); i++) {//we start from K + 1

            // poll the top element from the min-heap and assign them to the next available array index
            nums.set(indexOfNewlySortedArray++, minHeap.poll());

            // push the next array element into min-heap
            minHeap.add(nums.get(i));
        }

        // pop all remaining elements from the min-heap and assign them to the next available array index
        while (!minHeap.isEmpty()) {
            nums.set(indexOfNewlySortedArray++, minHeap.poll());
        }
    }
}
