package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/rotate-string/
Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
A shift on s consists of moving the leftmost character of s to the rightmost position.
For example, if s = "abcde", then it will be "bcdea" after one shift.

Example 1:
Input: s = "abcde", goal = "cdeab"
Output: true

Example 2:
Input: s = "abcde", goal = "abced"
Output: false

Constraints:
1 <= s.length, goal.length <= 100
s and goal consist of lowercase English letters.
 */
public class StringRotation {

    public static void main(String[] args) {
        String first = "abcde";
        String second = "cdeab";
        boolean isRotation = rotateString(first, second);
        System.out.println(isRotation);
    }

    public static boolean rotateString(String s, String goal) {
        //equal length
        return (s.length() == goal.length()
            && //AND
            s.concat(s).contains(goal)); //S concatenated with itself - contains each and every permutation of SHIFTING S itself, so contains will find if GOAl is a form of rotation

    }
}
