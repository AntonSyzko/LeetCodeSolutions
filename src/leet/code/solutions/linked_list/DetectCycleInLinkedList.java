package leet.code.solutions.linked_list;

import java.util.*;

/*
https://www.techiedelight.com/detect-cycle-linked-list-floyds-cycle-detection-algorithm/



 */
public class DetectCycleInLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode headNext = new ListNode(2);
        ListNode headNextNext = new ListNode(3);
        head.next = headNext;
        headNext.next = headNextNext;
        headNextNext.next = headNext;

        boolean result = detectCycleUsingSet(head);
        System.out.println(result);

        ListNode  start = detectCycleStart(head);
        System.out.println(start.val);

    }

    private static ListNode detectCycleStart(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                System.out.println("\r\n\t cycle detected at " + slow.val);
                break;
            }
        }

        if(fast==null || fast.next==null){//reached end with no cycle
            return null;//null -> no cycle detected
        }

        //slow stayed at cycle
        while (head != slow) {//head used as is from start -> until they met -> they met at cycle start
            head = head.next;
            slow = slow.next;
        }

        return  head;
    }

    //O(n) time and space
    private static boolean detectCycleUsingMap(ListNode head) {

        if(head==null || head.next==null){
            return false;
        }

        Map<ListNode, ListNode> map = new HashMap<>();

        while(head !=null){
            ListNode  nexNode = head.next;
            if(map.containsKey(nexNode)){
                return true;
            }
            if(nexNode!=null){
                map.put( head, nexNode );
            }
            head = head.next;
        }

        return false;
    }

    private static boolean detectCycleUsingSet(ListNode head) {

        if(head==null || head.next==null){
            return false;
        }

        Set<ListNode> set = new HashSet<>();

        while(head !=null){
            ListNode  nexNode = head.next;
            if(set.contains(nexNode)){
                return true;
            }
            if(nexNode!=null){
                set.add(nexNode );
            }
            head = head.next;
        }

        return false;
    }

     static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }


}
