package leet.code.solutions.arrays;

import java.util.Arrays;

/*
[ -4, -1, 0 , 3 , 10 ]
[ 0, 1 , 9 , 16, 100] - squares of above but sorted
 */
public class SquaresOfSortedArray {

    public static void main(String[] args) {
        int[] nums = {-4,-1,0,3,10};
        int[] sorted = sortedSquares(nums);
        System.out.println(Arrays.toString(sorted));
    }

    private static int[] sortedSquares(int[] nums) {

       int length = nums.length;

       int[] res = new int[length];

       int positivePointer = 0;

       while (positivePointer < length && nums[positivePointer] < 0) {//stopped at first POSTIVE number
           positivePointer++;
       }

       int negativePointer = positivePointer - 1;//so one last before first positive occurence is firts negative

        int index = 0;

        while (negativePointer >= 0 && positivePointer < length ) {

            if(nums[negativePointer] * nums[negativePointer] < nums[positivePointer] * nums[positivePointer]) {//if square of negative pointer  is LESS than positive

                res[index] = nums[negativePointer] * nums[negativePointer];//insert square of negative
                negativePointer -=1;//decrement negative

            }else {

                res[index] = nums[positivePointer] * nums[positivePointer];//insert square of positive
                positivePointer +=1;

            }

            index +=1;
        }

        while(negativePointer >= 0){
            res[index] = nums[negativePointer] * nums[negativePointer];
            negativePointer -=1;
            index++;
        }

        while(positivePointer < length){
            res[index] = nums[positivePointer] * nums[positivePointer];
            positivePointer +=1;
            index++;
        }

        return res;
    }


}
