package leet.code.solutions.linked_list;

import java.util.*;

/*
https://leetcode.com/problems/palindrome-linked-list/

Given the head of a singly linked list, return true if it is a palindrome.

Input: head = [1,2,2,1]
Output: true

Input: head = [1,2]
Output: false

Constraints:

The number of nodes in the list is in the range [1, 105].
0 <= Node.val <= 9

Follow up: Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLInkedList {

    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1))));
        boolean res = isPalindrome(node);
        System.out.println(res);
    }

    public static boolean isPalindrome(ListNode<Integer> head) {
        List<Integer> list = new ArrayList<>();

        while (head != null) { //populate List with ListNodes
            list.add(head.val);
            head = head.next;
        }

        int start = 0; //start of list
        int end = list.size()-1; //end of list

        while (start < end){ //before we hit middle

            if(!(list.get(start)).equals(list.get(end))){//if values from start != end
                return false; //exit
            }
            start++;//move on
            end--;
        }
        return true;//got here - OK
    }

    public static boolean isPalindromeListIterator(ListNode<Integer> head) {
        List<Integer> list = new ArrayList<>();

        while (head != null) { //populate List with ListNodes
            list.add(head.val);
            head = head.next;
        }

        ListIterator<Integer> forwardIterator = list.listIterator();
        ListIterator<Integer> backwardIterator = list.listIterator(list.size());

        while (forwardIterator.hasNext() ||  backwardIterator.hasPrevious()){ // OR

            if(!(forwardIterator.next()).equals(backwardIterator.previous())){// forward iter and backward iter values NOT equal
                return false; //exit
            }
        }

        return true;//got here - OK
    }


    public static boolean isPalindromeArray(ListNode<Integer> head) {
        List<Integer> list = new ArrayList<>();

        while (head != null) {
            list.add(head.val); //O(n) space
            head = head.next;
        }

        int[] array = list.stream().mapToInt(Integer::valueOf).toArray();//eats more memory + O(n) space

        int start = 0;
        int end = array.length - 1;

        while (start < end) {// O(n/2)
            if (array[start] != array[end]) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }
}
