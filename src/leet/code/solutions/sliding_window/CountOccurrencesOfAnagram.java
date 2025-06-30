package leet.code.solutions.sliding_window;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
https://www.callicoder.com/count-occurrences-of-anagram/

Given a word and a text, return the count of occurrences of the anagrams of the word in the text.

An anagram of a word is a word that’s formed by rearranging the letters of the original word. For example: the anagrams of the word for are

for
fro
ofr
orf
rof
rfo
Note that, the anagrams use all the original letters exactly once.

Examples 1

Input: text = forxxorfxdofr, word = for
Output : 3
Explanation : Anagrams of the word for - for, orf, ofr appear in the text and hence the count is 3.
Example 2

Input : text = aabaabaa, word = aaba
Output : 4
Explanation : Anagrams of the word aaba - aaba, abaa each appear twice in the text and hence the
count is 4.
 */
public class CountOccurrencesOfAnagram {
    public static void main(String[] args) {
        String str = "aabaabaa";
        String pattern = "aaba";
        int occurrencesOfPattern = countOccurrenceOfAnagramSlidingWindow(str, pattern);
        System.out.println(occurrencesOfPattern);
    }

    private static int countOccurrenceOfAnagramSlidingWindow(String text, String pattern) {
        int textLen = text.length();
        int patternLen = pattern.length();
        int count = 0;

        // Calculate the count of each character for the given word
        Map<Character, Integer> wordCharCount = new HashMap<>();
        for(int i = 0; i < patternLen; i++) {
            char c = pattern.charAt(i);
            wordCharCount.put(c, wordCharCount.getOrDefault(c, 0)+1);
        }

        // Stores the characrer count for the current substring (current window)
        Map<Character, Integer> substrCharCount = new HashMap<>();
        int windowStart = 0;

        for(int windowEnd = 0; windowEnd < textLen; windowEnd++) {
            char c = text.charAt(windowEnd);
            substrCharCount.put(c, substrCharCount.getOrDefault(c, 0) + 1); // Include the next char in the window

            if(windowEnd - windowStart + 1 == patternLen) { // We've hit the window size. Calculate result and Slide the window
                if(isAnagram(wordCharCount, substrCharCount)) {
                    count++;
                }

                // Reduce count for the char at windowStart since we are sliding the window now
                substrCharCount.put(text.charAt(windowStart), substrCharCount.get(text.charAt(windowStart)) - 1);

                windowStart++; // Slide the window ahead
            }
        }
        return count;
    }


        private static int countOccurrenceOfAnagram(String text, String word) {
            int res = 0;
            int wordLen = word.length();
            int textLen = text.length();

            Map<Character, Integer> wordCharsCounts = new HashMap<>();

            for (int i = 0; i < wordLen; i++) {
                char ch = text.charAt(i);
                wordCharsCounts.put(ch, wordCharsCounts.getOrDefault(ch,0) +1 );
            }

            for (int i = 0; i <= textLen - wordLen; i++) {

                Map<Character, Integer> textCharsCounts = new HashMap<>();

                for (int j = i; j < wordLen+i; j++) {
                    char ch = text.charAt(j);
                    textCharsCounts.put(ch, textCharsCounts.getOrDefault(ch,0) + 1);
                }

                if(isAnagram(textCharsCounts, wordCharsCounts)){
                    res++;
                }
            }
              return res;
    }

    private static int countOccurrencesMy(String str, String pattern) {
        int occurrencesRes = 0;

        Map<Character, Integer> patternCharsCounts = new HashMap<>();

        for(char each : pattern.toCharArray()) {
            patternCharsCounts.put(each, patternCharsCounts.getOrDefault(each,0)+1);
        }

        int right;
        int patternLen = pattern.length();

        Map<Character, Integer> ongoingCounts = new HashMap<>();

        for (int i = 0; i <= str.length() - patternLen; i++) {
            right = i;


            while (right < patternLen + i){
                ongoingCounts.put(str.charAt(right), ongoingCounts.getOrDefault(str.charAt(right),0)+1);
                right++;
            }
            if(ongoingCounts.keySet().containsAll(patternCharsCounts.keySet())
                    && ongoingCounts.values().containsAll(patternCharsCounts.values())){

                occurrencesRes++;

            }
            ongoingCounts.clear();
        }

        return occurrencesRes;
    }


        private static boolean isAnagram(Map<Character, Integer> word1CharCount, Map<Character, Integer> word2CharCount) {

        for (char each : word1CharCount.keySet()) {
            if(word1CharCount.get(each) != word2CharCount.get(each) ){
                return false;
            }
        }
        return true;
    }
    }
