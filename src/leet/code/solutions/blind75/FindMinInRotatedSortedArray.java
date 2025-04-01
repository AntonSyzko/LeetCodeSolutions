package leet.code.solutions.blind75;

public class FindMinInRotatedSortedArray {

    public static void main(String[] args) {
       int[] nums = {4,5,1,2,3};
       System.out.println(findMinInRotatedSortedArray(nums));

        int[] nums2 = {2,3,4,5,1};
        System.out.println(findMinInRotatedSortedArray(nums2));
    }

    private static int findMinInRotatedSortedArray(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // If array is not rotated or edge case
        if (nums.length == 1 || nums[left] < nums[right]) {
            return nums[left];
        }

        while (left < right) {

            int mid = left + (right - left) / 2;

            // If mid element is greater than rightmost element, minimum must be in the right half
            if (nums[mid] > nums[right]) {

                left = mid + 1;

            } else {// Otherwise, minimum is either at mid or in the left half

                right = mid;

            }
        }

        // When left == right, we've found the minimum
        return nums[left];
    }
}
