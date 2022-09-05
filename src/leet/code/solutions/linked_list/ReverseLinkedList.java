package leet.code.solutions.linked_list;

/*
    https://leetcode.com/problems/reverse-linked-list/
    Given the head of a singly linked list, reverse the list, and return the reversed list.

Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []

Constraints:

The number of nodes in the list is the range [0, 5000].
-5000 <= Node.val <= 5000


Follow up: A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        ListNode first = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println(first);
        ListNode reversed = reverseList(first);
        System.out.println(reversed);
    }

    // (HEAD) 1 -> 2 -> 3 ->  null
    // null <- 1 <- 2 <- 3 <- HEAD
    public static ListNode reverseList(ListNode head) {
        ListNode previous = null;

        while (head != null){
            ListNode next = head.next;//temp holder of next
            head.next = previous;
            previous = head;
            head = next;//move head
        }
        return previous;
    }
}
