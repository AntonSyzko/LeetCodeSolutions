package leet.code.solutions.stack;

import java.util.*;

/*
https://www.callicoder.com/stock-span-problem/

he stock span problem is a financial problem where we have a series of n daily price quotes for a stock and we need to calculate span of stock’s price for all n days.

The span S[i] of the stock’s price on a given day i is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to its price on day i.

For example, if the price of a stock over a period of 7 days are [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

Explanation

Stock price    Max Consecutive days (starting from today) for which the stock price was
               less than or equal to the current price

100            1
80             1
60             1
70             2
60             1
75             4
85             6
 */
public class StockSpan {

    public static void main(String[] args) {
      int[] prices = {100,80,60,70,60,75,85};
      int[] spans = stockSpan(prices);
      System.out.println(Arrays.toString(spans));
    }

    /*
    This approach has O(n) time complexity, as each index is pushed and popped at most once,
      O(n) space complexity in the worst case.
     */
    private static int[] stockSpan(int[] stockPrices) {

        if(stockPrices==null || stockPrices.length==0) {
            return null;
        } else if (stockPrices.length == 1) {
            return new int[]{1};
        }

        int[] spans = new int[stockPrices.length];

        Stack<Integer> stackOfIndexes = new Stack<>();
        stackOfIndexes.push(0);
        spans[0] = 1;// First day's span is always 1

        for (int index = 1; index < stockPrices.length; index++) {

            while (!stackOfIndexes.isEmpty()) {

                int topOfStackIndex = stackOfIndexes.peek();

                if(stockPrices[index] >= stockPrices[topOfStackIndex]){

                    stackOfIndexes.pop();//drop untill first bigger found

                }else{

                    break;//first bigger found -> stop, exit while loop
                }

            }

            if(stackOfIndexes.isEmpty()){//all previous indexes are empty
                spans[index] = index +1;
            }else{
                spans[index] = index  - stackOfIndexes.peek(); // Span is difference between current day and closest higher price day
            }

            // Push current day's index
            stackOfIndexes.push(index);

        }
       return spans;
    }


    private static int[] stockSpan2(int[] stockPrices) {
        int n = stockPrices.length;
        int[] spans = new int[n];
        // Stack will store indices, not prices
        Stack<Integer> stack = new Stack<>();

        // Process day 0
        stack.push(0);
        spans[0] = 1; // First day's span is always 1

        // Process remaining days
        for (int i = 1; i < n; i++) {
            // Pop while current price is greater than or equal to price at stack top
            while (!stack.isEmpty() && stockPrices[i] >= stockPrices[stack.peek()]) {
                stack.pop();
            }

            // Calculate span
            if (stack.isEmpty()) {
                spans[i] = i + 1; // All previous prices are smaller
            } else {
                spans[i] = i - stack.peek(); // Span is difference between current day and closest higher price day
            }

            // Push current day's index
            stack.push(i);
        }

        return spans;
    }
}
