package leet.code.solutions.linked_list;

import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/intersection-of-two-linked-lists/

Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
Output: Intersected at '8'
Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
 There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 */
public class IntersectionOfTwoLists {

    public static void main(String[] args) {
        // Create the shared portion of both lists
        ListNode shared = new ListNode(8, new ListNode(4, new ListNode(5)));

        // Create list1: 4->1->shared
        ListNode list1 = new ListNode(4, new ListNode(1, shared));

        // Create list2: 5->6->1->shared
        ListNode list2 = new ListNode(5, new ListNode(6, new ListNode(1, shared)));

        ListNode intersected = getIntersectionNode_Effective(list1, list2);

        System.out.println(intersected.val);
    }

    //Runtime O(m + n)
    //Space O(n)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set<ListNode> visitedInA = new HashSet<>();

        ListNode current = headA;

        while (current != null) {
            visitedInA.add(current);//add all headA to set
            current = current.next;
        }

        current = headB;
        while (current != null){
            if(visitedInA.contains(current)){//if in set
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /*
    This approach maintains O(m+n) time complexity
     O(1) space complexity, making it very efficient.
     */
    private static ListNode getIntersectionNode_Effective(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        // If the pointers are different, move them
        // When a pointer reaches the end, redirect it to the head of the other list
        while (pointerA != pointerB) {
            // Move pointer A
            if (pointerA == null) {
                pointerA = headB;
            } else {
                pointerA = pointerA.next;
            }

            // Move pointer B
            if (pointerB == null) {
                pointerB = headA;
            } else {
                pointerB = pointerB.next;
            }
        }

        // Either found the intersection point or both are null (no intersection)
        return pointerA;
    }


    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
