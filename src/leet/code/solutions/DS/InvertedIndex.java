package leet.code.solutions.DS;


import java.util.*;
import java.util.stream.Collectors;

/*
    //1. term -> tokenise -> stamming -> build index

 */
public class InvertedIndex {

    public static void main(String[] args) {
        String s1 = "A Big brown fox is running";
        String s2 = "The Brown large fox runs";
        String s3 = "The python is a snake";
        String s4 = "Python is a programming language, not a fox";


        String[] docs = {s1, s2,s3,s4};

        tokeniseStrings(docs);

        stamStrings(docs);

        buildInvertedIndexes();

        List<Integer> docsFoundAt = findRelatedDocs("fox");

        System.out.println(docsFoundAt);

         docsFoundAt = findRelatedDocs("snake");

        System.out.println(docsFoundAt);

         docsFoundAt = findRelatedDocs("PYTHON");

        System.out.println(docsFoundAt);
    }

    private static Map<Integer, List<String>> tokenisedDocs = new HashMap<>();
    private static Map<String, List<Integer>> invertedIndex = new HashMap<>();
    final static Set<String> stopWords = Set.of("a","the","is","an",",");


    private static void tokeniseStrings(String[] docs) {
        for(int i = 0 ; i < docs.length ; i++){

            String currDoc = docs[i];
            String[] tokenised = currDoc.trim().toLowerCase().split(" ");

            List<String> list = Arrays.asList(tokenised);

            tokenisedDocs.put(i, list);

        }
    }


    private static void stamStrings(String[] docs) {
            for(int i = 0 ; i < docs.length ; i++){
                List<String> tokenised = tokenisedDocs.get(i);

                List<String> stammed =  removeStopWords(tokenised);

                tokenisedDocs.replace(i, stammed);
            }
    }

    private static void buildInvertedIndexes() {
            for(Map.Entry<Integer, List<String>> entry : tokenisedDocs.entrySet()) {

                fillIndexesOfRelatedDocs(entry);
            }

        }

    private static void fillIndexesOfRelatedDocs(Map.Entry<Integer, List<String>> entry) {
        for(String word : entry.getValue()) {
            invertedIndex.computeIfAbsent(word, k -> new ArrayList<>()).add(entry.getKey());
        }
    }

    private static List<Integer> findRelatedDocs(String s) {

        List<Integer> relatedDocs = new ArrayList<>();
        for(Map.Entry<String, List<Integer>> entry : invertedIndex.entrySet()) {
            if(s.toLowerCase().equals(entry.getKey())) {
                relatedDocs.addAll(entry.getValue());
            }
        }
        return relatedDocs;
  }

  private static List<String> removeStopWords(List<String> tokenisedWords) {

       return tokenisedWords.stream().filter(word -> !stopWords.contains(word)).collect(Collectors.toList());
  }
}