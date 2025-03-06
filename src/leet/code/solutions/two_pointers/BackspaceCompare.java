package leet.code.solutions.two_pointers;

import java.util.Stack;

/*
ab#c

ad#c

deleteing char before # -> campare if two strings are same
 */
public class BackspaceCompare {

    public static void main(String[] args) {
        String first = "a##c";
        String second = "#a#c";


        boolean result = backspaceCompare(first, second);
        System.out.println(result);
    }


    private  static boolean backspaceCompare(String first, String second) {
        String firstAfterTruncation = "";
        String secondAfterTruncation = "";

        int start = 0;
        int next =  1;

        while (start < next && next < first.length()) {

            char currChar = first.charAt(start);
            char nextChar = first.charAt(next);

            if (currChar!= '#' && nextChar != '#') {
                firstAfterTruncation += currChar;

            }

            if(next==first.length()-1 && nextChar != '#'){
                firstAfterTruncation += nextChar;
            }

            start++;
            next++;

        }

        start = 0;
        next = 1;

        while (start < next && next < second.length()) {

            char currChar = second.charAt(start);
            char nextChar = second.charAt(next);

            if (currChar!= '#' && nextChar != '#') {
                secondAfterTruncation += currChar;

            }

            if(next==second.length()-1 && nextChar != '#'){
                secondAfterTruncation += nextChar;
            }

            start++;
            next++;

        }

        return firstAfterTruncation.equals(secondAfterTruncation);
    }

    private  static boolean backspaceCompareStack(String first, String second) {
        Stack<Character> stack = new Stack<>();

        for (char each : first.toCharArray()) {
            char curr = each;
            if(curr!='#'){
                stack.push(curr);
            } else if( curr == '#' && !stack.isEmpty()){
                stack.pop();
            }
        }

        first = stack.toString();

        stack.clear();

        for (char each : second.toCharArray()) {

            char curr = each;
            if(curr!='#'){
                stack.push(curr);
            }else if(curr == '#' && !stack.isEmpty()){
                stack.pop();
            }
        }
        second = stack.toString();
        return first.equals(second);

    }

    }
