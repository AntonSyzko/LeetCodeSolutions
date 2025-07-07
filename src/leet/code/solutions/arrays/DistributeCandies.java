package leet.code.solutions.arrays;

import java.util.Arrays;

/*
1103

https://leetcode.com/problems/distribute-candies-to-people/description/

We distribute some number of candies, to a row of n = num_people people in the following way:
We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.
Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.
This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).
Return an array (of length num_people and sum candies) that represents the final distribution of candies.

Example 1:

Input: candies = 7, num_people = 4
Output: [1,2,3,1]
Explanation:
On the first turn, ans[0] += 1, and the array is [1,0,0,0].
On the second turn, ans[1] += 2, and the array is [1,2,0,0].
On the third turn, ans[2] += 3, and the array is [1,2,3,0].
On the fourth turn, ans[3] += 1 (because there is only one candy left), and the final array is [1,2,3,1]
 */
public class DistributeCandies {


    public static void main(String[] args) {
        int[] res = distributeCandies(7,4);
        System.out.println(Arrays.toString(res));
    }

    private static int[] distributeCandies(int candies, int num_people) {
        int[] result = new int[num_people];

        for (int i = 0; candies > 0 ; i++) {//keep looping until candies > 0

            int newCandyValue = i + 1;

            result[i % num_people] += Math.min(candies, newCandyValue);//modulo ( i % nums_people)  keeps looping from start

            candies -=  newCandyValue;//decrement candies by currently used candies

        }

        return result;
    }

        private static int[] distributeCandies2(int candies, int num_people) {
            int[] result = new int[num_people];

            int currentCandy = 1;
            int index = 0;

            while (candies > 0) {

                int currentCandyToGive = Math.min(currentCandy, candies);

                result[index] += currentCandyToGive;

                candies -= currentCandyToGive;


                index = (index + 1) % num_people;

                currentCandy++;
            }

            return result;

    }
}
