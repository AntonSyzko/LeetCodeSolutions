package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
, I have an input of N, and I need to generate all combinations of binary representation of length N,
like for N = 1 :
 [0],[1]

for N = 2 :
 [0][0], [0][1], [1][1],[1][0]

N = 3 :
[[0, 0, 0], [0, 0, 1], [0, 1, 0], [0, 1, 1], [1, 0, 0], [1, 0, 1], [1, 1, 0], [1, 1, 1]]
 */
public class GenerateBinaryCombinations {

    public static void main(String[] args) {
        List<List<Integer>> lists = bins(3);
        System.out.println(lists);
    }

    private static List<List<Integer>>bins(int n){
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();
        dfs(n, combo,res);
        return res;
    }

    /*
    Time & Space Complexity:

        Time Complexity: O(2ᴺ × N) - 2ᴺ combinations, each taking O(N) to copy

        Space Complexity: O(2ᴺ × N) - storing all combinations

        Recursion Depth: O(N)
     */
    private static void dfs(int n, List<Integer> combo, List<List<Integer>> res){
        if(n == 0){//BASE
            res.add(new ArrayList<>(combo));
            return;
        }

        combo.add(0);
        dfs(n-1, combo, res);
        combo.remove(combo.size()-1);//backtrack

        combo.add(1);
        dfs(n-1, combo, res);
        combo.remove(combo.size()-1);//backtrack
    }
}