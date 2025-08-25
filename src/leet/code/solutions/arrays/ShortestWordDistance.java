package leet.code.solutions.arrays;

/*
shortest distance between words in array of words
 */
public class ShortestWordDistance {

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String w1 = "practice";
        String w2 = "coding";

        int shortestDist = shortestDistance(words,w1,w2);
        System.out.println(shortestDist);

         w1 = "makes";
         w2 = "coding";

         shortestDist = shortestDistance(words,w1,w2);
        System.out.println(shortestDist);
    }

    //Runs in O(n) time and O(1) space
    private static int shortestDistance(String[] words, String word1, String word2) {
        int minDist = Integer.MAX_VALUE;

        int w1Index = -1;//invalid index to start with
        int w2Index = -1;

        for (int i = 0; i < words.length; i++) {

            if (word1.equals(words[i])) { // w1 matched

                w1Index = i;

                if (w2Index != -1) {//w2 has been previously matched

                    minDist = Math.min(minDist, Math.abs(w1Index - w2Index));//ABS ! as we don't care from which side it is closer

                }

            } else if (word2.equals(words[i])) {//w2 matched

                w2Index = i;

                if (w1Index != -1) {//if w1 has been previously matched

                    minDist = Math.min(minDist, Math.abs(w1Index - w2Index));//ABS ! as we don't care from which side it is closer

                }
            }
        }

        return minDist;
    }

}