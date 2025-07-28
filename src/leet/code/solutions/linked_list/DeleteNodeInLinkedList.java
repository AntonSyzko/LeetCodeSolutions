package leet.code.solutions.linked_list;

/*
https://leetcode.com/problems/delete-node-in-a-linked-list/
Write a function to delete a node in a singly-linked list. You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.

It is guaranteed that the node to be deleted is not a tail node in the list.

Input: head = [4,5,1,9], node = 5
Output: [4,1,9]
Explanation: You are given the second node with value 5, the linked list should become 4 -> 1 -> 9 after calling your function.

Input: head = [4,5,1,9], node = 1
Output: [4,5,9]
Explanation: You are given the third node with value 1, the linked list should become 4 -> 5 -> 9 after calling your function.

Constraints:

The number of the nodes in the given list is in the range [2, 1000].
-1000 <= Node.val <= 1000
The value of each node in the list is unique.
The node to be deleted is in the list and is not a tail node
 */
public class DeleteNodeInLinkedList {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1,
                                     new ListNode(2,//to delete
                                           new ListNode(3,
                                                   new ListNode(4))));

        System.out.println(node1);

        System.out.println("delete node " + node1.next);

        deleteNode(node1.next);

        System.out.println(node1);
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val; //reset VAL of node to delete to VAL of next
        node.next = node.next.next; //reset the NEXT of node to delete to NEXT of NEXT to shift
        //so in fact we are not deleting current - we are overriding VAL of current with VAL of next
        //and reset next - in FACT the NEXT node to one passed in ARGS is what actually deleted, current node to delete is just taking it's characteristics
    }
}
