package leet.code.solutions.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMost2DistinctCharacters {

    public static void main(String[] args) {
        String s = "aabacbebebe";

        int len = lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(len);
    }

    /*
    Time O(n)
    Space O(1)
     */

    private static int lengthOfLongestSubstringTwoDistinct(String s) {

        int maxLen = 0;
        int start = 0;

        Map<Character, Integer> charCounts = new HashMap<>();

        for (int end = 0; end < s.length(); end++) {

            char curr = s.charAt(end);

            charCounts.put(curr, charCounts.getOrDefault(curr, 0) + 1);

            while( charCounts.size() > 2){//while !

                char charAtStart = s.charAt(start);

                charCounts.put(charAtStart, charCounts.get(charAtStart) - 1);

                if(charCounts.get(charAtStart) == 0){
                    charCounts.remove(charAtStart);
                }

                start++;//shrink window
            }//while end

            //update max window
            int windowLen = end - start + 1;
            maxLen = Math.max(maxLen, windowLen);
        }

        return maxLen;

    }
}