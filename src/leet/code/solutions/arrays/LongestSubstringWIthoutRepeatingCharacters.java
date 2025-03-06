package leet.code.solutions.arrays;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
abcabcbb

res = 3

abc -s longest

 */
public class LongestSubstringWIthoutRepeatingCharacters {

    public static void main(String[] args) {
      String str = "abcabcbb";
      int longest = longestSubstringSlidingWindow(str);

      System.out.println(longest);

         str = "pwwkew";
          longest = longestSubstringSlidingWindow(str);

        System.out.println(longest);
    }


    private static int longestSubstringSlidingWindow(String str) {
         Map<Character, Integer> map = new HashMap<>();

         int maxLen = 0;

         int left = 0;

         for (int right = 0; right < str.length(); right++) {

             char charAtRightWindowPosition = str.charAt(right);

             map.put(charAtRightWindowPosition, map.getOrDefault(charAtRightWindowPosition, 0) + 1);

             while (map.get(str.charAt(right)) > 1) {//char occurrence increased more than 1

                 char leftWindowCHar = str.charAt(left);

                 map.put(leftWindowCHar, map.get(leftWindowCHar) - 1);//decrease count of char of the left window

                 if(map.get(leftWindowCHar) == 0){//exhaused count to 0

                     map.remove(leftWindowCHar);//remove completely

                 }

                 left++;//move lefty window
             }

             maxLen = Math.max(maxLen, right - left + 1);
         }

         return maxLen;
    }



    //O(N^2) - time, memo - O(N)
        private static int longestSubstring(String str) {

        int maxLength = 0;

        for (int i = 0; i < str.length(); i++) {

            Set<Character> uniques = new HashSet<>();

            int maxLenSoFar = 0;

            for (int j = i; j < str.length(); j++) {

               if (!uniques.contains(str.charAt(j))) {

                   uniques.add(str.charAt(j));
                   maxLenSoFar ++;

               }else{

                   maxLength = Math.max(maxLength, maxLenSoFar);

                   break;

               }

            }

        }

        return maxLength;
    }
}
