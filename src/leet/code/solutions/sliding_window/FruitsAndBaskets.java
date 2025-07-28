package leet.code.solutions.sliding_window;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/fruit-into-baskets/description/

You are visiting a farm that has a single row of fruit trees arranged from left to right. The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.

You want to collect as much fruit as possible. However, the owner has some strict rules that you must follow:

You only have two baskets, and each basket can only hold a single type of fruit. There is no limit on the amount of fruit each basket can hold.
Starting from any tree of your choice, you must pick exactly one fruit from every tree (including the start tree) while moving to the right. The picked fruits must fit in one of your baskets.
Once you reach a tree with fruit that cannot fit in your baskets, you must stop.
Given the integer array fruits, return the maximum number of fruits you can pick.

Example 1:

Input: fruits = [1,2,1]
Output: 3
Explanation: We can pick from all 3 trees.
Example 2:

Input: fruits = [0,1,2,2]
Output: 3
Explanation: We can pick from trees [1,2,2].
If we had started at the first tree, we would only pick from trees [0,1].
Example 3:

Input: fruits = [1,2,3,2,2]
Output: 4
Explanation: We can pick from trees [2,3,2,2].
If we had started at the first tree, we would only pick from trees [1,2].
 */
public class FruitsAndBaskets {

    public static void main(String[] args) {
        int [] trees = {1,2,3,2,2};
        int maxFruitInBasketOfWto = totalFruit(trees);
        System.out.println(maxFruitInBasketOfWto);

        int [] trees2 = {3,3,3,1,2,1,1,2,3,3,4};
        int maxFruitInBasketOfWto2 = totalFruit(trees2);
        System.out.println(maxFruitInBasketOfWto2);

        int [] trees3 = {1,2,1};
        int maxFruitInBasketOfWto3 = totalFruit(trees3);
        System.out.println(maxFruitInBasketOfWto3);
    }

    /*
    The time complexity is O(n) and
    space complexity is O(1) since we'll have at most 3 entries in our HashMap.
     */
    private static int totalFruit(int[] fruits) {
        if(fruits == null || fruits.length == 0) return 0;
        if(fruits.length == 1) return 1;

        int maxRes = Integer.MIN_VALUE;

        int windowStart = 0 ;

        Map<Integer,Integer> fruitOccurrences = new HashMap<>();

        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {

            int currFruit = fruits[windowEnd];

            fruitOccurrences.put(currFruit,fruitOccurrences.getOrDefault(currFruit,0) + 1);//store curr fruit

            while (fruitOccurrences.size() > 2) {//WHILE ! window of > 2 fruits in basket hit

                int fruitAtWindowStart = fruits[windowStart];

                fruitOccurrences.put(fruitAtWindowStart,fruitOccurrences.get(fruitAtWindowStart) - 1);

                if(fruitOccurrences.get(fruitAtWindowStart) == 0) {
                    fruitOccurrences.remove(fruitAtWindowStart);
                }

                windowStart++;
            }

            //after while
            maxRes = Math.max(maxRes, ( windowEnd - windowStart + 1 ));//update max

        }
        return maxRes;
    }
}
