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
        ListNode<Integer> l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(7);
        l1.next.next.next.next = new ListNode(9);

        ListNode<Integer> l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(8);

        ListNode result = addTwoNumbers(l1, l2);

        while(result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();

    }

    // O(max(l1.size, l2.size))
    private  static ListNode addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {

        ListNode result = null, head = null;
        int tenthCarry = 0;

        while(l1 != null || l2 != null) {
            int sum = 0;

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

            if(result != null) {
                result.next = node;
                result = result.next;
            } else {
                result = head = node;
            }
        }

        if(tenthCarry > 0) {
            result.next = new ListNode(tenthCarry);//last tenth carry
        }

        return head;
    }



    private static  int addTwoNumsWithReversion(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> reversed1 = reverseList(list1);
        ListNode<Integer> reversed2 = reverseList(list2);

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

    private static ListNode reverseList(ListNode<Integer> head) {
        ListNode<Integer> prev = null;

        while(head!=null){

            ListNode<Integer> next = head.next;// temp holder of next

            head.next = prev;//reassign to prev ( so point from ex -> to now <- )

            prev = head;

            head = next;
        }

        return prev;
    }
}
