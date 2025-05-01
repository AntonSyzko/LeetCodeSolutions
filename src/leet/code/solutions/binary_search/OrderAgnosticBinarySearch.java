package leet.code.solutions.binary_search;

/*
https://www.callicoder.com/order-agnostic-binary-search/

Given a sorted array of numbers, find if a given number key is present in the array. Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.

Example 1:

Input: [2, 8, 11, 19], key = 11
Output: 2

Example 2:

Input: [32, 28, 17, 9, 3], key = 9
Output: 3
 */
public class OrderAgnosticBinarySearch {

    public static void main(String[] args) {
         int[] nums = {2, 8, 11, 19};//index = 2
         int target = 11;
            System.out.println(binarySearchRecursive(nums, target));

        int[] nums2 = {32, 28, 17, 9, 3};//index = 3
         int target2 = 9;
        System.out.println(binarySearchRecursive(nums2, target2));

        int[] nums3 = {-1,0,3,5,9,12};
        int target3 = 2;
        System.out.println(binarySearchRecursive(nums3, target3));
    }

    private static int binarySearchRecursive(int[] arr, int key) {
        if (arr == null || arr.length == 0) {
            return -1;
        }

        return binarySearchRecursiveHelper(arr, key,0,arr.length-1);
    }


    private static int binarySearchRecursiveHelper(int[] arr, int key, int start, int end) {

            if(start>end){//BASE
                return -1;
            }

            int mid = start + (end - start) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if((arr[start] < arr[end] && arr[mid] < key) || (arr[start] > arr[end] && arr[mid] > key)) {
                return binarySearchRecursiveHelper(arr,key, mid + 1, end);
            }else{
                return binarySearchRecursiveHelper(arr,key, start, mid -1);
            }
    }



    private static int binarySearch(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            int mid  = start + (end - start) / 2;

            if(arr[mid] == key){
                return mid;
            } else if(arr[start] < arr[end]){
                 if(arr[mid] > key){
                     end = mid - 1;
                 }else{
                     start = mid + 1;
                 }
             }else{
                 if(arr[mid] > key){
                     start = mid + 1;
                 }else{
                     end = mid - 1;
                 }
             }

        }

        return -1;
    }

}
