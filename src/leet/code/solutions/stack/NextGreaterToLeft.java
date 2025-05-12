package leet.code.solutions.stack;

import java.util.Arrays;
import java.util.Stack;

/*

https://www.callicoder.com/nearest-greater-to-left/


Problem Statement
Given an array of integers, find the nearest (not considering distance, but value) greater element to the left of every element.

The elements for which no greater element exists on the left side, print -1.

Example:

Input:  {9, 4, 15, 6, 2, 10}
Output: {-1, 9, -1, 15, 6, 15}

Explanation:
The first element has nothing on the left side, so the answer for first is -1.
Second element 4 has 9 on the left which is greater than 4, so the answer is 9.
Third element 15 has nothing greater on the left side, so the answer is -1.
Fourth element 6 has 15 as the nearest greater element on the left, so the answer is 15
Similarly, we get values for the fifth and sixth elements.
 */
public class NextGreaterToLeft {

    public static void main(String[] args) {
        int[] nums = {9, 4, 15, 6, 2, 10};
        int[] NGL = nextGreaterElementToLeft(nums);
        System.out.println(Arrays.toString(NGL));
    }

    private static int[] nextGreaterElementToLeft(int[] nums) {
        int[] NGL = new int[nums.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < nums.length; i++) {

            NGL[i] = -1;//default for not found

            while (!stack.isEmpty()) {

                int elementOnTopOfStack = stack.peek();

                if(nums[i] < elementOnTopOfStack){

                    NGL[i] = elementOnTopOfStack;
                    break;

                }else{

                    stack.pop();

                }
            }

            stack.push(nums[i]);
        }

        return NGL;
    }
}
