package leet.code.solutions.sliding_window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
