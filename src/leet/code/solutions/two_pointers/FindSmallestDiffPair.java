package leet.code.solutions.two_pointers;

import java.util.Arrays;

/*
https://www.callicoder.com/smallest-difference-pair-two-unsorted-arrays/

Given two non-empty arrays of integers, find the pair of values (one value from each array) with the smallest (non-negative) difference.

Example

Input: [1, 3, 15, 11, 2], [23, 127, 235, 19, 8]

Output: [11, 8]; this pair has the smallest difference.
 */
public class FindSmallestDiffPair {

    public static void main(String[] args) {
        int[] nums1 = {1,3,15,11,2};
        int[] nums2 = {23, 127, 235, 19, 8};
        System.out.println("\r\n\t smallest diff " + Arrays.toString(findPairWithSmallestDiffMy(nums1, nums2)));
    }

    private static int[] findPairWithSmallestDiffMy(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int nums1Pointer = 0;
        int nums2Pointer = 0;

        int[] smallestDiffPair = new int[2];

        int smallestDiff = Integer.MAX_VALUE;

        while (nums1Pointer < nums1.length && nums2Pointer < nums2.length) {

            int currDiff = nums1[nums1Pointer] - nums2[nums2Pointer];

            if(currDiff<0) {//non negative diff we care

                nums1Pointer++;

            }else{
                smallestDiff = Math.min(smallestDiff, currDiff);

                smallestDiffPair[0] = nums1[nums1Pointer];
                smallestDiffPair[1] = nums2[nums2Pointer];

                nums2Pointer++;
            }
        }
        return smallestDiffPair;
    }

    public static int[] findSmallestDifferencePair(int[] a1, int[] a2) {
        Arrays.sort(a1);
        Arrays.sort(a2);

        double smallestDiff = Double.MAX_VALUE;
        int[] smallestDiffPair = new int[2];
        int i = 0, j = 0;

        while(i < a1.length && j < a2.length) {
            double currentDiff = Math.abs(a1[i] - a2[j]);
            if(currentDiff < smallestDiff) {
                smallestDiff = currentDiff;
                smallestDiffPair[0] = a1[i];
                smallestDiffPair[1] = a2[j];
            }
            if(a1[i] < a2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return smallestDiffPair;
    }


private static int findSmallestDiff(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int nums1Pointer = 0;
        int nums2Pointer = 0;

        int smallestDiff = Integer.MAX_VALUE;

        while (nums1Pointer < nums1.length && nums2Pointer < nums2.length) {
            int currDiff = nums1[nums1Pointer] - nums2[nums2Pointer];
            if(currDiff<0) {
                nums1Pointer++;
            }else{
                smallestDiff = Math.min(smallestDiff, currDiff);
                nums2Pointer++;
            }
        }
        return smallestDiff;
    }


        private static int findPairWithSmallestDiffBruteForce(int[] nums1, int[] nums2) {
        int currDiff = Integer.MAX_VALUE;
        int smallestDiff = Integer.MAX_VALUE;

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if((nums1[i] - nums2[j]) >= 0) {
                    currDiff = Math.min(currDiff, nums1[i] - nums2[j]);
                    smallestDiff = Math.min(smallestDiff, currDiff);
                }
            }
        }
        return smallestDiff;
    }
}
