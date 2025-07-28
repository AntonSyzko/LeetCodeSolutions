package leet.code.solutions.linked_list;

/*
Add Two Numbers problem statement
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3 -> 7 -> 9) + (5 -> 6 -> 8)
Output: 7 -> 0 -> 8
Explanation: 97342 + 865 = 98207.
 */
public class AddTwoNumbersRepresentingLinkedList {

    public static void main(String[] args) {
        ListNode a = new ListNode(9);
        a.next = new ListNode(9);
        a.next.next = new ListNode(9);

        ListNode b = new ListNode(9);
        b.next = new ListNode(9);

        ListNode res =  addTwoNumbers2(a, b);
        System.out.println(res);


        ListNode a1 = new ListNode(2);
        a1.next = new ListNode(4);
        a1.next.next = new ListNode(3);

        ListNode b1 = new ListNode(5);
        b1.next = new ListNode(6);
        b1.next.next = new ListNode(4);

        ListNode res1 =  addTwoNumbers2(a1, b1);
        System.out.println(res1);
    }

    // O(max(l1.size, l2.size))
    private  static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode head = result;

        int tenthCarry = 0;//outside while - hence changes after every while loop iteration

        while(l1 != null || l2 != null) {// OR !!!

            int sum = 0;//get reset at every iteration back to 0

            if(l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            sum += tenthCarry;

            int value  = sum % 10;
            tenthCarry = sum/10;

            ListNode node = new ListNode(value);

            head.next = node;
            head = head.next;
        }

        //after while loop
        if(tenthCarry > 0) {//but IF !!!
            head.next = new ListNode(tenthCarry);//last tenth carry
        }

        return result.next;
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;

        int carry = 0;

        while (l1 != null || l2 != null) {// OR !!!

            int newL1Value = l1 == null ? 0 : l1.val;//if null node we use FAKE zero value , since add 0 is doing nothing
            int newL2Value = l2 == null ? 0 : l2.val;

            int newNodeValue = newL1Value + newL2Value + carry;

            int lastDigit = newNodeValue % 10;
            carry = newNodeValue / 10;

            cur.next= new ListNode(lastDigit);
            cur = cur.next;

            if(l1 != null) {//only if not null -> move to next
                l1 = l1.next;
            }
            if(l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {//any carry leftower
            ListNode newNode = new ListNode(carry);
            cur.next = newNode;
        }

        return head.next;
    }

    private static  int addTwoNumsWithReversion(ListNode list1, ListNode list2) {
        ListNode reversed1 = reverseList(list1);
        ListNode reversed2 = reverseList(list2);

        int firstNumber = reversed1.val;
        reversed1 = reversed1.next;//account for first digit

        while(reversed1 != null){
            int currRes = firstNumber * 10 + reversed1.val;
            firstNumber = currRes;
            reversed1 = reversed1.next;//move
        }

        int secondNumber = reversed2.val;
        reversed2 = reversed2.next;//accounted for first digit

        while(reversed2 !=null){
            int currRes = secondNumber * 10 + reversed2.val;
            secondNumber = currRes;
            reversed2 = reversed2.next;//move
        }

        return firstNumber + secondNumber;

    }

    private static ListNode reverseList(ListNode head) {
        ListNode prev = null;

        while(head!=null){

            ListNode next = head.next;// temp holder of next

            head.next = prev;//reassign to prev ( so point from ex -> to now <- )

            prev = head;

            head = next;
        }

        return prev;
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
