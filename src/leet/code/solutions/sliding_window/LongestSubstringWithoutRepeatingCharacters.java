package leet.code.solutions.sliding_window;

import java.util.*;

/*
find length of longest substring without repeating chars

[ a, b, c, b, a, d, a ]

longest [c,b,a,d] = 4

 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String str = "abcbada";//4
        int longest = findLongestSubstringWithoutRepeatingChars(str);
        System.out.println(longest);

         str = "aaaa";//1
         longest = findLongestSubstringWithoutRepeatingCharsSlidingWindow(str);
        System.out.println(longest);

          str = "abcde";//5
          longest = findLongestSubstringWithoutRepeatingCharsSlidingWindow(str);
        System.out.println(longest);

         str = "abccab";//3
         longest = findLongestSubstringWithoutRepeatingCharsSlidingWindow(str);
        System.out.println(longest);
    }

    /*

    Time and Space Complexity:

            Time Complexity: O(n) where n is the length of the string. We traverse the string only once.

            Space Complexity: O(min(m, n)) where m is the size of the character set (typically constant for ASCII) and n is the string length.
     */

    private static int lengthOfLongestSubstring(String s) {
        // Edge case: empty string
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLength = 0;
        int windowStart = 0;

        // Use a HashMap to store character -> index mapping
        // We could also use a HashSet, but HashMap allows us to jump directly
        // when we find a duplicate rather than shrinking one by one
        Map<Character, Integer> charIndexMap = new HashMap<>();

        // Expand the window
        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char currentChar = s.charAt(windowEnd);

            // If we've seen this character before and it's in our current window
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= windowStart) {
                // Move window start to position after the previous occurrence
                windowStart = charIndexMap.get(currentChar) + 1;
            }

            // Update character position in map
            charIndexMap.put(currentChar, windowEnd);

            // Update max length found so far
            maxLength = Math.max(maxLength, ( windowEnd - windowStart + 1));
        }

        return maxLength;
    }


    private static int lengthOfLongestSubstringOptimised(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int maxLength = 0;
        int windowStart = 0;

        int[] charIndex = new int[128];
        Arrays.fill(charIndex, -1);

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {

            char currentChar = s.charAt(windowEnd);

            if (charIndex[currentChar] >= windowStart) {

                // Move window start to position after the previous occurrence
                windowStart = charIndex[currentChar] + 1;

            } else {
                //keep growing window length and updating max
                maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
            }

            charIndex[currentChar] = windowEnd;

            // Early termination: if remaining characters can't form a longer substring
            if (maxLength >= s.length() - windowStart) {
                break;
            }
        }

        return maxLength;
    }


    // time O ( N) - only while
    // space O(N) - set if all uniques
    private static int findLongestSubstringWithoutRepeatingCharsSlidingWindow(String str) {
        int longestRes = 0;
        int windowLeft = 0;
        int windowRight = 0;

        Set<Character> alreadySeen = new HashSet<>();

        while (windowRight < str.length()) {

            char currChar = str.charAt(windowRight);

            if(!alreadySeen.contains(currChar)){

                alreadySeen.add(currChar);//add to seen

                longestRes = Math.max(longestRes, windowRight - windowLeft + 1);//update res
              //  longestRes = Math.max(longestRes, alreadySeen.size());//alternative way

                windowRight ++;

            }else{// already seen

                //here we move globally to the new start after windowRight - since any combination before that we know will contain at least 1 duplicate
                while(alreadySeen.contains(currChar)){

                    alreadySeen.remove(str.charAt(windowLeft));//remove windoLeft till we actually remove the very curr char -> thus we will have windowLeft starting after we previously met repeated char
                    windowLeft++;

                }

            }
        }

        return longestRes;
    }


        // O( N ^ 2 ) - one in for one in while
    private static int findLongestSubstringWithoutRepeatingChars(String str) {
        int longestLenRes = 0;

        for (int windowStart = 0; windowStart < str.length(); windowStart++) {

           int  windowEnd = windowStart;

            Set<Character> seenInThisWindow = new HashSet<>();//always new empty set  seen for window

           int ongoingNonRepeatingLen = 0;

            while (windowEnd < str.length()) {

                if(!seenInThisWindow.contains(str.charAt(windowEnd))){// not yet seen in this window
                    ongoingNonRepeatingLen++;
                } else {//already seen - exit while
                    break;
                }

                seenInThisWindow.add(str.charAt(windowEnd));
                windowEnd++;//move in while
            }

            //after while exited - update res
           longestLenRes = Math.max(ongoingNonRepeatingLen, longestLenRes);

        }

        return longestLenRes;
    }


    private static int lengthOfLongestSubstringSet(String s) {
        if(s == null || s.isEmpty()) return 0;

        if(s.length() == 1) return 1;

        int longestSubstrLength = Integer.MIN_VALUE;

        int windowStart = 0;
        int windowEnd = 0;

        Set<Character> windowSet = new HashSet<>();// O(26)

        while(windowEnd < s.length()) {

            char ch = s.charAt(windowEnd);

            if(!windowSet.contains(ch)) {

                windowSet.add(ch);

                longestSubstrLength = Math.max(longestSubstrLength, windowSet.size());

                windowEnd++;

            }else {

                //no while loop
                windowSet.remove(s.charAt(windowStart));
                windowStart++;

            }
        }

        return longestSubstrLength;
    }
}
