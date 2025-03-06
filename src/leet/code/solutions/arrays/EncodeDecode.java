package leet.code.solutions.arrays;

import java.util.ArrayList;
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
public class EncodeDecode {

    public static void main(String[] args) {
         List<String> strs = List.of("neet","code","love","you");
         String encoded = encode(strs);
        System.out.println(encoded);

        List<String> decoded = decode(encoded);
        for (String str : decoded) {
            System.out.println(str);
        }

    }



    private static String encode(List<String> strs) {
        StringBuffer finalString = new StringBuffer();

        for (String str : strs) {
            finalString.append(str.length()).append(",");
        }

        finalString.append("#");

        for (String each : strs) {
            finalString.append(each);
        }

        return finalString.toString();
    }



    private static List<String> decode(String str) {
        if(str.length()==0){
            return new ArrayList<>();
        }

        List<String> decodedRes = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int index = 0;

        while (str.charAt(index)!='#') {

            StringBuilder curr = new StringBuilder();

            while(str.charAt(index)!=',') {
                curr.append(str.charAt(index));
                index++;
            }

            sizes.add(Integer.parseInt(curr.toString()));

            index++;
        }

        index++;//last comma move before #

        for (Integer strSize : sizes) {
            decodedRes.add(str.substring(index,index+strSize));
            index = index+strSize;//jump index
        }

        return decodedRes;
    }

}
