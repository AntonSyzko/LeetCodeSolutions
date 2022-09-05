package leet.code.solutions.linked_list;

public class FindMiddleOfLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode sec = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        ListNode sixth = new ListNode(6);
        head.next = sec;
        sec.next = third;
        third.next = fourth;
        fourth.next = fifth;
        fifth.next = sixth;

        ListNode middleNode = findMiddleNode(head);
        System.out.println(middleNode);

    }

    private static ListNode findMiddleNode(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode fast = head;//both point to head at the beginning
        ListNode slow = head;

        //to avoid NPE we have to check fast != null and THEN ONLY fast.next != null
        while (fast != null && fast.next != null) { // while FAST ONLY has the room to iterate to NEXT
            fast = fast.next.next;//swich to it's next next till it reaches the end
            slow = slow.next;//swith to it's next
        }

        return slow;//slow stopped at middle , since it was twice slower and FAST has reached the end
    }
}
