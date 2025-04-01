package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/merge-two-sorted-lists/

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the first two lists.

Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:

Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]

Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.

 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
           ListNode l1 = new ListNode(1);
           l1.next = new ListNode(2);
           l1.next.next = new ListNode(4);

           ListNode l2 = new ListNode(1);
           l2.next = new ListNode(3);
           l2.next.next = new ListNode(4);

           ListNode merged =  mergeTwoLists(l1, l2);
           System.out.println(merged);
    }

    /*
    Time Complexity: O(n + m) where n and m are the lengths of the two lists
    Space Complexity: O(1) as we're only using a constant amount of extra space
     */
    private static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy head node
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        // Traverse both lists and compare nodes
        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {

                current.next = list1;
                list1 = list1.next;

            } else {

                current.next = list2;
                list2 = list2.next;

            }

            current = current.next;

        }

        //left overs
        // Attach any remaining nodes (at most one list will have remaining nodes)
        current.next = (list1 != null) ? list1 : list2;//(only one of them can have remaining nodes

        return dummyHead.next;
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
