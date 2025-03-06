package leet.code.solutions.BFS;

import java.beans.Introspector;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time Each intermediate word must exist in the dictionary For example,
Given:
start = "hit"   end = "cog"

dict = ["hot","dot","dog","lot","log"]

As one shortest transformation is "hit" ->"hot" ->"dot" ->"dog" ->"cog",
 the program should return its length 5.

Note: Return 0 if there is no such transformation sequence. All words have the same length. All words contain only lowercase alphabetic characters
 */
public class WordLadder {

    public static void main(String[] args) {
      HashSet<String> dictionary = new HashSet<>();
      dictionary.add("hot");
      dictionary.add("dot");
      dictionary.add("dog");
      dictionary.add("lot");
      dictionary.add("log");

      String start = "hit";
      String end = "cog";

        System.out.println("it takes " + ladderLength(start,end, dictionary));
    }

    public static int ladderLength(String start, String end, HashSet<String> dict) {
        if (dict.isEmpty()) return 0;

        dict.add(end);

        LinkedList<String> wordQueue = new LinkedList<>();//BFS
        LinkedList<Integer> distanceQueue = new LinkedList<>();//BFS

        wordQueue.add(start);//add start to start with
        distanceQueue.add(1);

        int res = Integer.MAX_VALUE;

        while (!wordQueue.isEmpty()) {

            String currWord = wordQueue.pop();
            Integer currentDistance = distanceQueue.pop();

            if (currWord.equals(end)) {//reached END word matching
                res = Math.min(res, currentDistance);
            }

            for (int i = 0; i < currWord.length(); i++) {//for every character in current word
                char[] currWordChars = currWord.toCharArray();

                for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {//traverse all alphabet

                    currWordChars[i] = alphabet;//replace char from curr word with alphabet char

                    String transformedWithOneLetterAlteration = new String(currWordChars);//construct new word after replacement of char with alphabet char

                    if (dict.contains(transformedWithOneLetterAlteration)) {//if it is in result dictionary

                        wordQueue.add(transformedWithOneLetterAlteration);//add to BFS to continue transforming it, as one letter match with dictionary found and we can continue working with this match onward
                        distanceQueue.add(currentDistance + 1);//increase distance of steps it takes to reach the final end

                        dict.remove(transformedWithOneLetterAlteration);//remove from dictinary since we have processed this word , found a match and account for step it took ( +1)
                    }
                }
            }
        }
        if (res < Integer.MAX_VALUE) {
            return res;
        } else {
            return 0;
        }
    }

}
