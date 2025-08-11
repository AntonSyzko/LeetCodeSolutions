package leet.code.solutions.linked_list;

public class RemoveNodeFromEndOfLinkedList {

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = null;

        ListNode res = removeNthFromEnd2(a, 2);
        System.out.println(res);
    }

    private static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = head;
        //1. move first N slots onward
        while(n > 0){
            first = first.next;
            n--;
        }

        if(first == null){//n is bigger than length of all nodes
            return head.next;
        }
        //2. create second pointer
        ListNode second = head;
        //3. move both but firts will reach end faster and second will be exatly at the node BEFORE the one we need to delete
        while(first.next != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;//delete by oversetting next next

        return dummy.next;
    }

    private static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode first = head;

        while (n > 0) {
            first = first.next;
            n--;
        }

        ListNode second = dummy;


        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;

        return dummy.next;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}