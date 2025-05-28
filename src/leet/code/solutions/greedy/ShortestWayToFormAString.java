package leet.code.solutions.greedy;

/*
A subsequence of a string is a new string that is formed from the original string by deleting some (can be none)
of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not).

Given two strings source and target, return *the minimum number of subsequences of source such that their concatenation equals *target. If the task is impossible, return -1.
Example 1:

Input: source = "abc", target = "abcbc"
Output: 2
Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences


 */
public class ShortestWayToFormAString {

    public static void main(String[] args) {
        String source = "abc";
        String target = "abcbc";

        int shortest = minSubsequences(source, target);
        System.out.println(shortest);

         source = "xyz";
         target = "xzyxz";

         shortest = minSubsequences(source, target);
        System.out.println(shortest);
    }

    /*

    Time Complexity: O(M × N)

        In the worst case, we need to scan through the entire source string for each character in the target string
        This gives us a time complexity of O(M × N), where M is the length of the source string and N is the length of the target string

Space Complexity: O(1)

        We only use a few variables regardless of input size
        Space complexity is O(1) (constant space)
     */
    private static int minSubsequences(String source, String target) {
        // Edge cases
        if (source.isEmpty() || target.isEmpty()) {
            return target.isEmpty() ? 0 : -1;
        }

        int resCount = 1;

        int targetIndex = 0;
        int targetLength = target.length();

        // Continue until we've matched all characters in target
        while (targetIndex < targetLength) {//while there's smth to search in target

            boolean foundMatch = false;

            for(int sourceIndex = 0; sourceIndex < source.length(); sourceIndex++) {//iterate through source

                // If characters match, move to next character in target
                if( targetIndex < targetLength &&  source.charAt(sourceIndex) == target.charAt(targetIndex)) {//target boundaries are ok AND chars MATCH in both
                    foundMatch = true;
                    targetIndex++;//increase target index for future comparisons
                }
            }

            // If we couldn't match any character in this pass, it's impossible
            if(!foundMatch) {//none of the chars in target matched any chars in source
                return -1;//fail fast
            }

            // If we haven't finished matching target, we need another subsequence
            if(targetIndex < targetLength) {
                resCount++;//one more match found
            }
        }

        return resCount;
    }

    }
