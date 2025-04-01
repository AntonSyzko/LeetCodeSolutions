package leet.code.solutions.blind75;

/*
https://leetcode.com/problems/3sum/

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
Notice that the solution set must not contain duplicate triplets.

Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation:
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.

Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.


Constraints:

3 <= nums.length <= 3000
-105 <= nums[i] <= 105
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeSum {

    public static void main(String[] args) {
      int[] nums = {-1,0,1,2,-1,-4};
      List<List<Integer>> res =  threeSum2(nums);
      System.out.println(res);
    }

    private static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length  && nums[i] <= 0; i++) {

            if(i == 0 || nums[i] != nums[i-1]){//if not very first index AND not same values next to each other
                twoSumTwo(nums,i, res);
            }
        }

        return res;
    }

    private static void twoSumTwo(int[] nums, int i, List<List<Integer>> res) {
        int left = i + 1;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if(sum < 0){
                left++;
            }else if (sum > 0){
                right--;
            }else{
                res.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                left++;
                right--;
            }
        }
    }


    private static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);// !!!!!!!!!!!!!!!! SORT 

        for (int i = 0; i < nums.length - 2; i++) {

            int first = nums[i];

            int secondIndex = i + 1;
            int thirdIndex = nums.length - 1;

            while (secondIndex < thirdIndex) {
                int sum = first + nums[secondIndex] + nums[thirdIndex];

                if(sum == 0){
                    res.add(Arrays.asList(nums[i], nums[secondIndex], nums[thirdIndex]));
                    secondIndex++;
                    thirdIndex--;

                    while (secondIndex < thirdIndex && nums[secondIndex] == nums[secondIndex + 1]) {//skip duplicates at the second index, but mind boundaries
                        secondIndex++;
                    }

                    while (secondIndex < thirdIndex && nums[thirdIndex] == nums[thirdIndex - 1]) {//skip duplicates at the third index , but mind boundaries
                        thirdIndex--;
                    }
                }

                if(sum > 0) {//sum bigger than 0

                    thirdIndex--;//reduce large numbers

                }else{// sum lower than 0

                    secondIndex++;// redice second numbers

                }
            }
        }

        return res;
    }

    }
