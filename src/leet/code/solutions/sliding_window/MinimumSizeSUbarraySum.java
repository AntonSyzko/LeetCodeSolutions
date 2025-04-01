package leet.code.solutions.sliding_window;

/*
target = 7
nums [ 2,3,1,2,4,3 ]

out: [4,3] has minimum elements = 2 and target = 7


 */
public class MinimumSizeSUbarraySum {

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        int misSubarrayLen = findMinSizeSubarrayEqualsToSum(nums, target);
        System.out.println(misSubarrayLen);
    }

    private static int findMinSizeSubarrayEqualsToSum(int[] nums, int target){
        if(nums==null || nums.length==0){
            return -1;
        }


        int windowStart = 0;

        int resMinSubarrayLen = nums.length;//the entire array len to start with

        int ongoingSum = 0;

        for (int widnowEnd = 0; widnowEnd < nums.length; widnowEnd++) {

            ongoingSum += nums[widnowEnd];

            while(ongoingSum >= target){

                int slidingWindowSize = widnowEnd - windowStart + 1;

                resMinSubarrayLen = Math.min(resMinSubarrayLen, slidingWindowSize);

                ongoingSum -= nums[windowStart];

                windowStart++;
            }
        }

        return resMinSubarrayLen;
    }
}
