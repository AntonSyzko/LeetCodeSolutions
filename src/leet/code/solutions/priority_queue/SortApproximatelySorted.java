package leet.code.solutions.priority_queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/*
K-sorted is when element is at K distance from it's sorted position
[3, -1, 2, 4, 5, 6 ]  here 3 is 2 positions from it's sorted place, so K = 2
 */
public class SortApproximatelySorted {
    public static void main(String[] args) {
        List<Integer> nums = List.of(3, -1, 2, 6, 4, 5, 8);
        sortApproximatelySorted(nums.listIterator(), 2);
        List<Integer> sorted = sortKSorted(nums, 2);
        System.out.println(sorted);
    }

    private static void sortApproximatelySorted(Iterator<Integer> sequence, int distanceToSortedPosition) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Adds the first K elements into minHeap. Stop if there are fewer than K elements .
        for (int i = 0; i < distanceToSortedPosition && sequence.hasNext(); i++) {
            minHeap.add(sequence.next());
        }

        // For every new element, add it to minHeap and extract the smallest.
        while (sequence.hasNext()) {
            minHeap.add(sequence.next());
            Integer smallestSoFar = minHeap.remove();
            System.out.println("smallest " + smallestSoFar);
        }

        // sequence is exhausted, iteratively extracts the remaining elements.
        while (!minHeap.isEmpty()) {
            Integer smallestLeft = minHeap.remove();
            System.out.println("left " + smallestLeft);
        }
    }

    private static List<Integer> sortKSorted(List<Integer> nums, int K) {
        List<Integer> res = new ArrayList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Adds the first K elements into minHeap. Stop if there are fewer than K elements in the initial list
        for (int i = 0; i < K && i < nums.size(); i++) {
            minHeap.add(nums.get(i));
        }

        //starts insertint to heap after K
        for (int i = K; i < nums.size(); i++) {
            minHeap.add(nums.get(i));
            Integer smallest = minHeap.remove();
            res.add(smallest);
        }

        while (!minHeap.isEmpty()) {
            Integer leftoversFromMinHeap = minHeap.remove();
            res.add(leftoversFromMinHeap);
        }
        return res;
    }

}
