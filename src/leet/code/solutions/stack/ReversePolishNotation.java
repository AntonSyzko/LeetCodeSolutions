package leet.code.solutions.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class ReversePolishNotation {
    public static void main(String[] args) {
        String rpn = "3,4,+,2,*,1,+";
        System.out.println(evalRPN(rpn));
    }

    private static int evalRPN(String RPNExpression) {
        Deque<Integer> intermediateResults = new ArrayDeque<>();//stack
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

    /*
    O(n)
    O(n)
     */

    private static int  evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (token.length() > 1 || Character.isDigit(token.charAt(0)) ||
                    (token.length() >= 2 && token.charAt(0) == '-')) {//for multi-digit nums like -235
                // This is a number (including negative numbers)
                stack.push(Integer.parseInt(token));
            }else{
                if(stack.isEmpty() || stack.size()<2){
                    throw new IllegalArgumentException("not enough  elements iun stack");
                }
                int second = stack.pop();
                int first = stack.pop();
                switch (token){
                    case "+":
                        stack.push(first + second);
                        break;
                    case "-":
                        stack.push(first - second);
                        break;
                    case "*":
                        stack.push(first * second);
                        break;
                    case "/":
                        stack.push(first / second);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown operator");
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid RPN expression");
        }

        return stack.pop();
    }


    private static int evalRPN_Functional(String[] tokens) {
        // Create a map of operators to their corresponding operations
        Map<String, BiFunction<Integer, Integer, Integer>> operations = Map.of(
                "+", (a, b) -> a + b,
                "-", (a, b) -> a - b,
                "*", (a, b) -> a * b,
                "/", (a, b) -> a / b
        );

        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (operations.containsKey(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Not enough elements in stack");
                }
                int second = stack.pop();
                int first = stack.pop();
                stack.push(operations.get(token).apply(first, second));
            } else {
                // This is a number
                stack.push(Integer.parseInt(token));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid RPN expression");
        }

        return stack.pop();
    }

}
