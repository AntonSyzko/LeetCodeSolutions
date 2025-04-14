package leet.code.solutions.linked_list;

/*
https://leetcode.com/problems/sort-list/

Given the head of a linked list, return the list after sorting it in ascending order.

Example 1:
Input: head = [4,2,1,3]
Output: [1,2,3,4]

Example 2:
Input: head = [-1,5,3,4,0]
Output: [-1,0,3,4,5]

Example 3:
Input: head = []
Output: []

Constraints:

The number of nodes in the list is in the range [0, 5 * 104].
-105 <= Node.val <= 105


Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 */
public class SortLinkedList {

    public static void main(String[] args) {
         ListNode head = new ListNode(4);
         head.next = new ListNode(2);
         head.next.next = new ListNode(1);
         head.next.next.next = new ListNode(3);

        System.out.println(head);

        ListNode sorted = sortList(head);

        System.out.println(sorted);
    }

    private static ListNode sortList(ListNode head) {
        // Base case: empty list or single node list
        if (head == null || head.next == null) {
            return head;
        }

        // Find the middle of the linked list
        ListNode middle = findMiddle(head);
        ListNode secondHalf = middle.next;

        // Break the linked list into two halves
        middle.next = null;//this just chops off all the nodes after middle, making the middle list being halved

        //divide and conquer - will end up sorting node one by one
        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(secondHalf);

        // Merge the sorted halves
        return merge(left, right);
    }

    private static ListNode merge(ListNode left, ListNode right) {
        // Create a dummy node to simplify merging
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Compare nodes from both lists and merge in ascending order
        while (left != null && right != null) {

            if (left.val <= right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }

            current = current.next;//move forward
        }

        // Attach any remaining nodes
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }

        return dummy.next;
    }

    private static ListNode findMiddle(ListNode head) {
        if (head == null) return null;

        ListNode slow = head;
        ListNode fast = head;

        // Fast pointer moves twice as fast as slow pointer
        // When fast reaches the end, slow will be at the middle
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
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
