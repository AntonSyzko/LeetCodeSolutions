package leet.code.solutions.dynamic_programming;

/*
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps.
 In how many distinct ways can you climb to the top?

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

Constraints:
1 <= n <= 45
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        int getToStep = 2;
        int stepsToTakeToGetTo = climbStairsDynamic(getToStep);
        System.out.println(" steps to get to " + getToStep + " are " + stepsToTakeToGetTo);

        getToStep = 3;
        stepsToTakeToGetTo = climbStairsDynamic(getToStep);
        System.out.println(" steps to get to " + getToStep + " are " + stepsToTakeToGetTo);

        getToStep = 4;
        stepsToTakeToGetTo = climbStairs(getToStep);
        System.out.println(" steps to get to " + getToStep + " are " + stepsToTakeToGetTo);

        getToStep = 5;
        stepsToTakeToGetTo = climbStairs2(getToStep);
        System.out.println(" steps to get to " + getToStep + " are " + stepsToTakeToGetTo);
    }

    /*
         int[] stepsToTake = new int [] {0,1,2,3,5,9}; // index is a step to get to, value is how many steps it takes
         return steps[n]; // at index N - it takes VALUE of dynamic array
       System.out.println(Arrays.toString(dynamicArray));
    */
    public static int climbStairs(int n) {

        if (n == 0) {
            return 1;
        } else if (n == 1) {
            return 1;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    //no recursion
    public static int climbStairs2(int n) {
        int first = 1;
        int second = 1;

        for (int i = 0; i < n-1; i++) {
            int temp = first;
            first = first + second;//aggregates results of previous two results
            second = temp;//shifts and last one now contains prev first
        }
        return first;
    }



        public static int climbStairsDynamic(int n) {
//        if(n==0) { //conditions match - but otherwisec
//            return 0;
//        } else if(n == 1) {
//            return 1;
//        }
        int[] dynamicArray = new int[n+1];
        dynamicArray[0]=1;
        dynamicArray[1]=1;

        for (int i = 2; i <= n; i++) {
            dynamicArray[i] = dynamicArray[i-1] + dynamicArray[i-2];
        }
        return dynamicArray[n];
    }
}
