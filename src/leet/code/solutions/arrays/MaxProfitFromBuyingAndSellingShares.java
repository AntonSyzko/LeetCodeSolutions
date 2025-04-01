package leet.code.solutions.arrays;

/*
https://www.techiedelight.com/maximum-profit-earned-buying-and-selling-shares/

Given a list containing future prediction of share prices, find the maximum profit earned by buying and selling shares any number of times with the constraint, a new transaction can only start after the previous transaction is complete, i.e., we can only hold at most one share at a time.

For example,

Stock Prices: {1, 5, 2, 3, 7, 6, 4, 5} Total profit earned is 10 Buy on day 1 and sell on day 2Buy on day 3 and sell on day 5Buy on day 7 and sell on day 8  Stock Prices: {10, 8, 6, 5, 4, 2} Total profit earned is 0

 */
public class MaxProfitFromBuyingAndSellingShares {

    public static void main(String[] args) {
        int[] prices = {1, 5, 2, 3, 7, 6, 4, 5};
        System.out.println(findMaxProfit(prices));

    }


    // selling shares any number of times
    public static int findMaxProfit(int[] prices) {
        // keep track of the maximum profit gained
        int profitRes = 0;

        // initialize the local minimum to the first element's index
        int buyStart = 0;// local minimum prices index

        // start from the second element
        for (int i = 1; i < prices.length; i++) {

            // update the local minimum if a decreasing sequence is found
            if (prices[i - 1] > prices[i]) {
                buyStart = i;
            }

            // sell shares if the current element is the peak, i.e., (`previous <= current > next`)
            if (prices[i - 1] <= prices[i]
                    && (i + 1 == prices.length || prices[i] > prices[i + 1])) { // checking PEAK here

                profitRes += (prices[i] - prices[buyStart]);
                System.out.printf("Buy on day %d and sell on day %d\n", buyStart + 1, i + 1);

            }
        }

        return profitRes;
    }
}
