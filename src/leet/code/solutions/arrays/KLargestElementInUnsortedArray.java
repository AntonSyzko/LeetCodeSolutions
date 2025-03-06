package leet.code.solutions.arrays;

import java.util.PriorityQueue;
import java.util.Queue;

/*
[3,2,1,5,6,4],
k = 2

output: 5

5 is second largets element in sorted array
 */
public class KLargestElementInUnsortedArray {

    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
         int k = 2;
        int kLargets =  findKLargestElement(nums, k);

        System.out.println(kLargets);


//        int[] nums2 = {3,2,3,1,2,4,5,5,6};
//        int k2= 4;
//        int kLargets2 =  findKLargestElement(nums2, k2);
//
//        System.out.println(kLargets2);
    }


    private static int findKLargestElement(int[] nums, int k) {

        Queue<Integer> minHeap = new PriorityQueue<>();//smallest at the root

        for (int num : nums) {

            minHeap.add(num);

            if (minHeap.size() > k) {//exceeded K
                minHeap.remove();//throw from the root, so smallest go out
            }
        }

        return minHeap.remove();//whats left is as bigger as K has reached
    }
}
