package leet.code.solutions.arrays;

/*
67
https://leetcode.com/problems/add-binary/description/

Given two binary strings a and b, return their sum as a binary string.

Example 1:

Input: a = "11", b = "1"
Output: "100"

Example 2:

Input: a = "1010", b = "1011"
Output: "10101"


Constraints:

1 <= a.length, b.length <= 104
a and b consist only of '0' or '1' characters.
Each string does not contain leading zeros except for the zero itself.

 */
public class AddBinary {

    public static void main(String[] args) {
        String a = "1010";
        String b = "1011";

        String res = addBinary(a, b);

        System.out.println(res);
    }

    /*
    O(max(m, n))
    O(max(m, n))
     */
    private static String addBinary(String a, String b) {

        StringBuilder sb = new StringBuilder();

        int aLen = a.length() -1 ;
        int bLen = b.length() -1;
        int carry = 0;

        while (aLen >= 0 || bLen >= 0 || carry > 0){

            int sum = carry;

            if(aLen >= 0){
                sum += a.charAt(aLen) - '0';// mind +=
                aLen--;
            }

            if(bLen >= 0){
                sum += b.charAt(bLen) - '0';// mind +=
                bLen--;
            }

            sb.append(sum % 2);//%2 will append 1 or 0 (sum = 1  mod2 is 1 will append 1 ,  if  sum = 2 MOD2 is 0)

            carry = sum / 2;
        }

        return sb.reverse().toString();
    }
    }