package leet.code.solutions.linked_list;

/*
https://leetcode.com/problems/reorder-list/description/

You are given the head of a singly linked-list. The list can be represented as:

L0 → L1 → … → Ln - 1 → Ln
Reorder the list to be on the following form:

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
You may not modify the values in the list's nodes. Only nodes themselves may be changed.



Example 1:


Input: head = [1,2,3,4]
Output: [1,4,2,3]
Example 2:


Input: head = [1,2,3,4,5]
Output: [1,5,2,4,3]


Constraints:

The number of nodes in the list is in the range [1, 5 * 104].
1 <= Node.val <= 1000
 */
public class ReorderList {


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(3);
        ListNode head4 = new ListNode(4);
        ListNode head5 = new ListNode(5);
        ListNode head6 = new ListNode(6);
        head.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = null;

        System.out.println(head);

        System.out.println("\r\n\t reorder ");

        reorderList(head);

        System.out.println(head);
    }

    //time overall O(n)
    //space O(n/2) = O(n)
    private static void reorderList(ListNode head) {
        /// Step 1: Use two-pointers to find the middle of the linked list

        ListNode fastPointer = head;
        ListNode slowPointer = head;

        // fastPointer moves twice as fast as the slowPointer
        while (fastPointer.next != null && fastPointer.next.next != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        /// Step 2: Split the list into two and reverse the second half

        // Now, slowPointer is at the middle of the list
        ListNode current = slowPointer.next; // This is the start of the second half
        slowPointer.next = null; // Split the list into two

        ListNode previous = null;
        // Reverse the second half of the list
        while (current != null) {
            ListNode nextHolder = current.next;
            current.next = previous;
            previous = current;
            current = nextHolder;
        }

        /// Step 3: Merge the two halves back together
        current = head; // Reset current to the start of the first half

        // Traverse the first and the reversed second half together
        while (previous != null) {//previous points at start of reversed second half
            // 'previous' traverses the reversed list
            ListNode temp = previous.next;
            // Link the current node of the first half to the current node of the reversed second half
            previous.next = current.next;
            // Link the current node of the reversed second half to the next node in the first half
            current.next = previous;

            // Move to the next node in the first half
            current = previous.next;
            // Proceed to the next node in the reversed second half
            previous = temp;
        }
    }
}
