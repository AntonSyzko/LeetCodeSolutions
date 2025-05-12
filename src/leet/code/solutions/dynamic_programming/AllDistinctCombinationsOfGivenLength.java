package leet.code.solutions.dynamic_programming;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
https://www.techiedelight.com/find-distinct-combinations-given-length-2/


Given an integer array, find all distinct combinations of a given length k.

For example,

Input:  {2, 3, 4}, k = 2
Output: {2, 3}, {2, 4}, {3, 4}

Input:  {1, 2, 1}, k = 2
Output: {1, 2}, {1, 1}, {2, 1}
The program should print all the distinct combinations, while preserving the relative order of elements as they appear in the array.

 */
public class AllDistinctCombinationsOfGivenLength {


    public static void main(String[] args) {
        int[] nums = {2, 3, 4};
        int combinationLimit = 2;

        Set<List<Integer>> res = findCombinations(nums, combinationLimit);

        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

        public static Set<List<Integer>> findCombinations(int[] nums, int distinctCombinationsLimit) {
            Set<List<Integer>> res = new HashSet<>();
            List<Integer> combo = new ArrayList<>();
            findCombinations(nums, 0, distinctCombinationsLimit, combo, res);

            return res;
        }

    public static void findCombinations(int[] nums, int start, int distinctCombinationsLimit,  List<Integer> combo, Set<List<Integer>> res) {
        if(nums.length == 0){
            return;
        }

        //BASE, distinctCombinations exhausted
        if( distinctCombinationsLimit == 0){
            res.add(new ArrayList<>(combo));//add to res
            return;//exits stack call -> jumps to remove last
        }

        //return if no more elements are left
        if( start == nums.length){
            return;
        }

        // include the current element in the current combination res and recur
        combo.add(nums[start]);

        //include means - retrieve from distinctCombinations ( by extracting -1 = adding to result )
        findCombinations(nums, start + 1, distinctCombinationsLimit - 1, combo,res);

        // exclude the lastly added element from the current combination
        combo.remove(combo.size() - 1); // BACKTRACK
       // currentRes.removeLast();//alternative

        // exclude the current element from the current combination and recur
        //exclude means keep it in distinctCombinations not accounting for it in res
        findCombinations(nums, start + 1, distinctCombinationsLimit ,combo,  res);
    }
}
