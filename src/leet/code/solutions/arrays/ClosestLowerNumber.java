package leet.code.solutions.arrays;

import java.util.Arrays;

/*
given array of nums find closest lower number containing same digits

so meaning - whichever first possible lower number possible from these digits - retrun it

input : 3, 1, 2, 0, 8, 4, 1, 2, 5, 5
output: 3, 1, 2, 0, 8, 4, 2, 1, 5, 5
 */
public class ClosestLowerNumber {

    public static void main(String[] args) {
        int[] nums = {3,1,2,0,8,4,1,2,5,5};
        int[] minClosest = getClosestLowerNum(nums);
        System.out.println(Arrays.toString(minClosest));

        System.out.println("*************************");

//        int[] nums2 = {3,1,6,8,9};
//        int[] minClosest2 = getClosestLowerNum(nums2);
//        System.out.println(Arrays.toString(minClosest2));

    }

    private  static  int[] getClosestLowerNum(int[] nums){
        if(nums==null || nums.length==0) return null;

        System.out.println("on start " + Arrays.toString(nums));

        int indexToSwap = getSwapIndexInNum(nums);//get index of first digit that is BIGGER than it's follower one in array going backwards from the end

        System.out.println("indexToSwap: "+indexToSwap + " is number " + nums[indexToSwap]);

        if(indexToSwap==-1) return null;

        int swapIndexInSubNum = getSwapIndexInSubnum(nums, nums[indexToSwap]); //index of  first digit that is LOWER to num[indexToSwap] going  to the right of it going from the END !!!

        System.out.println("swapIndexInSubNum : "+indexToSwap + " is number " + nums[swapIndexInSubNum]);


        swap(nums, indexToSwap, swapIndexInSubNum);//swap em

        System.out.println("after swap " + Arrays.toString(nums));

        reverse(nums,swapIndexInSubNum + 1, nums.length-1);

        System.out.println("after reverse " + Arrays.toString(nums));

        return nums;
    }

    //get index of first digit that is BIGGER than it's follower one in array going backwards from the end
    private static int getSwapIndexInNum(int[] nums) {

        for (int i = nums.length - 1; i >= 1 ; i--) {//going backwards

            if(nums[i - 1 ] > nums[i]){//first ever BIGGER than following met
                return i - 1 ;// return it's index
            }
        }

        return -1;
    }

    //swapping with first digit that is LOWER to given swapValue going  to the right of it
    //index of  first digit that is LOWER to num[indexToSwap] going  to the right of it
    private static int getSwapIndexInSubnum(int[] nums, int swapValue) {

        for (int i = nums.length - 1 ; i >= 0 ; i--) {//going backwards

            if(nums[i] < swapValue){//if curr num is LOWER than given

                return i ; //return it's index
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int indexToSwapFrom, int indexToSwapTo) {
        int temp = nums[indexToSwapFrom];
        nums[indexToSwapFrom] = nums[indexToSwapTo];
        nums[indexToSwapTo] = temp;
    }

    private static void reverse(int[] nums, int startIndex, int endIndex) {
        int swapsCount = (endIndex - startIndex + 1) / 2;//+1 cause java will keep division floor and we need ceiling 

        for (int swapAttempt = 0; swapAttempt < swapsCount; swapAttempt++) {

            swap(nums, startIndex + swapAttempt , endIndex - swapAttempt);
            
        }
    }
}
