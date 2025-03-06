package leet.code.solutions.backtracking;


/*
https://www.techiedelight.com/find-distinct-combinations-of-given-length/


Given an integer array, find all distinct combinations of a given length k.

For example,

Input:  {2, 3, 4}, k = 2
Output: {2, 3}, {2, 4}, {3, 4}

 Input:  {1, 2, 1}, k = 2
 Output: {1, 2}, {1, 1}, {2, 1}

The program should print all the distinct combinations,
while preserving the relative order of elements as they appear in the array.


 */

import java.util.ArrayList;
import java.util.List;

public class AllDistinctCombinationsOfGivenLength {


    public static void main(String[] args) {
            int[] nums = {2, 3, 4};

            List<List<Integer>> res = printAllDistinctCombinations(nums, 2);
             for (List<Integer> list : res) {
                 System.out.println(list);
             }
    }


    private static List<List<Integer>> printAllDistinctCombinations(int[] nums, int combinationLimit) {
        List<List<Integer>> distinctCombinationsRes = new ArrayList<>();

        findDistinctCombinations(nums,0,combinationLimit, distinctCombinationsRes, new ArrayList<>());

        return distinctCombinationsRes;
    }

    private static void findDistinctCombinations(int[] nums, int start, int combinationLimit, List<List<Integer>> distinctCombinations, ArrayList<Integer> currentResult) {
        //invalid input
        if(nums.length == 0 || combinationLimit > nums.length){
            return;
        }

   //     System.out.println("combinationLimit = " + combinationLimit);
        //base case
        if(combinationLimit == 0){//combinationLimit exhausted - add to res
          //  System.out.println("\r\n\t ---- K exhausted -> put : "  + currentResult + " ----");
            distinctCombinations.add(new ArrayList<>(currentResult));//put to final result
            return;//mind return here is from function on top of the call stack not the entire method - and it will jump to REMOVE LAST operation after this return
        }

        //traverse all digits
        for (int i = start; i < nums.length; i++) {

            currentResult.add(nums[i]);
            //System.out.println("added " + nums[i]);
           // System.out.println("\r\n\t curtr res " + Arrays.toString(currentResult.toArray()));

            findDistinctCombinations(nums,i + 1,combinationLimit - 1, distinctCombinations, currentResult);//with combinationLimit one less, and start moved one UP

         //   System.out.println(" removing " + currentResult.get(currentResult.size() - 1));
            currentResult.remove(currentResult.size() - 1);//backtrack - remove last
            //currentResult.removeLast(); // alternative

        }
    }
}
