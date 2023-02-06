package leet.code.solutions.arrays;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;

public class MaxLengthSubbarayHavingGivenSum {

    public static void main(String[] args) {
      int [] nums = {5,6,-5,5,3,5,3,-2,0};
      //int [] nums = {5,6,1};
       int target = 8;

       findMaxLenSubarray(nums, target);
    }

    public static void findMaxLenSubarray(int[] nums, int targetSum) {
        int maxLength = 0;

        int end_index = -1;

        for (int i = 0; i < nums.length; i++) {

            int currentSum = 0;

            for (int j = i; j < nums.length; j++) {

                currentSum += nums[j];

                if (currentSum == targetSum) {
                    if (maxLength < j - i + 1) {
                        maxLength = j - i + 1;
                        end_index = j;
                    }
                }
            }
        }
        System.out.println("[" + (end_index - maxLength + 1) + ", " + end_index + "]");
    }

    public static void findMaxLenSubarray2(int[] nums, int targetSum) {


        Map<Integer, Integer> currentSumToIndex  = new HashMap<>();

        currentSumToIndex.put(0, -1);

        int currentSum = 0;

        int maxLength = 0;

        int ending_index = -1;

        for (int i = 0; i < nums.length; i++) {

            currentSum += nums[i];

            currentSumToIndex.putIfAbsent(currentSum, i);

            if (currentSumToIndex.containsKey(currentSum - targetSum) &&
                    maxLength < i - currentSumToIndex.get(currentSum - targetSum)) {

                maxLength = i - currentSumToIndex.get(currentSum - targetSum);
                ending_index = i;
            }

        }
        System.out.println("Max length " + maxLength);
        System.out.println("[" + (ending_index - maxLength + 1) + ", " + ending_index + "]");


    }
}
