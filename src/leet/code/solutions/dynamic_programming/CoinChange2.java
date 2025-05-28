package leet.code.solutions.dynamic_programming;

import java.util.HashMap;
import java.util.Map;
/*
https://neetcode.io/problems/coin-change-ii

You are given an integer array coins representing coins of different denominations (e.g. 1 dollar, 5 dollars, etc) and an integer amount representing a target amount of money.

Return the number of distinct combinations that total up to amount. If it's impossible to make up the amount, return 0.

You may assume that you have an unlimited number of each coin and that each value in coins is unique.

Example 1:

Input: amount = 4, coins = [1,2,3]

Output: 4
Explanation:

1+1+1+1 = 4
1+1+2 = 4
2+2 = 4
1+3 = 4
Example 2:

Input: amount = 7, coins = [2,4]

Output: 0
Constraints:

1 <= coins.length <= 100
1 <= coins[i] <= 5000
0 <= amount <= 5000
 */
public class CoinChange2 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 5;

        int uniqueCombinations = changeRecursive(amount, coins);
        System.out.println(uniqueCombinations);
    }

    //------------------ recursive --------------------
    private static int changeRecursive(int amount, int[] coins) {
        int startingCoinIndex = 0;
        return dfsUnboundedKnapsack(coins, startingCoinIndex, amount);
    }

    private static int dfsUnboundedKnapsack(int[] coins, int coinIndex, int amount) {
        // Base case 1: Exact amount reached - found one valid combination
        if(amount == 0){
            return 1;
        }

        // Base case 2: No more coins or negative amount - invalid combination
        if(coinIndex >= coins.length || amount < 0){
            return 0;
        }

                //Unbounded knapsack
                // Two choices for current coin:
                // 1. Skip current coin - move to next coin with same amount
            int skipCoin =  dfsUnboundedKnapsack(coins, coinIndex + 1, amount);

               // 2. Use current coin - stay at same coin (unlimited use) with reduced amount
        int useCoin = dfsUnboundedKnapsack(coins, coinIndex , amount - coins[coinIndex]);

        return skipCoin + useCoin;
    }

    // OPTIMIZED RECURSIVE WITH MEMOIZATION
    public static int change_Memoized(int amount, int[] coins) {
        int startingCoinIndex = 0;
        // Memoization map: key = "coinIndex|amount", value = number of combinations
        Map<String, Integer> memo = new HashMap<>();
        return dfsMemo(coins, startingCoinIndex, amount, memo);
    }

    private static int dfsMemo(int[] coins, int coinIndex, int amount, Map<String, Integer> memo) {
        // Base case 1: Exact amount reached - found one valid combination
        if (amount == 0) {
            return 1;
        }
        // Base case 2: No more coins or negative amount - invalid combination
        if (coinIndex >= coins.length || amount < 0){
            return 0;
        }

        // Check memoization
        String memoKey = coinIndex + "|" + amount;

        if (memo.containsKey(memoKey)) {
            return memo.get(memoKey);
        }

        // kanpsack:
        // 1. Skip current coin - move to next coin with same amount
        int skipCoin = dfsMemo(coins, coinIndex + 1, amount, memo);
        // 2. Use current coin - stay at same coin (unlimited use) with reduced amount
        int useCoin = dfsMemo(coins, coinIndex, amount - coins[coinIndex], memo);

        int result = skipCoin + useCoin;

        // Store in memo
        memo.put(memoKey, result);

        return result;
    }

    // BOTTOM-UP DYNAMIC PROGRAMMING (Most Efficient)
    public static int change_DP(int amount, int[] coins) {
        // Step 1: Create DP array
        // dp[i] = number of ways to make amount i
        int[] dp = new int[amount + 1];
        dp[0] = 1; // One way to make amount 0 (use no coins)

        // Step 2: Process each coin type
        for (int coin : coins) {
            // Step 3: Update dp array for current coin
            // For each amount from current coin value to target amount
            for (int subAmount = coin; subAmount <= amount; subAmount++) {
                // Add ways to make (subAmount - coin) using current coin
                dp[subAmount] += dp[subAmount - coin];
            }
        }

        // Step 4: Return ways to make target amount
        return dp[amount];
    }


    // 2D DP VERSION (More Explicit)
    public static int change_2D_DP(int amount, int[] coins) {
        int n = coins.length;

        // Step 1: Create 2D DP table
        // dp[i][j] = ways to make amount j using first i coins
        int[][] dp = new int[n + 1][amount + 1];

        // Step 2: Base case - one way to make amount 0 with any number of coins
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Step 3: Fill DP table
        for (int currCoin = 1; currCoin <= n; currCoin++) {
            for (int subAmount = 1; subAmount <= amount; subAmount++) {
                // Don't use current coin
                dp[currCoin][subAmount] = dp[currCoin - 1][subAmount];

                // Use current coin if possible
                if (subAmount >= coins[currCoin - 1]) {
                    dp[currCoin][subAmount] += dp[currCoin][subAmount - coins[currCoin - 1]]; // Note: dp[currCoin] not dp[currCoin-1]
                }
            }
        }

        return dp[n][amount];
    }
}