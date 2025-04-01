package leet.code.solutions.blind75;

public class MaxSubarraySum {

    public static void main(String[] args) {
        int[] nums = { -2, -1,3,-1};//3

        System.out.println(maxSubarraySum(nums));
    }

    private static int maxSubarraySum(int[] nums) {
        int maxSum = nums[0];
        int currentSum = 0;

        for (int i = 0; i < nums.length; i++) {

            if(currentSum < 0){//if negative - no use of continuing - just reset to 0
                currentSum = 0;
            }

            currentSum = currentSum + nums[i];
            maxSum = Math.max(maxSum, currentSum);

        }

        return maxSum;
    }
}
