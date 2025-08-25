package leet.code.solutions.arrays;

/*
844

https://leetcode.com/problems/backspace-string-compare/description/

Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.

Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".
Example 2:

Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".

Constraints:

1 <= s.length, t.length <= 200
s and t only contain lowercase letters and '#' characters.

Follow up: Can you solve it in O(n) time and O(1) space?
 */
public class BackspaceStringsCompare {
    public static void main(String[] args) {
        String s = "a##c";
        String t = "#a#c";

        boolean ba = backspaceCompare(s, t);
        System.out.println(ba);
    }

    // O ( n + m )
    //O(n + m )
    public static boolean backspaceCompare(String s, String t) {
        return buildSTring(s).equals(buildSTring(t));
    }

    private static String buildSTring(String s) {

        StringBuilder sb = new StringBuilder();//acts like a stack

        for (char ch : s.toCharArray()) {

            if (ch == '#') {

                if(!sb.isEmpty()){//non empty stack
                    sb.deleteCharAt(sb.length() - 1);//delete last
                }

            }else{

                sb.append(ch);//only non # are stored !!!

            }
        }

        return sb.toString();
    }

    // Alternative more compact version
    // O ( n + m )
    // O ( 1 )
    public static boolean backspaceCompareCompact(String s, String t) {
        int sLen = s.length() - 1;
        int  tLen = t.length() - 1;

        int skipS = 0;
        int skipT = 0;

        while (sLen >= 0 || tLen >= 0) {//OR
            // Process string s
            while (sLen >= 0) {//iterate from END
                if (s.charAt(sLen) == '#') {
                    skipS++;
                    sLen--;
                } else if (skipS > 0) {
                    skipS--;
                    sLen--;
                } else {
                    break;
                }
            }

            // Process string t
            while (tLen >= 0) {
                if (t.charAt(tLen) == '#') {
                    skipT++;
                    tLen--;
                } else if (skipT > 0) {
                    skipT--;
                    tLen--;
                } else {
                    break;
                }
            }

            // Compare characters
            if (sLen >= 0 && tLen >= 0 && s.charAt(sLen) != t.charAt(tLen)) {
                return false;
            }

            // If one string is exhausted but the other isn't
            if ((sLen >= 0) != (tLen >= 0)) {
                return false;
            }

            sLen--;
            tLen--;

        }//big while end

        return true;
    }
}