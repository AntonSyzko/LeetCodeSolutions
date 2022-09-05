package leet.code.solutions.top100;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/roman-to-integer/
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII.
Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer.

Example 1:
Input: s = 'III'
Output: 3
Explanation: III = 3.

Example 2:
Input: s = 'LVIII'
Output: 58
Explanation: L = 50, V= 5, III = 3.

Example 3:
Input: s = 'MCMXCIV'
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class RomanToInteger {
    public static void main(String[] args) {
        String roman = "III";
        int integer = romanToInt(roman);
        System.out.println(integer);

        roman = "IV";
        integer = romanToInt(roman);
        System.out.println(integer);

        roman = "IX";
        integer = romanToInt(roman);
        System.out.println(integer);

        roman = "LVIII";
        integer = romanToInt(roman);
        System.out.println(integer);


        roman = "MCMXCIV";
        integer = romanToInt(roman);
        System.out.println(integer);
    }

    public static int romanToInt(String s) {
        int res = 0;

        Map<Character, Integer> romansToIntegers = new HashMap<>();
        romansToIntegers.put('I', 1);
        romansToIntegers.put('V', 5);
        romansToIntegers.put('X', 10);
        romansToIntegers.put('L', 50);
        romansToIntegers.put('C', 100);
        romansToIntegers.put('D', 500);
        romansToIntegers.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {
            //exceptions of IV , IX etc .......if current  is more than prev
            if (i > 0 && romansToIntegers.get(s.charAt(i)) > romansToIntegers.get(s.charAt(i - 1))) {

                //current minus 2   and times  previous
                res += romansToIntegers.get(s.charAt(i)) - 2  * romansToIntegers.get(s.charAt(i - 1));

            } else {
                res += romansToIntegers.get(s.charAt(i));
            }
        }
        return res;
    }

        public static int romanToInt2(String s) {
        int res = 0;
        Map<Character, Integer> mapa = new HashMap<>();
        mapa.put('I', 1);
        mapa.put('V', 5);
        mapa.put('X', 10);
        mapa.put('L', 50);
        mapa.put('C', 100);
        mapa.put('D', 500);
        mapa.put('M', 1000);

        for (int i = 0; i < s.length(); i++) {

            res += mapa.get(s.charAt(i));

            if (i != 0 && s.charAt(i) == 'V' && s.charAt(i - 1) == 'I') {
                res -= 2;
            }

            if (i != 0 && s.charAt(i) == 'X' && s.charAt(i - 1) == 'I') {
                res -= 2;
            }

            if (i != 0 && s.charAt(i) == 'L' && s.charAt(i - 1) == 'X') {
                res -= 20;
            }

            if (i != 0 && s.charAt(i) == 'C' && s.charAt(i - 1) == 'X') {
                res -= 20;
            }

            if (i != 0 && s.charAt(i) == 'D' && s.charAt(i - 1) == 'C') {
                res -= 200;
            }


            if (i != 0 && s.charAt(i) == 'M' && s.charAt(i - 1) == 'C') {
                res -= 200;
            }
        }

        return res;
    }
}
