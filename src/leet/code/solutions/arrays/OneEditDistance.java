package leet.code.solutions.arrays;

/*

Given two strings S and T, determine if they are both one edit distance apart
 */
public class OneEditDistance {

    public static void main(String[] args) {
        String s = "test";
        String t = "testA";

        boolean res = isOneEditDistance(s, t);
        System.out.println(res);

         s = "test";
         t = "testAA";

         res = isOneEditDistance(s, t);
        System.out.println(res);
    }

    private static boolean isOneEditDistance(String s, String t) {
        if(s==null || t==null){
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();

        if(Math.abs(sLen - tLen) > 1){//distance is already > 1
            return false;
        }

        if(s.equals(t)){
            return false;//same not 1 edit apart
        }

        int sIndex = 0;
        int tIndex = 0;

        int count=0;

        while(sIndex < sLen && tIndex < tLen){//AND

            if(s.charAt(sIndex) == t.charAt(tIndex)){//chars are same

                sIndex++;
                tIndex++;

            }else{//chars differ

                count++;

                if(count > 1){
                    return false;//fail fast
                }

                if(sLen > tLen){

                    sIndex++;

                }else if(sLen < tLen){

                    tIndex++;

                }else{//length is same

                    //advance both
                    sIndex++;
                    tIndex++;
                }

            }
        }//while end

        //remainder after while exited
        if(sIndex < sLen || tIndex < tLen){//OR
            count++;
        }

        return (count == 1);
    }

    private static boolean isOneEditDistance2(String s, String t) {
        if(s == null || t == null){
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();

        // Quick elimination: if length difference > 1, impossible
        if(Math.abs(sLen - tLen) > 1){
            return false;
        }
        // Edge case: identical strings (0 edits, not 1)
        if(s.equals(t)){
            return false;
        }

        // Find the first position where characters differ
        int traversingIndex = 0;
        int minLen = Math.min(sLen, tLen);

        while (traversingIndex < minLen && s.charAt(traversingIndex) == t.charAt(traversingIndex)) {
            traversingIndex++;
        }

        // If we reached the end of both strings, they're identical (handled above)
        // If we reached the end of one string, the other has exactly 1 extra char
        if(traversingIndex == minLen){//ended at end
            return true;
        }

        // We found a difference at position i
        // Now check what type of edit is needed:
        if(sLen == tLen){
            // Same length -> must be a REPLACEMENT
            // Check if rest of the strings are identical after skipping position i
            return s.substring(traversingIndex +1 ).equals(t.substring(traversingIndex +1));//traversingIndex +1 skipping position in BOTH
        }

        if(sLen > tLen){
            // s is longer -> need to DELETE from s
            // Check if s without char at position i equals t from position i onwards
            return s.substring(traversingIndex +1 ).equals(t.substring(traversingIndex));
        }else {
            // t is longer -> need to INSERT into s (or delete from t)
            // Check if s from position i onwards equals t without char at position i
            return s.substring(traversingIndex  ).equals(t.substring(traversingIndex + 1));
        }
    }
}