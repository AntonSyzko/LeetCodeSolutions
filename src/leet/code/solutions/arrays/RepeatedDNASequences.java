package leet.code.solutions.arrays;

import java.util.*;

/*
https://leetcode.com/problems/repeated-dna-sequences/description/

The DNA sequence is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T'.

For example, "ACGAATTCCG" is a DNA sequence.
When studying DNA, it is useful to identify repeated sequences within the DNA.

Given a string s that represents a DNA sequence, return all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule. You may return the answer in any order.

Example 1:

Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
Output: ["AAAAACCCCC","CCCCCAAAAA"]
Example 2:

Input: s = "AAAAAAAAAAAAA"
Output: ["AAAAAAAAAA"]

Constraints:

1 <= s.length <= 105
s[i] is either 'A', 'C', 'G', or 'T'.
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        String dna = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String > repeatedDNAs = findRepeatedDnaSequences(dna);
        System.out.println(repeatedDNAs);

         dna = "AAAAAAAAAAAAA";
         repeatedDNAs = findRepeatedDnaSequences(dna);
        System.out.println(repeatedDNAs);
    }

    /*
    O(n) and O(n)

    Time Complexity: O(n)

            The function loops through the string once, from index 0 to (string length - 10)
            For a string of length n, we perform (n - 9) iterations
            Within each iteration:

            substring operation takes O(1) time in Java (though it creates a new String object)
            HashSet operations (contains and add) are O(1) on average
            Therefore, each iteration costs O(1)

            The final conversion from Set to ArrayList is O(k) where k is the number of unique repeated sequences (k ≤ n - 9)
            Overall: O(n - 9 + k) = O(n)

Space Complexity: O(n)

            We store substrings in two HashSets:

            set: contains all seen 10-character sequences
            res: contains only the repeated sequences

            In the worst case, where most 10-character sequences repeat exactly once:

            set would store O(n) substrings
            res would store O(n) substrings

            Each substring requires O(1) space (since they're all fixed length of 10)
            The final ArrayList also requires O(k) space
            Overall: O(n)
     */
    private static List<String> findRepeatedDnaSequences(String s) {
        if(s.length() < 10){
            return Collections.emptyList();
        }

        Set<String> res = new HashSet<>();//for skip duplicates

        Set<String> seenSubstrings = new HashSet<>();

        int start = 0;
        int end = s.length() ;

        while(start + 10 <= end ) {//mind +10 as we create 10 length substrings

            String currString = s.substring(start, start + 10);

            if(seenSubstrings.contains(currString)) {//repeated identified
                res.add(currString);
            }

            seenSubstrings.add(currString);
            start ++;
        }

        //using just a list would contain duplicate entries, hence Set
        return new ArrayList<>(res);//convert Set to List
    }
}
