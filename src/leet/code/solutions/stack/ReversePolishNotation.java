package leet.code.solutions.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class ReversePolishNotation {
    public static void main(String[] args) {
        String rpn = "3,4,+,2,*,1,+";
        System.out.println(evalRPN(rpn));
    }

    private static int evalRPN(String RPNExpression) {
        Deque<Integer> intermediateResults = new ArrayDeque<>();//stack
        //Stack<Integer> intermediateResults = new Stack<>();
        String[] symbols = RPNExpression.split(",");

        for (String token : symbols) {
            //is operand
            if (token.length() == 1 && "+-/*".contains(token)) {
                //pop first TWO from the STACK
                final int first = intermediateResults.pop();
                final int second = intermediateResults.pop();

                switch (token.charAt(0)) {
                    case '+':
                        intermediateResults.push(first + second);
                        break;
                    case '-':
                        intermediateResults.push(first - second);
                        break;
                    case '/':
                        intermediateResults.push(first / second);
                        break;
                    case '*':
                        intermediateResults.push(first * second);
                        break;
                    default:
                        throw new IllegalArgumentException("token is not an arithmetic operand " + token);
                }
            } else {//is number
                intermediateResults.push(Integer.parseInt(token));
            }
        }

        return intermediateResults.pop();//pop last - is result
    }


}
