package leet.code.solutions.arrays;

import java.util.List;

/*
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
 */
public class BestTimeToBuySellStock {

    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        int maxProfit = maxProfitMy2(prices);
        System.out.println("max profit " + maxProfit);

        int[] prices2 = new int[]{7, 6, 4, 3, 1};
        int maxProfit2 = maxProfitMy2(prices2);
        System.out.println("max profit " + maxProfit2);

        int[] prices3 = new int[]{2, 4, 1};
        int maxProfit3 = maxProfitMy2(prices3);
        System.out.println("max profit " + maxProfit3);

    }

    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxPrice = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i]; //update minimum
            } else {
//max of current MAX and the gain if we SELL at current price - min val ( current - price we have bought ( min ))
                maxPrice = Math.max(maxPrice, prices[i] - minPrice);
            }
        }
        return maxPrice;
    }


    public static int maxProfitMy(int[] prices) {
        //current as smallest -min
        //if next bigger - set max if next smaller -set new min
        int minPrice = prices[0];
        int maxPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
                maxPrice = minPrice;
            } else if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            }
        }
        return maxPrice - minPrice;
    }

    public static int maxProfitMy2(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int currentPrice : prices){

            maxProfit = Math.max(maxProfit, currentPrice - minPrice);

            minPrice = Math.min(currentPrice, minPrice);

        }

//        for (int i = 1; i < prices.length; i++) {
//            minPrice = Math.min(minPrice, prices[i]);
//            // currentProfit = prices[i] - minPrice;
//            //    maxProfit = Math.max(maxProfit,currentPRofit);
//            maxProfit = Math.max(maxProfit, prices[i] - minPrice);//max profit so far OR current profit at this index
//        }
        return maxProfit;
    }

    public static double computeMaxProfitBook(List<Double> prices){
        double minPrice = Double.MAX_VALUE;
        double maxProfit = 0.0;

        for (double price : prices){
            maxProfit = Math.max(maxProfit, price - minPrice);
            minPrice = Math.min(minPrice, price);
        }
        return maxProfit;
    }
}
