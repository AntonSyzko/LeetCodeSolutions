package leet.code.solutions.blind75;

import java.util.Arrays;

/*
https://leetcode.com/problems/coin-change/description/

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

You may assume that you have an infinite number of each kind of coin.



Example 1:

Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:

Input: coins = [2], amount = 3
Output: -1
Example 3:

Input: coins = [1], amount = 0
Output: 0


Constraints:

1 <= coins.length <= 12
1 <= coins[i] <= 231 - 1
0 <= amount <= 104
 */
public class CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int targetAmount = 11;

        int fewestNumOfCoins = changeDP( coins, targetAmount);

        System.out.println(fewestNumOfCoins);
    }

    private static int changeDP( int[] coins, int amount) {

        int[] amountOfCoinsForDenominationDP = new int[amount + 1];//DP array to fill how many coins needed for each denomination

        Arrays.fill(amountOfCoinsForDenominationDP, amount + 1);//fill with amount +1 just to fill with exceeding on start

        amountOfCoinsForDenominationDP[0] = 0;//for denomination 0 - zero coins needed


        for (int denomination = 1; denomination <= amount; denomination++) {//traverse all denominations from 1 ( as ww have stored for 0 above)  to target amount

            for (int coin = 0; coin < coins.length; coin++) {//traverse coins we have

                if (denomination >= coins[coin]) {//the very denomination is bigger than the coin

                    amountOfCoinsForDenominationDP[denomination] = Math.min(//min of
                            amountOfCoinsForDenominationDP[denomination], // the very value in DP
                            1 + amountOfCoinsForDenominationDP[denomination - coins[coin]]);// or denom minus current coin value + 1

                }
            }
        }

        if (amountOfCoinsForDenominationDP[amount] < amount + 1) {//if last in DP is not defaultly filled
            return amountOfCoinsForDenominationDP[amount];
        }

        return -1;
    }


        private static int changeRecursive( int[] coins, int amount) {
            int minNumOfWaysRes = Integer.MAX_VALUE ;

            //BASE
            if(amount == 0) return 0;

            if(amount < 0 ) return -1;

            for (int coin : coins) {

                int minAmountSoFar = changeRecursive(coins, amount - coin);//recur with amount minus current coin

                if(minAmountSoFar >= 0) {//still smth to add to fit amount

                    minNumOfWaysRes = Math.min(minNumOfWaysRes, minAmountSoFar + 1);//as we will continue adding coins - accounting for it is +1

                }
            }

            return minNumOfWaysRes != Integer.MAX_VALUE ? minNumOfWaysRes : -1;//--1 if res hasn't updated as we went through

    }
}
