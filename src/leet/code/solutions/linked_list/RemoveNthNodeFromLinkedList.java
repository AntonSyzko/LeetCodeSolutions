package leet.code.solutions.linked_list;


public class RemoveNthNodeFromLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        System.out.println(head);

        int positionToRemove = 2;

        System.out.println("\r\n\t AFTER REMOVAL");

        ListNode afterRemoval = removeNthNode(head, positionToRemove);
        System.out.println(afterRemoval);


    }

    private static ListNode removeNthNode(ListNode head, int nodeToRemovePosition) {

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode prev = head;

        while (head != null ) {
            ListNode currNode = head;

            if(nodeToRemovePosition == 0){
                prev.next = currNode.next;
            }

            nodeToRemovePosition--;
            prev = currNode;
            head = head.next;

        }

        return dummyHead.next;

    }

    private  static class ListNode {
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
