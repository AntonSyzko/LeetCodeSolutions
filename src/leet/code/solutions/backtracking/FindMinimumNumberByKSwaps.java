package leet.code.solutions.backtracking;

/*
Find the minimum number possible by doing at-most `k` swaps
Given a positive integer, find the minimum number possible by doing at-most k swap operations upon its digits.

For example,

Input:  S = 934651, k = 1
Output: 134659

Input:  S = 934651, k = 2
Output: 134569

Input:  S = 52341, k = 2
Output: 12345 (Only 1 swap needed)

Input:  S = 12345, k = 2
Output: 12345 (no change as all digits are already sorted in increasing order)
 */
public class FindMinimumNumberByKSwaps {

    public static void main(String[] args) {
        // input number
        String s = "934651";
        int k = 2;

        String min = findMinimum(s, k);

        System.out.println("The minimum number formed by doing at-most " + k + " swaps is " + min);
    }

    // Wrapper over findMin() function
    private static String findMinimum(String strNumber, int numberOfSwaps) {
            // valid input check
            if (strNumber == null || strNumber.length() == 0) {
                return strNumber;
            }

            // convert digits of a given integer to a char array to facilitate operations on them
            char[] digits = strNumber.toCharArray();

            return findMin(digits, numberOfSwaps, strNumber);//strNumber wqe start as 'minSoFar' as at the start min is the very number before any swapping applied
    }

     private static String findMin(char[] digits, int numberOfSwaps, String minNumBerSoFar) {
        String currentPermutation = new String(digits);

        if(currentPermutation.compareTo(minNumBerSoFar) < 0){//if curr permutation digit is SMALLER tha min so far
            minNumBerSoFar = currentPermutation;//reset MIN
        }

         // BASE CASE
        if(numberOfSwaps < 1){//swap attempts exhausted
            return minNumBerSoFar;
        }


         for (int i = 0; i < digits.length; i++) {
             for (int j = i + 1; j <  digits.length; j++) {//next to i

                 if(digits[i] > digits[j]){// if preceding digit is BIGGER than following e.g. 51 -> swap

                     swapDigits(digits,i,j);//swap em

                     minNumBerSoFar = findMin(digits, numberOfSwaps - 1 , minNumBerSoFar);//recur with swapped and minus one swap attempt

                     swapDigits(digits,i,j);// BACKTRACK - restore the array

                 }
             }
         }

         return minNumBerSoFar;
     }

    private static void swapDigits(char[] digits, int i, int j) {
        char temp = digits[i];
        digits[i] = digits[j];
        digits[j] = temp;
    }
}
