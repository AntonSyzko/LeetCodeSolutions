package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given a non-negative integer n, find all n-letter words composed by 'a' and 'b', return them in a list of strings in lexicographical order.

Input: 2
Output: ["aa", "ab", "ba", "bb"]

Input: 4
Output: ["aaaa", "aaab", "aaba", "aabb", "abaa", "abab", "abba", "abbb", "baaa", "baab", "baba", "babb", "bbaa", "bbab", "bbba", "bbbb"]

 */
public class LetterCombinations {

    public static void main(String[] args) {

        int n = 2;

        List<String> combos = letterCombination(n);

        for (String combo : combos) {
            System.out.println(combo);
        }

    }



    private static List<String> letterCombination(int n) {
        List<String> result = new ArrayList<>();

        StringBuilder builder = new StringBuilder();

        dfs(0, n, builder,result);

        return result;
    }

    private  static String[] ARRAY = {"a","b"};


    private static void dfs(int startIndex, int len, StringBuilder builder, List<String> res) {

        if(startIndex == len ) {//BASE
            res.add(builder.toString());
            return;
        }

        for(String letter : ARRAY) {

            builder.append(letter);

            dfs(startIndex + 1, len, builder, res);

            builder.deleteCharAt(builder.length()-1);//BACKTRACK

        }

    }
}
