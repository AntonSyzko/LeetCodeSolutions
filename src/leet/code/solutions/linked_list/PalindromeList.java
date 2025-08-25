package leet.code.solutions.linked_list;

public class PalindromeList {
    public static void main(String[] args) {
       ListNode<Integer> list = new ListNode<>(1, new ListNode<>(2, new ListNode<>(2, new ListNode<>(1))));
        System.out.println(isLinkedListAPalindrome(list));
    }

    private static boolean isLinkedListAPalindrome (ListNode <Integer> list) {
        if(list == null){
            return true;
        }

        //used  just to hit mid point
        ListNode<Integer> slowPointer = list;
        ListNode<Integer> fastPointer = list;

        //Finds the second half of L.
        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;//stops at MID
        }

        //NOTE ! cannot reuse fist pointer - it is finished over by the time we found mid point ( second half )
        //second half now  points to the middle of the list ( ODD number cannot be palindrome )

        // Compare the first half and the reversed second half lists.
        ListNode<Integer> firstHalfIter = list;//start from HEAD
        ListNode<Integer> secondHalfReversedIter = reverseList(slowPointer);//ex second half list - but reversed

        while (secondHalfReversedIter != null && firstHalfIter != null){ //while there's room to iterate
            if(secondHalfReversedIter.val != firstHalfIter.val){ //vals NOT same
                return false;//not a palindrome
            }
            secondHalfReversedIter = secondHalfReversedIter.next;//shift onward
            firstHalfIter = firstHalfIter.next;//shift onward
        }
        return true;//got here ? palindrome it is
    }

    // (HEAD) 1 -> 2 -> 3 ->  null
    // null <- 1 <- 2 <- 3 <- HEAD
    public static ListNode<Integer> reverseList(ListNode<Integer> head) {
        ListNode<Integer> previous = null;//create previous, initially points to nothing

        while (head != null){
            ListNode<Integer> next = head.next;//remember next
            head.next = previous;//point head next to prev - so reverse hear , ->   to   <-
            previous = head;//prev is now head after reversal ( we just remember in prev ex head )
            head = next;// and now new head is next node of ex head
        }
        return previous;
    }
}
