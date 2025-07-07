package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.Map;

/*
697

https://leetcode.com/problems/degree-of-an-array/description/

Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:

Input: nums = [1,2,2,3,1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:

Input: nums = [1,2,2,3,1,4,2]
Output: 6
Explanation:
The degree is 3 because the element 2 is repeated 3 times.
So [2,2,3,1,4,2] is the shortest subarray, therefore returning 6.

Constraints:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.
 */
public class DegreeOfAnArray {

    public static void main(String[] args) {
        int[] nums = { 1,2,2,3,1};
        int shortestWIthSameDegree = findShortestSubArray(nums);
        System.out.println(shortestWIthSameDegree);
    }

    private static int findShortestSubArray(int[] nums) {
        Map<Integer,Integer> occurrenceFrequencyMap = new HashMap<>();

        int minDegreeLen = Integer.MAX_VALUE;
        int degree = 0;

        Map<Integer, Integer> firstSeenAtIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            firstSeenAtIndex.putIfAbsent(nums[i],i);//first ever index of occurrence of this number

            occurrenceFrequencyMap.put(nums[i], occurrenceFrequencyMap.getOrDefault(nums[i],0) + 1);

            int currDegree = occurrenceFrequencyMap.get(nums[i]);

            if(currDegree > degree){//new degree HIGH

                degree = currDegree ;
                minDegreeLen = i - firstSeenAtIndex.get(nums[i]) + 1;//update min as new degree high detected

            }else if (currDegree  == degree){//same degree as seen before

                minDegreeLen = Math.min(minDegreeLen, i - firstSeenAtIndex.get(nums[i]) + 1);// +1 as we are zero based

            }
        }

        return minDegreeLen;
    }
}