package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
letters: a string of unique letters
Output
all of its distinct permutations

Examples
Example 1:
Input:

letters = abc
Output: abc acb bac bca cab cba

 */
public class LettersPermutations {

    public static void main(String[] args) {
       String s = "abc";
       List<String> permuations = lettersPermutation(s);
       System.out.println(permuations);
    }


    private static List<String> lettersPermutation(String str){
        List<String> res = new ArrayList<>();
        int start = 0;
        List<Character> combo = new ArrayList<>();
        Set<Character> seen = new HashSet<>();

        dfs(str, start, seen, combo, res);

        return res;
    }

    private static void dfs(String str, int start, Set<Character> seen, List<Character>  combo, List<String> res){
        if(start == str.length()){//base
            res.add(combo.toString());
            return;
        }


        for(int i=0; i < str.length(); i++){

            if(seen.contains(str.charAt(i))) {//prune
                continue;
            }

            combo.add(str.charAt(i));

            seen.add(str.charAt(i));


            dfs(str, start + 1, seen, combo, res);

            combo.remove(combo.size()-1);//BACKTRACK

            seen.remove(str.charAt(i));//BACKTRACK

        }

    }
}
