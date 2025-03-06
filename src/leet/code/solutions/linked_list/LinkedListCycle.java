package leet.code.solutions.linked_list;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/linked-list-cycle/
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer.
Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).

Example 2:
Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.

Example 3:
Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.

Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.

Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
       node4.next = head; // cycle here = pos = 0
       // node4.next = null; // no cycle

        boolean listHasCycle = hasCycle2(head);
        System.out.println(listHasCycle);

      //  ListNode<Integer> cycleStart = findCycleStart(head);
       // System.out.println(cycleStart.val);
    }


    public static boolean hasCycle2(ListNode head) {

        if( head == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if( slow == fast ){
                return true;
            }
        }
        return false;

    }

        //O(1) memory
    //O(n) runtime
    public static boolean hasCycle(ListNode head) {

        if (head == null) return false;//just pre-check

        ListNode slow = head;
        ListNode fast = head.next;//was head.next - but is it ???

        while (slow != fast) { // iterate while they are NOT same

            if (fast == null // if we reached the END and did not find cycle
                ||           //OR
                fast.next == null) { // if fast next is NULL - so last node did not show cycle either

                return false; // so no cycle -> false
            }

            slow = slow.next; // move pointers
            fast = fast.next.next;// two pointers ahead as we  go
        }

        return true;//since we have  iterated in while loop till NOT same - means here not same - true
    }

    public static ListNode<Integer> findCycleStart(ListNode<Integer> head) {
        //if (head == null) return null;//just pre-check

        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // there is as cycle
                // Tries to find the start of the cycle.
                slow = head;
                // Both pointers advance at the same time.
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;//cycle already found - so no problem advancing fast, we don't care anymore
                }
                return slow;// slow is the start of cycle.
            }
        }
        return null; // No cycle.
    }


    //O(n) memory
    //O(n) runtime
    public static boolean hasCycleUsingList(ListNode head) {

        if (head == null) {
            return false;
        }

        List<ListNode> previousNodes = new ArrayList<>();

        while (head != null) {

            if (previousNodes.contains(head.next)) {//cycle found 
                return true;
            }

            previousNodes.add(head);//collect nodes as we go

            head = head.next;//shift pointer
        }
        return false;
    }
}
