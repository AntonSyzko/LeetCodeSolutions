package leet.code.solutions.linked_list;

/*
https://leetcode.com/problems/swap-nodes-in-pairs/

Given a linked list, swap every two adjacent nodes and return its head.
You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)

Example 1:
Input: head = [1,2,3,4]
Output: [2,1,4,3]

Example 2:
Input: head = []
Output: []

Example 3:
Input: head = [1]
Output: [1]

Constraints:
The number of nodes in the list is in the range [0, 100].
0 <= Node.val <= 100
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
       ListNode<Integer> node1 = new ListNode<>(1);
       ListNode<Integer> node2 = new ListNode<>(2);
       ListNode<Integer> node3 = new ListNode<>(3);
       ListNode<Integer> node4 = new ListNode<>(4);
       node1.next=node2;
       node2.next = node3;
       node3.next = node4;
       System.out.println(node1);

       ListNode<Integer> swappedAdjacent = swapPairsIter(node1);
        System.out.println(swappedAdjacent);
    }

    private static ListNode<Integer> swapPairsIter(ListNode<Integer> head) {
        if(head==null || head.next==null){
            return head;
        }

        ListNode<Integer> temp = new ListNode<>(0);
        temp.next = head;
        ListNode<Integer> curr = temp;


        while (curr.next != null && curr.next.next != null) {
                //extract for future swap
                ListNode firstNode = curr.next;
                ListNode secondNode = curr.next.next;

                firstNode.next = secondNode.next;//just store for next cycle reassignment

                //actual swap
                curr.next = secondNode;
                curr.next.next = firstNode;//mind line 56 we have stored next to this

                curr = curr.next.next;//to proceed in WHILE jump in TWO as we swap pairs
        }

        return temp.next;
    }


        private static ListNode<Integer> swapPairs(ListNode<Integer> head) {
            if(head == null || head.next == null){//recursion base case
                return head;
            }

            ListNode<Integer> next = head.next;
            head.next = swapPairs(head.next.next);//same procedure for next pair
            next.next = head;

            return next;
    }
}
