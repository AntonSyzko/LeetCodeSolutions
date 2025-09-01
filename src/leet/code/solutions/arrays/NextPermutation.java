package leet.code.solutions.arrays;

import java.util.Arrays;

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of
numbers.
If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending
order). The replacement must be in-place, do not allocate extra memory.
Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand
column.
1,2,3 [U+FFFD] 1,3,2
3,2,1 [U+FFFD] 1,2,3
1,1,5 [U+FFFD] 1,5,1
 */
public class NextPermutation {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3};
        nextPermutation(nums1);
        System.out.println(Arrays.toString(nums1));

        int[] nums2 = {3,2,1};
        nextPermutation(nums2);
        System.out.println(Arrays.toString(nums2));

        int[] nums3 = {1,1,5};
        nextPermutation(nums3);
        System.out.println(Arrays.toString(nums3));
    }

    private static void nextPermutation(int[] nums) {
        //1. find index of element that is less that it's prev (right to left)
        int lessThanPrev = -1;
        for (int i = nums.length -1 ; i > 0 ; i--) {//right to left
            if(nums[i] > nums[i - 1]){
                lessThanPrev = i - 1; // mind -1 -> cause less than prev
                break;//MIND break !!!
            }
        }

        if(lessThanPrev == -1){//not found
            reverse(nums, 0 , nums.length -1);//reverse entire
            return;
        }

        //2. Find element that is bigger then previously found index
        int biggerThanLessThanPrev = nums.length -1;
        for (int i = nums.length - 1; i >  0 ; i--) {
            if(nums[i] > nums[lessThanPrev]){
                biggerThanLessThanPrev = i;
                break;
            }
        }

        //3. swap elements at indexes
        swap(nums, lessThanPrev, biggerThanLessThanPrev);

        //4. reverse array from first index +1 to end
        reverse(nums, lessThanPrev + 1 , nums.length -1);//mind +1

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int left, int right) {

        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

}

/**
 The steps to solve this problem:
 1) scan from right to left, find the first element that is less than its previous one.
 4 5 6 3 2 1
 |
 p

 2) scan from right to left, find the first element that is greater than p.
 4 5 6 3 2 1
 |
 q

 3) swap p and q
 4 5 6 3 2 1
 swap
 4 6 5 3 2 1

 4) reverse elements [p + 1, nums.length]
 4 6 1 2 3 5

 * */