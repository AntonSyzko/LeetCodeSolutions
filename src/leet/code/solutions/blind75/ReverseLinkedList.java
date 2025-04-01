package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/reverse-linked-list/

Given the head of a singly linked list, reverse the list, and return the reversed list.


Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000

 */
public class  ReverseLinkedList {

    public static void main(String[] args) {
       ListNode head = new ListNode(1);
       head.next = new ListNode(2);
       head.next.next = new ListNode(3);
        System.out.println(head);

        System.out.println("\r\n\t ------------ reversed ----------------");
       ListNode revesed = reverseList(head);
       System.out.println(revesed);
    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;

        while( head != null){

           ListNode next = head.next;

           head.next = prev;//reverse next pointer in opposite direction

           prev = head;//move old previous

            head = next;//move next
        }

        return prev;//prev at the end contains last original node ( 3) with all pointers pointing in opposite direction ( 3  -> 2 -> 1 -> null )

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
