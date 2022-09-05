package leet.code.solutions.priority_queue;

import java.util.*;

public class MergeSortedArrays {
    public static void main(String[] args) {
        List<Integer> list1 = List.of(0,2,4);
        List<Integer> list2 = List.of(1,3,4);
        List<List<Integer>> lists = List.of(list1, list2);
        List<Integer> merges = mergeSortedArrays(lists);
        System.out.println(merges);
    }

    private static List<Integer> mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Iterator<Integer>> iters = new ArrayList<>(sortedArrays.size());

        for (List<Integer> array : sortedArrays) {
            iters.add(array.iterator());
        }

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(sortedArrays.size(), Comparator.comparingInt(o -> o.value));

        for (int i = 0; i < iters.size(); i++) {
            if (iters.get(i).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(i).next(), i));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            ArrayEntry headEntryFromHeap = minHeap.poll();
            result.add(headEntryFromHeap.value);
            if (iters.get(headEntryFromHeap.arrayld).hasNext()) {
                minHeap.add(new ArrayEntry(iters.get(headEntryFromHeap.arrayld).next(), headEntryFromHeap.arrayld));
            }
        }

        return result;
    }


    private static class ArrayEntry {
        public Integer value;
        public Integer arrayld;

        public ArrayEntry(Integer value, Integer arrayld) {
            this.value = value;
            this.arrayld = arrayld;
        }
    }
}
