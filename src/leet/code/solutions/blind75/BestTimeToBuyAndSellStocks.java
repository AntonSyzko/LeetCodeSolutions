package leet.code.solutions.blind75;

public class BestTimeToBuyAndSellStocks {
    public static void main(String[] args) {
        int[] prices = {7,1,5,6,3};
        int maxProf =  maxProfit(prices);
        System.out.println(maxProf);
    }

    //O(n)
    private static int maxProfit(int[] prices) {
        int maxProfitRes = 0;

        int minBuyPrice = prices[0];

        for (int i = 1; i < prices.length; i++) {

            if(prices[i] < minBuyPrice){
                minBuyPrice = prices[i];
            }

            int currentProfit = prices[i] - minBuyPrice;

            maxProfitRes = Math.max(maxProfitRes, currentProfit);
        }

        return maxProfitRes;
    }
}
