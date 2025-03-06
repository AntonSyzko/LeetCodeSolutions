package leet.code.solutions.linked_list;

/*
https://leetcode.com/problems/merge-two-sorted-lists/

You are given the heads of two sorted linked lists list1 and list2.
Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
Return the head of the merged linked list.

Example 1:
Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]

Example 2:
Input: list1 = [], list2 = []
Output: []

Example 3:
Input: list1 = [], list2 = [0]
Output: [0]

Constraints:
The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedLists {

    public static void main(String[] args) {
        ListNode<Integer> list1 = new ListNode<>(1, new ListNode<>(3, null));
        ListNode<Integer> list2 = new ListNode<>(2, new ListNode<>(-4, null));

    //    ListNode<Integer> skippedNegatives = skipNegatives( list2);

       // System.out.println(skippedNegatives);

        ListNode<Integer> merged = mergeTwoSortedLists(list1, list2);

        System.out.println(merged);
    }



        private static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> dummyHead = new ListNode(0, null);//future answer holder

        //so we are creating HEAD straight away 
        ListNode<Integer> currentResultNode = dummyHead;//result aggregator , dummyNode will hold it's head

        ListNode<Integer> list1Pointer = list1;//copies pointers
        ListNode<Integer> list2Pointer = list2;// not to change original list nodes

        while (list1Pointer != null && list2Pointer != null) {

            if (list1Pointer.val < list2Pointer.val) {

                currentResultNode.next = list1Pointer;//to result

                list1Pointer = list1Pointer.next;//move list 1 pointer

            } else {

                currentResultNode.next = list2Pointer;

                list2Pointer = list2Pointer.next;//move list 2 pointer

            }

            currentResultNode = currentResultNode.next;//move in answer to attach new results further, not to override it each time !!!

        }

        //left overs ? list1 OR list2 is not null after all - attach what's left -
        // since all sorted EQUAL parts were aggregated in above while - here just sorted LEFT OVER
        currentResultNode.next = list1Pointer != null ? list1Pointer : list2Pointer;

        return dummyHead.next;//mind NEXT !!! since the VERY dummyHead holds nonsense (0, null) and is just a placeholder

    }

    private static ListNode<Integer> skipNegatives(ListNode<Integer> list1, ListNode<Integer> list2) {
        ListNode<Integer> dummyHead = new ListNode<>(0, null);
        ListNode<Integer> resultNode = dummyHead;

        ListNode<Integer> list1Copy = list1;
        ListNode<Integer> list2Copy = list2;

        while (list1Copy != null && list2Copy != null) {
            if (list1Copy.val < 0) {
                list1Copy = list1Copy.next;
            } else {
                resultNode.next = list1Copy;
                list1Copy = list1Copy.next;
            }

            if (list2Copy.val < 0) {
                list2Copy = list2Copy.next;
            } else {
                resultNode.next = list2Copy;
                list2Copy = list2Copy.next;
            }

            resultNode = resultNode.next;
        }

        if (list1Copy != null) {
            if (list1Copy.val < 0) {
                list1Copy = list1Copy.next;
            } else {
                resultNode.next = list1Copy;
            }
        }

        if (list2Copy != null) {
            if (list2Copy.val < 0) {
                list2Copy = list2Copy.next;
            } else {
                resultNode.next = list2Copy;
            }
        }
        return dummyHead.next;
    }


    private static ListNode<Integer> skipNegatives(ListNode<Integer> list1) {
        ListNode<Integer> dummyHead = new ListNode<>(0, null);
        ListNode<Integer> resultNode = dummyHead;

        ListNode<Integer> list1Copy = list1;

        while (list1Copy != null) {
            if (list1Copy.val < 0) {
                list1Copy = list1Copy.next;
            } else {
                resultNode.next = list1Copy;
                list1Copy = list1Copy.next;
            }

            resultNode = resultNode.next;
        }

        return dummyHead.next;
    }
}
