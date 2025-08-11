package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/problems/palindrome-partitioning/description/

Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s.

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]

Constraints:

1 <= s.length <= 16
s contains only lowercase English letters.

 */
public class PalindromePartitioning {

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> list = partition(s);
        System.out.println(list);
    }

    private static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> combo = new ArrayList<>();
        int index = 0;

        dfsPartition(s,index, combo, res);

        return res;
    }

    /*
    Time complexity: O(n × 2^n)

        Same number of partitions (2^(n-1))
        The palindrome check is still O(m)
        StringBuilder append is O(1) per character
        But we still call toString() which is O(m)

    Space Complexity
        Both solutions have:

        O(n) recursion stack depth
        O(n) for the current combination list
        O(2^n) for storing all valid partitions in the result

        Your solution has an additional O(n) space for the StringBuilder.
     */
    private static void dfsPartition(String s, int index, List<String> combo, List<List<String>> res) {
        //BASE
        if(index == s.length()){//string is over
            res.add(new ArrayList<>(combo));
            return;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = index; i < s.length(); i++){
            sb.append(s.charAt(i));

            if(isPalindrome(sb.toString())){//main check

                combo.add(sb.toString());

                dfsPartition(s, i + 1, combo, res);//recur

                combo.remove(combo.size() - 1);//BACKTRACK

            }
        }
    }

    /*
    Time complexity: O(n × 2^n)

We have 2^(n-1) possible partitions in worst case
Each partition requires O(n) to check if it's a palindrome
Each substring operation is O(m) where m can be up to n
     */
    private  static void dfs(String s, int start, List<String> combo, List<List<String>> res){
        if( start == s.length()){
            res.add(new ArrayList<>(combo));
            return;
        }

        for(int end = start; end < s.length(); end ++){

            if(isPalindrome(s.substring(start, end +1))){
                combo.add(s.substring(start, end +1));

                dfs(s, end + 1, combo, res);

                combo.removeLast();
            }
        }
    }

    private static boolean isPalindrome(String  s){
        if(s.length() == 1){
            return true;
        }

        int start = 0;
        int end = s.length() - 1;

        while(start < end){
            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
