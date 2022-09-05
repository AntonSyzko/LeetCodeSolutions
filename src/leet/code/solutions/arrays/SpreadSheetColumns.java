package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/excel-sheet-column-title/

Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

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
Input: columnNumber = 1
Output: "A"
Example 2:

Input: columnNumber = 28
Output: "AB"
Example 3:

Input: columnNumber = 701
Output: "ZY"
 */
public class SpreadSheetColumns {
    public static void main(String[] args) {
        String spreadSheetColumn = "ZD";
        int res = decodeColumnID(spreadSheetColumn);
        System.out.println("Column "+ spreadSheetColumn + " is: " + res);
        String convertedBAck = convertToTitle(res);
        System.out.println("Number " + res + " converted back is: " + convertedBAck);
    }

    private static String convertToTitle(int columnNumber) {
        if(columnNumber <=0){
            return "";
        }

        char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        StringBuilder res = new StringBuilder();

        while (columnNumber != 0) {
            columnNumber = columnNumber -1;//we are not zero based, 1 based , A = 1

            int curr = columnNumber % 26;//reminder

            res.append(alphabet[curr]);//by position in alphabet

            columnNumber = columnNumber / 26;
        }

        return res.reverse().toString();//!!! reverse

    }

    private static int decodeColumnID(String col) {
        int result = 0;
        for (int i = 0; i < col.length(); i++) {
            int currentChar = col.charAt(i);
            //alphabet is base 26 , - 'A' gives you number of current char, all capital case , +1 since we are 1 based
            result = result * 26 + currentChar - 'A' + 1;
        }

        return result;
    }
}
