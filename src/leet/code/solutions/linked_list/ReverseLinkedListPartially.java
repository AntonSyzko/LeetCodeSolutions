package leet.code.solutions.linked_list;

public class ReverseLinkedListPartially {

    public static void main(String[] args) {
        ListNode<Integer> list = new ListNode<>(1, new ListNode<>(2, new ListNode<>(3, new ListNode<>(4, null))));
        ListNode<Integer> partiallyReversed = reverseSublist(list, 2, 3);
        System.out.println(partiallyReversed);
    }

    public static ListNode<Integer> reverseSublist(ListNode<Integer> originalList, int start, int finish) {
        if (start == finish) {
            return originalList;
        }
        ListNode<Integer> dummyHead = new ListNode<>(0, originalList);
        ListNode<Integer> sublistHead = dummyHead;

        int shiftReversionPosition = 1;

        while (shiftReversionPosition++ < start) {
            sublistHead = sublistHead.next;
        }

        ListNode<Integer> reverseSublistIter = sublistHead.next;

        while (start++ < finish) {
            ListNode<Integer> temp = reverseSublistIter.next;
            reverseSublistIter.next = temp.next;
            temp.next = sublistHead.next;
            sublistHead.next = temp;
        }
        return dummyHead.next;
    }
}
