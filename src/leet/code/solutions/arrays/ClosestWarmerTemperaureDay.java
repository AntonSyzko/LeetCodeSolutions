package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.Stack;

/*
https://www.youtube.com/watch?v=-59FbGWsCgI

find how many days it's to hotter temperature from current
temperatures : [13, 12, 15, 11, 9, 12, 16 ]
               [2,  1,  4,  2,  1,  1,  0 ]

 */
public class ClosestWarmerTemperaureDay {

    public static void main(String[] args) {
        int[] temperatures = {13, 12, 15, 11, 9, 12, 16};
        int[] closestHotDays = closestHotterDays(temperatures);
        System.out.println(Arrays.toString(closestHotDays));


        closestHotDays = closestHotterDaysStack(temperatures);
        System.out.println(Arrays.toString(closestHotDays));

    }

    //time O(n^2)
    //space O(N)
    private static int[] closestHotterDays(int[] temperatures) {
        int[] res = new int[temperatures.length];

        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;//distance difference
                    break;//found closest hot day no need to traverse on
                }
            }
        }
        return res;
    }

    private static int[] closestHotterDaysStack(int[] temperatures) {
        int[] res = new int[temperatures.length];

        Stack<HotDay> stack = new Stack<>();//hottest temperaturees on top of stack

        //backwards eliminates need to account everything at right once hottest days in stack
        for (int i = temperatures.length-1; i >=0 ; i--) {//traverse backwards

            //while in stack AND temperature of one on top of stack is LESS than current
            while (!stack.isEmpty() && stack.peek().temperature <= temperatures[i]){
                //remove(pop not peek here) - anyways current in array is HOTTER that one on top of stack
                stack.pop();
            }
            //if NOT empty stack
            if (!stack.isEmpty()) {
                res[i] = stack.peek().index - i; //count difference in indexes and add to result
            }
            // push on top of stack
            stack.push(new HotDay(temperatures[i], i));
        }
        return res;
    }

    private static class HotDay {
        int temperature;
        int index;

        public HotDay(int temperature, int index) {
            this.temperature = temperature;
            this.index = index;
        }

        public int getTemperature() {
            return temperature;
        }

        public int getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return "HotDay{" +
                "temperature=" + temperature +
                ", index=" + index +
                '}';
        }
    }
}
