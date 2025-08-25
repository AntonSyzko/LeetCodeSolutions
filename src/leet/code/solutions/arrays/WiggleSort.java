package leet.code.solutions.arrays;

/*
Given an unsorted array nums, reorder it in-place such that nums[0] <nums[1] >nums[2] <nums[3].... Example:
Input: nums = [3,5,2,1,6,4]
Output: One possible answer is [3,5,1,6,2,4]
 */
public class WiggleSort {

    public static void main(String[] args) {
        int guess = guessNumber(10, 6);
        System.out.println(guess);
    }

    public static int guessNumber(int n,  int calle) {
        int left  = 0;
        int right = n;

        int result = 2;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if(calle == mid) {
                result = 0;
            }else if ( calle > mid){
                left = mid + 1;
                result = 1;
                System.out.println("too high");
            }else{
                right = mid - 1;
                result = -1;
                System.out.println("too low");
            }

            if(result == 0){
                break;
            }
        }

        return result;

    }
}