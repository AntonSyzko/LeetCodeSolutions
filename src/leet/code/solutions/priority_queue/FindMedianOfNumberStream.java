package leet.code.solutions.priority_queue;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class FindMedianOfNumberStream {

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 4, 5, 6);
        printRunningMedian(nums);
    }


    private static void printRunningMedian(List<Integer> nums) {
        // minHeap stores the larger half of numbers
        PriorityQueue<Integer> largerHalf = new PriorityQueue<>();

        // maxHeap stores the smaller half of numbers
        PriorityQueue<Integer> smallerHalf = new PriorityQueue<>(Collections.reverseOrder());

        for (Integer num : nums) {
            // Add the current number to the appropriate heap
            if (smallerHalf.isEmpty() || num <= smallerHalf.peek()) {
                smallerHalf.add(num);
            } else {
                largerHalf.add(num);
            }

            // Balance the heaps
            if (smallerHalf.size() > largerHalf.size() + 1) {
                largerHalf.add(smallerHalf.poll());
            } else if (largerHalf.size() > smallerHalf.size()) {
                smallerHalf.add(largerHalf.poll());
            }

            // Calculate and print current median
            double median;

            if (smallerHalf.size() > largerHalf.size()) {
                median = smallerHalf.peek();
            } else {
                median = (smallerHalf.peek() + largerHalf.peek()) / 2.0;
            }

            System.out.println(median);
        }
    }
}

