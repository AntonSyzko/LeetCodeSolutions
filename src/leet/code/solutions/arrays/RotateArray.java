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

        k =  k % nums.length;// % module is here if K ( rotatitons) exceeds length
        int firstPartOfArrayLength = nums.length - k;

        reverse(nums,0, firstPartOfArrayLength - 1);//rotate first part
        reverse(nums, firstPartOfArrayLength , nums.length-1);//rotate second part
        reverse(nums,0, nums.length-1);//rotate the ENTIRE WHOLE array
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



    private static int[] rotate(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len];

        int indexInRotated = 0;
        int kCopy = k;

        while( kCopy >= 0){
            int elementFromOrigonal = nums[len - kCopy -1];
            res[indexInRotated] = elementFromOrigonal;
            indexInRotated++;
            kCopy--;
        }

        int indexInOriginal = 0;

        while(k > 0){
            int elementFromOriginal = nums[indexInOriginal];
            res[indexInRotated] = elementFromOriginal;//indexInRotated continues growing after prev while loop
            indexInRotated++;
            indexInOriginal++;
            k--;
        }

        return res;
    }
}
