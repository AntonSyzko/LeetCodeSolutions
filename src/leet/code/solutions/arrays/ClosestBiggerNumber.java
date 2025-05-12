package leet.code.solutions.arrays;

import java.util.Arrays;

public class ClosestBiggerNumber {

    public static void main(String[] args) {
        int[] nums = {2,4,3,8,1};
        int[] minClosest = nextPermutation(nums);
        System.out.println(Arrays.toString(minClosest));

        System.out.println("*************************");

//        int[] nums2 = {3,1,6,8,9};
//        int[] minClosest2 = nextPermutation(nums2);
//        System.out.println(Arrays.toString(minClosest2));

    }

    public static int[] nextPermutation(int[] nums) {
        // Edge case check
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        // Find the first element (pivot) from the right that is smaller than its next element
        int pivotIndex = findPivotIndex(nums);
        System.out.println("pivotIndex: " + pivotIndex + " element : " + nums[pivotIndex]);

        // If no such element exists, entire array is in descending order
        // Return array sorted in ascending order (smallest permutation)
        if (pivotIndex == -1) {
            reverseArray(nums, 0, nums.length - 1);
            return nums;
        }

        // Find the smallest element to the right of pivot that is greater than the pivot
        int nextGreaterIndex = findNextGreaterIndex(nums, pivotIndex);
        System.out.println("nextGreaterIndex: " + nextGreaterIndex + " element : " + nums[nextGreaterIndex]);


        // Swap the pivot and the next greater element
        swap(nums, pivotIndex, nextGreaterIndex);
        System.out.println("\r\n\t after swap " + Arrays.toString(nums));

        System.out.println("reverse  from " + (pivotIndex + 1) + " to " + (nextGreaterIndex + 1));

        // Reverse the elements to the right of the pivot position
        reverseArray(nums, pivotIndex + 1, nums.length - 1);

        System.out.println("\r\n\t after reverse " + Arrays.toString(nums));


        return nums;
    }

    // Find the first element from the right that is smaller than its next element
    private static int findPivotIndex(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                return i;
            }
        }
        return -1; // No such element found
    }

    // Find the smallest element to the right of pivotIndex that is greater than the pivot
    private static int findNextGreaterIndex(int[] nums, int pivotIndex) {
        int pivot = nums[pivotIndex];
        int nextGreaterIndex = pivotIndex + 1;

        // Find the smallest element greater than pivot
        for (int i = pivotIndex + 1; i < nums.length; i++) {
            if (nums[i] > pivot && nums[i] <= nums[nextGreaterIndex]) {
                nextGreaterIndex = i;
            }
        }

        return nextGreaterIndex;
    }

    // Swap elements at positions i and j
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // Reverse the elements in the array between start and end (inclusive)
    private static void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

}
