package leet.code.solutions.binary_search;

/*
Given an infinite sorted array (or an array with unknown size), find if a given target value is present in the array.
Write a function to return the index of the target if it is present in the array, otherwise return -1.

Example 1:

Input: [2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28. 32, 35], target = 16
Output: 7
Explanation: The target is present at index '7' in the array.
Example 2:

Input: [2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28. 32, 35], target = 19
Output: -1
Explanation: The target not present in the array
 */
public class SearchInfiniteArray {

    public static void main(String[] args) {
    int[] nums = {2, 5, 7, 9, 10, 12, 15, 16, 18, 20, 24, 28, 32, 35};
    int target = 16;

    System.out.println(searchInfiniteArray(nums, target));
    }

    private static int searchInfiniteArray(int[] nums, int target) {
        int start = 0;
         int end = 1;//start with end just on second position

        // First try to find the lower and upper bounds before applying binary search
        while (target > nums[end]) {
            start = end;
            end *= 2; // increase end exponentially ( double it)
        }

         while (start <= end) {

             int mid = start + (end - start)/2;

             if(nums[mid] == target){//res found

                 return mid;

             } else if( target > nums[mid]  ){//targer is bigger than mid

                 start = mid+1;

             }else{//target is lower than mid

                end = mid - 1;

             }
         }

         return -1;
    }

}
