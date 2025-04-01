package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/remove-nth-node-from-end-of-list/

Given the head of a linked list, remove the nth node from the end of the list and return its head.

Example 1:
Input: head = [1,2,3,4,5], n = 2
Output: [1,2,3,5]

Example 2:
Input: head = [1], n = 1
Output: []

Example 3:
Input: head = [1,2], n = 1
Output: [1]

Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */
public class RemoveNodeFromEndOfLinkedList {

    public static void main(String[] args) {
      ListNode head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);

        System.out.println(head);

      int positionToRemove = 2;

        System.out.println("\r\n\t AFTER REMOVAL");

      ListNode afterRemoval = removeNthFromEnd(head, positionToRemove);
        System.out.println(afterRemoval);


    }

    private static ListNode removeNthFromEnd(ListNode head, int positionToRemove) {
        // Create a dummy node to handle edge cases
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Fast and slow pointers
        ListNode fast = dummy;
        ListNode slow = dummy;

        int fstStart = 0;

        // Advance fast pointer n steps ahead
        while (fstStart <= positionToRemove) {
            fast = fast.next;
            fstStart++;//move fast start
        }
//fast start index  is now  N steps ahead of slow since we have started from N shifted position

        // Move both pointers until fast reaches the end
        while (fast.next != null) {

            slow = slow.next;
            fast = fast.next;//fast is ALWAYS N steps ahead of slow since we have started from N shifted position

        }

        //slow stopped at the  node we have to REMOVE
        // Skip the nth node from the end
        slow.next = slow.next.next;

        return dummy.next;
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
