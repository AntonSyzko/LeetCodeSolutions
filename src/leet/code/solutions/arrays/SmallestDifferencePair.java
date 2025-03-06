package leet.code.solutions.arrays;

import java.util.Arrays;

/*
Given two non-empty arrays of integers, find the pair of values (one value from each array) with the smallest (non-negative) difference.

Example

Input: [1, 3, 15, 11, 2], [23, 127, 235, 19, 8]

Output: [11, 8]; this pair has the smallest difference.
 */
public class SmallestDifferencePair {

    public static void main(String[] args) {
       int[] first = new int[]{1,3,15,11,2};
       int[] second = new int[]{23,127,235,19,8};
       int[] smallestDiifPair = findSmallestDifferencePair(first, second);
       System.out.println(Arrays.toString(smallestDiifPair));
    }


    //Time complexity: O(mlog(m) + nlog(n))
    public static int[] findSmallestDifferencePair(int[] array1, int[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);

        int[] res = new int[2];

        int firstPointer = 0;
        int secondPointer = 0;

        int smallestDiff = Integer.MAX_VALUE;


        while (firstPointer < array1.length && secondPointer < array2.length) {

            int currDiff = Math.abs(array1[firstPointer] - array2[secondPointer]);

            if(currDiff < smallestDiff){

                smallestDiff = currDiff;
                res[0] = array1[firstPointer];
                res[1] = array2[secondPointer];
            }

            if(array1[firstPointer] < array2[secondPointer]){

                firstPointer++;

            } else if (array1[firstPointer] > array2[secondPointer]) {

                secondPointer++;

            }

        }

        return res;
    }

    }
