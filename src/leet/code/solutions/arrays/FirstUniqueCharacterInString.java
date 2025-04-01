package leet.code.solutions.arrays;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
https://leetcode.com/problems/first-unique-character-in-a-string/

Given a string s, find the first non-repeating character in it and return its index.
 If it does not exist, return -1.
 */
public class FirstUniqueCharacterInString {

    public static void main(String[] args) {
        String input = "loveleetcode";
        int res = firstUniqChar(input); //2
        System.out.println(res);

        input = "leetcode";
        res = firstUniqChar(input);//0
        System.out.println(res);

        input = "aabb";
        res = firstUniqChar(input); //-1
        System.out.println(res);

        input = "aadadaad";
        res = firstUniqChar(input); //-1
        System.out.println(res);
    }




        public static int firstUniqChar(String s) {
        Map<Character, Integer> charsToIndexes = new HashMap<>(); // chars to it's index occurences

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            if(!charsToIndexes.containsKey(current)){ // if NOT in map
                charsToIndexes.put(current, i);// store it's index as a value
            } else { // if IS in map - duplicate
                charsToIndexes.put(current, -1); // invalidate - set  index to -1
            }
        }

        int min = Integer.MAX_VALUE;// starting MIN with MAX value

        for (char each : charsToIndexes.keySet()){

            if(charsToIndexes.get(each) > -1 // if NOT invalidtaed ( duplicate )
                &&  //AND
                charsToIndexes.get(each) < min){ // less than temp MIN
                min = charsToIndexes.get(each); // new min
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min; //check if MIN changed and return
    }
}
