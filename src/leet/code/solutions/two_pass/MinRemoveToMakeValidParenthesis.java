package leet.code.solutions.two_pass;

public class MinRemoveToMakeValidParenthesis {

    public static void main(String[] args) {
        String s = "a)b(c)d";

        String res = minRemoveToMakeValid(s);
        System.out.println(res);

        s = "lee(t(c)o)de)";
        res = minRemoveToMakeValid(s);
        System.out.println(res);

        s = "))((";
        res = minRemoveToMakeValid(s);
        System.out.println(res);
    }

    /*
    O(n)
    O(n)
     */
    private static String minRemoveToMakeValid(String s) {

        // Pass 1: Remove invalid ')' characters
        StringBuilder firstPass = new StringBuilder();

        int unmatchedOpen = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                unmatchedOpen++;

            }else if (c == ')') {
                //case when closing  ) comes before opening (
                if(unmatchedOpen == 0){
                    continue;//won't be appended
                }
                // Skipped invalid ')' when unmatchedOpen == 0

                unmatchedOpen--;

            }
                firstPass.append(c);// Always keep letters

        }

        // Pass 2: Remove excess '(' from right to left

        StringBuilder res = new StringBuilder();

        for(int i = firstPass.length() - 1; i >= 0 ; i --){

            char c = firstPass.charAt(i);

            if(c == '(' && unmatchedOpen > 0){

                unmatchedOpen--; // // Skip this excess '('

            }else{

                res.append(c);

            }
        }

        return res.reverse().toString();
    }
}
