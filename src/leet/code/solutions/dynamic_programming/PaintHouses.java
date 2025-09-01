package leet.code.solutions.dynamic_programming;

import java.util.Arrays;

/*
In this problem, we are given a scenario where we have n houses in a row, and we need to paint each house in such a way that no two adjacent houses have the same color.

There are three colors available: red, blue, and green. The cost of painting each house with each color is different and is provided to us in the form of a n x 3 cost matrix - costs.

Each row in the costs matrix corresponds to a house, and each column to a color (where the first column is red, the second is blue, and the third is green).
For instance, costs[0][0] represents the cost of painting the first house red,
while costs[1][2] represents the cost of painting the second house green.

Our goal is to find the minimum total cost to paint all the houses while making sure that no two adjacent houses are painted the same color.

[
  [17, 2 ,17]
  [16, 16, 5 ]
  [14, 3, 19]
 }
  cannot paint adjacent houses with same color ( color is defined by index ( 0 , 1 , 2)

  so -> min path is 2 -> 5 -> 3
  paint first house ( row 0 ) with color 1 ( price 2 )
  paint 1 house 9 row 1) with color 2 ( price 5)
  paint last 2 house ( row 2 ) with color 1 ( price 3 )
 */
public class PaintHouses {

    public static void main(String[] args) {
        int [][] housesWithPrices = {
                {17, 2 ,17},
                {16, 16, 5 },
                {14, 3, 19}
        };

        int minPrice = paintHouses(housesWithPrices);

        System.out.println(minPrice);

        }

        //O(n)
        //O(n)
        //this is TOP -> DOWN DP
        private static int paintHouses(int[][] housesWithPrices) {
             int n = housesWithPrices.length;

             //only ONE dimension - lastly calculated ROW
             int[] DP = new int[n]; // dp contains previously calculated prices with min options calculated
              Arrays.fill(DP, 0);

            for (int i = 0; i < n; i++) {

                int priceStartingWIthColor_0 = housesWithPrices[i][0] + Math.min(DP[1],DP[2]);//current house color price + MIN of two PREVIOUSLY adjacent ( not same color index e.g. if on index color 1 - we can take MIN from 0 or 2 )
                int priceStartingWIthColor_1 = housesWithPrices[i][1] + Math.min(DP[0],DP[2]);//MIND MINS !!!!!!!!!!!!!!!!!!!!
                int priceStartingWIthColor_2 = housesWithPrices[i][2] + Math.min(DP[0],DP[1]);

                DP = new int[]{priceStartingWIthColor_0,priceStartingWIthColor_1,priceStartingWIthColor_2};//each time updating to lastly calculated minimals
            }

            int minPrice = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                minPrice = Math.min(minPrice, DP[i]);//min of single LASTLY calculated ROW
            }
            return minPrice;

        }
}