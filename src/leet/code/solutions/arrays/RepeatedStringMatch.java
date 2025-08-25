package leet.code.solutions.arrays;

/*
686

https://leetcode.com/problems/repeated-string-match/

Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.

Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".

Example 1:

Input: a = "abcd", b = "cdabcdab"
Output: 3
Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
Example 2:

Input: a = "a", b = "aa"
Output: 2

Constraints:

1 <= a.length, b.length <= 104
a and b consist of lowercase English letters.
 */
public class RepeatedStringMatch {

    public static void main(String[] args) {
        String a = "abc";
        String b = "cabcabca";

        int rep = repeatedStringMatch(a, b);
        System.out.println(rep);
    }

    //O(n × (n + m))
    // where n = a.length, m = b.length
    private static int repeatedStringMatch(String a, String b) {
        if(!hasAllChars(a,b)){
            return -1;
        }

        StringBuilder sb = new StringBuilder();
        int times = 0;

        // Keep adding 'a' until we have enough length to potentially contain 'b'
        while (sb.length() < b.length()) {
            sb.append(a);
            times++;
        }

        if (sb.toString().contains(b)) {
            return times;
        }

        // Try adding one more 'a' (in case b spans across repetition boundary)
        sb.append(a);
        times++;

        if(sb.toString().contains(b)){
            return times;
        }

        return -1;
    }

    private static boolean hasAllChars(String a, String b){
        boolean[] alphabet = new boolean[26];

        for(char c : a.toCharArray()){
            alphabet[c - 'a'] = true;
        }

        for(char c : b.toCharArray()){
            if(!alphabet[c - 'a']){
                return false;
            }
        }
        return true;
    }
}