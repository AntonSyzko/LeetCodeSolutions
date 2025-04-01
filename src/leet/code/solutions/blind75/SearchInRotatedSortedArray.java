package leet.code.solutions.blind75;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
            int[] nums = {3,4,5,6,7,1,2};
            int target = 1;
          int foundAt = search(nums,target);
          System.out.println(foundAt);

    }

    private static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Direct hit
            if (nums[mid] == target) {
                return mid;
            }

            // Check which side is sorted

            if (nums[left] <= nums[mid]) {// Left side is sorted

                if (nums[left] <= target && target < nums[mid]) {// Target is in the left sorted portion

                    right = mid - 1;

                } else {// Target is in the right portion

                    left = mid + 1;

                }

            } else {// Right side is sorted

                if (nums[mid] < target && target <= nums[right]) {// Target is in the right sorted portion

                    left = mid + 1;

                } else {// Target is in the left portion

                    right = mid - 1;

                }
            }
        }

        return -1;  // Target not found
    }
}
