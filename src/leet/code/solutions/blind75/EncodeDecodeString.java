package leet.code.solutions.blind75;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.

Please implement encode and decode

Example 1:

Input: ["neet","code","love","you"]
Output:["neet","code","love","you"]

Example 2:

Input: ["we","say",":","yes"]
Output: ["we","say",":","yes"]

Constraints:

0 <= strs.length < 100
0 <= strs[i].length < 200
strs[i] contains only UTF-8 characters.

 */
public class EncodeDecodeString {

    public static void main(String[] args) {
        List<String> strs = List.of("neet","code","love","you");
        String encoded = encode(strs);
        System.out.println(encoded);

        List<String> decoded = decode(encoded);
        for (String str : decoded) {
            System.out.print(str + ", ");
        }

    }



    public static String encode(List<String> strs) {
        StringBuilder encodedBuilder = new StringBuilder();

        for (String str : strs) {
            // Append the length of the string, followed by a delimiter
            encodedBuilder.append(str.length()).append("#");
            // Append the actual string
            encodedBuilder.append(str);
        }

        return encodedBuilder.toString();
    }

    public static List<String> decode(String str) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < str.length()) {
            // Find the position of '#' which separates length from content
            int delimiterPos = str.indexOf("#", i);

            // Parse the length
            int length = Integer.parseInt(str.substring(i, delimiterPos));

            // Extract the string of specified length
            String decodedStr = str.substring(delimiterPos + 1, delimiterPos + 1 + length);
            result.add(decodedStr);

            // Move to the next string
            i = delimiterPos + 1 + length;
        }

        return result;
    }
}
