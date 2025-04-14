package leet.code.solutions.arrays;

import java.util.Stack;

/*
https://leetcode.com/problems/reverse-only-letters/

Given a string s, reverse the string according to the following rules:

All the characters that are not English letters remain in the same position.
All the English letters (lowercase or uppercase) should be reversed.
Return s after reversing it.



Example 1:

Input: s = "ab-cd"
Output: "dc-ba"
Example 2:

Input: s = "a-bC-dEf-ghIj"
Output: "j-Ih-gfE-dCba"
Example 3:

Input: s = "Test1ng-Leet=code-Q!"
Output: "Qedo1ct-eeLg=ntse-T!"


Constraints:

1 <= s.length <= 100
s consists of characters with ASCII values in the range [33, 122].
s does not contain '\"' or '\\'.
 */
public class ReverseOnlyLetters {

    public static void main(String[] args) {
         String s = "Test1ng-Leet=code-Q!";
         String reversed = reverseOnlyLettersStack(s);
         System.out.println(reversed);
    }

    /*
    Time Complexity: O(n) where n is the length of the string. We only scan through the string once with the two pointers.

    Space Complexity: O(n) for the character array.
     */

    private static String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if(!Character.isLetter(s.charAt(left))){
                chars[left] = s.charAt(left);
                left++;
                continue;
            }

            if(!Character.isLetter(s.charAt(right))){
                chars[right] = s.charAt(right);
                right--;
                continue;
            }

            char temp = chars[left];
            chars[left] = s.charAt(right);
            chars[right] = temp;

            left++;
            right--;
        }

        return new String(chars);
    }


    /**
     Time Complexity:

     Stack: O(n) - two passes through the string (one to fill the stack, one to build the result)


     Space Complexity:

     Stack: O(k) where k is the number of letters in the string, plus O(n) for the StringBuilder
     */
    private static String reverseOnlyLettersStack(String s) {

        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char c : s.toCharArray()){
            if(Character.isLetter(c)){
                stack.push(c);
            }
        }

        for(int i = 0; i < s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                sb.append(stack.pop());
            }else{
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();

    }

    }

    /*
      Two-Pointer: O(n) - single pass through the string
     Stack: O(n) - two passes through the string (one to fill the stack, one to build the result)


     Space Complexity:

     Two-Pointer: O(n) - for the character array
     Stack: O(k) where k is the number of letters in the string, plus O(n) for the StringBuilder
     */