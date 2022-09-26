package leet.code.solutions.linked_list;

public class SumListNodesIntegers {

    public static void main(String[] args) {
  ListNode<Integer> list1 = new ListNode<Integer>(1, new ListNode<Integer>(2, new ListNode<Integer>(9)));
  ListNode<Integer> list2 = new ListNode<Integer>(2, new ListNode<Integer>(3, new ListNode<Integer>(6)));

  ListNode<Integer> sum = addTwoNumbers(list1, list2);
        System.out.println(sum);
    }

    public static ListNode<Integer> addTwoNumbers(ListNode<Integer> list1, ListNode<Integer> list2) {
       ListNode<Integer> dmmyHead = new ListNode<>(0, null);//placeholder fo future result head
       ListNode<Integer> resultIter = dmmyHead;
       int tenthCarry = 0;

       while (list1 != null || list2 != null) {//!!! mind OR
           int currentTwoNodesSum = tenthCarry;//start with previously calculated tenth carry ( to bare tenth reminders )

           if( list1 != null) {//because of OR
               currentTwoNodesSum += list1.val;
               list1 = list1.next;//move list 1
           }

           if(list2 != null) {
               currentTwoNodesSum += list2.val;;
               list2 = list2.next;//move list2
           }


           resultIter.next = new ListNode<>(currentTwoNodesSum % 10, null);//res node without tenth carry

           tenthCarry = currentTwoNodesSum / 10;

           resultIter = resultIter.next;//move result
       }

        // carry cannot exceed 1, so we never need to add more than one node.
       if( tenthCarry > 0 ) {
           resultIter.next = new ListNode<>(tenthCarry, null);
       }

       return dmmyHead.next;//res is after dummy head
    }
}
