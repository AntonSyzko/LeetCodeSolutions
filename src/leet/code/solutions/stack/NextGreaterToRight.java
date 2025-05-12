package leet.code.solutions.stack;

import java.util.Arrays;
import java.util.Stack;

/*
https://www.callicoder.com/nearest-greater-to-right/

Given an array, print the Next Greater Element (NGE) for every element. The Next greater element for an element x is the first greater element on the right side of x in the array.

The elements for which no greater element exist, print -1.

Example 1:

Input:  {1, 3, 2, 4}
Output: {3, 4, 4, -1}

Element     Next greater element on the right
1           3
3           4
2           4
4           -1
Example 2:

Input:  {6, 4, 12, 5, 2, 10}
Output: {12, 12, -1, 10, 10, -1}

Element     Next greater element on the right
6           12
4           12
12          -1
5           10
2           10
10          -1
 */
public class NextGreaterToRight {

    public static void main(String[] args) {
        int [] nums = {1, 3, 2, 4};
        int[] res = nextGreaterElementUsingStack(nums);
        System.out.println(Arrays.toString(res));
    }


    private static int[] nextGreaterElementUsingStack(int[] array) {
        int[] NGE = new int[array.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = array.length-1; i >= 0 ; i--) {// !!!! REVERSE ORDER !!!!

            NGE[i] = -1;//preemptive fill with -1 if no NGE elemnts found at all

            while (!stack.isEmpty()) {

                int elementOnTopOfStack = stack.peek();


                if(elementOnTopOfStack > array[i]){

                    NGE[i] = elementOnTopOfStack;//found prev bigger than current - so store it in res
                    break;//break while

                }else {//current is BIGGER than elementOnTopOfStack

                    stack.pop();//remove from stack since we will push current to stack as it is BIGGER tha what is on top of stack currently
                }

            }
            stack.push(array[i]);//push current to the stack's TOP

        }
        return NGE;
    }




        private static int[] findNextGreaterToRight(int[] nums) {
             Stack<Integer> stack = new Stack<>();

            int[] res = new int[nums.length];
            Arrays.fill(res, -1);//to store all -1 if no next greater will be found

            for (int index = 0; index < nums.length; index++) {

                while (!stack.isEmpty() // while stack has smth
                        && // AND
                        nums[stack.peek()] < nums[index]) {//on top of stack ( without retrieving !!!) is LESS than current element

                   int indexFromTopOfStack =  stack.pop();

                    res[indexFromTopOfStack] = nums[index];

                }

                stack.push(index);//push to top of stack
            }

        return res;
    }

    private static int[] nextGreaterElementToRight(int[] arr) {
        int[] NGE = new int[arr.length];

        Stack<Integer> stack = new Stack<>();

        for(int i = arr.length - 1; i >= 0; i--) {
            NGE[i] = -1;//-1 default for not found

            int curr = arr[i];

            while(!stack.isEmpty() ){

                int topOsStack = stack.peek();

                if(topOsStack > curr){
                    NGE[i] = topOsStack;
                    break;
                }else{
                    stack.pop();
                }

            }

            stack.push(curr);
        }

        return NGE;
    }
}
