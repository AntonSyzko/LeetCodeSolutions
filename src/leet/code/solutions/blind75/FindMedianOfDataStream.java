package leet.code.solutions.blind75;

import java.util.PriorityQueue;

/*
https://leetcode.com/problems/find-median-from-data-stream/

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.


Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0


Constraints:

-105 <= num <= 105
There will be at least one element in the data structure before calling findMedian.
At most 5 * 104 calls will be made to addNum and findMedian.


Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
 */
public class FindMedianOfDataStream {

    public static void main(String[] args) {

    }

    class MedianFinder {
        // Max heap for the lower half of the numbers
        private PriorityQueue<Integer> maxHeap;

        // Min heap for the upper half of the numbers
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {
            // Initialize both heaps
            // maxHeap will store the smaller half of the numbers
            maxHeap = new PriorityQueue<>((a, b) -> b - a);

            // minHeap will store the larger half of the numbers
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // First determine which heap the number should go to

            // If maxHeap is empty or num is less than maxHeap's top, add to maxHeap
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                // Otherwise, add to minHeap
                minHeap.offer(num);
            }

            // Balance the heaps:
            // The size difference between heaps should be at most 1

            // If maxHeap has more than one extra element, move one to minHeap
            if (maxHeap.size() > minHeap.size() + 1) {

                minHeap.offer(maxHeap.poll());

            } else if (minHeap.size() > maxHeap.size()) { // If minHeap has more elements than maxHeap, move one to maxHeap

                maxHeap.offer(minHeap.poll());

            }
        }

        public double findMedian() {
            // If maxHeap has more elements, it contains the median
            if (maxHeap.size() > minHeap.size()) {

                return maxHeap.peek();

            } else { // Otherwise, the median is the average of the top elements of both heaps

                return (maxHeap.peek() + minHeap.peek()) / 2.0;

            }
        }
    }
}
