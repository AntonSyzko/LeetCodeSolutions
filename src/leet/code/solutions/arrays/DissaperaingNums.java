package leet.code.solutions.arrays;

import java.util.*;

/*
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.



Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [5,6]
Example 2:

Input: nums = [1,1]
Output: [2]


Constraints:

n == nums.length
1 <= n <= 105
1 <= nums[i] <= n
 */
public class DissaperaingNums {

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        List<Integer> missing = findDisappearedNumbers(nums);
        System.out.println(missing);
    }

    private static List<Integer> findDisappearedNumbersSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        List<Integer> res =  new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 1; i <=  nums.length; i++) {
            if (!set.contains(i)) {
                res.add(i);
            }
        }

        return res;
    }


private static List<Integer> findDisappearedNumbers(int[] nums) {//crutchy
      if(nums==null||nums.length==0){
          return null;
      }

      if(nums.length==1){
          return List.of(nums[0]);
      }

      List<Integer> result = new ArrayList<>();

      Arrays.sort(nums);

      int prev = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];

            if(curr == nums[i-1]+1 || curr==nums[i-1]){
                prev = curr;
            } else{
                prev++;
                while(curr != prev){
                    result.add(prev++);
                }


            }
        }

        return result;
    }
}
