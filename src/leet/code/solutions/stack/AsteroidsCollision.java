package leet.code.solutions.stack;

import java.util.Arrays;
import java.util.Stack;

/*
https://leetcode.com/problems/asteroid-collision/description/

We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
 */
public class AsteroidsCollision {

    public static void main(String[] args) {
        int[] asteroids = {10,2,-5}; // -5 killed 2 , anand 10 killed -5 -> 10 only survived
        int[] afterCOllision = asteroidCollision(asteroids);
        System.out.println(Arrays.toString(afterCOllision));

        int[] asteroids2 = {5,10,-5};//10 killed -5, 5 and 10 are moving same direction - -> both survived
        int[] afterCOllision2 = asteroidCollision(asteroids2);
        System.out.println(Arrays.toString(afterCOllision2));

        int[] asteroids3 = {10, 5,-5}; // 5 and -5 mutually destryed each other, only 10 remained
        int[] afterCOllision3 = asteroidCollision(asteroids3);
        System.out.println(Arrays.toString(afterCOllision3));
    }

    private static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;

        while (i < asteroids.length) {

            if(asteroids[i] > 0){
                stack.push(asteroids[i]);

            }else{//oppsite direction asteroid with '-' sign

                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroids[i])){// in stack is + sign, and smaller than negative one
                    stack.pop();//drop smaller from stack
                }

                if(stack.isEmpty() || stack.peek() < 0){ //stack is empty OR with negative sign

                    stack.push(asteroids[i]);//add

                }else if (stack.peek() == Math.abs(asteroids[i])){ //same asteroids with just + and - signs
                    stack.pop(); // drop + sign
                    //and mind no push to stack - as BOTH are destroyed as they are same
                }

            }

            i++;

        }

        int[] remainingInStack = new int[stack.size()];
        for(int j = stack.size() -1; j >= 0; j--){//reverse order MIND !!!
            remainingInStack[j] = stack.pop();
        }
        return remainingInStack;
    }
}
