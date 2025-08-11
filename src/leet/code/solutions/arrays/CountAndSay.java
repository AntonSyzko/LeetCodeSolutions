package leet.code.solutions.arrays;

/*
38


The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

countAndSay(1) = "1"
countAndSay(n) is the run-length encoding of countAndSay(n - 1).
Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of the characters (length of the run). For example, to compress the string "3322251" we replace "33" with "23", replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string becomes "23321511".

Given a positive integer n, return the nth element of the count-and-say sequence.



Example 1:

Input: n = 4

Output: "1211"

Explanation:

countAndSay(1) = "1"
countAndSay(2) = RLE of "1" = "11"
countAndSay(3) = RLE of "11" = "21"
countAndSay(4) = RLE of "21" = "1211"
Example 2:

Input: n = 1

Output: "1"

Explanation:

This is the base case.



Constraints:

1 <= n <= 30


Follow up: Could you solve it iteratively?
 */
public class CountAndSay {

    // Test class to demonstrate the solution
    public static void main(String[] args) {

        // Test cases
        System.out.println("n=1: " + countAndSay(1)); // "1"
        System.out.println("n=2: " + countAndSay(2)); // "11"
        System.out.println("n=3: " + countAndSay(3)); // "21"
        System.out.println("n=4: " + countAndSay(4)); // "1211"
        System.out.println("n=5: " + countAndSay(5)); // "111221"
    }

    /*
    Time Complexity: O(2^n)

    Each term can be up to twice as long as the previous term
    In the worst case, the length grows exponentially
    Processing each term takes O(length) time
    Total: O(1 + 2 + 4 + 8 + ... + 2^n) = O(2^n)

    Space Complexity: O(2^n)

    We store strings that can grow exponentially in length
    StringBuilder operations also require temporary space
    The final result string can be up to O(2^n) characters long
     */
    private static String countAndSay(int n) {
        if (n == 1) return "1";

        String curr = "1";

        for (int i = 2; i <= n; i++) {
            String currentPermutation = getNextPermutation(curr);
            curr = currentPermutation;
        }

        return curr;
    }

    private static String getNextPermutation(String curr) {

        StringBuilder res = new StringBuilder();

        int len = curr.length();

        int index = 0;

        while (index < len) {

            char currChar = curr.charAt(index);

            int count = 1;//current char occurrence count, as we have some char it is 1 by default

            while ((index + count <= len - 1) //in boundaries
                    &&
                    curr.charAt(index + count) == currChar) {//char at count offset is still same as char we have started with

                count++;//increase count for curr char
            }

            res.append(count).append(currChar);//aggregate res

            // Move to the next group of different characters
            index = index + count;
        }

        return res.toString();
    }
}