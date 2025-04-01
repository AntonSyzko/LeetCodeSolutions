package leet.code.solutions.dynamic_programming;

/*
The Levenshtein distance (or Edit distance) is a way of quantifying how different two strings are from one another by counting the minimum number of operations required to transform one string into the other.

The Levenshtein distance between two words is the minimum number of single-character edits (i.e., insertions, deletions, or substitutions) required to change one word into the other. Each of these operations has a unit cost.

 For example, the Levenshtein distance between kitten and sitting is 3. T
 he minimal edit script that transforms the former into the latter is:

kitten —> sitten (substitution of s for k)
sitten —> sittin (substitution of i for e)
sittin —> sitting (insertion of g at the end)

 */
public class LevensteinDistance {

    public static void main(String[] args) {
        String source = "kitten";
        String target = "sitting";

        int distance = levensteinDist(source, source.length(), target, target.length());

        System.out.println(distance);
    }


    public static int levensteinDist(String sourceStr, int sourceLen, String targetStr, int targetLen) {
        if (sourceLen == 0) {
            return targetLen;
        }

        if (targetLen == 0) {
            return sourceLen;
        }

        int lastCharacterReplacementCost = sourceStr.charAt(sourceLen - 1) == targetStr.charAt(targetLen - 1) ? 0 : 1;   //last  chars same ?

        //Delete the last character of source. The size of SOURCE reduces by 1, and target remains the same. This accounts for X[1…i-1], Y[1…j] as we move in on the source string, but not in the target string.
        int deleteLastFromSource = levensteinDist(sourceStr, sourceLen - 1, targetStr, targetLen) + 1;
        //Insert the last character of TARGET into SOURCE. The size of TARGET reduces by 1, and X remains the same. This accounts for X[1…i], Y[1…j-1] as we move in on the target substring, but not in the source substring.
        int insertLastFromTargetIntoSource = levensteinDist(sourceStr, sourceLen, targetStr, targetLen - 1)  + 1;
        //Substitute (Replace) the current character of SOURCE by the current character of TARGET. The size of both SOURCE and TARGET reduces by 1. This accounts for X[1…i-1], Y[1…j-1] as we move in both the source and target string.
        int substituteSourceCharByTargetChar = levensteinDist(sourceStr, sourceLen - 1, targetStr, targetLen - 1) + lastCharacterReplacementCost;

        return minOfThree(deleteLastFromSource, insertLastFromTargetIntoSource, substituteSourceCharByTargetChar);
    }


    private static int minOfThree(int one, int two, int three) {
        return Math.min(one, Math.min(two, three));
    }

    // Function to find Levenshtein distance between string `X` and `Y`.
    public static int dist(String X, String Y) {
        // `m` and `n` is the total number of characters in `X` and `Y`, respectively
        int m = X.length();
        int n = Y.length();

        // For all pairs of `i` and `j`, `T[i, j]` will hold the Levenshtein distance
        // between the first `i` characters of `X` and the first `j` characters of `Y`.
        // Note that `T` holds `(m+1)×(n+1)` values.

        int[][] T = new int[m + 1][n + 1];

        // we can transform source prefixes into an empty string by
        // dropping all characters

        for (int i = 1; i <= m; i++) {
            T[i][0] = i;                // (case 1)
        }

        // we can reach target prefixes from empty source prefix
        // by inserting every character

        for (int j = 1; j <= n; j++) {
            T[0][j] = j;                // (case 1)
        }

        int cost;

        // fill the lookup table in a bottom-up manner
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {   // (case 2)
                    cost = 0;                           // (case 2)
                } else {
                    cost = 1;                           // (case 3c)
                }

                T[i][j] = minOfThree(T[i - 1][j] + 1,      // deletion (case 3b)
                        T[i][j - 1] + 1,                // insertion (case 3a)
                        T[i - 1][j - 1] + cost);        // replace (case 2 + 3c)
            }

        }
        return T[m][n];

    }
}
