package leet.code.solutions.priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/*

https://leetcode.com/problems/merge-k-sorted-lists/description/


You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.



Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6


Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []


Constraints:

k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
       ListNode l1 = new ListNode(1);
       ListNode l2 = new ListNode(4);
       ListNode l3 = new ListNode(5);
       l1.next = l2;
       l2.next = l3;

       ListNode l4 = new ListNode(1);
       ListNode l5 = new ListNode(3);
       ListNode l6 = new ListNode(4);
       l4.next = l5;
       l5.next = l6;

        ListNode l7 = new ListNode(2);
        ListNode l8 = new ListNode(6);
        l7.next = l8;

        ListNode[] nodes = new ListNode[]{l1, l4, l7};

        ListNode merged  = mergeKLists(nodes);
        System.out.println(merged);
    }

    private static ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.val));


        //traverse all list nodex and add to min heap
        for (ListNode node : lists) {

            while (node != null) {//while there are list nodes available
                minHeap.add(node);
                node = node.next;
            }

        }

        ListNode dummy = new ListNode(0);
        ListNode head = dummy;

        // Build sorted list by polling from heap
        while (!minHeap.isEmpty()) {
            ListNode fromPQ = minHeap.poll();
            head.next = fromPQ;
            head = head.next;
        }

        return dummy.next;
    }

    private static ListNode mergeKLists2(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        Queue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (final ListNode list : lists){
            if (list != null){
                minHeap.offer(list);//adds only heads to Q
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();

            if (minNode.next != null){
                minHeap.offer(minNode.next);//adds next from curr to Q
            }

            curr.next = minNode;
            curr = curr.next;
        }

        return dummy.next;
    }
    
    
    private static   class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
