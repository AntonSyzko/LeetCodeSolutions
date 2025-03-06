package leet.code.solutions.linked_list;

/*
[1,2,3,4,5]

mid is [3,4,5]


[1,2,3,4,5,6]

mid is [4,5,6]

if two mid nodes, retrun second
 */
public class MiddleOfLinkedList {
    public static void main(String[] args) {
   ListNode head = new ListNode(1);
   head.next = new ListNode(2);
   head.next.next = new ListNode(3);
   head.next.next.next = new ListNode(4);
    head.next.next.next.next = new ListNode(5);
    head.next.next.next.next.next = new ListNode(6);

        System.out.println(middleNode(head));
    }

    private static ListNode middleNode(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {//checking on fast since it will reach NULL ( end of list) just faster
            slow = slow.next;
            fast = fast.next.next;//moves twice faster
        }

        return slow;//slow now at the mid
    }
}
