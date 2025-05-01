package leet.code.solutions.binary_search;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
https://www.techiedelight.com/find-k-closest-elements-to-given-value-array/

Given a sorted integer array, find the k closest elements to target in the array where k and target are given positive integers.

The target may or may not be present in the input array. If target is less than or equal to the first element in the input array,
return first k elements. Similarly, if target is more than or equal to the last element in the input array, return the last k elements.
 The returned elements should be in the same order as present in the input array.

 For example,

Input:  [10, 12, 15, 17, 18, 20, 25], k = 4, target = 16
Output: [12, 15, 17, 18]

Input:  [2, 3, 4, 5, 6, 7], k = 3, target = 1
Output: [2, 3, 4]

Input:  [2, 3, 4, 5, 6, 7], k = 2, target = 8
Output: [6, 7]
 */
public class FindKClosestElementInArray {

    public static void main(String[] args) {
        int[] nums = {10, 12, 15, 17, 18, 20, 25 };
        int target = 16;
        int k = 4;
        System.out.println(findKClosestElements(nums, k, target));

        int[] nums2 = {2, 3, 4, 5, 6, 7 };
        int target2 = 1;
        int k2 = 3;
        System.out.println(findKClosestElements(nums2, k2, target2));

        int[] nums3 = {2, 3, 4, 5, 6, 7 };
        int target3 = 8;
        int k3 = 2;
        System.out.println(findKClosestElements(nums3, k3, target3));

        int[] nums4 = {1,2,3,4,5};
        int target4 = 3;
        int k4 = 4;
        System.out.println(findKClosestElements(nums4, k4, target4));
    }

    public static List<Integer> findKClosestElements(int[] nums, int k, int target){
        int left = 0, right = nums.length - 1;

        while (right - left >= k){

            if(Math.abs(nums[left] - target) > Math.abs(nums[right] - target)){//absolute distance to target from left > absolute distance from right

              left++;//decrease left

            }else{

              right--;

            }
        }

        //left & right stopped at proper pivot point - so just extract
        return Arrays.stream(nums, left, right+1).boxed().collect(Collectors.toList());
    }


}
