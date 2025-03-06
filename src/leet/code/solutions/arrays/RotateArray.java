package leet.code.solutions.arrays;

import java.util.Arrays;

public class RotateArray {

    public static void main(String[] args) {

        int[] array = {1,2,3,4,5,6,7};
        System.out.println(Arrays.toString(array));
        rotateReverse(array, 3);
        System.out.println(Arrays.toString(array));
    }

    //O(n*k)
    private static void rotateBubbleSort(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = nums.length-1; j >0; j--) {
                int temp = nums[j];
                nums[j] = nums[j-1];
                nums[j-1] = temp;
            }
        }
    }


    private static void rotateReverse(int[] nums, int k) {

        k =  k % nums.length;
        int firstPartOfArrayLength = nums.length - k;

        reverse(nums,0, firstPartOfArrayLength - 1);//rotate first part
        reverse(nums, firstPartOfArrayLength , nums.length-1);//rotate second part
        reverse(nums,0, nums.length-1);//rotate the entire aray
    }


    private static void reverse(int[] array, int start, int end) {
        if(array == null || array.length == 1){
            return;
        }

        while (start < end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

}
