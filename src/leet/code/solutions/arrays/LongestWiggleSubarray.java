package leet.code.solutions.arrays;

public class LongestWiggleSubarray {


    public static void main(String[] args) {
        int[] test1 = {1, 10,5,2,6,3,4};
        System.out.println("Test 1: " + wiggleMaxLength(test1)); // Expected: 6
    }

    //O(n)
    //O(1)

    /**
     * Longest Wiggle Subarray (consecutive elements)
     * Key difference from subsequence: must reset when pattern breaks (equal elements)
     */
    public static int wiggleMaxLength(int[] nums) {
        if(nums==null || nums.length==0) return 0;

        int upCount = 1;
        int downCount = 1;

        int max = 1;

        for (int i = 1; i < nums.length; i++) {

            if(nums[i]>nums[i-1]) {
                upCount = downCount + 1; // EXTEND from where previos sign switch was, subsequence grows from last sign ( negative positive) switch
                max = Math.max(max, downCount);

            } else if(nums[i]<nums[i-1]) {
                downCount = upCount + 1;//EXTEND,  subsequence grows from last sign ( negative positive) switch
                max = Math.max(max, downCount);

            } else{
                upCount = 1;
                downCount = 1;
            }

        }

        return    Math.max(max, Math.max(upCount,downCount));
    }
}