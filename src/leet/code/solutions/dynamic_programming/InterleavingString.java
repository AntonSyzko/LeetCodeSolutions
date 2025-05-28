package leet.code.solutions.dynamic_programming;

/*
https://neetcode.io/problems/interleaving-string

You are given three strings s1, s2, and s3. Return true if s3 is formed by interleaving s1 and s2 together or false otherwise.

Interleaving two strings s and t is done by dividing s and t into n and m substrings respectively, where the following conditions are met

|n - m| <= 1, i.e. the difference between the number of substrings of s and t is at most 1.
s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
Interleaving s and t is s1 + t1 + s2 + t2 + ... or t1 + s1 + t2 + s2 + ...
You may assume that s1, s2 and s3 consist of lowercase English letters.

Example 1:

Input: s1 = "aaaa", s2 = "bbbb", s3 = "aabbbbaa"

Output: true
Explanation: We can split s1 into ["aa", "aa"], s2 can remain as "bbbb" and s3 is formed by interleaving ["aa", "aa"] and "bbbb".

Example 2:

Input: s1 = "", s2 = "", s3 = ""

Output: true
Example 3:

Input: s1 = "abc", s2 = "xyz", s3 = "abxzcy"

Output: false
Explanation: We can't split s3 into ["ab", "xz", "cy"] as the order of characters is not maintained.

Constraints:

0 <= s1.length, s2.length <= 50
0 <= s3.length <= 100
 */
public class InterleavingString {

    public static void main(String[] args) {
          String s1 = "aaaa";
          String s2 = "bbbb";
          String s3 = "aabbbbaa";

          System.out.println(isInterleave_DP(s1, s2, s3));
    }

    private static boolean isInterleave_DP(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if (m + n != s3.length()) return false;
         Boolean[][] dp = new Boolean[m + 1][n + 1];
        return dfs_DP(0, 0, 0, s1, s2, s3, dp);
    }

    /*
Time & Space Complexity

    Time complexity: O( m+n)

    Space complexity: O(m+n)

    Where m is the length of the string s1 and n is the length of the string s2.
 */
    private  static boolean dfs_DP(int s1Index, int s2Index, int s3Index, String s1, String s2, String s3, Boolean[][] dp ) {
        if (s3Index == s3.length()) {
            return (s1Index == s1.length()) && (s2Index == s2.length());
        }

        if (dp[s1Index][s2Index] != null) {//memo hit
            return dp[s1Index][s2Index];
        }

        boolean res = false;

        //advance s1 index
        if (s1Index < s1.length() && s1.charAt(s1Index) == s3.charAt(s3Index)) {
            res = dfs_DP(s1Index + 1, s2Index, s3Index + 1, s1, s2, s3, dp);
        }

        //advance s2 index
        if (!res && s2Index < s2.length() && s2.charAt(s2Index) == s3.charAt(s3Index)) {
            res = dfs_DP(s1Index, s2Index + 1, s3Index + 1, s1, s2, s3, dp);
        }

        dp[s1Index][s2Index] = res;//memoise

        return res;
    }

    // ------- RECURSIVE -------------------
    private static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        int s1Index = 0;
        int s2Index = 0;
        int s3Index = 0;
        return dfs(s1Index, s2Index, s3Index, s1, s2, s3);
    }

    /*
    Time & Space Complexity

        Time complexity: O(2 m+n)

        Space complexity: O(m+n)

        Where m is the length of the string s1 and n is the length of the string s2.
     */
    private static boolean dfs(int s1Index, int s2Index, int s3Index, String s1, String s2, String s3){
        if(s3Index == s3.length()){
            return (s1Index == s1.length()) && (s2Index == s2.length());//both indexes have reached end
        }

        //advance s1 index
        if(s1Index < s1.length() && s1.charAt(s1Index) == s3.charAt(s3Index)){
            if(dfs(s1Index + 1, s2Index, s3Index + 1, s1, s2, s3)){
                return true;
            }
        }

        //advance s2 index
        if(s2Index < s2.length() && s2.charAt(s2Index) == s3.charAt(s3Index)){
            if(dfs(s1Index, s2Index + 1 , s3Index + 1, s1, s2, s3)){
                return true;
            }
        }

        //none returned true -> false
        return false;
    }
}
