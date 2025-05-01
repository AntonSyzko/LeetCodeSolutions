package leet.code.solutions.dynamic_programming;

/*
https://www.techiedelight.com/rod-cutting/

Given a rod of length n and a list of rod prices of length i, where 1 <= i <= n, find the optimal way to cut the rod into smaller rods to maximize profit.

For example, consider the following rod lengths and values:

Input: length[] = [1, 2, 3, 4, 5,  6,  7,  8 ]
price[] =         [1, 5, 8, 9, 10, 17, 17, 20]

Rod length: 4
 Best: Cut the rod into two pieces of length 2 each to gain revenue of 5 + 5 = 10

 Cut           Profit
 4                9
 1, 3            (1 + 8) = 9
 2, 2            (5 + 5) = 10
 3, 1            (8 + 1) = 9
 1, 1, 2         (1 + 1 + 5) = 7
 1, 2, 1         (1 + 5 + 1) = 7
 2, 1, 1         (5 + 1 + 1) = 7
 1, 1, 1, 1      (1 + 1 + 1 + 1) = 4
 */
public class RodCuttingProblem {

    public static void main(String[] args) {
        int prices[] = { 1, 5, 8, 9, 10, 17, 17, 20 };

        // rod length
        int n = 4;

        System.out.println("Profit is " + rodCut_DP(prices, n));
    }

    //The time complexity of the above bottom-up solution is O(n2) and requires O(n) extra space, where n is the rod length.
    public static int rodCut_DP(int[] prices, int rodLength) {
        // `DP[i]` stores the maximum profit achieved from a rod of length `i`
        int[] DP = new int[rodLength + 1];
        //DP[0] = 0;//just for visibility here - cutting rod with 0 lenth yeilds 0 profit

        // consider a rod of length `i`
        for (int i = 1; i <= rodLength ; i++) {

            // divide the rod of length `i` into two rods of length `j` and `i-j` each and take maximum
            for (int j = 1; j <= i ; j++) {

                DP[i] = Math.max(DP[i], prices[j - 1] + DP[i - j]);

            }
        }

        return DP[rodLength];

    }


    //The time complexity of the above solution is O(n^n) and occupies space in the call stack, where n is the rod length.
    public static int rodCut(int[] price, int rodLength){

        if(rodLength==0) {//BASE
            return 0;
        }

        int maxValue = Integer.MIN_VALUE;


        // one by one, partition the given rod of length `n` into two parts of
        // length (1, n-1), (2, n-2), (3, n-3), … ,(n-1, 1), (n, 0) and
        // take maximum
        for (int i = 1; i <= rodLength ; i++) {

            int currentCutCost = price[i]  - rodCut(price, rodLength - i);

            if(currentCutCost > maxValue){
                maxValue = currentCutCost;
            }
        }

        return maxValue;
    }
}
