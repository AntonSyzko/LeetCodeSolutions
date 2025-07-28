package leet.code.solutions.binary_search;

public class SearchInRotatedSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,1,2};
        int target = 1;

        int found = search(nums, target);
        System.out.println(found);
    }

    private static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }


            if(nums[left] < nums[mid]) { // nums[left ... mid] are sorted.
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                }else{
                    left = mid + 1;
                }
            }else{// nums[mid ...  n - 1] are sorted.
                if(nums[right] >= target && target > nums[mid]) {
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    }
