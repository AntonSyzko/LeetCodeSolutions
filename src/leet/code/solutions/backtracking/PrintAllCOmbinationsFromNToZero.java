package leet.code.solutions.backtracking;

import java.util.Arrays;
import java.util.stream.Collectors;

/*
https://www.techiedelight.com/print-all-combination-numbers-from-1-to-n/

Print all combinations of numbers from 1 to `n` having sum `n`
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
 */
public class PrintAllCOmbinationsFromNToZero {

    public static void main(String[] args) {
        int numberAndTotalSum = 5;
        int[] resultArray = new int[numberAndTotalSum];

        System.out.println("\r\n\t all combinations that equal in sum  to " + numberAndTotalSum);
        // print all combinations of numbers from 1 to `n` having sum `n`
        printCombinations(1, numberAndTotalSum, resultArray, 0);
    }

    // Recursive function to print all combinations of numbers from `startNumber` to `number` having sum `number`.
    // The `index` denotes the next free slot in the output array `out`
    public static void printCombinations(int startNumber, int numberAndTotalSum, int[] resultArray, int index) {

        // if the sum becomes `number`, print the combination
        if (numberAndTotalSum == 0) {//number exhausetd to 0
            System.out.println(Arrays.stream(resultArray).limit(index).boxed().collect(Collectors.toList()));
        }


        // start from the previous element in the combination till `number`
        for (int currentNumber = startNumber; currentNumber <= numberAndTotalSum; currentNumber++) {

            // place current element at the current index
            resultArray[index] = currentNumber;

            // recur with a reduced sum ( sum - current number) , starting from current number onwards, increasing INDEX of next position in OUT array
            printCombinations(currentNumber, numberAndTotalSum - currentNumber, resultArray, index + 1);//recur and insert number in next index

        }
    }

}
