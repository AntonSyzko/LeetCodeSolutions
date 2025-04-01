package leet.code.solutions.backtracking;

import java.util.Arrays;

/*
https://www.techiedelight.com/find-combinations-of-elements-satisfies-given-constraints/

Given a positive number n, find all combinations of 2×n elements such that every element from 1 to n appears exactly twice and the distance between its two appearances is exactly equal to the value of the element.

For example,

Input: n = 3
Output:
3 1 2 1 3 2
2 3 1 2 1 3
 */
public class FindAllCombinationsSatisfyingConstraints {

    public static void main(String[] args) {
        // given number
        int n = 3;

        // create an input array double the size of a given number with all its elements initialized by -1
        int[] res = new int[2 * n];
        Arrays.fill(res, -1); // -1 here will mean slot is not set ( not visited)

        // start from element 1
        int startNum = 1;
        findAllCombinations(res, startNum, n);
    }


    public static void findAllCombinations(int[] res, int startNum, int givenNumberCeiling) {
        //BASE
        if (startNum > givenNumberCeiling) {//2 * n condition met
            System.out.println(Arrays.toString(res));
            return;
        }

        //iterate 2 * N times as per condition
        for (int indexInRes = 0; indexInRes < givenNumberCeiling * 2; indexInRes++) {

            int secondSlotIndex = indexInRes  + startNum + 1;//second slot is in startNum(+1) slots away from current index

            if (res[indexInRes] == -1 //if current slot for this index is not yet occupied
                && secondSlotIndex < givenNumberCeiling * 2 // and the added next index is within boundaries
                && res[secondSlotIndex] == -1) { // and next slot ( out of 2 slots by condition) is NOT occupied

                res[indexInRes] = startNum;//set current slot with start num
                res[secondSlotIndex] = startNum;//set next shifted slot with start num


                findAllCombinations(res, startNum + 1, givenNumberCeiling);//recur with start num + 1 ( next )


                res[indexInRes] = -1; // BACKTRACK - unmark visisted for next combination
                res[secondSlotIndex] = -1;// BACKTRACK

            }
        }
    }
}
