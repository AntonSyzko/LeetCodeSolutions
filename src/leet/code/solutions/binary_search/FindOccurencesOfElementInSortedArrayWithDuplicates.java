package leet.code.solutions.binary_search;

/*
https://www.techiedelight.com/count-occurrences-number-sorted-array-duplicates/

Given a sorted integer array containing duplicates, count occurrences of a given number. If the element is not found in the array, report that as well.

For example,

Input:  nums[] = [2, 5, 5, 5, 6, 6, 8, 9, 9, 9]
target = 5
Output: Target 5 occurs 3 times

 Input:  nums[] = [2, 5, 5, 5, 6, 6, 8, 9, 9, 9]
 target = 6
 Output: Target 6 occurs 2 times
 */
public class FindOccurencesOfElementInSortedArrayWithDuplicates {

    public static void main(String[] args) {
       int[] nums = {2, 5, 5, 5, 6, 6, 8, 9, 9, 9};
       int target = 5;

       int firstOccurenceOfTarget = findOccurrenceBinarySearch(nums, target, true);//searching for the LEFTMOST occurrence of target in array
       int lastOccurenceOfTarget =  findOccurrenceBinarySearch(nums, target, false);//searching for the RIGHTMOST occurrence of target in array

       int countOfOccurences = lastOccurenceOfTarget - firstOccurenceOfTarget +1;

       System.out.println(countOfOccurences);
    }

    private static int findOccurrenceBinarySearch(int[] nums, int target, boolean occurrenceFlag) {
        int start = 0;
        int end = nums.length - 1;

        int result = -1;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {

                result = mid;
                //TRUE for searching the LEFTMOST occurrence
                if(occurrenceFlag){

                     end = mid - 1;//search at LEFT side of array

                }else{// so FALSE -> search at RIGHT side of an array

                    start = mid + 1;//search at RIGHT side of an array

                }

            } else if (nums[mid] > target) {
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }

        return result;
    }
}
