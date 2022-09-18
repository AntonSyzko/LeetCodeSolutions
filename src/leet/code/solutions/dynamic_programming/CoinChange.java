package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/* MEDIUM
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount.
If that amount of money cannot be made up by any combination of the coins, return -1.
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
        int amount = 11;
        int minCoinsNeeded = coinChangeRecursive(coins, amount);
        System.out.println(minCoinsNeeded);
    }

    private static int coinChangeMy(int[] coins, int amount) {
        Arrays.sort(coins);//optimization - no need to traverse more than needed later

        int[]DP = new int[amount+1];

        Arrays.fill(DP,amount+1);//pre-fill with MAX (bigger than target here )

        DP[0]=0;//takes 0 coins to make 0 amount - smallest subproblem - going bottom UP

        for (int subAmount = 0; subAmount <= amount; subAmount++) {//traverse all subamounts of amount

            for (int eachCoin : coins){//traverse each coin in coins

                if(eachCoin <= subAmount){//if to make this subAmount with current coin is possible
                    //min of stored so far OR amount to make this subamount with current coin +1 coin
                    DP[subAmount] = Math.min(DP[subAmount], DP[subAmount - eachCoin] + 1);

                }else {
                    //go to new sub amount , since the smallest coin was bigger than current subamount ( we are soring in line 1  )
                    break;
                }
            }
        }
        return DP[amount] > amount ?   -1 : DP[amount];
    }

    private static int coinChangeRecursive(int[] coins, int amount) {
        int res = Integer.MAX_VALUE;

        if (amount == 0) {//recursion base
            return 0;
        }

        if(amount < 0){//recursion base
            return -1;
        }

        for (int eachCoin : coins){
            //recursively exctract each coin from amount to see the minimum
            int minAmountSoFar  = coinChangeRecursive(coins, amount - eachCoin);

            if(minAmountSoFar >= 0){
                res = Math.min(res, minAmountSoFar + 1);//res or min so far + 1 coin at this very iteration
            }
        }

        return res != Integer.MAX_VALUE ? res : -1;
    }


    private static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);//optimisation
        int[] DP = new int[amount + 1];
        Arrays.fill(DP, amount + 1);//pre fill with smth invalid, not default zeroes
        DP[0] = 0;//to get amount of 0 - we need 0 coins

        for (int i = 0; i <= amount; i++) {//traverse all sum-amounts untill <= amount
            for (int j = 0; j < coins.length; j++) {//traverse each coin
                if (coins[j] <= i) {//if coin is less than very amount
                    //choose current min so far, or amount - current coin =1
                    DP[i] = Math.min(DP[i], DP[i - coins[j]] + 1);
                }else {
                    break;//optimized - no need to check further coins since they are sorted and the current one is less tha amount anyways
                }
            }
        }
//if DP is more than amount - we had no way to create amount with coins w e have 
        return DP[amount] > amount ? -1 : DP[amount];
    }

    private static int coinChangeNoptimization(int[] coins, int amount) {
        int[] DP = new int[amount + 1];
        Arrays.fill(DP, amount + 1);
        DP[0] = 0;

        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    DP[i] = Math.min(DP[i], DP[i - coins[j]] + 1);
                }
            }
        }
        return DP[amount] > amount ? -1 : DP[amount];
    }
}
