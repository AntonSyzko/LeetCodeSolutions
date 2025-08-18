package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
The set [1,2,3,. . . ,n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, We get the following se-
quence (ie, for n = 3):
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.
 */
public class PermutationSequence {

    public static void main(String[] args) {
        String res =  getPermutation(3, 2);
        System.out.println(res);
    }

    private static String getPermutation(int n, int k) {
        List<String> res = new ArrayList<>();
        StringBuilder combo = new StringBuilder();

        Set<Integer> visited = new HashSet<>();

        dfs(n,combo, visited, res);

        return res.get(k - 1 );// 0 -> n indexed
    }

    private static void dfs(int n,StringBuilder combo, Set<Integer> visited,  List<String> res) {
        if(combo.length() == n){
            res.add(combo.toString());
            return;
        }

        for (int i = 1; i <= n; i++) { //<=

            if(visited.contains(i)){//prune
                continue;
            }

            visited.add(i);
            combo.append(i);

            dfs(n,combo,visited,res);

            combo.deleteCharAt(combo.length()-1);//backtrack
            visited.remove(i);

        }
    }
}