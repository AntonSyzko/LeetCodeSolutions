package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
given SORTED  array of nums return two numbers making sum of K
[-3,0,2,4,5]  K=7  result [2,5]
 */
public class TwoSumVariousWays {

    public static void main(String[] args) {
        int[] nums = new int[]{-3, 0, 2, 4, 5};
        int[] sumToK = twoSumTwoPointers(nums, 7);
        System.out.println(Arrays.toString(sumToK));
    }

    private static int[] twoSumBruteForce(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] + nums[j] == k) {
                    return new int[]{nums[i], nums[j]};
                }
            }
        }
        return new int[0];
    }

    private static int[] twoSumSet(int[] nums, int k) {
        Set<Integer> alreadyMet = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int differenceToTargetK = k - nums[i];
            if (alreadyMet.contains(differenceToTargetK)) {
                return new int[]{differenceToTargetK, nums[i]};
            }
            alreadyMet.add(nums[i]);
        }
        return new int[0];
    }

    private static int[] twoSumBinarySearch(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {

            int differenceToTarget = k - nums[i];

            int left = i + 1;//num after i
            int right = nums.length - 1;

            while (left <= right) {

                int mid = left + (right - left) / 2;

                if (differenceToTarget == nums[mid]) { // match
                    return new int[]{differenceToTarget, nums[i]};
                }

                if (differenceToTarget < nums[mid]) {//diff is smaller
                    right = mid - 1; // move right bound lower
                } else { // diff is too big
                    left = mid + 1;//move left bound higher
                }
            }
        }

        return new int[0];//no match

    }

    private static int[] twoSumTwoPointers(int[] nums, int k) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int currentSum = nums[left] + nums[right];

            if (currentSum == k) {//match
                return new int[]{nums[left], nums[right]};
            } else if ((nums[left] + nums[right] < k)) {//sum too low
                left++;//set left pointer higher
            } else { // too big
                right--; //set right pointer lower
            }
        }
        return new int[0];
    }
}
