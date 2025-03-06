package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.callicoder.com/find-triplets-with-zero-sum/

https://www.callicoder.com/find-triplets-with-zero-sum/
Given an array of unsorted numbers, find all UNIQUE triplets in the array whose sum is zero. The array may have duplicates.

Example 1:

Input: a[] = [-5, 3, 2, -3, 1]
Output: [-5, 3, 2] [2, -3, 1]
Example 2:

Input: a[] = [-1, -1, 2, -1, -1, 2, -1, -1, 2]
Output: [-1, -1, 2]
 */
public class TripletsWithZeroSum {

    public static void main(String[] args) {
        int [] nums = {-5, 3, 2, -3, 1};
        List<List<Integer>> tripletsWithZeroSum = findTripletsWithZeroSum(nums);
        for(List<Integer> triplet: tripletsWithZeroSum) {
            for(Integer num: triplet) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }


    private static List<List<Integer>> findTripletsWithZeroSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++) {
            if( i > 0 && nums[i] == nums[i-1]){//if current i equals to prev -> duplicate - skip
                continue;
            }

            //fix first number of a triplet
            int firstNumber = nums[i];

            //initialise pointers
            int leftIndex = i+1;
            int rightIndex = nums.length-1;

            //tow sum approach to get other two numbers
            while (leftIndex < rightIndex){
                int currSum = firstNumber + nums[leftIndex] + nums[rightIndex];

                if( currSum == 0){//found a triplet with 0 sum
                    res.add(List.of(firstNumber, nums[leftIndex] , nums[rightIndex]));
                    //move pointers
                    leftIndex++;
                    rightIndex--;

                    // Skip duplicates
                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex-1]){
                        leftIndex++;
                    }

                    while( leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex+1]){
                        rightIndex--;
                    }

                } else if ( currSum < 0){
                    leftIndex++;
                }else {
                    rightIndex--;
                }
            }

        }
        return res;
    }

    }
