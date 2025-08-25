package leet.code.solutions.backtracking;

import java.util.Arrays;
/*
3309

https://leetcode.com/problems/maximum-possible-number-by-binary-concatenation/description/

You are given an array of integers nums of size 3.

Return the maximum possible number whose binary representation can be formed by concatenating the binary representation of all elements in nums in some order.

Note that the binary representation of any number does not contain leading zeros.

Example 1:

Input: nums = [1,2,3]

Output: 30

Explanation:

Concatenate the numbers in the order [3, 1, 2] to get the result "11110", which is the binary representation of 30.

Example 2:

Input: nums = [2,8,16]

Output: 1296

Explanation:

Concatenate the numbers in the order [2, 8, 16] to get the result "10100010000", which is the binary representation of 1296.

Constraints:

nums.length == 3
1 <= nums[i] <= 127
 */
public class MaxPossibleBinaryConcatenation {

    public static void main(String[] args) {
        int [] nums = {1,2,3};

        int maxGood = maxGoodNumberSorting(nums);

        System.out.println(maxGood);
    }

    //but best solution is sorting
    //Time: O(n log n), Space: O(n)
    public static int maxGoodNumberSorting(int[] nums) {
        String[] strNums = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            strNums[i] = Integer.toBinaryString(nums[i]);
        }

        Arrays.sort(strNums, (a,b) -> (b + a).compareTo(a + b));

        StringBuilder combo = new StringBuilder();
        for(int i = 0; i < strNums.length; i++){
            combo.append(strNums[i]);
        }

        return Integer.parseInt(combo.toString(), 2);
    }


    /*
    Time Complexity: O(n! × n × L)

            n! permutations to explore
            n operations per permutation level
            L for Integer.parseInt() conversion at each leaf

Space Complexity: O(n × L)

        Single StringBuilder reused (better than List<String>)
        Recursion depth: O(n)
     */

    public static int maxGoodNumberDFS(int[] nums) {

        int [] res = new int[1];//res holder pass by value
        res[0] = Integer.MIN_VALUE;

        StringBuilder combo = new StringBuilder();

        boolean[] used = new boolean[nums.length];

        int depth = 0;

        dfs(nums, res, combo, used, depth);

        return res[0];
    }


    private static void dfs(int[] nums,int[] maxBin,StringBuilder combo, boolean[] used,  int depth) {

        if(depth == nums.length){//BASE

            maxBin[0] = Math.max(maxBin[0], Integer.parseInt(combo.toString(),2) );

            return;
        }

        for (int i = 0; i < nums.length ; i++) {// 0-> nums

            if(used[i]) {
                continue;//skip used
            }

            String binString = Integer.toBinaryString(nums[i]);
            int comboPreAppendLength = combo.length();
            combo.append(binString);
            used[i] = true;

            dfs(nums , maxBin, combo, used, depth +1);//recur depth +1

            combo.setLength(comboPreAppendLength);//BACKTRACK
            used[i] = false;

        }
    }
    }