package leet.code.solutions.linked_list;

/*
https://leetcode.com/problems/partition-list/description/

Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Input: head = [1,4,3,2,5,2], x = 3
Output: [1,2,2,4,3,5]
Example 2:

Input: head = [2,1], x = 2
Output: [1,2]


Constraints:

The number of nodes in the list is in the range [0, 200].
-100 <= Node.val <= 100
-200 <= x <= 200
 */
public class PartitionList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);

        int pivot = 3;

        System.out.println(head);

        ListNode partitioned =  partition(head, pivot);
        System.out.println(partitioned);
    }

    private static ListNode partition(ListNode head, int pivot) {
        ListNode beforeHeadHolder = new ListNode(0);
        ListNode beforeList = beforeHeadHolder;

        ListNode afterHeadHolder = new ListNode(0);
        ListNode afterList = afterHeadHolder;

        while (head != null) {

            if (head.val < pivot) {//< PIVOT is CRUX
                 beforeList.next = head;
                 beforeList = beforeList.next;//move before
            }else{
                afterList.next = head;
                afterList = afterList.next;//move after
            }
            head = head.next;//move head
        }

        afterList.next = null;//assign tail at the very end as NULL
        beforeList.next = afterHeadHolder.next;// next of before ( we stopped at it's last) is next of ofter's holder

        return beforeHeadHolder.next;// holder's next poits to start aofactual before
    }



     static  class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }

         @Override
         public String toString() {
             return "ListNode{" +
                     "val=" + val +
                     ", next=" + next +
                     '}';
         }
     }
    }
