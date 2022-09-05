package leet.code.solutions.arrays;


/*
[1,1,2,3,3,3,4,5]  longest equal of 3 = 3
 */
public class LongestSubarrayOfEqualValues {
    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};//5 of fives
        int longestSubArrayOfEquals = lengthOfLongestEqualSubarray(nums);
        System.out.println(longestSubArrayOfEquals);
    }

    private static int lengthOfLongestEqualSubarray(int[] nums) {
        int currentEqualSubarrayLength = 1; //by default single number is a subarray and it UNIQUE - equal to itself

        int longestSubarrayLength = 1;//by default single number is a subarray and it UNIQUE and LONGEST so far

        for (int i = 0; i < nums.length - 1; i++) {

            if (nums[i] == nums[i + 1]) { //contiguous equal elements

                currentEqualSubarrayLength++;

            } else {//not equal

                currentEqualSubarrayLength = 1;//reset to 1 - single equal to itself subarray
            }
            //choose LONGEST so far
            longestSubarrayLength = Math.max(longestSubarrayLength, currentEqualSubarrayLength);
        }
        return longestSubarrayLength;
    }
}
