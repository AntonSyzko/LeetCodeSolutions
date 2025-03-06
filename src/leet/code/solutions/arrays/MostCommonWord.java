package leet.code.solutions.arrays;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {

    public static void main(String[] args) {
     String paragraph = "Bob hit ball, yes BALL, but ball hit Bob twice the HIT";
     String[] banned = { "hit"};
     String mostCommon = getMostCommonWord(paragraph, banned);
     System.out.println(mostCommon);
    }



    private static String getMostCommonWord(String paragraph, String[] banned){

        Set<String> bannedSet = new HashSet<>();
        for (String word : banned) {
            bannedSet.add(word.toLowerCase());
        }

        Map<String, Integer> countsOfWordOccurences = new HashMap<>();

        for (String word : paragraph.replaceAll("[^a-zA-Z]"," ").toLowerCase().split(" ")) {//removed punctuation by replacing all non a-zAZ with " " - space

            if(!bannedSet.contains(word)){
                countsOfWordOccurences.put(word, countsOfWordOccurences.getOrDefault(word, 0) + 1);
            }
        }

        String mostCommonWord = "";

        for (String word : countsOfWordOccurences.keySet()) {
            //if current word from counts occured more than anything than curr mostCOmmon did ( starting from "" empty to begin with)
            if(mostCommonWord.equals("") || countsOfWordOccurences.get(word) > countsOfWordOccurences.get(mostCommonWord)){
                mostCommonWord = word;
            }
        }


        return mostCommonWord;
    }


    private static String getMostCommonWordMy(String paragraph, String[] banned){

        Set<String> bannedSet = new HashSet<>();
        for (String word : banned) {
            bannedSet.add(word.toLowerCase());
        }

        Map<String, Integer> map = new HashMap<>();

        for (String word : paragraph.replaceAll("[^a-zA-Z]"," ").toLowerCase().split(" ")) {//removed punctuation by replacing all non a-zAZ with " " - space

            if(!bannedSet.contains(word)){
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        int max = 0;
        String mostCommonWord = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if(entry.getValue() > max){
                max = entry.getValue();
                mostCommonWord = entry.getKey();
            }
        }

        return mostCommonWord;
    }
}
