package leet.code.solutions.priority_queue;

import java.util.*;

public class TopKInStream {

    public static void main(String[] args) {
        List<String > words = List.of("AAAAA","a","bbb","cccc","tt");
        List<String> topLongest = topK(3, words.listIterator());
        System.out.println(topLongest);

        List<String> topShortest = bottomK(3, words.listIterator());
        System.out.println(topShortest);
    }

    private static List<String> topK(int k, Iterator<String> iter) {
        //k - is initial capacity here in constructor
        PriorityQueue<String> minHeap = new PriorityQueue<>(k, Comparator.comparingInt(String::length));

        while (iter.hasNext()) {
            minHeap.add(iter.next());
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return new ArrayList<>(minHeap);
    }

    private static List<String> bottomK(int k, Iterator<String> iter) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>(k, (str1, str2) -> Integer.compare(str2.length(), str1.length()));

        while (iter.hasNext()) {
            maxHeap.add(iter.next());
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return new ArrayList<>(maxHeap);
    }
}
