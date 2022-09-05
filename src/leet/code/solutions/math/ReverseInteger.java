package leet.code.solutions.math;

/*
https://leetcode.com/problems/reverse-integer/
Given a signed 32-bit integer x, return x with its digits reversed. If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:
Input: x = 123
Output: 321

Example 2:
Input: x = -123
Output: -321

Example 3:
Input: x = 120
Output: 21

Constraints:
-231 <= x <= 231 - 1
 */
public class ReverseInteger {

    public static void main(String[] args) {
        int num = 1534236469;
        int reversed = reverse(num);
        System.out.println(reversed);
    }

    public static int reverse(int x) {

        int reversed = 0;

        while (x != 0) {
            int popTenthReminder = x % 10;

            x = x / 10;

            // MORE than next we * 10          OR         equal next time we *10   AND  popTenth reminder will make it overflow
            if (reversed > Integer.MAX_VALUE / 10 || reversed == Integer.MAX_VALUE / 10 && popTenthReminder > 7) {//
                return 0;
            }
            // less than next we * 10          OR         equal next time we *10   AND  popTenth reminder will make it overflow
            if (reversed < Integer.MIN_VALUE / 10 || reversed == Integer.MIN_VALUE / 10 && popTenthReminder < -8) {
                return 0;
            }
            // here we multiply by 10 and we have to account for that above
            reversed = (reversed * 10) + popTenthReminder;//and we add tenthReminder and we have to account for that for overflow scenario 

        }

        return reversed;
    }

    public static int reverseSimple(int x) {
        int result = 0;
        int xRemaining = Math.abs(x);

        while (xRemaining != 0) {
            result = result * 10 + xRemaining % 10;
            xRemaining = xRemaining / 10;
        }
        return x < 0 ? -xRemaining : xRemaining;

    }

}
