package leet.code.solutions.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/merge-sorted-array/
You are given two integer arrays nums1 and nums2, sorted in non-decreasing order,
and two integers m and n, representing the number of elements in nums1 and nums2 respectively.
Merge nums1 and nums2 into a single array sorted in non-decreasing order.
The final sorted array should not be returned by the function, but instead be stored inside the array nums1.
To accommodate this, nums1 has a length of m + n, where the first m elements denote the elements that should be merged,
and the last n elements are set to 0 and should be ignored. nums2 has a length of n.

Example 1:
Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
Output: [1,2,2,3,5,6]
Explanation: The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.

Example 2:
Input: nums1 = [1], m = 1, nums2 = [], n = 0
Output: [1]
Explanation: The arrays we are merging are [1] and [].
The result of the merge is [1].

Example 3:
Input: nums1 = [0], m = 0, nums2 = [1], n = 1
Output: [1]
Explanation: The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.

Constraints:
nums1.length == m + n
nums2.length == n
0 <= m, n <= 200
1 <= m + n <= 200
-109 <= nums1[i], nums2[j] <= 109

Follow up: Can you come up with an algorithm that runs in O(m + n) time?
 */
public class MergeSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 0, 0};
        int[] nums2 = new int[]{2, 4};
        merge(nums1, 2, nums2, 2);


        nums1 = new int[]{1, 2, 3, 0, 0, 0};
        nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, 3);

        nums1 = new int[]{4, 5, 6, 0, 0, 0};
        nums2 = new int[]{1, 2, 3};
        merge(nums1, 3, nums2, 3);
    }

    // O (m + n )
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        m--;//since indexes shifted, start from 0
        n--;
        int indexOfNums1Tail = nums1.length - 1;//we will add to the end as we traverse ( -1 - shift index )

        while (indexOfNums1Tail >= 0) {

            if (m < 0) {//empty first array
                nums1[indexOfNums1Tail] = nums2[n];
                n--;//inserted from nums2 - decrement n
            } else if (n < 0) {//empty second array
                nums1[indexOfNums1Tail] = nums1[m];
                m--;//inserted from nums1 - decrement m
            } else {
                if (nums1[m] > nums2[n]) { // if in first MORE than second
                    nums1[indexOfNums1Tail] = nums1[m]; //shift FIRST to the end index
                    m--;// nums1 shifted - decrement m !!!
                } else { // if in second MORE than first
                    nums1[indexOfNums1Tail] = nums2[n];//shift SECOND to the end index
                    n--;// nums2 shifted - decrement n !!!
                }
            }

            indexOfNums1Tail--;//decrement for while loop
        }
        System.out.println(Arrays.toString(nums1));
    }



    public static void mergeJava8(int[] nums1, int m, int[] nums2, int n) {
        List<Integer> list1 = Arrays.stream(nums1).boxed().limit(m).collect(Collectors.toList());
        List<Integer> list2 = Arrays.stream(nums2).boxed().collect(Collectors.toList());

        list1.addAll(list2);
        Collections.sort(list1);

        nums1 = list1.stream().mapToInt(e -> e).toArray();

        System.out.println(Arrays.toString(nums1));
    }

}
