package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

https://leetcode.com/problems/find-common-characters/description/
Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.



Example 1:

Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:

Input: words = ["cool","lock","cook"]
Output: ["c","o"]


Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.


 */
public class FindCommonChars {

    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
        List<String> commonChars = commonChars(words);
        System.out.println(commonChars);

    }

    private static List<String> commonChars(String[] words) {
        List<String> commonChars = new ArrayList<>();

        int [] minFrequencies = new int[26];//global counts, set to max to pick minimums after
        Arrays.fill(minFrequencies, Integer.MAX_VALUE);


        for (String word : words) {
            int[] currentFrequencies = new int[26];

            for (int i = 0; i < word.length(); i++) {
                char curr = word.toCharArray()[i];
                currentFrequencies[curr - 'a']++;
            }

            for(int alphabetIndex = 0; alphabetIndex < 26; alphabetIndex++ ){//pick minimum of currently occuring letters vs globally

                minFrequencies[alphabetIndex] = Math.min(minFrequencies[alphabetIndex], currentFrequencies[alphabetIndex]);

            }
        }


        for(int i = 0; i < 26; i++){

            while (minFrequencies[i] > 0){//if letter occurs more than once
                commonChars.add(String.valueOf((char)(i + 'a')));
                minFrequencies[i]--;//decrement as counted in
            }

        }

        return commonChars;
    }

}
