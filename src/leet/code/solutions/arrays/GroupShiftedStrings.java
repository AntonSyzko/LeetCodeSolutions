package leet.code.solutions.arrays;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
For example, the string "abc" when shifted yields "bcd", and subsequently "cde", all the way up to "xyz".
These shifted versions all belong to the same shifting sequence started by "abc". If "abc" and "bcd" are both in the input array,
they should be grouped together in the output.
 */
public class GroupShiftedStrings {

    public static void main(String[] args) {
        String[] strs = {"abc", "bcd", "cde", "bgt"};

        List<List<String>> grouppedShites = groupStrings(strs);

        System.out.println(grouppedShites);
    }

    //O(Σ∣strings[i]∣)
    //O(Σ∣strings[i]∣)
    private static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String s : strings) {

            String currCharDiffsKey = getCurrCharDiffs(s);//key is a string representing an array of differences between conseq chars (1,1 ) or ( 6,3,1 ) etc ...
            //thus key for all permutations of shifts will be same ( since we always shift by 1 and diffs will stay same anyway

            groups.computeIfAbsent(currCharDiffsKey, k -> new ArrayList<>()).add(s);

        }

        return new ArrayList<>(groups.values());
    }

    private static String getCurrCharDiffs(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 1 ; i < s.length(); i++){//start from 1

            char curr = s.charAt(i);
            char prev = s.charAt(i-1);

            //difference between consecutive chars ( e.g. for abc ( b - c = 1, c - b = 1 , resulted in '1,1' identifier)
            int diffBetweenChars = ((curr - prev) + 26 ) % 26;//modulo is to wrap around alphabet end : z -> a

            sb.append(diffBetweenChars).append(",");
        }

        return sb.toString().trim();
    }
}