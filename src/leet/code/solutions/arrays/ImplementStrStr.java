package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/implement-strstr/

Implement strStr().
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
Clarification: What should we return when needle is an empty string? This is a great question to ask during an interview.
For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

Example 1:
Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:
Input: haystack = "aaaaa", needle = "bba"
Output: -1

Example 3:
Input: haystack = "", needle = ""
Output: 0

Constraints: 0 <= haystack.length, needle.length <= 5 * ,haystack and needle consist of only lower-case English characters.
 */
public class ImplementStrStr {

    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        int indexOfNeedleInHaystack = strStr2(haystack, needle);
        System.out.println(indexOfNeedleInHaystack);

        haystack = "abc";
        needle = "c";
        indexOfNeedleInHaystack = strStr2(haystack, needle);
        System.out.println(indexOfNeedleInHaystack);

        haystack = "aaa";
        needle = "aa";
        indexOfNeedleInHaystack = strStr2(haystack, needle);
        System.out.println(indexOfNeedleInHaystack);

        haystack = "mississippi";
        needle = "issip";
        indexOfNeedleInHaystack = strStr2(haystack, needle);
        System.out.println(indexOfNeedleInHaystack);

        haystack = "mississippi";
        needle = "issipi";
        indexOfNeedleInHaystack = strStr2(haystack, needle);
        System.out.println(indexOfNeedleInHaystack);
    }


    public static int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }
        if (haystack.isEmpty() || needle.isEmpty()) {
            return 0;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (haystack.startsWith(needle, i)) {//if FULL length needle EXISTS in haystack starting at i
                    // if (haystack.substring(i, needle.length() + i).equals(needle)) { // out of bound
                    return i;
                }
            }
        }
        return -1;
    }

    public static int strStr2(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }
        if (haystack.isEmpty() || needle.isEmpty()) {
            return 0;
        }


        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            int j ;
            for (j=0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == needle.length() ) {//length match ?
                return i;
            }
        }
        return -1;
    }

}
