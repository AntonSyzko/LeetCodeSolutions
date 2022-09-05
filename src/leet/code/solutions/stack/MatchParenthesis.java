package leet.code.solutions.stack;

import javax.print.DocFlavor;
import java.util.ArrayDeque;
import java.util.Deque;

public class MatchParenthesis {

    public static void main(String[] args) {
  String input = "{[()]}";
        System.out.println(isMatchedParenthesis(input));

         input = "{[()]";
        System.out.println(isMatchedParenthesis(input));
    }

    private static boolean isMatchedParenthesis(String input) {
        Deque<Character> leftChars = new ArrayDeque<>();

        for (int i = 0; i < input.length() ; i++) {

             if(input.charAt(i) == '(' || input.charAt(i) == '{' || input.charAt(i) == '['){
                 leftChars.push(input.charAt(i));
             }else {
                 if(leftChars.isEmpty()){//started with closing bracket
                     return false;
                 }

                 if((input.charAt(i) == ')' && leftChars.peekFirst() != '(' )
                     || (input.charAt(i) == '}' && leftChars.peekFirst() != '{' )
                     ||(input.charAt(i) == ']' && leftChars.peekFirst() != '[' )){
                     return false;
                 }
                 leftChars.removeFirst();//retrieve from TOP
             }
        }
        return leftChars.isEmpty();//nothing left - good
    }
}
