package leet.code.solutions.arrays;

/*
https://www.educative.io/courses/grokking-the-coding-interview/gx2OqlvEnWG

Given a set with distinct elements, find all of its distinct subsets.

Example 1:
Input: [1, 3]
Output: [], [1], [3], [1,3]

Example 2:
Input: [1, 5, 3]
Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3]
 */

import java.util.ArrayList;
import java.util.List;

public class FindSubsets {

    public static void main(String[] args) {
        List<List<Integer>> result = findSubsets2(new int[] { 1, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = findSubsets2(new int[] { 1, 5, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }


    public static List<List<Integer>> findSubsets2(int[] nums) {
        List<List<Integer>> res =  new ArrayList<>();
        res.add(new ArrayList<>());//add empty to start with


        for (int eachNumber : nums) {//for each number of input

            int allCurrentSubsetsSize = res.size();

            for (int existingSubsetIndex = 0; existingSubsetIndex < allCurrentSubsetsSize; existingSubsetIndex++) {//iterate over all subsets so far constructed

                //copy each subset so far constructed for not to override it or add to existing - but continue adding NEW subsets
                List<Integer> currentExistingSubsetCopy = new ArrayList<>(res.get(existingSubsetIndex));

                currentExistingSubsetCopy.add(eachNumber);

                res.add(currentExistingSubsetCopy);
            }

        }

        return res;

    }


        // space and time O(N * 2N)
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());// add empty subset to start

        for (int eachElement : nums) {//traverse all numbers of input

            int currentSubsetsSize = res.size();//current size of ALL subsets so far

            for (int i = 0; i < currentSubsetsSize; i++) {//so for each subset so far

                // create a new subset from the existing subset and insert the current element to it
                List<Integer> newSubsetThatsGonnaBeAdded = new ArrayList<>(res.get(i));//get each subset so far we have and add it to new

                newSubsetThatsGonnaBeAdded.add(eachElement);//add new element to this each we have so far

                res.add(newSubsetThatsGonnaBeAdded);//and add this subset with new element added to the result set of all

            }
        }

        return res;
    }
}
