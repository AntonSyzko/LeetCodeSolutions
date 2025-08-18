package leet.code.solutions.DS;

import java.util.Stack;

/*
Design a stack class that supports the push, pop, top, and getMin operations.

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
Each function should run in

O(1) time.


 */
public class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {// pushes the element val onto the stack.
      stack.push(val);

      if(minStack.isEmpty() || val <= minStack.peek()){// MIND !!! <= , since if == we need to update as well

              minStack.push(val);

      }
    }

    public void pop() {//removes the element on the top of the stack.
        int stackPeek = stack.peek();

        if(stackPeek == minStack.peek()){
            minStack.pop();
        }
        stack.pop();
    }

    public int top() {//gets the top element of the stack.
        return stack.peek();
    }

    public int getMin() {//retrieves the minimum element in the stack.
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(-2);
        minStack.push(-3);
        minStack.push(-3);

        int min =  minStack.getMin();
        System.out.println(min);

        minStack.pop();

        int top = minStack.top();
        System.out.println(top);

        min = minStack.getMin();
        System.out.println(min);
    }
}