package leet.code.solutions.binary_search;

/*
You are given an array of length n which was originally sorted in ascending order. It has now been rotated between 1 and n times.
Assuming all elements in the rotated sorted array nums are unique, return the minimum element of this array.

What if duplicates are allowed?
Would this affect the run-time complexity? How and why?


3 3 1 3 3 3

A solution that runs in O(n) time is trivial, can you write an algorithm that runs in O(log n) time?
 */

public class FindMinimumInRotatedSortedArrayWithDuplicates {

    public static void main(String[] args) {
        int[] nums = {3,3,1,3,3,3};

        int mmin = findMin(nums);

        System.out.println(mmin);
    }

    private static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[right]) {

               right = mid;

            }else if ( nums[mid] > nums[right] ) {

                left = mid + 1;

            }else{

               right--;

            }
        }

        return nums[left];
    }


    /*
            Worst-case Time Complexity: O(n)
        In the worst case, we might have to examine every element
        Example worst case: [1, 1, 1, 1, 1, 1]
   */

    private static int findMin2(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid element is greater than right element,
            // the minimum must be in the right half
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            }
            // If mid element is less than right element,
            // the minimum must be in the left half
            else if (nums[mid] < nums[right]) {
                right = mid;
            }
            // If they are equal, we can't determine which half
            // has the minimum, so we reduce the search space conservatively
            else { // nums[mid] == nums[right]
                right--;
            }
        }

        return nums[left];
    }

    public int findMinRec(int[] nums) {
        return findMinHelper(nums, 0, nums.length - 1);
    }

    private int findMinHelper(int[] nums, int left, int right) {
        // Base case 1: If there's only one element
        if (left == right) {
            return nums[left];
        }

        // Base case 2: If array is not rotated or this segment is not rotated
        if (nums[left] < nums[right]) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        // If mid element is greater than right element,
        // the minimum must be in the right half
        if (nums[mid] > nums[right]) {
            return findMinHelper(nums, mid + 1, right);
        }
        // If mid element is less than right element,
        // the minimum must be in the left half including mid
        else if (nums[mid] < nums[right]) {
            return findMinHelper(nums, left, mid);
        }
        // If they are equal, we can't determine which half
        // Check both halves, but we can optimize by reducing the right boundary
        else {
            // Optimization: If left and mid are equal, minimum is in right half
            if (nums[left] > nums[mid]) {
                return findMinHelper(nums, left + 1, mid);
            }

            // Another optimization: we can check if mid is the minimum
            if (mid > left && nums[mid - 1] > nums[mid]) {
                return nums[mid];
            }
            if (mid < right && nums[mid] > nums[mid + 1]) {
                return nums[mid + 1];
            }

            // Check both halves (worst case for duplicates)
            int leftMin = findMinHelper(nums, left, mid - 1);
            int rightMin = findMinHelper(nums, mid + 1, right);

            return Math.min(leftMin, rightMin);
        }
    }

    // Alternative implementation for the duplicate case that's more efficient
    // but still potentially O(n) in worst case
    private int findMinHelperAlternative(int[] nums, int left, int right) {
        // Base case
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;

        // If mid element is greater than right element,
        // the minimum must be in the right half
        if (nums[mid] > nums[right]) {
            return findMinHelper(nums, mid + 1, right);
        }
        // If mid element is less than right element,
        // the minimum must be in the left half including mid
        else if (nums[mid] < nums[right]) {
            return findMinHelper(nums, left, mid);
        }
        // If they are equal, we can't determine which half
        // So we reduce the search space conservatively
        else {
            // We can at least eliminate one element
            return findMinHelper(nums, left, right - 1);
        }
    }

    public int findMinRec2(int[] num) {
        return findMin2(num, 0, num.length-1);
    }
    public int findMin2(int[] num, int left, int right){
        if(right==left){
            return num[left];
        }
        if(right == left +1){
            return Math.min(num[left], num[right]);
        }

        int middle = (right-left)/2 + left;
// already sorted
        if(num[right] > num[left]){
            return num[left];
//right shift one
        }else if(num[right] == num[left]){
            return findMin2(num, left+1, right);
//go right
        }else if(num[middle] >= num[left]){
            return findMin2(num, middle, right);
//go left
        }else{
            return findMin2(num, left, middle);
        }
    }
}
