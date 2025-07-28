package leet.code.solutions.binary_search;

/*
Given a sorted array and a target value, return the index if the target is found. If not,
return the index where it would be if it were inserted in order. You may assume no
duplicates in the array.
Here are few examples.

[1,3,5,6], 5 -> 2
[1,3,5,6], 2 -> 1
[1,3,5,6], 7 -> 4
[1,3,5,6], 0 -> 0
 */
public class SearchInsertPosition {

    public static void main(String[] args) {
        int[] arr = {1,3,5,6};
        int target = 2;
        int insertPosition = searchInsertBinSearch(arr,target);
        System.out.println(insertPosition);
    }

/*
        The key insight is that when the binary search ends without finding the target, the left pointer points to the position where the target should be inserted to maintain the sorted order.
 */

    /*
    The time complexity is O(log n) because we're using binary search. The space complexity is O(1) since we only use a constant amount of extra space.
     */
    private static int searchInsertBinSearch(int[] array, int target) {
       int left = 0, right = array.length - 1;

       while (left <= right) {
           int mid = left + (right - left) / 2;

           if (array[mid] == target) {

               return mid;

           } else if (target < array[mid]) {

               right = mid - 1;

           }else{

               left = mid + 1;

           }
       }

        // If we exit the loop, target wasn't found
        // 'left' will be the position where target should be inserted
       return left;

    }


    private static int searchInsertBruteForce(int[] array, int target) {
        if(array == null || array.length == 0){
            return 0;
        }

        if(target <= array[0]){
            return 0;
        }

        for (int i = 0; i < array.length -1 ; i++) {
             if(target > array[i] && target <= array[i+1]){
                  return i+1;
             }
        }
        return array.length;

    }
}
