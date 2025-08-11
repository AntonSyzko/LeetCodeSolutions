package leet.code.solutions.linked_list;

import java.util.ArrayDeque;
import java.util.Deque;

/*

https://neetcode.io/problems/reorder-linked-list?list=neetcode150

You are given the head of a singly linked-list.

The positions of a linked list of length = 7 for example, can intially be represented as:

[0, 1, 2, 3, 4, 5, 6]

Reorder the nodes of the linked list to be in the following order:

[0, 6, 1, 5, 2, 4, 3]

Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:

[0, n-1, 1, n-2, 2, n-3, ...]

You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.

Example 1:

Input: head = [2,4,6,8]

Output: [2,8,4,6]
Example 2:

Input: head = [2,4,6,8,10]

Output: [2,10,4,8,6]
Constraints:

1 <= Length of the list <= 1000.
1 <= Node.val <= 1000

 */
public class ReorderList2 {

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        ListNode head2 = new ListNode(4);
        ListNode head3 = new ListNode(6);
        ListNode head4 = new ListNode(8);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;

        System.out.println(head);

        reorderList(head);
        System.out.println(head);

    }

    private static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;

        Deque<ListNode> doubleSidedQue = new ArrayDeque<>();

        ListNode copy = head;//copy for not to move head to END as we gonna need it yet

        while (copy != null) {
            doubleSidedQue.offer(copy);//populate the que
            copy = copy.next;
        }

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        boolean takeFromFront = true;

        while (!doubleSidedQue.isEmpty() ) {

            ListNode fromQ;

            if(takeFromFront) {
                fromQ  = doubleSidedQue.removeFirst();
            }else{
                fromQ = doubleSidedQue.removeLast();
            }

            tail.next = fromQ;
            tail = fromQ;

            takeFromFront = !takeFromFront;
        }


        tail.next = null;//to avoid cycle causing stack overflow, cause in original list pointers are different we end in cycle
    }


    private static void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle
        ListNode slow = head,
                fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        ListNode second = reverse(slow.next);
        slow.next = null; // Cut the list

        // Step 3: Merge two halves
        ListNode first = head;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;

            first.next = second;
            second.next = temp1;

            first = temp1;
            second = temp2;
        }
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;

        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    private static class ListNode {
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
