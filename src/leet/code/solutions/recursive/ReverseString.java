package leet.code.solutions.recursive;

/*
https://www.techiedelight.com/reverse-a-string-using-recursion/

    Write a recursive program to efficiently reverse a given string in C, C++, and Java.

For example,

Input:  Techie Delight
Output: thgileD eihceT
 */
public class ReverseString {

    public static void main(String[] args) {
        String str = "Techie Delight";

        char[] c = str.toCharArray();
        reverse(c, 0, c.length - 1);
        str = new String(c);

        System.out.print("Reverse of the given string is " + str);
    }

    private static void reverse(char[] chars, int start, int end) {
        if (start < end) {
            swap(chars, start, end);
            reverse(chars, start + 1, end-1);
        }
    }

    private static void swap(char[] chars, int start, int end) {
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;
    }
}
