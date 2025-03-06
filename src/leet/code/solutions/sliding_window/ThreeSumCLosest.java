package leet.code.solutions.sliding_window;

import java.util.Arrays;

/*
Given an array S of n integers, find three integers in S such that the sum is closest to
a given number, target. Return the sum of the three integers. You may assume that
each input would have exactly one solution.

For example, given array S = -1 2 1 -4,
and target = 1.

The sum that is closest to the target is 2.
 (-1 + 2 + 1 = 2).


 */
public class ThreeSumCLosest {

    public static void main(String[] args) {
      int[] nums = {-1, 2,1,-4};
      int target = 1;

      int closestToTarget = threeSumClosest(nums, target);
        System.out.println(closestToTarget);
    }

    private static int threeSumClosest(int[] num, int target) {
        int min = Integer.MAX_VALUE;
        int result = 0;

        Arrays.sort(num);// SORT !!!!!!!!!!!!

        for (int first = 0; first < num.length; first++) {

            int second = first + 1;
            int third = num.length - 1;

            while (second < third) {

                int sum = num[first] + num[second] + num[third];

                int diff = Math.abs(sum - target);

                if(diff == 0) return 0;

                if (diff < min) {
                    min = diff;
                    result = sum;
                }

                if (sum <= target) {
                    second++;//mobe second index
                } else {
                    third--;//shring window from end
                }
            }
        }
        return result;
    }
}
