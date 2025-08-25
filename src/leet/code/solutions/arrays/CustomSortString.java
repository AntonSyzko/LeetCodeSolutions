package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
791

https://leetcode.com/problems/custom-sort-string/

You are given two strings order and s. All the characters of order are unique and were sorted in some custom order previously.

Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.

Return any permutation of s that satisfies this property.

Example 1:

Input: order = "cba", s = "abcd"

Output: "cbad"

Explanation: "a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".

Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:

Input: order = "bcafg", s = "abcd"

Output: "bcad"

Explanation: The characters "b", "c", and "a" from order dictate the order for the characters in s. The character "d" in s does not appear in order, so its position is flexible.

Following the order of appearance in order, "b", "c", and "a" from s should be arranged as "b", "c", "a". "d" can be placed at any position since it's not in order. The output "bcad" correctly follows this rule. Other arrangements like "dbca" or "bcda" would also be valid, as long as "b", "c", "a" maintain their order.

Constraints:

1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
 */
public class CustomSortString {

    public static void main(String[] args) {
        String order = "kqep";
        String s = "pekeq";//kqeep

        String perm = customSortString(order, s);
        System.out.println(perm);

        String ordrr2 = "bcafg";
        String s2 = "abcd";

        String perm2 = customSortString(ordrr2, s2);
        System.out.println(perm2);
    }

    public  static String customSortString(String order, String s) {

        Map<Character, Integer> freqOfS = new HashMap<>();
        for (char sch : s.toCharArray()) {
            freqOfS.put(sch, freqOfS.getOrDefault(sch, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();

        for(char inOrder : order.toCharArray()) {//iterate order string as it has the right positions per se

            if(freqOfS.containsKey(inOrder)) {//if exists in S

                int count = freqOfS.get(inOrder);//how many times repeated in S

                for(int i = 0 ; i < count; i++){
                    sb.append(inOrder);
                }

                freqOfS.remove(inOrder);//processed all of it in S - remove
            }
        }

        //left overs that were not in order at all
        for(Map.Entry<Character, Integer> entry : freqOfS.entrySet()) {
            sb.append(entry.getKey());//just append order does not matter
        }

        return sb.toString();
    }
}