package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
216

https://leetcode.com/problems/combination-sum-iii/description/

Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
Example 3:

Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.


Constraints:

2 <= k <= 9
1 <= n <= 60
 */
public class CombinationSum3 {

    public static void main(String[] args) {
        int k = 3;
        int n = 9;
        List<List<Integer>> res = combinationSum3(k, n);
        System.out.println(res);
    }

    /*
    time O(9 ^ k)
    space O(n)
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        int startNumber = 1;//since 1 - 9

        dfsCombo(k, n, startNumber, seen, combo, res);

        return res;
    }

    private static void dfsCombo(int allowedLimit, int targetSum, int startNumber, Set<Integer> seen, List<Integer> combo, List<List<Integer>> res) {
        if(allowedLimit == 0 && targetSum == 0){//base
            res.add(new ArrayList<>(combo));
            return;
        }

        if(targetSum < 0 || allowedLimit < 0){//base
            return;
        }

        for (int i = startNumber; i <= 9; i++) {// 1 - 9

            if(seen.contains(i)) {//prune
                continue;
            }

            seen.add(i);
            combo.add(i);

            dfsCombo(allowedLimit - 1, targetSum - i  , i + 1, seen, combo, res);

            combo.remove(combo.size() - 1);//backtrack
            seen.remove(i);
        }
    }
}