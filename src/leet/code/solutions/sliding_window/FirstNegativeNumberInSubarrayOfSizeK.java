package leet.code.solutions.sliding_window;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class FirstNegativeNumberInSubarrayOfSizeK {

    public static void main(String[] args) {
        int[] nums = { 10, -1, -5, 7, -15, 20, 18, 24};
        int k = 3;

        System.out.println(Arrays.toString(findFirstNegativeNumberInSubarrayOfSizeK_BruteForce(nums, k)));
    }

    private static int[] findFirstNegativeNumberInSubarrayOfSizeK_BruteForce(int[] arr, int k) {

        int [] result = new int[arr.length - k + 1];

        int start = 0, end = 0;

        Queue<Integer> queue = new LinkedList<>();//store negatives as wee see then FIFO

        while (end < arr.length) {

            if(arr[end] < 0){
                queue.add(arr[end]);
            }

            int currWidowLength = end - start +1;

            if(currWidowLength == k){

                if(queue.isEmpty()){

                    result[start] = 0;

                }else {
                    int firstNegativeInQ = queue.peek();
                    result[start] = firstNegativeInQ;

                    if(arr[start] == firstNegativeInQ){
                        queue.poll();
                    }
                }

                start++;
            }

            end++;
        }

        return result;
    }
}
