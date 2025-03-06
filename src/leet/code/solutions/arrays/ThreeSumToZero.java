package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;

/*
Problem:
Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.
Note: Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
The solution set must not contain duplicate triplets.
For example, given array S = {-1 0 1 2 -1 -4},
A solution set is:
(-1, 0, 1)
(-1, -1, 2)
 */

public class ThreeSumToZero {

    public static void main(String[] args) {
    int[] nums = {-1, 0 ,1, 2, -1 ,-4};
    ArrayList<ArrayList<Integer>> res = threeSum(nums);
    System.out.println(res);
    }

    private static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (num.length < 3)
            return result;

       // sort array
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {//mind -2

           //avoid duplicate solutions
            if (i == 0 || num[i] > num[i - 1]) {

                int negate = -num[i];//to compare with  other two summed up and if their sum = this with negative sign = overall sum == 0

                int start = i + 1;
                int end = num.length - 1;

                while (start < end) {
                    //case 1 - zero sum found
                    if (num[start] + num[end] == negate) {//other two summed up and if their sum = this with negative sign = overall sum == 0

                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(num[i]);
                        temp.add(num[start]);
                        temp.add(num[end]);

                        result.add(temp);

                        start++;
                        end--;
                        //avoid duplicate solutions
                        while (start < end && num[end] == num[end + 1]){//skip duplicates when END shifted
                            end--;
                        }

                        while (start < end && num[start] == num[start - 1]){//skip duplicates whn START shifted
                            start++;
                        }

                    //case 2
                    } else if (num[start] + num[end] < negate) {//sum of two is too small

                        start++;

                       //case 3
                    } else {//sum of two is too high
                        end--;
                    }
                }
            }
        }
        return result;
    }
}
