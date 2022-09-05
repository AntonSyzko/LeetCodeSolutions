package leet.code.solutions.math;

import java.util.Arrays;

/*
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer.
The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.
Increment the large integer by one and return the resulting array of digits.

Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].

Example 3:
Input: digits = [9]
Output: [1,0]
Explanation: The array represents the integer 9.
Incrementing by one gives 9 + 1 = 10.
Thus, the result should be [1,0].

Constraints:
1 <= digits.length <= 100
0 <= digits[i] <= 9
digits does not contain any leading 0's.
 */
public class PlusOne {

    public static void main(String[] args) {
        int[] digit = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(plusOne(digit)));

        digit = new int[]{4, 3, 2, 1};
        System.out.println(Arrays.toString(plusOne(digit)));

        digit = new int[]{1, 0};
        System.out.println(Arrays.toString(plusOne(digit)));

        digit = new int[]{3, 2, 9};
        System.out.println(Arrays.toString(plusOne(digit)));

        digit = new int[]{9};
        System.out.println(Arrays.toString(plusOne(digit)));

        digit = new int[]{9, 9};
        System.out.println(Arrays.toString(plusOne(digit)));

        digit = new int[]{8, 9, 9, 9};
        System.out.println(Arrays.toString(plusOne(digit)));
    }


    public static int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {

            if (digits[i] < 9) {

                digits[i]++;//just +1
                return digits;//and thats is it

            }//else == 9

            digits[i] = 0; //set zero and continue
        }

        //if we got here AT ALL - then (9,9, etc scenarios) - so resize and add starting 1 to remaining zeros
        // original digits  is now ALL ZEROES [0,0,0]
        int[] resizedResult = new int[digits.length + 1]; //new array with old size + 1

        resizedResult[0] = 1;//first element of resized = 1 ( tenth carry )

        return resizedResult;
    }
}
