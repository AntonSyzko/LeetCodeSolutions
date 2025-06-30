package leet.code.solutions.stack;

import java.util.Arrays;

/*
Design a stack that supports push, pop, top, and retrieving the minimum element in
constant time.
push(x) – Push element x onto stack. pop() – Removes the element on top of the
stack. top() – Get the top element. getMin() – Retrieve the minimum element in the
stack.
 */
public class MinStackArray {

    public static void main(String[] args) {

        MinStackArray minStack = new MinStackArray(100);
        minStack.push(10);
        minStack.push(20);

        System.out.println("curr min alt " + minStack.getMinAlt());

        System.out.println("get min " + minStack.getMin());
        System.out.println("top " + minStack.top());

        minStack.pop();

        System.out.println("after pop");
        System.out.println("top " + minStack.top());
        System.out.println("min"+ minStack.getMin());
        System.out.println("min alt "+ minStack.getMinAlt());
    }

    private int capacity;
    private int[] array ;
    private int index ;
    private int currentMin;


    public MinStackArray(int capacity){
        this.capacity = capacity;
        array = new int[capacity];
        index = -1;
        currentMin = Integer.MAX_VALUE;
    }

    public void push(int x) {

        if(array.length >= this.capacity){
            array = Arrays.copyOf(array, array.length * 2);
        }

        array[++index] = x;

        if(array[index] <= currentMin){
            currentMin = array[index];
        }
    }


    public void pop() {
        if(index > -1){
            if (index == array.length/2 && array.length > this.capacity) {
                array = Arrays.copyOf(array, array.length / 2);
            }

            if(index > 0 && array[index] == currentMin){
                currentMin = array[index -1];//if popped one was smallest - set samllest to previous
            }

            index--;
        }
    }

    public int top() {
        if(index > -1){
            return array[index];
        }
        return 0;
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            if(array[i] < min){
                min = array[i];
            }
        }

        return min;
    }

    public int getMinAlt(){
        return currentMin;
    }
}
