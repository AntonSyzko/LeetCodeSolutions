package leet.code.solutions.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://www.callicoder.com/find-triplets-with-zero-sum/

Given an array of unsorted numbers, find all unique triplets in the array whose sum is zero. The array may have duplicates.

Example 1:

Input: a[] = [-5, 3, 2, -3, 1]
Output: [-5, 3, 2] [2, -3, 1]
Example 2:

Input: a[] = [-1, -1, 2, -1, -1, 2, -1, -1, 2]
Output: [-1, -1, 2]
 */
public class TripletsWithZeroSum {

    public static void main(String[] args) {
        int[] nums = {-1, -1, 2, -1, -1, 2, -1, -1, 2};
        List<List<Integer>> res = findTripletsWithZeroSum(nums);
        System.out.println(res);
    }

    private static List<List<Integer>> findTripletsWithZeroSum(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(arr);

        for (int first = 0; first < arr.length - 2; first++) {//iterate till last 2

            if (first > 0 && arr[first] == arr[first - 1]) {//skip duplicates
                continue;
            }

            int firstNum = arr[first];

            int secondIndex = first + 1;
            int thirdIndex = arr.length - 1;

            while (secondIndex < thirdIndex) {

                int sum = firstNum + arr[secondIndex] + arr[thirdIndex];

                if (sum == 0) {

                    List<Integer> currRes = List.of(firstNum, arr[secondIndex], arr[thirdIndex]);
                    res.add(currRes);//store result

                    secondIndex++;
                    thirdIndex--;

                    while (secondIndex < thirdIndex && arr[secondIndex] == arr[secondIndex - 1]) {//skip duplicates next to second
                        secondIndex++;
                    }

                    while (secondIndex < thirdIndex && arr[thirdIndex] == arr[thirdIndex + 1]) {//skip duplicates next to third
                        thirdIndex--;
                    }

                } else if (sum < 0) {//sum too low

                    secondIndex++;

                } else {//sum too high

                    thirdIndex--;

                }
            }
        }

        return res;
    }
}
