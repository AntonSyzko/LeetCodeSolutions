package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
https://leetcode.com/problems/fizz-buzz/

Given an integer n, return a string array answer (1-indexed) where:

answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
answer[i] == "Fizz" if i is divisible by 3.
answer[i] == "Buzz" if i is divisible by 5.
answer[i] == i (as a string) if none of the above conditions are true.

Input: n = 3
Output: ["1","2","Fizz"]
 */
public class FizzBuzz {

    public static void main(String[] args) {
        List<String> res = fizzBuzz(3);
        res.forEach(System.out::println);
    }

    public static List<String> fizzBuzz(int n) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if ((i % 3 == 0) && (i % 5 == 0)) {
                res.add( "FizzBuzz");
            } else if (i % 3 == 0) {
                res.add( "Fizz");
            } else if (i % 5 == 0) {
                res.add( "Buzz");
            } else {
                res.add( String.valueOf(i));
            }
        }
        return res;
    }
}
