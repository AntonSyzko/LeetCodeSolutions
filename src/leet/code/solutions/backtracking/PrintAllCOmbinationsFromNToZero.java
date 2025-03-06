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
        int number = 5;
        int[] out = new int[number];

        // print all combinations of numbers from 1 to `n` having sum `n`
        printCombinations(1, number, out, 0);
    }

    // Recursive function to print all combinations of numbers from `startNumber` to `number` having sum `number`.
    // The `index` denotes the next free slot in the output array `out`
    public static void printCombinations(int startNumber, int number, int[] out, int index) {

        // if the sum becomes `number`, print the combination
        if (number == 0) {//number exhausetd to 0
            System.out.println(Arrays.stream(out).limit(index).boxed().collect(Collectors.toList()));
        }


        // start from the previous element in the combination till `number`
        for (int currentNumber = startNumber; currentNumber <= number; currentNumber++) {

            // place current element at the current index
            out[index] = currentNumber;

            // recur with a reduced sum
            printCombinations(currentNumber, number - currentNumber, out, index + 1);//recur and insert number in next index

        }

    }
}
