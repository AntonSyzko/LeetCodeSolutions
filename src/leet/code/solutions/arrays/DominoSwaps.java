package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/description/

n a row of dominoes, tops[i] and bottoms[i] represent the top and bottom halves of the ith domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)

We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.

Return the minimum number of rotations so that all the values in tops are the same, or all the values in bottoms are the same.

If it cannot be done, return -1.



Example 1:


Input: tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
Output: 2
Explanation:
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
Example 2:

Input: tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
Output: -1
Explanation:
In this case, it is not possible to rotate the dominoes to make one row of values equal
 */
public class DominoSwaps {
    public static void main(String[] args) {
        int[] tops = {2,1,2,4,2,2};
        int [] bottoms = {5,2,6,2,3,2};

        int minRotations = minDominoRotations(tops, bottoms);
        System.out.println(minRotations);
    }

    /*
    Time & Space Complexity

            Time Complexity: O(n),
                where n is the length of the arrays. We only need to iterate through all n dominoes once.
            Space Complexity: O(1),
                as we use arrays of fixed size (7).
     */
    private static int minDominoRotations(int[] tops, int[] bottoms) {

        int len = tops.length;//both are same len

        int[] topCounts = new int[7];
        int[] bottomCounts = new int[7];
        int[] same = new int[7];//same in both arrays at same index - so no use of swap - it'll remain same after swap

        for(int i = 0; i < len; i++){
            topCounts[tops[i]]++;
            bottomCounts[bottoms[i]]++;

            if(tops[i] == bottoms[i]){//at same index same dice
                same[tops[i]]++;
            }
        }


        for(int domDice = 1; domDice <= 6; domDice++){//iterate through 6 possible dice variations

            int deltaToAll = (topCounts[domDice] + bottomCounts[domDice])  - same[domDice];//exclude same as swapping same does not help

            if(deltaToAll >= len){//so this dice has more occurrences than an entire array length -> meaning we can swap smth in either

                int numberOfSwapsInTop =      len - topCounts[domDice];
                int numberOfSwapsInBottom =   len -  bottomCounts[domDice];

                //pick min diff to len
                return Math.min(numberOfSwapsInTop, numberOfSwapsInBottom);
            }
        }

        return -1;
    }

    //----alt less efficient ---
    public int minDominoRotations2(int[] tops, int[] bottoms) {
        int n = tops.length;

        // Try to make all values equal to tops[0]
        int rotations1 = checkRotations(tops[0], tops, bottoms, n);

        // Try to make all values equal to bottoms[0]
        int rotations2 = checkRotations(bottoms[0], tops, bottoms, n);

        int result = Math.min(rotations1, rotations2);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    /*
    Time & Space Complexity

Time Complexity: O(n), where n is the length of the arrays. We only need to iterate through all n dominoes twice.
Space Complexity: O(1), as we only use a constant amount of extra space.
     */
    private int checkRotations(int target, int[] tops, int[] bottoms, int n) {
        int topRotations = 0;
        int bottomRotations = 0;

        for (int i = 0; i < n; i++) {
            // If neither top nor bottom matches the target, it's impossible
            if (tops[i] != target && bottoms[i] != target) {
                return Integer.MAX_VALUE;
            }

            // Count rotations for making tops equal to target
            if (tops[i] != target) {
                topRotations++;
            }

            // Count rotations for making bottoms equal to target
            if (bottoms[i] != target) {
                bottomRotations++;
            }
        }

        return Math.min(topRotations, bottomRotations);
    }
}
