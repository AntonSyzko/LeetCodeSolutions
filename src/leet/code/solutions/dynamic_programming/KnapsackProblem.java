package leet.code.solutions.dynamic_programming;

/*
https://www.callicoder.com/0-1-knapsack-problem/

 */
public class KnapsackProblem {

    public static void main(String[] args) {

        int [] values = {60,100,120};
        int [] weights = {10,20,30};
        int knapsackCapacity = 50;
        int numberOfItems = 3;

        int maxValuesInKnapsack = findMaxValue(values, weights, knapsackCapacity, numberOfItems);
        System.out.println(maxValuesInKnapsack);
    }

    //Time Complexity: O(2^n) Exponential
    //Space Complexity: O(1), no additional space reserved. Only Stack space is used in recursion.
    private static int findMaxValue(int[] values, int[] weights, int knapsackCapacity, int numberOfItems) {

        //base case
        if(numberOfItems == 0 || knapsackCapacity == 0) {
            return 0;//no items OR capacity 0
        }

        //if weight of curr item is BIGGER than the entire knapsack capacity
        //mind [numberOfItems-1] here is just a zero based index access to last item from tail
        if(weights[numberOfItems - 1] > knapsackCapacity ){
            return findMaxValue(values, weights, knapsackCapacity, numberOfItems - 1);//then apply algo excluding(numberOfItems-1) the current item, which was BIGGER than entire knapsack capacity
        }

        //Maximum value obtained from n-1 items and all the  weights (Excluding the n-1-th item)
        int maxValuesExcludingCurrentItem = findMaxValue(values, weights, knapsackCapacity, numberOfItems - 1);//apply algo without curr item ( numberOfItems -1 ) -> move one left in array of n

        //including here means counting in VALUES array  but not counting in KNAPSACK CAPACITY
        //Value of the n-th item plus the maximum value that can be obtained from n-1 items and W-wt[n] weights (Including the n-th item)
        //mind [numberOfItems-1] here is just a zero based index access to last item from tail
        int maxValueIncludingCurrentItem =  values[numberOfItems - 1]
                +
                findMaxValue(values,  weights, knapsackCapacity - weights[numberOfItems - 1], numberOfItems - 1);

        return Math.max(maxValuesExcludingCurrentItem, maxValueIncludingCurrentItem);
    }
    }
