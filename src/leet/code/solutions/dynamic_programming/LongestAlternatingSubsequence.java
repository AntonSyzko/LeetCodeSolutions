package leet.code.solutions.dynamic_programming;

/*
https://www.techiedelight.com/longest-alternating-subsequence-problem/

Given an integer array, find the length of the longest subsequence with alternate low and high elements in the array.

For example,

Input: nums[] = [8, 9, 6, 4, 5, 7, 3, 2, 4]
Output: 6Explanation: There are several subsequences with alternate low and high elements having length 6:
[8, 9, 6, 7, 3, 4]
[8, 9, 6, 7, 2, 4]
[8, 9, 4, 7, 3, 4]
[8, 9, 4, 7, 2, 4]
 */
public class LongestAlternatingSubsequence {

    public static void main(String[] args) {
        int[] nums = { 8, 9, 6, 4, 5, 7, 3, 2, 4 };
        System.out.println("The length of the longest alternating subsequence is " + findLongestSequence(nums));

        int[] nums2 = { 1,2,3,4,5,1,0};
        System.out.println("The length of the longest alternating subsequence is " + findLongestSequence(nums2));
    }

    /*
     TIME
        O(n)
     SPACE:
        O(1)
    where n is the length of the array.
     */
    private static int findLongestSequence(int[] nums) {

        if(nums.length == 0) return 0;

        int high = 1;
        int low = 1;

        int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int curr = nums[i];

            if(curr > prev){

                high = low + 1;//high counter increased by 1 from prev LOW

            }else if (curr < prev){

                low = high +1;// low counter increased by 1 from prev HIGH

            }

            prev = curr;//update prev
        }

        return Math.max(high, low);
    }
}
