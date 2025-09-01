package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
The problem requires finding all unique combinations of an integer n's factors, excluding 1 and n itself. A number's factors are the numbers that divide it evenly without leaving a remainder.

For example, for n = 8, the combinations of factors can be [2, 2, 2] which multiply together to give 8, or [2, 4] as 2 * 4 = 8 as well. The goal is to list all such combinations for any given n.

The constraints are that the factors must be between 2 and n-1 inclusive, since 1 and n are not considered in this problem. The solution should return all the possible combinations in any order.

n = 8
factors [2,2,2] , [2,4]
 */
public class FactorCombinations {

    public static void main(String[] args) {
        int n = 8;
        List<List<Integer>> res = getFactors(n);

        System.out.println(res);
    }

    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();
        int start = 2;

        dfsFactor(start, n, combo, res);

        return res;
    }

    private static void dfsFactor(int start, int n, List<Integer> combo, List<List<Integer>> res) {

        if (n == 1) {//BASE -> all divisions exhausted
            if (combo.size() > 1) {//to avoid [8] -> the very N ( since n/n == 1) and size of combo will be 1
                res.add(new ArrayList<>(combo));
            }

            return;
        }

        for (int i = start; i <= n; i++) {// 2 --> n (excl)

            if (n % i == 0) {//divisible

                combo.add(i);

                dfsFactor(i, (n / i), combo, res); //recur with factor divided by curr i ( n / i )

                combo.remove(combo.size() - 1);//backtrack
            }

        }
    }


    private static List<List<Integer>> getFactors2(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();

        int start = 2;

        int currFactor = n;

        dfsFactorPickNotPick(start, currFactor, n, combo, res);

        return res;
    }

    /*
    Time Complexity: O(2^√n)

        At each step, we make at most 2 recursive calls (use factor vs skip factor)
        The depth of recursion is bounded by the number of prime factors of n
        In the worst case, we try all factors from 2 to √n (since any factor > √n would have a corresponding factor < √n)
        The branching factor is 2, and maximum depth is roughly √n

Space Complexity: O(√n)

        Recursion depth: O(√n) in worst case
        Each recursive call stores a combination: O(√n) space for the longest combination
        Result storage: varies based on number of factorizations, but each combination is O(log n) factors on average
     */
    private static void dfsFactorPickNotPick(int start, int currFactor, final int n, List<Integer> combo, List<List<Integer>> res) {
        if (currFactor == 1) {//BASE - all divisions finally ended at 1 ( 8/2 = 4 -> 4/2 = 2 -> 2/2 = 1)
            res.add(new ArrayList<>(combo));
            return;
        }

        if (start >= n) { // BASE start is too large, we have to go as far as n - 1
            return;
        }

        if (currFactor % start == 0) {//divisible

            combo.add(start);

            //use curr start element again (unbounded)
            dfsFactorPickNotPick(start, currFactor / start, n, combo, res);//recur using same number but with divided factor

            combo.remove(combo.size() - 1);//backtrack

            //skip ( curr factor stays same)
            dfsFactorPickNotPick(start + 1, currFactor, n, combo, res); //recur with next number in possible permutation of 2 -> n(excl) but with same factor


        } else {// not mod = 0

            //skip to next ( curr factor stays same)
            dfsFactorPickNotPick(start + 1, currFactor, n, combo, res);//recur with next possible number in 2 -> n(excl) but with same factor
        }
    }
}