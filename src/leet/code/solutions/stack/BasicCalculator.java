package leet.code.solutions.stack;

import java.util.Stack;

public class BasicCalculator {

    public static void main(String[] args) {
        int res = calculate("1+ (10 - 3)");
        System.out.println(res);
    }


    private static int calculate(String s) {
        int ans = 0;
        int num = 0;
        int sign = 1;
        // stack.peek() := the current environment's sign
        Stack <Integer> stack = new Stack<>();
        stack.push(sign);

        for (final char c : s.toCharArray())
            if (Character.isDigit(c))

                num = num * 10 + (c - '0');

            else if (c == '(')

                stack.push(sign);

            else if (c == ')')

                stack.pop();

            else if (c == '+' || c == '-') {
                ans += sign * num;//prev sum * aggregated so far num

                sign = (c == '+' ? 1 : -1) * stack.peek();//curr new sign * from what is on stack

                num = 0;
            }

        return ans + (sign * num);//last time same as line 35
    }
}