package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/license-key-formatting/

You are given a license key represented as a string s that consists of only alphanumeric characters and dashes.
The string is separated into n + 1 groups by n dashes. You are also given an integer k.

We want to reformat the string s such that each group contains exactly k characters, except for the first group,
which could be shorter than k but still must contain at least one character. Furthermore, there must be a dash inserted between two groups,
and you should convert all lowercase letters to uppercase.

Return the reformatted license key.

Example 1:
Input: s = "5F3Z-2e-9-w", k = 4
Output: "5F3Z-2E9W"
Explanation: The string s has been split into two parts, each part has 4 characters.
Note that the two extra dashes are not needed and can be removed.

Example 2:
Input: s = "2-5g-3-J", k = 2
Output: "2-5G-3J"
Explanation: The string s has been split into three parts, each part has 2 characters except the first part as it could be shorter as mentioned above.

Constraints:
1 <= s.length <= 105
s consists of English letters, digits, and dashes '-'.
1 <= k <= 104
 */
public class LicenseKeyFormatting {
    public static void main(String[] args) {
        String key = "5F3Z-2e-9-w";
        String formattedKey = licenseKeyFormattingNotOptimized2(key, 4);
        System.out.println(formattedKey);

//        key = "2-5g-3-J";
//        formattedKey = licenseKeyFormattingNotOptimized2(key, 2);
//        System.out.println(formattedKey);
//
//        key = "2-4A0r7-4k";
//        formattedKey = licenseKeyFormattingNotOptimized2(key, 3);
//        System.out.println(formattedKey);
//
//        key = "---";
//        formattedKey = licenseKeyFormattingNotOptimized2(key, 3);
//        System.out.println(formattedKey);

    }

    public static String licenseKeyFormatting(String s, int k) {
        s = s.toUpperCase().replaceAll("-", "");

        StringBuilder sb = new StringBuilder(s);

        //BACKWARDS IN GROUP OF K
        for (int i = s.length() - k; i > 0; i = i - k) {//mind i>0 since we do not want to append - at start of the string
            sb.insert(i, "-");//append - at groups of K ( but not at the  start of resulting string )

        }
        return sb.toString();
    }


    public static String licenseKeyFormattingNotOptimized(String s, int k) {
        String upperCased = s.replaceAll("-", "").toUpperCase();
        StringBuilder res = new StringBuilder();

        int firstGroupIndentation = upperCased.length() % k;

        if (firstGroupIndentation != 0) {
            String firstGroup = upperCased.substring(0, firstGroupIndentation) + "-";
            res.append(firstGroup);
        }

        for (int i = firstGroupIndentation; i < upperCased.length(); i += k) {
            String currentBlock = upperCased.substring(i, i + k);
            res.append(currentBlock).append("-");
        }

        if (res.length() != 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();

    }

    public static String licenseKeyFormattingNotOptimized2(String s, int k) {
        String upperCased = s.replaceAll("-", "").toUpperCase();
        StringBuilder res = new StringBuilder(upperCased);

        int firstGroupIndentation = upperCased.length() % k;

        if (firstGroupIndentation != 0) {
            res.insert(firstGroupIndentation,"-");

        }

        for (int i = 0; i < upperCased.length(); i += k) {
            res.insert(i + k,"-");
        }

//        if (res.length() != 0) {
//            res.deleteCharAt(res.length() - 1);
//        }
        return res.toString();
    }
}
