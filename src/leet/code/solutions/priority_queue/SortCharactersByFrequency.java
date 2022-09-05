package leet.code.solutions.priority_queue;

import java.util.*;

/*
https://leetcode.com/problems/sort-characters-by-frequency/

Given a string s, sort it in decreasing order based on the frequency of the characters. The frequency of a character is the number of times it appears in the string.

Return the sorted string. If there are multiple answers, return any of them.

Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 */
public class SortCharactersByFrequency {

    public static void main(String[] args) {
        String unsorted = "tree";
        System.out.println(" sorted by frequency " + frequencySort(unsorted));
    }


    public static String frequencySort(String s) {
        Map<Character, Integer> charsToOccurrences = new HashMap<>();

        for (char each : s.toCharArray()) {
            charsToOccurrences.put(each, charsToOccurrences.getOrDefault(each, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> charsToOccurrences.get(b) - charsToOccurrences.get(a));
        maxHeap.addAll(charsToOccurrences.keySet());

        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char current = maxHeap.remove();
            for (int i = 0; i < charsToOccurrences.get(current); i++) {
                result.append(current);
            }
        }
        return result.toString();
    }
    
//    public static String frequencySort2(String s) {
//        char[] array = s.toCharArray();
//        Arrays.sort(array);
//        return new String(array);
//    }
}
