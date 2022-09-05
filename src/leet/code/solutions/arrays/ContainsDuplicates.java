package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
https://leetcode.com/problems/contains-duplicate/
Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.

Input: nums = [1,2,3,1]
Output: true
Input: nums = [1,2,3,4]
Output: false

 */
public class ContainsDuplicates {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
        boolean containsDuplicates = containsDuplicate(nums); //true
        System.out.println(containsDuplicates);

        nums = new int[]{1, 2, 3, 1};
        containsDuplicates = containsDuplicate(nums);//true
        System.out.println(containsDuplicates);

        nums = new int[]{1, 2, 3, 4};
        containsDuplicates = containsDuplicate(nums);//false
        System.out.println(containsDuplicates);
    }

    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int each : nums) {
            if (set.contains(each)) {
                return true; //exit FAST - if contains already - NO need to add ALL anymore
            } else {
               set.add(each);
            }
        }
        return false;
    }


    public static boolean containsDuplicate1(int[] nums) {
        Set<Integer> uniques = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        return uniques.size() != nums.length;
    }

    public static boolean containsDuplicate2(int[] nums) {
        Set<Integer> uniques = IntStream.of(nums).boxed().collect(Collectors.toSet());
        return uniques.size() != nums.length;
    }

    public static boolean containsDuplicate3(int[] nums) {
        Set<Integer> uniques = new HashSet<>();
        uniques.addAll(Arrays.stream(nums).boxed().collect(Collectors.toList()));

        return uniques.size() != nums.length;
    }
}
