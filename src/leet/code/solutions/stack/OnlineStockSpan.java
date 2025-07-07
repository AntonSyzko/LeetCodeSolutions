package leet.code.solutions.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
901

https://leetcode.com/problems/online-stock-span/description/

Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.

Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6

Constraints:

1 <= price <= 105
At most 104 calls will be made to next.
 */
public class OnlineStockSpan {

    /*
    Time & Space Complexity

        Time Complexity: O(1) amortized per call

            Each index is pushed exactly once and popped at most once
            Total operations across all calls: O(N)
            Amortized per call: O(1)

    Space Complexity: O(N)

        Stack can hold at most N indices
        Prices list grows to N elements
     */
    private static class StockSpanner {
       private  Stack<Integer> stack;
       private  List<Integer> prices;

        public StockSpanner(Stack<Integer> stack, List<Integer> prices) {
            this.stack = stack;
            this.prices = prices;
        }

        public int next(int price) {

            // Add current price to our list
            prices.add(price);

            int currentIndex = prices.size() - 1;

            // Pop all indices with prices <= current price , These days are "dominated" by today's price
            while (!stack.isEmpty() && prices.get(stack.peek()) <= price) {
                stack.pop();
            }

            // Calculate span
            int span;

            if(stack.isEmpty()){
                // All previous days had price <= today's price
                span =  currentIndex + 1;
            }else{
                // Span is distance from last day with price > today's price
                span = currentIndex - stack.peek();
            }

            // Push current INDEX to stack
            stack.push(currentIndex);

            return span;
        }
    }

    // OPTIMISIED

    private class StockSpanner2 {
        private Stack<int[]> stack; // [price, span]

        public int next(int price) {
            int span = 1;

            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.pop()[1]; // Add the span of dominated days
            }

            stack.push(new int[]{price, span});

            return span;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();

        StockSpanner stockSpanner = new StockSpanner(stack, list);

        System.out.println(stockSpanner.next(100));
        System.out.println(stockSpanner.next(80));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(70));
        System.out.println(stockSpanner.next(60));
        System.out.println(stockSpanner.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner.next(85));//6 days before higher proce ever occured

    }
}