package leet.code.solutions.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
This is a follow-up problem of Shortest Word Distance. The only difference is now word1 could be the same as
word2.
Given a list of words and two words word1 and word2, return the shortest distance between these two words
in the list.
word1 and word2 may be the same and they represent two individual words in the list.
For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.
 */
public class ShortestWordDistance3 {

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String w1 = "makes";
        String w2 = "coding";

        int shortestDist = shortestWordDistance(words,w1,w2);
        System.out.println(shortestDist);

        w1 = "makes";
        w2 = "makes";

        shortestDist = shortestWordDistance(words,w1,w2);
        System.out.println(shortestDist);
    }

    /*
    O(n + k₁+k₂) time,

    Where n = length of words array, k₁ = occurrences of word1, k₂ = occurrences of word2.

     O(n) space
     */
    public static int shortestWordDistance(String[] words, String word1, String word2) {

        int shortestRes = Integer.MAX_VALUE;

        Map<String, List<Integer>> map = new HashMap<>();

        for(int i =0; i < words.length; i++){
            map.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);//word : index
        }

        List<Integer> w1Indexes = map.get(word1);
        List<Integer> w2Indexes = map.get(word2);

        int w1Index = 0;
        int w2Index = 0;

        while (w1Index < w1Indexes.size() &&  w2Index < w2Indexes.size()){

            int w1Position = w1Indexes.get(w1Index);
            int w2Position = w2Indexes.get(w2Index);

            if(word1.equals(word2) && w1Position == w2Position){

                if(w1Index + 1 < w1Indexes.size()){
                    w1Index++;
                }else{
                    w2Index++;
                }
                continue;
            }

            shortestRes = Math.min(shortestRes, Math.abs(w1Position - w2Position));

            if(w1Position < w2Position){
                w1Index++;
            }else{
                w2Index++;
            }
        }

        return shortestRes;
    }

    //O(n) , O(1)
    public static int shortestWordDistanceOptimal(String[] words, String word1, String word2) {
        int pos1 = -1;
        int pos2 = -1;
        int minDistance = Integer.MAX_VALUE;
        boolean sameWord = word1.equals(word2);

        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(word1)) {

                if(sameWord) {
                    if(pos1 != -1) {
                        minDistance = Math.min(minDistance, i - pos1);
                    }
                } else if(pos2 != -1) {
                    minDistance = Math.min(minDistance, Math.abs(i - pos2));
                }
                pos1 = i;

            } else if(words[i].equals(word2)) {

                if(pos1 != -1) {
                    minDistance = Math.min(minDistance, Math.abs(i - pos1));
                }

                pos2 = i;
            }
        }

        return minDistance;
    }
}