package leet.code.solutions.dynamic_programming;

/*
https://www.techiedelight.com/find-maximum-profit-that-can-be-earned-by-selling-stocks/

Find maximum profit that can be earned by conditionally selling stocks
Given a list containing future price predictions of two different stocks for the next n–days,
find the maximum profit earned by selling the stocks with a constraint that the second stock can be sold,
 only if no transaction happened on the previous day for any of the stock.

Assume that the given prices are relative to the buying price.
Each day, we can either sell a single share of the first stock or a single share of the second stock or sell nothing.

For example,

Input:
Day    Price(x)  Price(y)
1        5        8
2        3        4
3        4        3
4        6        5
5        3        10

Output: Maximum profit earned is 25

Explanation:

Day 1: Sell stock y at a price of 8
Day 2: Sell stock x at a price of 3
Day 3: Sell stock x at a price of 4
Day 4: Don’t sell anything
Day 5: Sell stock y at a price of 10
 */
public class MaxConditionalProfit {
    public static void main(String[] args) {

        int[] x = { 5, 3, 4, 6, 3 };
        int[] y = { 8, 4, 3, 5, 10 };

        System.out.println("The maximum profit earned is " + findMaxProfitLastTwoTrackedDP(x, y));
    }

    // Function to find the maximum profit that can be earned by selling the stocks.
    // Here, arrays `x[0…n-1]` and `y[0…n-1]` contains the two different stocks'
    // future price predictions for the next n–days.
    public static int findMaxProfitLastTwoTrackedDP(int[] x, int[] y) {
        // base case
        if (x.length == 0) {
            return 0;
        }

        int prev_of_prev = 0;
        int prev = Integer.max(x[0], y[0]);

        // Find the maximum profit in a bottom-up manner
        for (int i = 1; i < x.length; i++) {
            int curr = Integer.max(x[i] + prev, y[i] + prev_of_prev);
            prev_of_prev = prev;
            prev = curr;
        }

        // `prev` stores the maximum profit gained till day `n`
        return prev;
    }

    // Function to find the maximum earnings that can be earned by selling the stocks.
    // Here, arrays `x[0…n-1]` and `y[0…n-1]` contains the two different stocks'
    // future price predictions for the next n–days.
    public static int findMaxProfitDP(int[] x, int[] y) {
        // base case
        if (x.length == 0) {
            return 0;
        }

        // create an auxiliary array `DP[]` to save solutions to the subproblems.
        // Here, `DP[i]` stores the maximum earnings till day `i`.
        int[] DP = new int[x.length + 1];

        // Base cases
        DP[0] = 0;
        DP[1] = Integer.max(x[0], y[0]);

        // Fill the auxiliary array `DP[]` in a bottom-up manner
        for (int i = 2; i <= x.length; i++) {
            DP[i] = Integer.max(x[i - 1] + DP[i - 1], y[i - 1] + DP[i - 2]);
        }

        // `DP[n]` stores the maximum earnings till day `n`
        return DP[x.length];
    }

    // Recursive function to find the maximum profit that can be earned by selling
    // stocks. Here, arrays `x[0…n]` and `y[0…n]` contains the two different stocks'
    // future price predictions for the next n–days.
    public static int findMaxProfit(int[] x, int[] y, int n) {
        // base case
        if (n < 0) {
            return 0;
        }

        // store the maximum profit gained
        int profit = 0;

        // sell the first stock on the n'th day, and recur for the (n-1)'th day
        profit = Integer.max(profit, x[n] + findMaxProfit(x, y, n - 1));

        // sell the second stock on the n'th day, and recur for the (n-2)'th day
        // (no transaction can be made on the (n-1)'th day)
        profit = Integer.max(profit, y[n] + findMaxProfit(x, y, n - 2));

        // return the maximum profit
        return profit;
    }
}
