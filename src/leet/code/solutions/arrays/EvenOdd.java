package leet.code.solutions.arrays;

import java.util.Arrays;

public class EvenOdd {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 10};
        System.out.println("ORIGINAL");
        System.out.println(Arrays.toString(nums));
        System.out.println("EVENS FIRST");
        int[] evenOddRedistributed = redistributeEvenOdd(nums);
        System.out.println(Arrays.toString(evenOddRedistributed));
    }

    //space O(1)
    //time O(n)
    private static int[] redistributeEvenOdd(int[] nums) {
        //pointers
        int nextEven = 0;
        int nextOdd = nums.length - 1;

        while (nextEven < nextOdd) {

            if (nums[nextEven] % 2 == 0) { // MOD 2 check for even
                nextEven++; //move even pointer

            } else {//odd
                //swap
                int temp = nums[nextEven];
                nums[nextEven] = nums[nextOdd];
                nums[nextOdd] = temp;

                nextOdd--;//decrease odd pointer
            }
        }
        return nums;
    }
}
