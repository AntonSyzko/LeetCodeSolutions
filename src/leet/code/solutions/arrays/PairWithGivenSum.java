package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://www.techiedelight.com/find-pair-with-given-sum-array/

Given an unsorted integer array, find a pair with the given sum in it.

Input:
nums = [8, 7, 2, 5, 3, 1]
target = 10
Output:
Pair found (8, 2)
or
Pair found (7, 3)

Input:
nums = [5, 2, 6, 8, 1, 9]
target = 12
Output: Pair not found
 */
public class PairWithGivenSum {
    public static void main(String[] args) {
        int[] nums = {8, 7, 2, 5, 3, 1};
        findPAirWithGivenSumBruteForce(nums, 10);
        findPairUsingSorting(nums, 10);
        findPairUsingHashing(nums, 10);
    }

    //O(n^2)
    private static void findPAirWithGivenSumBruteForce(int[] nums, int targetSum) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                if (nums[i] + nums[j] == targetSum) {
                    System.out.println("found sum " + targetSum + " with elements: " + nums[i] + " + " + nums[j]);
                    return;//if only one result is enough
                }
            }
        }
        System.out.println(" sum not found");
    }


    // Function to find a pair in an array with a given sum using sorting
    //The time complexity of the above solution is O(n.log(n)) and doesn’t require any extra space.
    public static void findPairUsingSorting(int[] nums, int target) {
        // sort the array in ascending order
        Arrays.sort(nums);

        // maintain two indices pointing to endpoints of the array
        int lowIndex = 0;
        int highIndex = nums.length - 1;

        // reduce the search space `nums[low…high]` at each iteration of the loop
        // loop till the search space is exhausted
        while (lowIndex < highIndex) {

            //sum found
            if (nums[lowIndex] + nums[highIndex] == target) {
                System.out.println("found sum " + target + " with elements: " + nums[lowIndex] + " + " + nums[highIndex]);
                return;
            }

            // increment `low` index if the total is less than the desired sum;
            if (nums[lowIndex] + nums[highIndex] < target) {
                lowIndex++;
            } else { // decrement `high` index if the total is more than the desired sum
                highIndex--;
            }
        }
        System.out.println(" sum not found");
    }

    // Function to find a pair in an array with a given sum using hashing
    public static void findPairUsingHashing(int[] nums, int target) {
        Map<Integer, Integer> mapElementToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            // check if pair (nums[i], target-nums[i]) exists
            // if the difference is seen before, print the pair
            if (mapElementToIndex.containsKey(target - nums[i])) {
                                                                                //get value by index !!!
                System.out.println("found sum " + target + " with elements: " + nums[mapElementToIndex.get(target - nums[i])] + " + " + nums[i]);
                return;
            }
            // store index of the current element in the map
            mapElementToIndex.put(nums[i], i);
        }
        // we reach here if the pair is not found
        System.out.println("Pair not found");
    }
}
