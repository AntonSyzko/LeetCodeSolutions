package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*

https://www.techiedelight.com/print-all-combination-numbers-from-1-to-n/

Given a positive integer n, print all combinations of numbers between 1 and n having sum n.

For example,

For n = 5, the following combinations are possible:
{ 5 }
{ 1, 4 }
{ 2, 3 }
{ 1, 1, 3 }
{ 1, 2, 2 }
{ 1, 1, 1, 2 }
{ 1, 1, 1, 1, 1 }

 For n = 4, the following combinations are possible:
  { 4 }
  { 1, 3 }
  { 2, 2 }
  { 1, 1, 2 }
  { 1, 1, 1, 1 }
 */
public class PrintAllCombinationsFrom1ToN {

    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> combo = new ArrayList<>();

        // print all combinations of numbers from 1 to `n` having sum `n`
        printCombinations(1, n, combo, result);

        System.out.println(result);
    }

    private static void printCombinations(int startNum, int endNum, List<Integer> combo, List<List<Integer>> result) {
       //BASE
        if(endNum == 0){//exhausted end
            result.add(new ArrayList<>(combo));
            return;
        }

        for (int i = startNum; i <= endNum; i++) {

            combo.add(i);

            printCombinations(i, endNum - i, combo, result);//recur with reduced end number

            combo.remove(combo.size()-1);//BACKTRACK

        }
    }
}
