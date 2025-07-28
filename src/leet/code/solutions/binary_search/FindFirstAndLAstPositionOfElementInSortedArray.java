package leet.code.solutions.binary_search;

import java.util.Arrays;

public class FindFirstAndLAstPositionOfElementInSortedArray {

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,3,4,5,6};
        int target = 2;
        int[] range = searchRange(nums, target);
        System.out.println(Arrays.toString(range));
    }


    //O(log n)
    //O(1)
    private static int[] searchRange(int[] nums, int target) {
        int firstOccurrenceIndex = findBinSearch(nums, target);

        if(firstOccurrenceIndex == nums.length  || nums[firstOccurrenceIndex] != target ){
            return new int[]{-1, -1};
        }

        int lastOccurrenceIndex = findBinSearch(nums, target + 1 ) - 1;// +1 is to search for the next BIGGER by 1 from target, and -1 is to account actually to prev to it target

        return new int[]{firstOccurrenceIndex, lastOccurrenceIndex};
    }

    private static int findBinSearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length ;

        while (left < right) {//mind NO <=

            int mid = left + (right - left) / 2;

            if(nums[mid] >= target){//mind >=
                right = mid ;//we move RIGHT as LEFTMOST as possible to find the FIRST possible occurrence of MID
            }else{
                left = mid + 1;
            }
        }

        return left;//mind return left
    }
}
