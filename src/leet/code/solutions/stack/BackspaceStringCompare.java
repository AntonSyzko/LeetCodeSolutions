package leet.code.solutions.stack;

import java.util.Stack;

/*
# means deletion of a char before it

few ## means times chars deleted

like ad## -> delete two chars = ''

compare strings after chars got deleted
 */
public class BackspaceStringCompare {

    public static void main(String[] args) {

        String s = "aa##";
        String t = "a#bn";
        System.out.println(backspaceCompare(s, t));
    }

    private  static  boolean backspaceCompare ( String s, String t){


        Stack<Character> sStack = new Stack<>();
        Stack<Character> tStack = new Stack<>();

        for(char each :s.toCharArray()){
            if(each != '#'){
                sStack.push(each);
            }else  if( !sStack.isEmpty()){
                sStack.pop();
            }
        }

        for(char each : t.toCharArray()){
            if(each != '#'){
                tStack.push(each);
            } else if (!tStack.isEmpty()){
                tStack.pop();
            }
        }


        while(!sStack.isEmpty()){
            char curr = sStack.pop();

            if(tStack.isEmpty() || curr != tStack.pop()){
                return false;
            }
        }

        return sStack.isEmpty() && tStack.isEmpty();
    }

}
