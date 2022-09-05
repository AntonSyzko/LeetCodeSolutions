package leet.code.solutions.math;

/*
Given a non-negative integer x, compute and return the square root of x.
Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.
Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.

Example 1:
Input: x = 4
Output: 2

Example 2:
Input: x = 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since the decimal part is truncated, 2 is returned.

Constraints:  0 <= x <= 231 - 1
 */
public class SquareRoot {


    public static void main(String[] args) {
        System.out.println(mySqrt(4));

        System.out.println(mySqrt(8));

        System.out.println(mySqrt2(16));

        System.out.println(mySqrt2(2147395599));

    }




    public static int mySqrt(int x) {
        long start = 1;
        long end = x;

        if (x < 2) {
            return x;
        }

        while (start < end) {

            long mid = start + (end - start) / 2;
            long midPower2 = mid * mid;

            if (midPower2 == x) { //exact match
                return (int) mid;
            } else if (midPower2 > x) { // exceeded range
                end = mid; // make MID as next end
            } else if (midPower2 < x) { //below range
                start = mid + 1; // make  MID new start
            }
        }

        return (int) (start - 1); // at the end if no correct match ( first if), return one below start ( so power of lower matching )
    }


    public static int mySqrt2(int x) {
        int res = -1;
        int start = 0;
        int end = x;

        while (start <= end) {

            int mid = start + (end - start) / 2;

            if (mid <= x / mid) {

                start++;
                res = mid;

            } else {

                end--;
            }
        }
        return res;
    }

    public static int mySqrt33(int x) {
        long start = 1;
        long end = x;

        while (start < end) {
            long mid = start + (end - start) / 2;

            if (mid * mid == x) {
                return (int) mid;
            } else if (mid * mid < x) {
                start = mid;
            } else if (mid * mid > x) {
                end = mid;
            }
        }

        if (end * end == x) {
            return (int) end;
        }

        return (int) start;
    }
}
