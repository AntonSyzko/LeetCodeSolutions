package leet.code.solutions.arrays;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/intersection-of-two-arrays-ii/

Given two integer arrays nums1 and nums2, return an array of their intersection.
Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.

Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]

Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.

Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */
public class IntersectionOfTwoArrays {


    public static void main(String[] args) {
        int[] nums1 = new int[]{4, 9, 5};
        int[] nums2 = new int[]{9, 4, 9, 8, 4};

        int[] res = intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));

        nums1 = new int[]{1, 2, 2, 1};
        nums2 = new int[]{2, 2};

        res = intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));

        nums1 = new int[]{1};
        nums2 = new int[]{1, 1};

        res = intersect(nums1, nums2);
        System.out.println(Arrays.toString(res));

    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        // int [] res = new int[Math.max(nums1.length, nums2.length)];
        List<Integer> res = new ArrayList<>();

        Arrays.sort(nums1);//sorting will allow to compare one by one
        Arrays.sort(nums2);//we don't need the order - we need jus common numbers ( intersection)

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {//since sorted we just compare
                i++;// i was too low, raise it
            } else if (nums1[i] > nums2[j]) {
                j++;// i was too high - raise j
            } else {//equal i and j - so intersected number
                res.add(nums1[i]);//add to reuslt
                i++;//still raise both to move on
                j++;
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] intersectArraysOnly(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int resIndex = 0;
        int[] res = new int[Math.max(nums1.length, nums2.length)];

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                res[resIndex++] = nums1[i];
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(res, 0, resIndex);//to eliminate ending zeros from res array
    }

    public static int[] intersectArraysOnlyInPlace(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        int resIndex = 0;
        //no additional array
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                nums1[resIndex++] = nums1[i];//we are overriding values of mums1 - since I has gone far from resIndex - safe to change in place
                i++;
                j++;
            }
        }
        return Arrays.copyOfRange(nums1, 0, resIndex);//eliminate from nums1 everything till the res index
    }

    public static int[] intersectUniquesOnly(int[] nums1, int[] nums2) {
        Set<Integer> set1 = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(nums2).boxed().collect(Collectors.toSet());

        Set<Integer> intersection = set1.stream().filter(set2::contains).collect(Collectors.toSet());

        return intersection.stream().mapToInt(Integer::intValue).toArray();
    }


}
