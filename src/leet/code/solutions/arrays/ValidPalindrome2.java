package leet.code.solutions.arrays;

/*
https://leetcode.com/problems/valid-palindrome/

You can remove 1 char and check if it's a palindrome


Constraints:

1 <= s.length <= 2 * 105
s consists only of printable ASCII characters.
 */
public class ValidPalindrome2 {

    public static void main(String[] args) {
        String test = "aba";
        boolean isPalindrome = isPalindrome(test);
        System.out.println(isPalindrome);

        test = "abca";
        isPalindrome = isPalindrome(test);
        System.out.println(isPalindrome);

        test = "kazaka";
        isPalindrome = isPalindrome(test);
        System.out.println(isPalindrome);

        test = "skazaka";
        isPalindrome = isPalindrome(test);
        System.out.println(isPalindrome);

    }


    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;

        boolean result = false;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                result = true;
            } else {
                return checkPalindromeWithoutOneChar(s, start + 1, end) || //we need to check just is palindrome with ONE (only one char ) missing
                    checkPalindromeWithoutOneChar(s, start, end - 1);
            }
            start++;
            end--;
        }
        return result;
    }

    private static boolean checkPalindromeWithoutOneChar(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isPalindromeRecursive(String s) {
        int start = 0;
        int end = s.length() - 1;

        boolean result = false;
        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                result = true;
            } else {
                return isPalindromeRecursive(s.substring(0, start) + s.substring(++start))
                       ||
                       isPalindromeRecursive(s.substring(0, end) + s.substring(++end));
            }
            start++;
            end--;
        }
        return result;
    }

}
