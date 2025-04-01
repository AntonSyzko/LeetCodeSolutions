package leet.code.solutions.stack;

import java.util.Arrays;
import java.util.Stack;

/*
https://leetcode.com/problems/daily-temperatures/description/

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {

    public static void main(String[] args) {
      int[] temperatures = {73,74,75,71,69,72,76,73};
      int [] closestPositiveTemperatures =  dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(closestPositiveTemperatures));
    }

    //time O(n) / putting to stack but we are alose deleting from stack as we go
    //spapce O(n)
    private static int[] dailyTemperatures(int[] temperatures) {
         int[] answers = new int[temperatures.length];
        Stack<TemperatureIndexPair> stack = new Stack<>();

        for (int i = temperatures.length-1; i >= 0; i--) {

            //if temperature on top of stack is LESS  or == then curr
            while (!stack.isEmpty() //if smth is in stack
                    &&
                    stack.peek().temperature <= temperatures[i]) {
               stack.pop();//delete it from stack ( as anyways it will lower temperature for everything later , since even current is BIGGER than it
            }

            if(!stack.isEmpty()){//so now if stack is not empty - there is a temperature that is HOTTER than current on top of stack

                answers[i] = stack.peek().index - i;//result - substract indexes

            }

            stack.push(new TemperatureIndexPair( temperatures[i],i));//add to stack as we go

        }

        return answers;
    }


    private static class TemperatureIndexPair {
        int temperature;
        int index;

        public TemperatureIndexPair(int temperature, int index) {
            this.temperature = temperature;
            this.index = index;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }
}
