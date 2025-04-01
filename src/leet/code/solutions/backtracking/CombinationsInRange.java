package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/combinations/description/

Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].

You may return the answer in any order.



Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Explanation: There are 4 choose 2 = 6 total combinations.
Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.

Example 2:

Input: n = 1, k = 1
Output: [[1]]
Explanation: There is 1 choose 1 = 1 total combination.

 */
public class CombinationsInRange {

    public static void main(String[] args) {
     int n = 4;
     int limit = 2;

     List<List<Integer>> res = combine(n, limit);

     System.out.println(res);
    }

    private static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ongoingCOmbination = new ArrayList<>();
        int start = 1;

        combinationHelper(start, n, k, ongoingCOmbination, res);

        return res;

    }

    private static void combinationHelper(int start, int end, int limit, List<Integer> ongoingCOmbination, List<List<Integer>> res) {
        if(ongoingCOmbination.size() == limit) {
            res.add(new ArrayList<>(ongoingCOmbination));
            return;
        }

        for(int i = start; i <= end; i++){
            ongoingCOmbination.add(i);

            combinationHelper(i + 1, end, limit, ongoingCOmbination, res);

            ongoingCOmbination.remove(ongoingCOmbination.size()-1);
        }
    }
}
