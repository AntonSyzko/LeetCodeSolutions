package leet.code.solutions.backtracking;


/*
https://www.techiedelight.com/find-distinct-combinations-of-given-length/


Given an integer array, find all distinct combinations of a given length k.

For example,

Input:  {2, 3, 4},
k = 2
Output: {2, 3}, {2, 4}, {3, 4}

 Input:  {1, 2, 1},
 k = 2
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
        List<Integer> ongoingCombination = new ArrayList<>();
        int startNum = 0;

        findDistinctCombinations(nums,startNum,combinationLimit, distinctCombinationsRes, ongoingCombination);

        return distinctCombinationsRes;
    }

    private static void findDistinctCombinations(int[] nums, int start, int combinationLimit, List<List<Integer>> distinctCombinationsRes, List<Integer> ongoingCombination) {
        //invalid input
        if(nums.length == 0 || combinationLimit > nums.length){
            return;
        }

        //BASE case
        if(combinationLimit == 0){//combinationLimit exhausted - add to res
            distinctCombinationsRes.add(new ArrayList<>(ongoingCombination));//put to final result
            return;//mind return here is from function on top of the call stack not the entire method - and it will jump to REMOVE LAST operation after this return
        }

        //traverse all digits
        for (int i = start; i < nums.length; i++) {

            ongoingCombination.add(nums[i]);

            findDistinctCombinations(nums,i + 1,combinationLimit - 1, distinctCombinationsRes, ongoingCombination);//with combinationLimit one less, and start moved one UP

            ongoingCombination.remove(ongoingCombination.size() - 1);//BACKTRACK - remove last
            //currentResult.removeLast(); // alternative

        }
    }
}
