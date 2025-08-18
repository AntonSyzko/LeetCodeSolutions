package leet.code.solutions.arrays;

public class RotateString {

    public static void main(String[] args) {
        String s = "abcde";

        String goal = "cdeab";
        System.out.println(rotateString(s, goal));

        goal = "abced";
        System.out.println(rotateStringInefficient(s, goal));
    }

    //O(1), O(1)
    private static boolean rotateString(String str, String goal) {
        if (str.length() != goal.length()) {
            return false;
        }

        str = str + str;

        if(str.contains(goal)){
            return true;
        }

        return false;
    }


    //O(n)
        private static boolean rotateStringInefficient(String str, String goal) {
        if (str.length() != goal.length()) {
            return false;
        }

        int rotation = 0;

        while (rotation < str.length()) {

            char temp = str.charAt(0);
            str = str.substring(1,str.length()) + temp;

            if(str.equals(goal)){
                return true;
            }
            rotation++;

        }
        return false;
    }
}
