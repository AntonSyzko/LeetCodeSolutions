package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/remove-duplicates-from-sorted-array/
Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once.
The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in the first part of the array nums.
More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array.
You must do this by modifying the input array in-place with O(1) extra memory.

Custom Judge:

The judge will test your solution with the following code:

int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.

Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).

 */
public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
//        int[] nums = new int[]{1, 1, 2};//1,2 = 2 unique
//        int res = removeDuplicates2(nums);
//        System.out.println("Number of duplicate records : " + res);
//
//
//        nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};//0,1,2,3,4 = 5 unique
//        res = removeDuplicates2(nums);
//        System.out.println("Number of duplicate records : " + res);

        int [] nums2 = new int[]{1, 1, 2,2,3};
        int[] resArray  = removeDuplicatesFromArrayTwoPointers(nums2);
        System.out.println(Arrays.toString(resArray));
    }

    public static int removeDuplicates2(int[] nums) {
        int lastUniqueIndex = 1;//0 zero index is per-se unique - so start from 1, so zero index value will stay in result untouched

        for (int i = 1; i < nums.length; i++) {//start from 1
            if (nums[lastUniqueIndex - 1] != nums[i]) {// so NOT equal - unique !!!
                nums[lastUniqueIndex] = nums[i];//re-arrange array - store UNIQUES one after each other
                lastUniqueIndex++;
            }
        }
        //returns No of valid entries after deletion
        return lastUniqueIndex;
    }

    private static int[] removeDuplicatesFromArrayTwoPointers(int[] array) {

        int start = 0;
        int next = 1;

        while (next < array.length) {
            if(array[next] == array[start]){//duplicate

                next++;

            }else{

                start++;//move start cause we met diplicate above
                array[start] = array[next];
                next++;

            }
        }

        int rangeToCopy = start + 1;//so from zero to this range

        return Arrays.copyOf(array,rangeToCopy);

    }

    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;

        int current = nums[0];
        int indexToReplace = 1;

        for (int i = 1; i < nums.length ; i++) {
            if(nums[i] != current) {
                current = nums[i];
                nums[indexToReplace] = current;
                indexToReplace ++;
            }
        }
        return indexToReplace;
    }

    public static int[] removeDuplicatesFromArray(int[] nums) {
        Set<Integer> res = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
    }
