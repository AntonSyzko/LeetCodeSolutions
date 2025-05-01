package leet.code.solutions.arrays;

/*

https://leetcode.com/problems/candy/description/

There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
Return the minimum number of candies you need to have to distribute the candies to the children.

Example 1:

Input: ratings = [1,0,2]
Output: 5
Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.


Example 2:

Input: ratings = [1,2,2]
Output: 4
Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
The third child gets 1 candy because it satisfies the above two conditions.

Constraints:

n == ratings.length
1 <= n <= 2 * 104
0 <= ratings[i] <= 2 * 104
 */

import java.util.Arrays;

public class Candy {

    // Test cases
    public static void main(String[] args) {
        Candy solution = new Candy();

        // Example 1
        int[] ratings1 = {1, 0, 2};
        System.out.println("Example 1 Output: " + solution.candy(ratings1)); // Expected: 5

        // Example 2
        int[] ratings2 = {1, 2, 2};
        System.out.println("Example 2 Output: " + solution.candy(ratings2)); // Expected: 4

        // Additional test cases
        int[] ratings3 = {1, 3, 2, 2, 1};
        System.out.println("Example 3 Output: " + solution.candy(ratings3)); // Expected: 7
    }

    /**
     * Returns the minimum number of candies needed for distribution.
     *
     * Algorithm:
     * 1. Initialize an array to track candies for each child, starting with 1 candy each
     * 2. First pass (left to right): Ensure higher-rated children get more candies than their left neighbors
     * 3. Second pass (right to left): Ensure higher-rated children get more candies than their right neighbors
     * 4. Sum up all candies
     *
     * @param ratings Array of children's ratings
     * @return Minimum total candies required
     */
    /*
       Time Complexity: O(n)

                We perform three passes through the array of length n:

                One pass to initialize the candies array: O(n)
                One left-to-right pass: O(n)
                One right-to-left pass: O(n)
                One final pass to sum the candies: O(n)

                Total: O(4n) = O(n)

                Space Complexity: O(n)

                    We use an additional array of size n to track the number of candies for each child
     */
    public int candy(int[] ratings) {
        int n = ratings.length;

        // Edge case: if there's only one child, return 1
        if (n == 1) {
            return 1;
        }

        // Initialize array to track candies for each child
        int[] candies = new int[n];

        // Start by giving each child 1 candy (minimum requirement)
        Arrays.fill(candies, 1);

        // First pass: left to right
        // Ensure children with higher ratings than their left neighbor
        // get more candies than that neighbor
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Second pass: right to left
        // Ensure children with higher ratings than their right neighbor
        // get more candies than that neighbor
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                // We take the max because we don't want to undo the work of the first pass
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Calculate the total number of candies
        int totalCandies = 0;
        for (int candy : candies) {
            totalCandies += candy;
        }

        return totalCandies;
    }

    private static int candy2(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int[] candies = new int[ratings.length];
        candies[0] = 1;

            //from let to right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            } else {// if not ascending, assign 1
                candies[i] = 1;
            }
        }

        int result = candies[ratings.length - 1];

        //from right to left
        for (int i = ratings.length - 2; i >= 0; i--) {
            int cur = 1;
            if (ratings[i] > ratings[i + 1]) {
                cur = candies[i + 1] + 1;
            }
            result += Math.max(cur, candies[i]);
            candies[i] = cur;
        }

        return result;
    }
}
