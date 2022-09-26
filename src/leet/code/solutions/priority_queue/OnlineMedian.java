package leet.code.solutions.priority_queue;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class OnlineMedian {

    public static void main(String[] args) {
     List<Integer> nums = List.of(1,2,3,4,5,6);
     onlineMedian(nums.listIterator());
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static void onlineMedian(Iterator<Integer> sequence) {
        // minHeap stores the larger half seen so far.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        // maxHeap stores the smaller half seen so far.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY, Collections.reverseOrder());

        while (sequence.hasNext()) {
            int currentElement = sequence.next();

            if (minHeap.isEmpty()) {
                // This is the very first element.
                minHeap.add(currentElement);
            } else {
                if (currentElement >= minHeap.peek()) {
                    minHeap.add(currentElement);
                } else {
                    maxHeap.add(currentElement);
                }
            }

            // Ensure minHeap and maxHeap have equal number of elements if
            // an even number of elements is read; otherwise, minHeap must have one more element than maxHeap.
            if (minHeap.size() > maxHeap.size() + 1) {
                maxHeap.add(minHeap.remove());
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.remove());
            }

            System.out.println(minHeap.size() == maxHeap.size() ? 0.5 * (minHeap.peek() + maxHeap.peek()) : minHeap.peek());
        }
    }
}

