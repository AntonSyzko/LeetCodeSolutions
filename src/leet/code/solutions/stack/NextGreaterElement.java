package leet.code.solutions.stack;

import java.util.Arrays;
import java.util.Stack;

/*
Find the next greater element for every array element
Given an integer array, find the next greater element for every array element. The next greater element of a number x is the first greater number to the right of x in the array.

In other words, for each element A[i] in the array A, find an element A[j] such that j > i and A[j] > A[i]
and the value of j should be as minimum as possible.
 If the next greater element doesn’t exist in the array for any element, consider it -1.


 For example,

Input:  [2, 7, 3, 5, 4, 6, 8]
Output: [7, 8, 5, 6, 6, 8, -1]

 Input:  [5, 4, 3, 2, 1]
 Output: [-1, -1, -1, -1, -1]

  Note that the next greater element for the last array element is always -1.

 */
public class NextGreaterElement {

    public static void main(String[] args) {
   int[] nums = {2, 7, 3, 5, 4, 6, 8};
   int[] res = nextGreater(nums);
        System.out.println(Arrays.toString(res));
    }

    private static int[] nextGreater(int[] nums) {
        // base case
        if (nums == null) {
            return nums;
        }

        int[] res = new int[nums.length];
        Arrays.fill(res, -1);//keeps default -1 everywhere in case of no bigger elements will be found - gonna work

        Stack<Integer> stack = new Stack<>();

        for (int index = 0; index < nums.length; index++) {

            System.out.println("\r\n\tintex " + index + " element " + nums[index]);
            System.out.println("\r\n\tstack " + stack);
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                System.out.println("\r\n\t --->  popping from the stack " + stack.peek());
                int indexFromTopOfTheStack = stack.pop();//pop() -> extracted
                res[indexFromTopOfTheStack] = nums[index];
                System.out.println("\r\n\t --->  current res" + Arrays.toString(res));
            }

            stack.push(index);
        }

        return res;
    }
}
