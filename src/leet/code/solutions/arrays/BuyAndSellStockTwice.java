package leet.code.solutions.arrays;

public class BuyAndSellStockTwice {
    public static void main(String[] args) {
        int[] prices = {12, 11, 13, 9, 12, 8, 14, 13, 15};
        int maxProfitSingleDay = maxProfitSingleDay(prices);
        int maxProfit = maxProfitSellingTwice(prices);

        System.out.println(maxProfitSingleDay);
        System.out.println(maxProfit);
    }

    public static int maxProfitSellingTwice(int[] prices) {
        int maxTotalProfit = 0;
        int minPriceSoFar = Integer.MAX_VALUE;
        int[] firstBuySellProfits = new int[prices.length];

        // Forward phase. For each day, we record maximum profit if we  sell on that day.
        for (int i = 0; i < prices.length; i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices[i]);
            maxTotalProfit = Math.max(maxTotalProfit, prices[i] - minPriceSoFar);
            firstBuySellProfits[i] = maxTotalProfit;
        }

        int maxPriceSoFar = Integer.MIN_VALUE;

        // Backward phase. For each day, find the maximum profit if we make  the second buy on that day.
        for (int i = prices.length - 1; i > 0; --i) {
            maxPriceSoFar = Math.max(maxPriceSoFar, prices[i]);
            maxTotalProfit = Math.max(maxTotalProfit, maxPriceSoFar - prices[i] + firstBuySellProfits[i - 1]);//todo
        }
        return maxTotalProfit;
    }

    public static int maxProfitSingleDay(int[] prices) {
        int maxTotalProfit = 0;
        int minPriceSoFar = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            minPriceSoFar = Math.min(minPriceSoFar, prices[i]);
            maxTotalProfit = Math.max(maxTotalProfit, prices[i] - minPriceSoFar);
        }
        return maxTotalProfit;
    }
}
