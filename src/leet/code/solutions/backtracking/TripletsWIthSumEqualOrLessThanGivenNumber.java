package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
https://www.techiedelight.com/print-triplets-array-sum-less-equal-given-number/

Given an unsorted integer array, print all triplets in it with sum less than or equal to a given number.

For example,

Input: nums = [ 2, 7, 4, 9, 5, 1, 3 ]  sum = 10

 Output: Triplets are (1, 2, 3) (1, 2, 4) (1, 2, 5) (1, 2, 7) (1, 3, 4) (1, 3, 5) (1, 4, 5) (2, 3, 4) (2, 3, 5)
 */
public class TripletsWIthSumEqualOrLessThanGivenNumber {

    public static void main(String[] args) {
      //  int[] nums = { 2, 7, 4, 9, 5, 1, 3 };
        List<Integer> nums = Arrays.asList(2, 7, 4, 9, 5, 1, 3);    // 1 2 3 4 5 7 9
        int sum = 10;

        printAllTriplets(nums, sum);
    }

    // Wrapper over `generateAllTriplets()` function
    private static void printAllTriplets(List<Integer> input, int sum) {
        //!!!!!!!!!!!!!!!!!!!!!  sort the input
        Collections.sort(input);
        List<Integer> currentCombination = new ArrayList<>();

        // find all distinct triplets
        List<List<Integer>> result = new ArrayList<>();
        generateAllTriplets(input, sum, 0, currentCombination, result);

        // print all triplets
        System.out.println(result);
    }


    // Function to print all distinct triplets in the array with a sum less than or equal to a given number
    private static void generateAllTriplets(List<Integer> numsInput, int sum, int begin, List<Integer> currentTripletCombination, List<List<Integer>> result) {
        //BASE
        if (currentTripletCombination.size() == 3) {//reached triplet size
            result.add(new ArrayList<>(currentTripletCombination));//add to RES
            return;
        }

    //    for (int indexInNums = begin; indexInNums < numsInput.size() && numsInput.get(indexInNums) <= sum; indexInNums++) {
        for (int indexInNums = begin; indexInNums < numsInput.size(); indexInNums++) {

            if(numsInput.get(indexInNums) <= sum){//if itself number is lower than very SUM

                currentTripletCombination.add(numsInput.get(indexInNums));//add current num to current triplet combination

                generateAllTriplets(numsInput, sum - numsInput.get(indexInNums), indexInNums + 1, currentTripletCombination, result);

                currentTripletCombination.remove(currentTripletCombination.size() - 1);        // backtrack
                //  currentTripletCombination.removeLast();       // backtrack alternative
            }
        }
    }

    //sort of 3 sum problem
    public static void printAllTriplets2(int[] nums, int sum){
        Arrays.sort(nums); //!!!!!

        //sort of 3 sum problem
        for (int firstInTriplet = 0; firstInTriplet <= nums.length - 3; firstInTriplet++) {

            int low =   firstInTriplet + 1;//next to upper loop
            int high =  nums.length - 1;//last in sorted

            while (low < high){
                
                if(nums[firstInTriplet] + nums[low] + nums[high] > sum){//sum too high

                    high--;//lower upper bound

                }else{//sum is less or equal

                    for (int i = low + 1; i <= high ; i++) {//i is next to LOW so we are concatenating first in triplet + the very low +  next to LOW until <= HIGH
                        System.out.println("(" +nums[firstInTriplet] + nums[low] + nums[i] + ")");
                    }

                    low++;

                }
            }
        }
    }
}
