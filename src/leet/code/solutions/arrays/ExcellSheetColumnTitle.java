package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/excel-sheet-column-number/

Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.

For example:
A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28
...

Example 1:
Input: columnTitle = "A"
Output: 1

Example 2:
Input: columnTitle = "AB"
Output: 28

Example 3:
Input: columnTitle = "ZY"
Output: 701

Constraints:
1 <= columnTitle.length <= 7
columnTitle consists only of uppercase English letters.
columnTitle is in the range ["A", "FXSHRXW"].
 */
public class ExcellSheetColumnTitle {

    public static void main(String[] args) {
//        int res = 'A';
//        System.out.println(res - 64);
//
//        res = 'B';
//        System.out.println(res - 64);
//
//        res = 'Z';
//        System.out.println(res - 64);

        String input = "AB";
        int count = titleToNumber(input);
        System.out.println(count);
    }

    public static int titleToNumber(String columnTitle) {
        int res = 0;

        if(columnTitle == null) {
            return -1;
        }

        for (int i = 0; i < columnTitle.length(); i++) {
             res *= 26; //base of each iteration in alphabetical order, so that next time let's say A occurs it's not 1 , but 27 etc ...
             res += columnTitle.charAt(i)- 'A' + 1 ; //char - `A` as base number , this  will give it's ASCI number and  +1 ( so that A -A is not zero )

          //  res += columnTitle.charAt(i)- 64  ; base for capital case `A` is 64 in ASCI
        }
        return res;
    }
}
