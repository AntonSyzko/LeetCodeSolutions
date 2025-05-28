package leet.code.solutions.arrays;

public class VerifyingAliensDicitionary {

    public static void main(String[] args) {
        String a = "aba";
        String b = "abce";
        String c = "abcftyu";

        String alphabet = "abcdef";

        boolean isSorted = isAlienSorted(new String[]{a,b,c}, alphabet);
        System.out.println(isSorted);

        a = "aba";
        b = "aabe";//second a is not lexi
        c = "abcftyu";

        isSorted = isAlienSorted(new String[]{a,b,c}, alphabet);
        System.out.println(isSorted);

        a = "aba";
        b = "abbe";
        c = "abcatyu";//a at 4 position is not lexi

        isSorted = isAlienSorted(new String[]{a,b,c}, alphabet);
        System.out.println(isSorted);

        b = "aabe";
        a = "aba";//b shorter an later in array -> so not lexit - lexi is every nest is longer or same in length
        c = "abcftyu";

        isSorted = isAlienSorted(new String[]{b,a,c}, alphabet);
        System.out.println(isSorted);
    }

    /*
    Time and Space Complexity

        Time Complexity: O(m)
            where m is the total number of characters in all words. In the worst case, we need to compare each character in each word.

        Space Complexity: O(1)
            as we only use a fixed-size array to store the order mapping (size 26 for lowercase English letters).
     */
    private static boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0) {
            return false;
        }

        if (words.length == 1) {
            return true;
        }

        // Create a mapping of each character to its position in the alien order
        int[] charOrder = new int[26];
        for (int i = 0; i < order.length(); i++) {
            charOrder[order.charAt(i) - 'a'] = i;
        }

        // Compare adjacent words
        for (int i = 0; i < words.length - 1; i++) {//iterate till one before last
            String currentWord = words[i];
            String nextWord = words[i + 1];

            // Check if the words are in correct order
            if (!compareWords(currentWord, nextWord, charOrder)) {
                return false;
            }
        }

        return true;
    }

    private static boolean compareWords(String word1, String word2, int[] charOrder) {
        // Compare characters at each position
        int minLength = Math.min(word1.length(), word2.length());

        for (int i = 0; i < minLength; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);

            if (c1 != c2) {
                // If characters differ, check their order in the alien alphabet
                return charOrder[c1 - 'a'] < charOrder[c2 - 'a'];
            }
        }

        // If all characters match up to the minimum length,
        // the shorter word should come first
        return word1.length() <= word2.length();
    }
}
