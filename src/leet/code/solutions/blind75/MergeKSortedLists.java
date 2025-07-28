package leet.code.solutions.blind75;

import java.util.PriorityQueue;

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
    //[[1,4,5],[1,3,4],[2,6]]
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

        ListNode[] lists = new ListNode[]{l1,l4,l7};

        ListNode mergedAll = mergeKLists(lists);
        System.out.println(mergedAll);

    }

    private static ListNode mergeKLists(ListNode[] lists) {
         ListNode dymmyHead = new ListNode(0);
         ListNode newHead = dymmyHead;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((node1, node2) -> node1.val - node2.val);

        for (ListNode node : lists) {//iterating over all nodes in array

            while (node != null) {//iterating over nodes in one linked list of nodes
                minHeap.offer(node);
                node = node.next;
            }

        }

        //now minHip contains ALL nodes of ALL lists dorted from samllest to biggest
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            newHead.next = node;
            newHead = newHead.next;
        }

        return dymmyHead.next;

   }

    private  static class ListNode {
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
