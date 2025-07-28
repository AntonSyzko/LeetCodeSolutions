package leet.code.solutions.binary_search;

import java.util.Arrays;

/*

875

https://leetcode.com/problems/koko-eating-bananas/description/

Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
 */
public class KokoEatingBananas {

    public static void main(String[] args) {
        int[] bananas = {3,6,7,11};
        int hoursGivenToEat = 8;

        int minSpeedToEatAllBananas = minEatingSpeed(bananas, hoursGivenToEat);
        System.out.println(minSpeedToEatAllBananas);
    }


    private static int minEatingSpeed(int[] piles, int h) {
        int left = 1; // min is 1 banana per hour
        int right = Arrays.stream(piles).max().getAsInt();//max is the biggest pile per hour

        int res = right;//default is max pile

        while (left <= right) {

            int mid = left + (right - left) / 2;//mid here is a speed of eating bananas

            int hoursTaken = 0;

            for (int  pile : piles) {
                hoursTaken += Math.ceil((double) pile / mid);//(pile/mid) is hours taken to eat current pile with speed of MID bananas per hour ( upper ceiled)
            }

            if(hoursTaken <= h){//hours is still withing our initial h , can reduce

                res = mid;//reset current res as we know we can eat with current speed
                right = mid - 1;

            }else{
                left = mid + 1;
            }
        }

        return res;
    }
    }
