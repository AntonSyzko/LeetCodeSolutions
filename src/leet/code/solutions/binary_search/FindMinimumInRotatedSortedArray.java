package leet.code.solutions.binary_search;

/*
https://neetcode.io/problems/find-minimum-in-rotated-sorted-array

You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times. For example, the array nums = [1,2,3,4,5,6] might become:

[3,4,5,6,1,2] if it was rotated 4 times.
[1,2,3,4,5,6] if it was rotated 6 times.
Notice that rotating the array 4 times moves the last four elements of the array to the beginning. Rotating the array 6 times produces the original array.

Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.

A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?

Example 1:

Input: nums = [3,4,5,6,1,2]

Output: 1

Example 2:

Input: nums = [4,5,0,1,2,3]

Output: 0

Example 3:

Input: nums = [4,5,6,7]

Output: 4
Constraints:

1 <= nums.length <= 1000
-1000 <= nums[i] <= 1000

 */
public class FindMinimumInRotatedSortedArray {

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2,3};

        int min = findMinNik(nums);
        System.out.println(min);
    }

    private static int findMin(int[] nums) {
        if(nums.length == 0) return -1;
        if(nums.length == 1) return nums[0];

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                right = mid;//search left part
            } else {
                left = mid + 1;//search right part
            }
        }
        return nums[left];
    }

    private static int findMinNik(int[] nums) {
        if(nums.length==0) return -1;
        if(nums.length == 1) return nums[0];

        int left = 0, right = nums.length-1;

        while(left < right) {

            int mid = left + (right - left) / 2;

            if( mid > 0 && (nums[mid] < nums[mid - 1])) {

                return nums[mid];

            }else if (nums[left] <= nums[mid] && nums[mid] > nums[right] ) {

                left = mid + 1;//look at right from mid

            }else{

                right = mid - 1;//look at left from mid

            }
        }

        return nums[left];
    }


        private static int findMinMy(int[] nums) {
        if(nums.length==0) return -1;

        if(nums.length == 1) return nums[0];

        int left = 0, right = nums.length-1;

        while(left <= right){

            int mid =  left + (right - left)/2;

            // Compare with next element (if within bounds)

            if( mid < nums.length - 1  && nums[mid] > nums[mid+1]){
                return nums[mid+1];
            }

            // Compare with previous element (if within bounds)
            if( mid > 0 && (nums[mid-1] > nums[mid])){
                return nums[mid];
            }

           // Decide which half to search
            if (nums[mid] > nums[0]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return nums[0];
    }
}
