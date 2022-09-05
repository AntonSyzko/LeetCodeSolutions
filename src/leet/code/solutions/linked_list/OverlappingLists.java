package leet.code.solutions.linked_list;

/*
lists overlap only when TAIL of some list points to other's lost node

The lists overlap if and only if both have the same tail node:
once the lists converge at a node, they cannot diverge at a later node.
Therefore, checking for overlap amounts to finding the tail nodes for each list.
 */
public class OverlappingLists {
    public static void main(String[] args) {
        ListNode<Integer> l11 = new ListNode<>(1);
        ListNode<Integer> l12 = new ListNode<>(2);
        l11.next = l12;

        ListNode<Integer> l21 = new ListNode<>(3);
        ListNode<Integer> l22 = new ListNode<>(4);
        l21.next = l22;
        l22.next = l11;//overlap HERE

        ListNode<Integer> overlappingRes = overlappingNoCycleLists(l11,l21);
        System.out.println(overlappingRes.val);
    }

    //time O(n)
    //space O(1)
    public static ListNode<Integer> overlappingNoCycleLists( ListNode<Integer> list1, ListNode<Integer> list2){
        int firstListLength = length(list1);
        int secondListLength = length(list2);

        // Advances the longer list to get equal length lists.
        if(firstListLength > secondListLength) {
            list1 = advanceListByK(firstListLength - secondListLength, list1);
        }else {
            list2 = advanceListByK(secondListLength - firstListLength, list2);
        }

        while (list1 != null && list2 != null && list1 != list2){//advancing in BOTH lists while they are NOT end and they are NOT same
            list1 = list1.next;
            list2 = list2.next;
        }
        //here either overlapped node or null
        return list1; // null ptr implies there is no overlap between LI and L2.
    }

    public static ListNode<Integer> advanceListByK(int positionAdvance, ListNode<Integer> list){
        while (positionAdvance-- >0 ) {
            list = list.next;//advance
        }
        return list;
    }

    private static int length(ListNode<Integer> list){
        int length = 0;

        while (list != null) {
            ++length;
            list = list.next;
        }
        return length;
    }
}
