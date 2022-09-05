package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.Map;

/*
https://www.techiedelight.com/find-majority-element-in-an-array-boyer-moore-majority-vote-algorithm/

Given an integer array containing duplicates,
return the majority element if present. A majority element appears more than n/2 times, where n is the array size.

For example, the majority element is 2 in array {2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2}.
 */
public class MajorityVote {
    public static void main(String[] args) {
       // int[] nums = {2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2};
        int[] nums = {1,2,3,1,5};
        int maxVote = findMajorityElementBoyerMoore(nums);
        System.out.println(maxVote);
    }

    // Function to find the majority element present in a given array
    //The time complexity of the above solution is O(n) and requires O(n) extra space.
    private static int findMajorityElement(int[] nums) {
        Map<Integer, Integer> mapOfFrequencies = new HashMap<>();
        for (int each : nums) {
            mapOfFrequencies.put(each, mapOfFrequencies.getOrDefault(each, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> each : mapOfFrequencies.entrySet()) {
            if (each.getValue() >= nums.length / 2) {//more than half - means majority
                return each.getKey();
            }
        }
        return -1;
    }

    // Boyer–Moore majority vote algorithm, linear time
    private static int findMajorityElementBoyerMoore(int[] nums) {
        // `majorityElement` stores the majority element (if present)
        int majorityElement = -1;

        // initialize counter `occurences` with 0
        int occurences = 0;

        // do for each element `nums[j]` in the array
        for (int j = 0; j < nums.length; j++) {
            // if counter `occurences` becomes 0
            if (occurences == 0) {
                // set the current candidate to `nums[j]`
                majorityElement = nums[j];

                // reset the counter to 1
                occurences = 1;
            }
            // otherwise, increment the counter if `nums[j]` is a current candidate
            else if (majorityElement == nums[j]) {
                occurences++;
            }
            // otherwise, decrement the counter if `nums[j]` is a current candidate
            else {
                occurences--;
            }
        }

        return majorityElement;
    }
}
