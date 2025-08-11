package leet.code.solutions.backtracking;

import java.util.ArrayList;
import java.util.List;

/*
93

https://leetcode.com/problems/restore-ip-addresses/description/

A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

Example 1:

Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

Constraints:

1 <= s.length <= 20
s consists of digits only.
 */
public class RestoreIpAddress {

    public static void main(String[] args) {
        String str  = "0000";
        List<String> ips = restoreIpAddresses(str);
        System.out.println(ips);

         str  = "25525511135";
         ips = restoreIpAddresses(str);
        System.out.println(ips);
    }

    private static List<String> restoreIpAddresses(String str) {
        List<String> res = new ArrayList<>();
        List<String> currCombo = new ArrayList<>();
        int index = 0;

        dfs(str, index,currCombo,res);

        return res;
    }

    private static void dfs(String str, int index,List<String> currCombo,List<String> res) {
        // BASE case: we have 4 parts and used all characters
        if(currCombo.size() == 4 && index == str.length()){
            res.add(String.join(".",currCombo));
            return;
        }

        // Pruning: if we have 4 parts already or no more characters
        if(currCombo.size() == 4 || index == str.length()){
            return;
        }

        // Try lengths 1, 2, 3 for current IP part
        for(int length = 1; length <= 3; length++){

            int endIndex = index + length;

            // Check if we have enough characters left
            if(endIndex > str.length()){
                break; // Use break instead of return to try other lengths
            }

            String subStr = str.substring(index,endIndex);

            // Check for leading zeros: if length > 1 and starts with '0', invalid
            if(length > 1 && subStr.charAt(0) == '0'){
                break;// No point trying longer lengths if this has leading zero
            }

            // Check if number is valid (0-255)
            if(Integer.parseInt(subStr) > 255){
                break;// No point trying longer lengths if already > 255
            }

            // Add to current combination and recurse
            currCombo.add(subStr);

            dfs(str,endIndex,currCombo,res);

            currCombo.remove(currCombo.size()-1);//BACKTRACK
        }
    }
}