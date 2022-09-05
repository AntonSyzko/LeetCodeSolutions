package leet.code.solutions.arrays;

import java.util.IntSummaryStatistics;

public class MovingMedian {

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 7, 2, 0, 5};
        runningMedianJava8(nums);
    }

    private static void runningMedian(int[] nums) {
        int currentSum = 0;
        double currentMedian;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            currentMedian = Math.floor(currentSum / (i+1));
            System.out.println(currentMedian);
        }
    }

    private static void runningMedianJava8(int[] nums) {
        IntSummaryStatistics summaryStatistics = new IntSummaryStatistics();

        for (int num : nums){
            summaryStatistics.accept(num);
            System.out.println(summaryStatistics.getAverage());
        }
    }
    }
