package leet.code.solutions.arrays;

import java.util.*;
import java.util.function.Function;

/*
https://leetcode.com/problems/majority-element/

Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2

Constraints:
n == nums.length
1 <= n <= 5 * 104
-231 <= nums[i] <= 231 - 1
Follow-up: Could you solve the problem in linear time and in O(1) space?
 */
public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,1,2,2,3,1,3,4,4,4,1,1,4,4,4,4,4};
        int res = find(nums);
        System.out.println(res);

//        List<Integer> numbers = List.of(2, 2, 1, 1, 1, 2, 2);
//        res = majorityElementList(numbers);
//        System.out.println(res);
    }

    public static int majorityElement(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> intToTimesOccurred = new HashMap<>();

        for (int each : nums) {
            // +1 because we have to account for current occurence
            if (intToTimesOccurred.containsKey(each) && intToTimesOccurred.get(each) + 1 > nums.length / 2) {//> then a half of elements (length/2)
                return each;
            } else {
                intToTimesOccurred.put(each, intToTimesOccurred.getOrDefault(each, 0) + 1);
            }
        }
        return -1;//not found
    }

    public static int majorityElementList(List<Integer> nums) {
        if (nums.size() == 1) {
            return nums.get(0);
        }

        Map<Integer, Integer> intToTimesOccurred = new HashMap<>();

        for (int each : nums) {
            // +1 because we have to account for current occurence
            if (intToTimesOccurred.containsKey(each) && intToTimesOccurred.get(each) + 1 > nums.size() / 2) {//> then a half of elements (length/2)
                return each;
            } else {
                intToTimesOccurred.put(each, intToTimesOccurred.getOrDefault(each, 0) + 1);
            }
        }
        return -1;//not found
    }

    static int find(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        Map<Integer, Integer> counts = new HashMap<>();

        for (int i = 1; i < nums.length; i++) {
            count++;
            if (nums[i] != nums[i - 1] || i == nums.length-1) {
                if(i == nums.length-1) count++;
                counts.put(nums[i - 1], count);
                count = 0;
            }
        }
        int v = 0;
        int ret = 0;
        for(int key : counts.keySet()) {
            int current = counts.get(key);
            if(current> v){
                ret = key;
                v = current;
            }
        }
return ret;
    }

    //O(1) space
    //https://blog.devgenius.io/leetcode-169-majority-element-solution-with-images-7abab996e95e
    private static int majorityElement2(int[] nums) {

        int majorityOccurenceCount = 0, res = 0;

        for (int currentNum : nums) {
            if (majorityOccurenceCount == 0) {
                res = currentNum;
            }
            if (currentNum != res) {
                majorityOccurenceCount--;//decrease current mjority
            } else {
                majorityOccurenceCount++;//increase
            }
        }
        return res;
    }

    private static int majorityElementList2(List<Integer> nums) {

        int majorityOccurenceCount = 0, res = 0;

        for (int currentNum : nums) {
            if (majorityOccurenceCount == 0) {
                res = currentNum;
            }
            if (currentNum != res) {
                majorityOccurenceCount--;//decrease current mjority
            } else {
                majorityOccurenceCount++;//increase
            }
        }
        return res;
    }


    public static int majorityElement_just_most_occured(int[] nums) {


        Map<Integer, Integer> intToTimesOccurred = new HashMap<>();

        for (int each : nums) {
            if (!intToTimesOccurred.containsKey(each)) {
                intToTimesOccurred.put(each, 1);
            } else {
                intToTimesOccurred.put(each, intToTimesOccurred.get(each) + 1);
            }
        }

        return intToTimesOccurred.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}
