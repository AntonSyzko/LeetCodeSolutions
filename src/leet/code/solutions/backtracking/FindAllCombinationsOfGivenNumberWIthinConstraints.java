package leet.code.solutions.backtracking;

import java.util.Arrays;

/*
Given a positive number n, find all combinations of 2×n elements such that every element from 1 to n appears exactly twice and the distance between its two appearances is exactly equal to the value of the element.

For example,

Input: n = 3
Output: 3 1 2 1 3 2
        2 3 1 2 1 3

Input: n = 4
Output:4 1 3 1 2 4 3 2
       2 3 4 2 1 3 1 4

Total 52 configurations possible. Note that no combination of elements is possible for some values of n like 2, 5, 6, etc.

 */
public class FindAllCombinationsOfGivenNumberWIthinConstraints {

    public static void main(String[] args) {
        int numberConstraint = 3;

        // create an input array double the size of a given number with all its elements initialized by -1
        int[] resultArray = new int[2 * numberConstraint];
        Arrays.fill(resultArray, -1);

        // start from element 1
        int startElement = 1;
        findAllCombinations(resultArray, startElement, numberConstraint);
    }

    public static void findAllCombinations(int[] resultArray, int startElement, int numberConstraint){
        // if all elements are filled, print the solution
        if (startElement > numberConstraint) {
            System.out.println(Arrays.toString(resultArray));
            return;
        }

        for (int i = 0; i < (2 * numberConstraint); i++) {

            if((resultArray[i] == -1) && ((i + startElement + 1) < (2 * numberConstraint)) && (resultArray[i + startElement + 1] == -1)){//if is not filled ( = -1) AND is withing constraints

                //insert
                resultArray[i] = startElement;
                resultArray[i + startElement + 1] = startElement;//insert in next possible cell

                findAllCombinations(resultArray, startElement + 1, numberConstraint);//recur with start element increased

                //backtrack
                resultArray[i] = -1;
                resultArray[i + startElement + 1] = -1;

            }

        }
    }

}
