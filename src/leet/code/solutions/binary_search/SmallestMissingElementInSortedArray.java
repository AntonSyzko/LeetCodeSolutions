package leet.code.solutions.binary_search;

/*
https://www.techiedelight.com/find-smallest-missing-element-sorted-array/

Given a sorted array of non-negative distinct integers, find the smallest missing non-negative element in it.

For example,

Input:  nums[] = [0, 1, 2, 6, 9, 11, 15]
Output: The smallest missing element is 3

Input:  nums[] = [1, 2, 3, 4, 6, 9, 11, 15]
Output: The smallest missing element is 0

 Input:  nums[] = [0, 1, 2, 3, 4, 5, 6]
 Output: The smallest missing element is 7
 */
public class SmallestMissingElementInSortedArray {

    public static void main(String[] args) {
      int[] nums = {0, 1, 2, 6, 9, 11, 15};

      int smallestMissing = findSmallestMissing(nums, 0, nums.length - 1);
      System.out.println(smallestMissing);
    }


    /*
    The time complexity of the above solution is O(log(n)) and requires O(log(n)) implicit space for the call stack.
     */
    private static int findSmallestMissing(int[] nums, int start, int end){

      if(start > end){//BASE
          return start;
      }

      int mid = start + (end - start)/2;


        // if the mid-index matches with its value, then the mismatch lies on the right half
          if(nums[mid]==mid){

              return findSmallestMissing(nums, mid+1, end);

          }else{

              // mismatch lies on the left half
              return findSmallestMissing(nums, start, mid-1);

          }
    }
}

/**
    A simple analysis of the problem shows us that the smallest missing number would be the element’s index, which is not equal to its element.

    For instance, consider array [0, 1, 2, 6, 9, 11, 15].
    Here, the smallest missing element is 3 since 6 is present at index 3 (instead of element 3).
    If all elements in the array are at their right position,
    then the smallest missing number is equal to the array size;
     For instance, 6 in the case of [0, 1, 2, 3, 4, 5].
 **/
