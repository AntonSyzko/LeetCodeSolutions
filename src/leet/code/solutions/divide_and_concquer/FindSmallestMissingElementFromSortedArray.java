package leet.code.solutions.divide_and_concquer;

/*
https://www.techiedelight.com/find-smallest-missing-element-sorted-array/

Given a sorted array of non-negative distinct integers, find the smallest missing non-negative element in it.

For example,

Input:  nums[] = [0, 1, 2, 6, 9, 11, 15]    Output: The smallest missing element is 3
 Input:  nums[] = [1, 2, 3, 4, 6, 9, 11, 15]    Output: The smallest missing element is 0
 Input:  nums[] = [0, 1, 2, 3, 4, 5, 6]    Output: The smallest missing element is 7
 */
public class FindSmallestMissingElementFromSortedArray {

    public static void main(String[] args) {
        int [] nums = {0, 1, 2, 6, 9, 11, 15};
        int smallestMissing = findSmallestMissingElementByComparingToItsIndex(nums);
        System.out.println(smallestMissing);

        int [] nums2 = {1, 2, 3, 4, 6, 9, 11, 15};
         int smallestMissing2 = findSmallestMissingElementByComparingToItsIndex(nums2);
        System.out.println(smallestMissing2);

        int [] nums3 = {0, 1, 2, 3, 4, 5, 6};
         int smallestMissing3 = findSmallestMissingElementByComparingToItsIndex(nums3);
        System.out.println(smallestMissing3);

        int [] nums4 = {0, 1, 2, 3, 4, 5, 6};
        int smallestMissing4 = findSmallestMissingElementBinarySearch(nums4, 0, nums4.length-1);
        System.out.println(smallestMissing4);
    }


    private static int findSmallestMissingElementBinarySearch(int[] nums, int left, int right) {
        if(left >  right){//BASE
            return left;
        }

        int mid =  left + (right - left)/2;


        // if the mid-index matches with its value, then the mismatch lies on the right half, cause missing is bigger than current mid
        if(nums[mid] == mid){
            return findSmallestMissingElementBinarySearch(nums, mid + 1, right);
        }else{// mismatch lies on the left half
            return findSmallestMissingElementBinarySearch(nums, left, mid - 1);
        }
    }


        //O(n)
    private static int findSmallestMissingElementByComparingToItsIndex(int[] nums) {

        int smallestMissing  = Integer.MAX_VALUE;

        for (int i = 0; i < nums.length ; i++) {

            if(nums[i] != i){ // if elelemnt is not same as it's index -> index is the smallest elelemnt missing
                smallestMissing = Math.min(smallestMissing, i);
            }

        }

        if(smallestMissing == Integer.MAX_VALUE){//all in order - then missing is the one after the very last
            return nums.length;
        }

        return smallestMissing;

    }

    //O (n)
    private static int findSmallestMissingElement(int[] nums) {

        int missingElelemnt ;
        int smallestMissingElelemntRes = Integer.MAX_VALUE;

        if(nums[0] != 0){//0 is smallest in any sorted array of non negative numbers
            smallestMissingElelemntRes = 0;
        }

        for (int i = 1; i < nums.length ; i++) {
            if (nums[i] != nums[i - 1] + 1) {
                missingElelemnt = nums[i -1] + 1;
                smallestMissingElelemntRes = Math.min(smallestMissingElelemntRes, missingElelemnt);

            }
        }

        if(smallestMissingElelemntRes == Integer.MAX_VALUE){
            smallestMissingElelemntRes = nums[nums.length - 1] + 1;
        }

        return smallestMissingElelemntRes;
    }


}
