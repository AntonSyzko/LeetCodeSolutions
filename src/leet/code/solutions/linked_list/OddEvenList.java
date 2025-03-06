package leet.code.solutions.linked_list;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
1 -> 2 -> 3 -> -> 4 -> 5 -> NULL

1 -> 3 -> 5 -> 2 -> 4 -> NULL

group add even nodes ( not by val of node but by index in list )


 */
public class OddEvenList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);


        System.out.println(oddEvenList(head));
    }

    private static ListNode oddEvenList(ListNode head) {

    if(head == null){
        return null;
    }

    ListNode odd = head;
    ListNode even = head.next;
    ListNode evenHead = even;

    while(even != null && even.next != null){//even moves faster since it starts at least from 2 and will reach end faster

        odd.next = even.next;//assing next in position

        odd = odd.next;//just move

        even.next = odd.next;//assing next

        even = even.next;//just move
    }

    odd.next = evenHead;//add head

    return head;

    }

}
