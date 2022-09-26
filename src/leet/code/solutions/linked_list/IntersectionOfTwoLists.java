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
        ListNode list1 = new ListNode(4, new ListNode(1, new ListNode(8, new ListNode(4, new ListNode(5)))));
        ListNode list2 = new ListNode(5, new ListNode(6, new ListNode(1, new ListNode(8, new ListNode(4, new ListNode(5))))));

        ListNode intersected = getIntersectionNode(list1, list2);
        System.out.println(intersected);
    }

    //Runtime O(m + n)
    //Space O(n)
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        Set<ListNode> visited = new HashSet<>();

        while (headA != null) {
            visited.add(headA);//add all headA to set
            headA = headA.next;
        }
        
        while (headB != null){
            if(visited.contains(headB)){//if in set
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
