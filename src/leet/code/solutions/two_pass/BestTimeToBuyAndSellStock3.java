package leet.code.solutions.two_pass;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/

You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete at most two transactions.

Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:

Input: prices = [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.


Example 2:

Input: prices = [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: prices = [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.


Constraints:

1 <= prices.length <= 105
0 <= prices[i] <= 105
 */
public class BestTimeToBuyAndSellStock3 {

    public static void main(String[] args) {
        int[] prices1 = {1, 4, 5, 7, 6 ,3, 2, 9};
        System.out.println("Test 1: [1 4 5 7 6 3 2 9]");
        System.out.println("Expected: 6, Got: " + maxProfit(prices1));

    }

    /*
     Time Complexity: O(n) - three passes through the array
      Space Complexity: O(n) - two arrays to store intermediate results
     */
    public static int maxProfit(int[] prices) {
        // Handle edge cases
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int len = prices.length;

        // leftProfit[i] = maximum profit from one transaction using days [0...i]
        int[] leftProfit = new int[len];

        // rightProfit[i] = maximum profit from one transaction using days [i...len-1]
        int[] rightProfit = new int[len];

        // Build leftProfit array (forward pass)
        buildLeftProfitArray(prices, leftProfit);

        // Build rightProfit array (backward pass)
        buildRightProfitArray(prices, rightProfit);

        // Find the best split point
        return findMaxCombinedProfit(leftProfit, rightProfit);
    }

    /**
     * Builds array where leftProfit[i] = max profit from one transaction in days [0...i].
     *
     * Strategy: For each day, either do nothing or sell today (buying on the cheapest day seen so far).
     */
    private static void buildLeftProfitArray(int[] prices, int[] leftProfit) {
        leftProfit[0] = 0; // Can't make profit with just one price
        int minPriceSoFar = prices[0]; // Track the lowest price seen so far

        for (int i = 1; i < prices.length; i++) {//start from 1
            // Update the minimum price we've seen
            minPriceSoFar = Math.min(minPriceSoFar, prices[i]);

            // Either keep previous best profit [i-1], or sell  after buying at lowest price (today[i] - minPriceSoFar)
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - minPriceSoFar);//leftProfit[i - 1] here is do nothing - keep whatever from previous day
        }
    }

    /**
     * Builds array where rightProfit[i] = max profit from one transaction in days [i...n-1].
     *
     * Strategy: For each day, either do nothing or buy today (selling on the highest day seen later).
     */
    private static void buildRightProfitArray(int[] prices, int[] rightProfit) {
        int n = prices.length;//last
        rightProfit[n - 1] = 0; // Can't make profit with just one price
        int maxPriceSoFar = prices[n - 1]; // Track the highest price seen so far (going backwards)

        for (int i = n - 2; i >= 0; i--) {//iter backwards
            // Update the maximum price we've seen (going backwards)
            maxPriceSoFar = Math.max(maxPriceSoFar, prices[i]);

            // Either keep previous best profit [i + 1], or buy today and sell at highest future price ( maxPriceSoFar - prices[i])
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPriceSoFar - prices[i]);//rightProfit[i + 1] here is do nothing - keep whatever from previous day
        }
    }

    /**
     * Finds the maximum combined profit by trying all possible split points.
     *
     * At split point i: first transaction uses days [0...i], second uses days [i+1...n-1]
     * But we allow overlap, so rightProfit[i] covers days [i...n-1]
     */
    private static int findMaxCombinedProfit(int[] leftProfit, int[] rightProfit) {
        int maxTotalProfit = 0;

        for (int i = 0; i < leftProfit.length; i++) {
            // Total profit if we split at day i
            int totalProfit = leftProfit[i] + rightProfit[i];

            maxTotalProfit = Math.max(maxTotalProfit, totalProfit);
        }

        return maxTotalProfit;
    }

    /**
     Approach: Split Point Method
     * - For each possible "split day", calculate:
     *   1. Best profit from one transaction before/on that day
     *   2. Best profit from one transaction after that day
     *   3. Sum them for total profit with 2 transactions
     * - Return the maximum sum across all split points

     **/

    public static int maxProfitClean(int[] prices) {
        if (prices.length <= 1) return 0;

        // Track maximum profit in each state
        int buy1 = -prices[0];   // After buying first stock
        int sell1 = 0;           // After selling first stock
        int buy2 = -prices[0];   // After buying second stock
        int sell2 = 0;           // After selling second stock

        for (int i = 1; i < prices.length; i++) {
            int price = prices[i];

            // Update states (order matters - update from right to left)
            sell2 = Math.max(sell2, buy2 + price);
            buy2 = Math.max(buy2, sell1 - price);

            sell1 = Math.max(sell1, buy1 + price);
            buy1 = Math.max(buy1, -price);
        }

        return sell2;
    }
}