package leet.code.solutions.linked_list;

public class DeleteKthElelemntFromList {

    public static void main(String[] args) {
      ListNode<Integer> list = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, null))));
        System.out.println(list);

        ListNode<Integer> withKRemoved = removeKthLast(list,2);//has to remove 2nd from end
        System.out.println(withKRemoved);

    }


    // Assumes L has at least k nodes, deletes the k-th last node in L.
    //time O(n)
    //space O(1)
    public static ListNode<Integer> removeKthLast(ListNode<Integer> list, int indexToRemove) {
        ListNode<Integer> dummyHead = new ListNode<>(0, list);
        ListNode<Integer> firstIterator = dummyHead.next;

        while (indexToRemove-- > 0) {
            firstIterator = firstIterator.next;//advance first to stop at index we need to remove
        }

        ListNode<Integer> secondIterator = dummyHead;//second iter starts from start

        while (firstIterator != null) {//move both till first stops
            firstIterator = firstIterator.next;//this will hit end
            secondIterator = secondIterator.next;//and second will be next to the node we have to remove
        }
        // second points to the (k + 1)-th last node, deletes its successor.
        secondIterator.next = secondIterator.next.next;

        return dummyHead.next;
    }
}
