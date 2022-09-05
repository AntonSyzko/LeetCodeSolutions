package leet.code.solutions.priority_queue;

import java.util.*;
import java.util.stream.Collectors;

/*
https://leetcode.com/problems/top-k-frequent-words/
Given an array of strings words and an integer k, return the k most frequent strings.
Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.

Example 1:
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.

Constraints:
1 <= words.length <= 500
1 <= words[i] <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]

Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */
public class TopKFrequentWords {
    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        List<String> topK = topKFrequent(words, 2);
        System.out.println(topK);

         List<String> topKLongest = topKLongest(words, 2);
        System.out.println(topKLongest);
    }

    //Space O(N)
    //Time: O(N * Log k )
    private static List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> wordsToOccurrences = new HashMap<>();

        for (String word : words) {
            wordsToOccurrences.put(word, wordsToOccurrences.getOrDefault(word, 0) + 1);//this makes O(N)
        }

        PriorityQueue<String> priorityQueue = new PriorityQueue<>((word1, word2) -> {
            int frequency1 = wordsToOccurrences.get(word1);
            int frequency2 = wordsToOccurrences.get(word2);

            if (frequency1 == frequency2) {//if same length
                return word2.compareTo(word1); //alphabetical order then
            }
            return frequency1 - frequency2;//by length
        });


        for (Map.Entry<String, Integer> each : wordsToOccurrences.entrySet()) {//add all to priority Q
            priorityQueue.add(each.getKey());
            //this makes Log K
            if (priorityQueue.size() > k) {// if more than K
                priorityQueue.poll();//delete
            }
        }
        while (!priorityQueue.isEmpty()) {// whats is in Q
            result.add(priorityQueue.poll());//add to result
        }
        Collections.reverse(result);//reverse since we need the order from highest to lowest and in QUEUE is stored from lowest to highest
        return result;
    }


    //Space O(N)
    //Time: O(N * Log k )
    private static List<String> topKLongest(String[] words, int k) {
        List<String> result = new ArrayList<>();
        Map<String, Integer> wordsToOccurrences = new HashMap<>();

        for (String word : words) {
            wordsToOccurrences.put(word, wordsToOccurrences.getOrDefault(word, 0) + 1);//this makes O(N)
        }

        PriorityQueue<String> priorityQueue = new PriorityQueue<>((word1, word2) -> {
            int frequency1 = wordsToOccurrences.get(word1);
            int frequency2 = wordsToOccurrences.get(word2);

            if (frequency1 == frequency2) {//if same length
                return word2.compareTo(word1); //alphabetical order then
            }
            return frequency2 - frequency1;//by length reverse order from highest to lowest
        });


        for (Map.Entry<String, Integer> each : wordsToOccurrences.entrySet()) {//add all to priority Q
            priorityQueue.add(each.getKey());
            //this makes Log K
            if (priorityQueue.size() > k) {// if more than K
                priorityQueue.poll();//delete
            }
        }
        while (!priorityQueue.isEmpty()) {// whats is in Q
            result.add(priorityQueue.poll());//add to result
        }
        return result;
    }
}
