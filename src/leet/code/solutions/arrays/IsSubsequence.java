package leet.code.solutions.arrays;

public class IsSubsequence {

    public static void main(String[] args) {
      String s= "abc";
      String t = "ahbgdc";

        System.out.println(isSubsequence(s,t));

        String s1 = "acb";
        String t1 = "ahbgdc";

        System.out.println(isSubsequence(s1,t1));

    }

    private static boolean isSubsequence(String s, String pattern) {
        if(s.equals("")) return true;
        if(s.equals(pattern)) return true;

        int indexInS = 0;//index in S , will be increasing each time we see same char in pattern

        for (int i = 0; i < pattern.length(); i++) {
            if(s.charAt(indexInS) == pattern.charAt(i)) {
                indexInS++;
            }

            if(indexInS >= s.length()) {//if increased index in S reched length of s
                return true;//return fast
            }
        }

        //pattern length exhausted but indexS in S did not increas enought times
        return false;
    }

}
