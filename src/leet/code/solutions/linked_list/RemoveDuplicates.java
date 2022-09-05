package leet.code.solutions.linked_list;

public class RemoveDuplicates {
    public static void main(String[] args) {
    ListNode<Integer> list = new ListNode<>(1, new ListNode<>(1, new ListNode<>(2)));
    ListNode<Integer> noDupliates = removeDuplicates(list);
        System.out.println(noDupliates);
    }

    private static ListNode<Integer> removeDuplicates(ListNode<Integer> list) {
        ListNode<Integer> iterator = list;//copy to work with , points just to HEAD of original

        while (iterator != null) {//traverse list

            ListNode<Integer> nextDistinct = iterator.next;//initially points to HEAD, so next

            while (nextDistinct != null && nextDistinct.val == iterator.val) {//has room to iterate && same next
                nextDistinct = nextDistinct.next;//jump over it , skip it , overriding with next
            }

            iterator.next = nextDistinct;//skip all duples - nextDistinct is now next unique

            iterator = nextDistinct;//move iterator - but from next distinct, which is now LAST UNIQUE

        }

        return list;
    }
}
