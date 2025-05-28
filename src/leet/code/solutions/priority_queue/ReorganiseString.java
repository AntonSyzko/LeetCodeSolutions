package leet.code.solutions.priority_queue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
https://leetcode.com/problems/reorganize-string/description/

Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.

Return any possible rearrangement of s or return "" if not possible.

Example 1:

Input: s = "aab"
Output: "aba"
Example 2:

Input: s = "aaab"
Output: ""

Constraints:

1 <= s.length <= 500
s consists of lowercase English letters.
 */
public class ReorganiseString {
    public static void main(String[] args) {
        String s = "aaab";
        String reorg = reorganizeString(s);
        System.out.println(reorg);

        s = "aab";
        reorg = reorganizeString(s);
        System.out.println(reorg);

        s = "aabb";
        reorg = reorganizeString(s);
        System.out.println(reorg);
    }

    /*
    Time Complexity:

        Counting frequencies: O(n) where n is the length of the string
        Finding maximum frequency: O(k) where k is the number of unique characters
        Creating the heap: O(k)
        Building the result: O(n log k) due to heap operations

        Overall Time Complexity: O(n log k), which simplifies to O(n log 26) or O(n) since k is at most 26 (constant).
Space Complexity:

        HashMap for frequencies: O(k)
        PriorityQueue: O(k)
        Result string: O(n)

        Overall Space Complexity: O(n + k), which simplifies to O(n) since k is at most 26.
     */

    private static String reorganizeString(String str) {

        StringBuilder sb = new StringBuilder();

        Map<Character,Integer> occurrenceMAp = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            occurrenceMAp.put(str.charAt(i),occurrenceMAp.getOrDefault(str.charAt(i),0) + 1);
        }

        // Check if reorganization is possible
        // If any character appears more than half the length of the string (rounded up),
        // it's impossible to reorganize
        int maxFreq = 0;
        for(int freq : occurrenceMAp.values()){
            maxFreq = Math.max(maxFreq,freq);
        }
        // If any character appears more than half the length of the string (rounded up),
        // it's impossible to reorganize
        if(maxFreq > (str.length() +1)/2){
            return "";
        }
        // Create max heap based on character frequencies
        Queue<Character> maxHip = new PriorityQueue<>((a, b)-> occurrenceMAp.get(b)-occurrenceMAp.get(a));
        maxHip.addAll(occurrenceMAp.keySet());

        while (maxHip.size() > 1) {

            char firstMostOccurred = maxHip.poll();
            char secondMostOccurred = maxHip.poll();

            sb.append(firstMostOccurred);
            sb.append(secondMostOccurred);

            // Decrement frequencies and add back if needed
            occurrenceMAp.put(firstMostOccurred,occurrenceMAp.getOrDefault(firstMostOccurred,0)  -1);
            if(occurrenceMAp.get(firstMostOccurred)>0){
                maxHip.add(firstMostOccurred);
            }
            occurrenceMAp.put(secondMostOccurred,occurrenceMAp.getOrDefault(secondMostOccurred,0)  -1);
            if(occurrenceMAp.get(secondMostOccurred)>0){
                maxHip.add(secondMostOccurred);
            }
        }

        // Handle the last character if there is one
        if(maxHip.size() ==1){
            char remainingChar = maxHip.poll();

            sb.append(remainingChar);
        }

        return sb.toString();
    }

    private static String reorganizeString2(String str) {
        StringBuilder sb = new StringBuilder();

        // Count character frequencies
        int[] frequencies = new int[26];
        for (char c : str.toCharArray()) {
            frequencies[c - 'a']++;
        }

        // Check if reorganization is possible
        // If any character appears more than half the length of the string (rounded up),
        // it's impossible to reorganize
        int maxFreq = 0;
        for (int freq : frequencies) {
            maxFreq = Math.max(maxFreq, freq);
        }
        if (maxFreq > (str.length() + 1) / 2) {
            return "";
        }

        // Create max heap based on character frequencies
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) ->
                frequencies[b - 'a'] - frequencies[a - 'a']);

        // Add all characters with non-zero frequency to the heap
        for (char c = 'a'; c <= 'z'; c++) {
            if (frequencies[c - 'a'] > 0) {
                maxHeap.add(c);
            }
        }

        // Build the result string
        while (maxHeap.size() > 1) {
            char first = maxHeap.poll();
            char second = maxHeap.poll();

            sb.append(first);
            sb.append(second);

            // Decrement frequencies and add back if needed
            if (--frequencies[first - 'a'] > 0) {
                maxHeap.add(first);
            }

            if (--frequencies[second - 'a'] > 0) {
                maxHeap.add(second);
            }
        }

        // Handle the last character if there is one
        if (!maxHeap.isEmpty()) {
            char last = maxHeap.poll();

            // Verify that this last character only appears once more
            // This is redundant due to our initial check, but kept for clarity
            if (frequencies[last - 'a'] > 1) {
                return "";
            }

            sb.append(last);
        }

        return sb.toString();
    }
}
