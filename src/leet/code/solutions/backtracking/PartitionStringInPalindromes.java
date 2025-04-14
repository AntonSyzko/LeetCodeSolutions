package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Examples
Example 1:
Input: aab
Output:
  [
  ["aa","b"],
  ["a","a","b"]
  ]

 */
public class PartitionStringInPalindromes {

    public static void main(String[] args) {
      String str = "aab";

      List<List<String>> palindromes = partition(str);

        System.out.println(palindromes);
    }


    private static List<List<String>> partition(String str) {
        List<List<String>> res = new ArrayList<>();
        List<String> currentPartition = new ArrayList<>();

        // Start the backtracking from index 0
        partitionDFS(str, 0, currentPartition, res);

        return res;

    }

    private static void partitionDFS(String str, int startIndex, List<String> currentPartition, List<List<String>> res) {
         if(startIndex == str.length()){
             res.add(new ArrayList<>(currentPartition));
             return;
         }

        for (int end = startIndex; end < str.length(); end++) {

            String substring = str.substring(startIndex, end + 1);//aggregate temp result

            if(isPalindrome(substring)){//condition of tree pruning

                currentPartition.add(substring);

                partitionDFS(str, end + 1, currentPartition, res);

                currentPartition.remove(currentPartition.size() - 1);//BACKTRACK

            }


        }
    }

    private static boolean isPalindrome(String s) {

        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
