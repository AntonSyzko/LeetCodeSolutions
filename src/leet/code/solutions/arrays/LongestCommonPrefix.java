package leet.code.solutions.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/*
https://leetcode.com/problems/longest-common-prefix/
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".
Example 1:
Input: strs = ["flower","flow","flight"]
Output: "fl"

Example 2:
Input: strs = ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.

Constraints:
1 <= strs.length <= 200
0 <= strs[i].length <= 200
strs[i] consists of only lower-case English letters.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] arr = new String[]{"flower", "flow", "flight"};
        String longestCommonPrefix = longestCommonPrefix44(arr);
        System.out.println(longestCommonPrefix);

        arr = new String[]{"dog", "racecar", "car"};
        longestCommonPrefix = longestCommonPrefix33(arr);
        System.out.println(longestCommonPrefix);
    }

    public static String longestCommonPrefix(String[] strs) {
        String res = "";

        int index = 0;//cannot be more than smallest of em

        for (char each : strs[0].toCharArray()) {//base is first

            for (int i = 1; i < strs.length; i++) {
                if (index > strs[i].length() //index is more than others ( cannot be common )
                    || //OR
                    strs[i].charAt(index) != each) { //not equal in some of strings - so NOT common to ALL OF EM ANYMORE -EXIT !!!!
                    return res; //exit ( one of chars out of all is different - this cannot be  common to all prefix )
                }
            }
            res += each; //aggregate
            index++; //raise index
        }
        return res;
    }

    public static String longestCommonPrefix2(String[] strs) {
        StringBuilder longestCommonPrefix = new StringBuilder();
        Arrays.sort(strs, Comparator.comparing(String::length));

        int index = 0;
        for (char eachInShortest : strs[0].toCharArray()) {

            for (int j = 1; j < strs.length; j++) {
                if (index > strs[j].length() || strs[j].charAt(index) != eachInShortest) {
                    return longestCommonPrefix.toString();
                }
            }
            longestCommonPrefix.append(eachInShortest);
            index++;
        }

        return longestCommonPrefix.toString();
    }

    public static String longestCommonPrefix3(String[] strs) {
        StringBuilder longestCommonPrefix = new StringBuilder();

        if (strs == null || strs.length == 0) {
            return longestCommonPrefix.toString();
        }
        Arrays.sort(strs, Comparator.comparing(String::length));

        for (int i = 0; i < strs[0].length(); i++) {
            for (int j = 1; j < strs.length; j++) {
                if (i > strs[j].length() || strs[j].charAt(i) != strs[0].charAt(i)) {
                    return longestCommonPrefix.toString();
                }
            }
            longestCommonPrefix.append(strs[0].charAt(i));
        }

        return longestCommonPrefix.toString();
    }

    public static String longestCommonPrefix33(String[] strs) {

        String longestCommonPrefix = "";

        for (int i = 0; i < strs[0].length(); i++) {//just iterate over all chars of first string of input  array

            char currentChar = strs[0].charAt(i);//get current char from first string

            for (int j = 1; j < strs.length; j++) {//iterate over rest of strings in input array
                if (i > strs[j].length() //first string length is more than current string length ,index is more than others ( cannot be common )
                    ||
                    currentChar != strs[j].charAt(i)) {
                    return longestCommonPrefix;
                }
                longestCommonPrefix += currentChar;
            }
        }
        return "";
    }

    public static String longestCommonPrefix44(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }

            String commonPrefix = strs[0];//take any first

            for (int word = 0; word < strs.length; word++){//iterate over all words

                while(strs[word].indexOf(commonPrefix) != 0){//while index of common prefix is not ZERO - so the beginning og of word

                    commonPrefix = commonPrefix.substring(0, commonPrefix.length()-1);//substract last char - decrease by 1 char from end

                    if(commonPrefix.isEmpty()){//all exhaused - not common pref
                        return "";
                    }
                }

            }
            return commonPrefix;
    }
    }
