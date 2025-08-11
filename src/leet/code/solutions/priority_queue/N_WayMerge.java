package leet.code.solutions.priority_queue;

import java.util.*;

public class N_WayMerge {

    public static void main(String[] args) {
        List<Integer> list1 = List.of(3,5,7);
        List<Integer> list2 = List.of(2,4,6);
        List<Integer> list3 = List.of(1,3,745);
        List<Integer> list4 = List.of(-1);

        List<List<Integer>> lists = List.of(list1,list2,list3, list4);

        List<Integer> merged = nWayMergeMoreEffective(lists);
        System.out.println(merged);
    }

    /*
           Time complexity: O(N log N) where N is total number of elements
           Space complexity: O(N) for the priority queue
     */

    private static List<Integer> nWayMerge(List<List<Integer>> lists){

        Queue<Integer> minHip = new PriorityQueue<>();

       for (List<Integer> list : lists) {
           minHip.addAll(list);//adds an entire lost altogether
       }

       List<Integer> res = new ArrayList<>();

       while(!minHip.isEmpty()){
           res.add(minHip.poll());
       }

       return res;
    }

    public static List<Integer> nWayMergeMoreEffective(List<List<Integer>> lists) {
        List<Integer> result = new ArrayList<>();

        // Min-heap storing arrays: [value, listIndex]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add first element from each non-empty list
        for (int i = 0; i < lists.size(); i++) {

            if (!lists.get(i).isEmpty()) {
                int value = lists.get(i).get(0);
                int index = i;
                minHeap.offer(new int[]{value,index});
            }

        }

        // Track current index for each list
        int[] indices = new int[lists.size()];

        // Process elements one by one
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int value = current[0];
            int listIndex = current[1];

            result.add(value);

            // Move to next element in the same list
            indices[listIndex]++;

            // Add next element from same list if available
            if (indices[listIndex] < lists.get(listIndex).size()) {
                int nextValue = lists.get(listIndex).get(indices[listIndex]);
                minHeap.offer(new int[]{nextValue, listIndex});
            }
        }

        return result;
    }
}