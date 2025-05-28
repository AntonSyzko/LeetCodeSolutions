package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/*
https://neetcode.io/problems/buy-and-sell-crypto-with-cooldown

You are given an integer array prices where prices[i] is the price of NeetCoin on the ith day.

You may buy and sell one NeetCoin multiple times with the following restrictions:

After you sell your NeetCoin, you cannot buy another one on the next day (i.e., there is a cooldown period of one day).
You may only own at most one NeetCoin at a time.
You may complete as many transactions as you like.

Return the maximum profit you can achieve.

Example 1:

Input: prices = [1,3,4,0,4]

Output: 6
Explanation: Buy on day 0 (price = 1) and sell on day 1 (price = 3), profit = 3-1 = 2. Then buy on day 3 (price = 0) and sell on day 4 (price = 4), profit = 4-0 = 4. Total profit is 2 + 4 = 6.

Example 2:

Input: prices = [1]

Output: 0
Constraints:

1 <= prices.length <= 5000
0 <= prices[i] <= 1000
 */
public class BuyAndSellWithCooldown {

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,1};
        int maxProfit = maxProfit_DP(prices);
        System.out.println(maxProfit);

        int[] prices2 = {1,3,4,0,4};
        int maxProfit2 = maxProfitRecursive(prices2);
        System.out.println(maxProfit2);
    }


    private static int maxProfit_DP(int[] prices) {

        Map<String, Integer> DP = new HashMap<>();

        boolean buying = true;

        return maxProfitDFS_DP(0, buying, prices, DP);
    }

    /*
      time : O(n)
      space: O(n)
     */
    private static int maxProfitDFS_DP(int index, boolean buying, int[] prices, Map<String ,Integer> DP) {
        //BASE
        if(index >= prices.length){
            return 0;
        }

        String  dp_key = index + "|" + buying;//memo key

        if(DP.containsKey(dp_key)){//memo hit
            return DP.get(dp_key);
        }

        int cooldown = maxProfitDFS_DP(index + 1, buying, prices, DP);//skip day , no action, buying stays same

        if(buying){
            buying = false;//reverse boolean
            int buy = maxProfitDFS_DP(index + 1, buying, prices, DP) - prices[index];// - prices[i] -> we have spent some money
            DP.put(dp_key, Math.max(buy, cooldown));

        }else{//sell

            buying = true;
            int sell = maxProfitDFS_DP(index + 2, buying, prices, DP) +  prices[index]; // +2 since after sell is cooldown mandatory , // + prices[i] -> we have gained some money by selling
            DP.put(dp_key, Math.max(sell, cooldown));
        }

        return DP.get(dp_key);
    }

    // ------------ RECURSIVE -----------------------
    private static int maxProfitRecursive(int[] prices) {

        boolean buying = true;

        return maxProfitDFSRec(0, buying, prices);
    }

    /*
    time: O( 2 ^ n)
    space: O(n)
     */
    private static int maxProfitDFSRec(int i, boolean buying, int[] prices) {
        //BASE
        if(i >= prices.length){
            return 0;
        }

        int cooldown = maxProfitDFSRec(i+1, buying, prices);//here 'buying' is not altered, reversed... cooldown mena no action, just skip a day

        if(buying){

            buying = false;//basically reverse boolean buying = ! buying meaning selling further since we have bought at this step
            int buy = maxProfitDFSRec(i + 1, buying, prices) - prices[i]; // - prices[i] -> we have spent some money
            return Math.max(buy, cooldown);

        }else{//selling

            buying = true;//basically reverse boolean buying = ! buying meaning buying further since we have sold at this step
            int sell = maxProfitDFSRec(i + 2, buying, prices) +  prices[i];//+2 cause we have to skip a day ( COOLDOWN) after selling // // + prices[i] -> we have GAINED some money  when selling
            return Math.max(sell, cooldown);

        }
    }
    }
