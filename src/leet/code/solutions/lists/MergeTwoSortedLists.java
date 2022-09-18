package leet.code.solutions.lists;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/merge-two-sorted-lists/

You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
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

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode list1_1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        System.out.println(list1_1);

        ListNode list2_1 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(list2_1);

        ListNode merged = mergeTwoLists(list1_1, list2_1);
        System.out.println(merged);
    }

        public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);//to store aggregated result list
        ListNode head = dummy;//pointer to retrun

        while (list1 != null && list2 != null) { //while both have smth to compare BOTH AND && !!! ( remainders will be appended after )

            if(list1.val < list2.val){
                dummy.next = list1; //set res node
                list1 = list1.next;//processed - done current - move to next in list1
            } else {
                dummy.next = list2;//set res node
                list2 = list2.next;//processed - done current - move to next in list2
            }

            dummy = dummy.next;//processed res - done current res - move to next
        }

        //LEFTOVERS
        if ( list1 != null){ //left over in LIST 1 ?
            dummy.next = list1; //just append the entire leftover
        } else { // otherwise left over in LIST 2 ?
             dummy.next = list2;//just append the entire leftover
        }

        return head.next;//return pointer  to dummy
    }
}
