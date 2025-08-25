package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and
your method will be called repeatedly many times with different parameters. How would you optimize it?
Design a class which receives a list of words in the constructor, and implements a method that takes two words
word1 and word2 and return the shortest distance between these two words in the list.
For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3. Given word1 = "makes", word2 = "coding", return 1.
 */
public class ShortestWordDistance2 {

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};

        ShortestWordDistance2 sw = new ShortestWordDistance2();
        sw.ShortestWordDistance(words);

        int shortest  = sw.shortest("practice", "coding");
        System.out.println(shortest);
        shortest =  sw.shortest("makes", "coding");
        System.out.println(shortest);

    }

    private  Map<String, List<Integer>> wordsToIndexes ;

    public   void ShortestWordDistance(String[] words) {
        wordsToIndexes = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            wordsToIndexes.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);//store words : indexes
        }

    }

    /*
    O(M+N).

    where M is freqency of word1 and N is the frequency of word2.

     Since M+N < size of word list,
     the time is O(K) where k is the list size.
    */
    public  int shortest(String word1, String word2) {

        int shortestDistnace = Integer.MAX_VALUE;//res

        List<Integer> indexesOf1 = wordsToIndexes.get(word1);//all indexes of given word
        List<Integer> indexesOf2 = wordsToIndexes.get(word2);

        int firstIndex = 0;
        int secondIndex = 0;

        while (firstIndex < indexesOf1.size() && secondIndex < indexesOf2.size()) {

            shortestDistnace = Math.min(shortestDistnace, Math.abs(indexesOf1.get(firstIndex) - indexesOf2.get(secondIndex)));

            if(indexesOf1.get(firstIndex) < indexesOf2.get(secondIndex)){//check if indexes not overlap
                firstIndex++;
            }else{
                secondIndex++;
            }

        }

        return shortestDistnace;
    }
}